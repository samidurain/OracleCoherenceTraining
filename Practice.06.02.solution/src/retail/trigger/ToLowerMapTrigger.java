package retail.trigger;


import com.oracle.education.retail.Item;
import com.tangosol.util.MapTrigger;

public class ToLowerMapTrigger implements MapTrigger {

	private static final long serialVersionUID = 1L;

	@Override
	public void process(Entry entry) {
		System.out.println("\n\nProcess('" + entry + "')");
		
		Object o = entry.getValue();
		if (o != null && (o instanceof Item )) {
			Item i= (Item)o;
			String sku = i.getSku();
			i.setSku(sku.toLowerCase());
			entry.setValue(i);
		}
	}

	public boolean equals(Object o) {
		return o != null && o.getClass() == this.getClass();
	}

	public int hashCode() {
		return getClass().getName().hashCode();
	}

}
