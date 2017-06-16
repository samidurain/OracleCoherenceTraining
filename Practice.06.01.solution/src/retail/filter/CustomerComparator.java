package retail.filter;

import java.util.Comparator;

import com.oracle.education.retail.Customer;

//
// TODO: Complete the comparator by implementing the Comparator interface
//       Cast the two objects to Customer and then compare the names
//       See the Practice.06.01\ resource directory for a complete implementation of the compare method
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
