import com.oracle.education.retail.Order;
import com.tangosol.util.InvocableMap.Entry;
import com.tangosol.util.processor.AbstractProcessor;

public class DiscountEntryProcessor extends AbstractProcessor {
	private static final long serialVersionUID = 1L;

	@Override
	public Object process(Entry entry) {
		System.out.println("Called");
		Order order = (Order)entry.getValue();

		StringBuffer sb = new StringBuffer();
		sb.append("Order [").append(order.getId()).append("] Changed:");
		sb.append("\torderTotal before=").append(order.getOrderTotal());
		order.setOrderTotal((float)(order.getOrderTotal() * .9));
		entry.setValue(order);
		sb.append("\torderTotal after=").append(order.getOrderTotal());
		System.out.println(sb.toString());

		return null;
	}
}
