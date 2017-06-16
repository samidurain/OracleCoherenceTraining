package retail.filter;

import java.util.Comparator;

import com.oracle.education.retail.Customer;

public class CustomerComparator implements Comparator {

		public int compare(Object first, Object second) {
			if (!( first instanceof Customer) ||
					!( second instanceof Customer)){
					throw new IllegalArgumentException("object not an Customer");
				}
			
			Customer customerOne = (Customer) first;
			Customer customerTwo = (Customer) second;
			
		return customerOne.getName().compareTo(customerTwo.getName());
	}

}
