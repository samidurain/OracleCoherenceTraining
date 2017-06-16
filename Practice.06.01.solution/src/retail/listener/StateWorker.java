package retail.listener;


import java.util.LinkedList;
import java.util.Queue;

import com.oracle.education.retail.State;
import com.oracle.education.retail.repository.CoherenceStateRepository;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

/**
 * This class decouples the put on a queue from a listener event
 * Doing a put in a map listener event is itself a 'nested' put
 * which due to queue lengths could deadlock the service
 * So we use this class to decouple the two events.
 * 
 * @author Oracle Education
 *
 */
public class StateWorker implements Runnable {

	private Queue<State> stateQueue = new LinkedList<State>();

	private boolean running = true;
	
	private NamedCache stateCache = null;
	private NamedCache getStateCache() {
		if (stateCache != null ) return stateCache;
		return stateCache = CacheFactory.getCache(CoherenceStateRepository.CACHENAME);
	}
	public void shutdown () { running = false; }
	
	public void storeState(State state) {
		NamedCache cache = getStateCache();
		if ( cache == null ) {
			System.out.println("Unable to get cache!");
			return;
		}
		
		if ( cache.containsKey(state.getId())) {
			System.out.println("State '"+state.getName()+"' already in cache");
			return;
		}
		System.out.println("Adding State '"+state+"'.");
		cache.put(state.getId(), state);		
	}
	
	@Override
	public void run() {
		
		while (running == true ) {
			while (!stateQueue.isEmpty()) {
				State state = stateQueue.remove();
				storeState(state);
			}
			// Now go to sleep
			// Most state workers sleep most of the time anyway!
			//
			try { Thread.sleep(500);} catch (InterruptedException e) {}
		}
		return;
	}

}
