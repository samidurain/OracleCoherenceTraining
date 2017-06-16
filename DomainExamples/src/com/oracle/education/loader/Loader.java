package com.oracle.education.loader;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.oracle.education.retail.Address;
import com.oracle.education.retail.CreditCard;
import com.oracle.education.retail.Customer;
import com.oracle.education.retail.Item;
import com.oracle.education.retail.Line;
import com.oracle.education.retail.Order;
import com.oracle.education.retail.repository.CoherenceCustomerRepository;
import com.oracle.education.retail.repository.CoherenceItemRepository;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class Loader {

	private static Item[] itemList = new Item[] { new Item("FishCo Fishing Rod", 24.99f, "FCFR"),
			new Item("American Flyer 2500 Road Bicycle", 1299.99f, "AF2500"),
			new Item("Cutter 4500 Mountain Bicycle", 3199.99f, "CT4500"),
			new Item("Sean Williams Extreme Snow Board", 394.99f, "SWESB"),
			new Item("TitleFlite Golf Balls (pack of 8)", 32.49f, "TFGB"),
			new Item("Cajun Crew Ocean Kayak 24'", 525.00f, "CCOK24"),
			new Item("YBOX 720", 350.00f, "YB720"),
			new Item("Sanctify Mountain Bike Seat", 54.99f, "SMBS"),
			new Item("Isadora Xtreme Rear Derailleur", 294.25f, "IXRD"),
			new Item("Acme Bicycle Cable", 29.99f, "ABC"),
			new Item("NoName MTB Spokes", 2.99f, "NNMS"),
			new Item("FishCo Fishing Net (Small)", 5.99f, "FCFNS"),
			new Item("FishCo Fishing Net (Medium)", 6.99f, "FCFNM"),
			new Item("FishCo Fishing Net (Large)", 7.99f, "FCFNL"),
			new Item("Acme Mountain Climbing Rope Kit", 65.39f, "AMCRK"),
			new Item("Acme Mountain Climbing Spikes", 15.99f, "AMCS"),
			new Item("Sniffstein Deer Hunting Scent", 8.99f, "SDHS"),
			new Item("Rumson High Powered Hunting Rifle", 1275.99f, "RHPHR"),
			new Item("BlackBeard Rifle Bullets", 15.99f, "BBRB"),
			new Item("Time Corp Time Machine", 399999.99f, "TCTM") };

	private static String[] ccDescriptions = new String[] { "My Visa", "My MasterCard",
			"My Discover Card", "My AMEX Card", "My Backup Visa", "My Backup MasterCard",
			"My Backup AMEX" };

	private static Address[] addresses = new Address[] {
			new Address("38 Clark Place", "San Francisco", "CA", "94121"),
			new Address("44 Pumpkin Ct", "Shirley", "MA", "01464"),
			new Address("76 Nile Ct", "Arvata", "CO", "80001"),
			new Address("1920 Voss Rd", "Houston", "TX", "77057"),
			new Address("865 Seminole Tr", "Avenel", "NJ", "07001"),
			new Address("335 Woodruff Ave", "Tampa", "FL", "33066"),
			new Address("20 Pine St", "Dolan Springs", "AZ", "86441"),
			new Address("1224 Van Ness Dr", "Torrance", "CA", "90501"),
			new Address("14789 Prospect Ave", "Jim Thorpe", "PA", "18229"),
			new Address("100 Remsen Ave", "Columbia", "MD", "21044") };

	private static String[] customerNames = new String[] { "James Jones", "Scott Riley",
			"Johnny Greif", "Tobey Sandor", "Chris Sandow", "Max Xavier", "Logan Pollard",
			"Gina Smith", "Leslie Green", "Stephanie Kramer" };

	private static boolean debug = false;
	private static Random random = new Random();
	private static NamedCache items = CacheFactory.getCache(CoherenceItemRepository.CACHENAME);
	private static NamedCache customers = CacheFactory.getCache(CoherenceCustomerRepository.CACHENAME);

	public static void main(String[] args) throws Exception {
		try {
			load();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void load() throws Exception {
		load (false);
	}
	public static void load(boolean customerOnly) throws Exception {

		int start = 0;
		int finish = 10;

		// Create items
		if ( !customerOnly ) {
			for (int x = 0; x < itemList.length; x++) {
				// Just add all items to cache by array index
				items.put(itemList[x].getId(), itemList[x]);
			}
		}

		// Load object data in cache
		System.out.println("Loading 10 customers in cache: " + start + "-" + (finish - 1));
		for (int i = start; i < finish; i++) {
			if (debug) {
				System.out.println("=======================================================");
				System.out.println("Creating Customer [" + i + "]");
			}

			Customer customer = new Customer(customerNames[i], addresses[i]);

			// Create customer credit card(s)
			for (long cc = 0; !customerOnly && cc < randomNumber(0, 3) + 1; cc++) {
				if (debug) System.out.println("Creating Credit Card [" + cc + "]");
				customer.addCreditCard(createCreditCard());
				if (debug) System.out.println("Credit Card [" + cc + "] complete\n");
			}

			// Create customer order(s)
			for (long o = 0; !customerOnly && o < randomNumber(0, 3) + 1; o++) {
				if (debug) System.out.println("Creating Order [" + o + "]");
				customer.addOrder(createOrder(customer));
				if (debug) System.out.println("Order [" + o + "] complete\n");
			}

			customers.put(customer.getId(), customer);
			if (debug) System.out.println("Customer [" + i + "] complete\n");
		}

		if (debug) LogAllCustomers();
	}

	@SuppressWarnings("unchecked")
	private static void LogAllCustomers() {
		System.out.println("================================================================");

		Iterator<Map.Entry> entries = customers.entrySet().iterator();
		while (entries.hasNext()) {
			Customer cust = (Customer)entries.next().getValue();
			System.out.println(cust);
			for (CreditCard cc : cust.getCreditCards()) {
				System.out.println(cc);
			}

			for (Order order : cust.getOrders()) {
				System.out.println(order);

				System.out.println("Begin Lines**********");
				for (Line line : order.getLines()) {
					System.out.println(line);
					System.out.println(line.getItem());
				}
				System.out.println("End Lines\n");
			}
		}
	}

	public static long randomNumber(int low, int high) {
		int r = random.nextInt(high);

		return r;
	}

	public static CreditCard createCreditCard() {
		// Randomly create card number
		StringBuffer cardNumber = new StringBuffer();
		for (int i = 0; i < 16; i++) {
			cardNumber.append(randomNumber(0, 10));
		}

		// Randomly create expiration date
		StringBuffer expiration = new StringBuffer();
		expiration.append(String.format("%02d", randomNumber(0, 12) + 1)).append("/").append(
				String.format("%02d", randomNumber(0, 5) + 11));

		// Randomly create csv number
		StringBuffer csv = new StringBuffer();
		for (int i = 0; i < 3; i++) {
			csv.append(randomNumber(0, 10));
		}

		CreditCard cc = new CreditCard(cardNumber.toString(), ccDescriptions[(int)randomNumber(0,
				ccDescriptions.length)], expiration.toString(), csv.toString());

		// Randomly set limit
		cc.setLimit((randomNumber(0, 15) + 1) * 1000);

		// Randomly set balance
		float balance = ((((cc.getLimit() / 1000) - randomNumber(0, 5)) + 1) * 1000);
		if (balance < 1) balance = 1;
		cc.setBalance(randomNumber(0, (int)balance));

		if (debug) System.out.println(cc);
		return cc;
	}

	@SuppressWarnings("unchecked")
	private static Order createOrder(Customer customer) {

		long loop;
		long random;

		// Generate a random date that is within 7 days of today
		Calendar cal = Calendar.getInstance();
		StringBuffer date = new StringBuffer();
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);

		day -= randomNumber(0, 7);
		if (day < 1) {
			day = 28 + day; // Keeping it simple
			month--;
			if (month < 1) month = 12;
		}

		date.append(cal.get(Calendar.YEAR)).append("-").append(String.format("%02d", month)).append(
				"-").append(String.format("%02d", day));

		// Create the order
		if (debug) System.out.println("Date=" + date.toString());
		Order order = new Order(customer.getAddress(), Date.valueOf(date.toString()));

		// Add customer to Order
		order.setCustomer(customer);

		// Add credit card to order
		Collection<CreditCard> cards = customer.getCreditCards();
		if (debug) System.out.println("Cards size=" + cards.size());
		loop = 0;
		random = randomNumber(0, cards.size());
		if (debug) System.out.println("Random Card=" + random);
		for (CreditCard card : cards) {
			if (debug) {
				System.out.println("TESTING... This loop is getting entered");
				System.out.println("loop=" + loop);
				System.out.println("random=" + random);
			}

			if (loop == random) {
				if (debug) System.out.println("Setting card [" + card.getId() + "] into order");
				order.setCreditCard(card);
				break;
			}
			loop++;
		}

		// Add 1-5 order line items randomly
		for (int l = 0; l < randomNumber(0, 4) + 1; l++) {
			// Randomly select an item and a quantity
			Set<Long> keyArray = items.keySet();

			loop = 0;
			random = randomNumber(0, keyArray.size());
			if (debug) {
				System.out.println("keyArray.size()=" + keyArray.size());
				System.out.println("Random key=" + random);
			}

			for (Long key : keyArray) {
				if (loop == random) {
					if (debug) System.out.println("Order Info:" + order.getId() + " key=" + key);
					Line line = new Line(order.getId(), key);
					line.setQuantity(randomNumber(0, 3) + 1);
					order.addLine(line);
					break;
				}
				loop++;
			}
		}

		return order;
	}
}
