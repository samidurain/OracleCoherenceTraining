package retail.listener;


import com.oracle.education.retail.Address;
import com.oracle.education.retail.Customer;
import com.oracle.education.retail.State;
import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;

public class AddStateMapListener implements MapListener {

	
	/*
	 * We need to decouple puts from the handle event thread
	 * so we use a queue to get and put
	 */
	StateWorker worker = null;
	
	private void queuedPut (State state) {
		if ( worker == null) {
			worker = new StateWorker();
			new Thread(worker).start();
		}
		worker.storeState(state);
	}
	
	
	private String printId(int id) {
		switch(id) {
		case MapEvent.ENTRY_DELETED: return "ENTRY_DELETED";
		case MapEvent.ENTRY_INSERTED: return "ENTRY_INSERTED";
		case MapEvent.ENTRY_UPDATED: return "ENTRY_UPDATED";
		}
		return "ENTRY_UNKNOWN";
	}
	private void printEvent( MapEvent event ) {
		
		StringBuffer sb = new StringBuffer();

		sb.append("MapEvent:")
			.append("id:").append(event.getId()).append("'"+printId(event.getId())+"'").append("\n");
		
		sb.append("Key:").append(event.getKey()).append("\n");
		sb.append("New value:").append(event.getNewValue()).append("\n");
		sb.append("Old value:").append(event.getOldValue()).append("\n");
		System.out.println(sb.toString());
	}
	@Override
	public void entryDeleted(MapEvent event) {
		System.out.println("\n\n+++entryDeleted++++\n");
		printEvent(event);
	}

	@Override
	public void entryInserted(MapEvent event) {
		System.out.println("\n\n+++entryInserted++++sanjeev\n");
		printEvent(event);
		
		Object o = event.getNewValue();
		if (! ( o instanceof Customer ) ) {
			return;
		}
		
		Customer customer = (Customer)o;
		System.out.println("Received insert of customer " + customer);
		Address address = customer.getAddress();
		
		String stateName = address.getState();
		State newState = new State(stateName);
		queuedPut(newState);
	}

	@Override
	public void entryUpdated(MapEvent event) {
		System.out.println("\n\n+++entryUpdated++++\n");
		printEvent(event);
	}
}
