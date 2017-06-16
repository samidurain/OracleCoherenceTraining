package retail.listener;

import com.tangosol.net.BackingMapManagerContext;
import com.tangosol.net.BackingMapManager;
import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;

public class EchoingBackingMapListener extends AbstractBackingMapListener {

	
	
	public EchoingBackingMapListener(BackingMapManagerContext context) {
		super(context);
		// setDebug(true);
		StringBuffer sb = new StringBuffer();
		sb.append("\n\n\n++++++++++++++++++++++++++++++++++++++\n");
		sb.append("Called EchoingBackingMapListener c'tor\n");
		sb.append("++++++++++++++++++++++++++++++++++++++\n\n\n");
		System.out.println(sb.toString());
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
		
		sb.append("Key:").append(getConvertedKey(event)).append("\n");
		sb.append("New value:").append(getNewValue(event)).append("\n");
		sb.append("Old value:").append(getOldValue(event)).append("\n");
		System.out.println(sb.toString());
	}
	public void entryDeleted(MapEvent event) {
		System.out.println("\n\n+++entryDeleted++++\n");
		printEvent(event);
	}

	public void entryInserted(MapEvent event) {
		System.out.println("\n\n+++entryInserted++++hhghghg\n");
		printEvent(event);
	}

	public void entryUpdated(MapEvent event) {
		System.out.println("\n\n+++entryUpdated++++\n");
		printEvent(event);
	}
}
