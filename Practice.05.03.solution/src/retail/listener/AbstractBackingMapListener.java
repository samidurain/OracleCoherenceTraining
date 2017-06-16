package retail.listener;

import com.tangosol.net.BackingMapManagerContext;
import com.tangosol.net.BackingMapManager;
import com.tangosol.util.MapEvent;
import com.tangosol.util.MapListener;

/**
 * <p>The {@link AbstractBackingMapListener} provides a simplified 
 * base implementation for backing {@link MapListener}s that provide real objects in a map
 * event (in normal Java representation) rather than those that use the internal 
 * Coherence format (ie: {@link Binary}s.</p>
 * 
 * <p>Backing {@link MapListener}s are embeddable {@link MapListener}s that are 
 * injected into Coherence Cache members (storage-enabled) for the purpose of
 * handling events directly in-process of the primary partitions (of distributed schemes).</p>
 * 
 * <p>They are extremely useful for performing in-process processing of events within 
 * Coherence itself.</p>
 * 
 * @author Oracle Education
 */

public abstract class AbstractBackingMapListener implements MapListener {

	
	private boolean debug = false;
	protected void setDebug(boolean debug) {
		this.debug = debug;
	}
	/**
	 * <p>The {@link BackingMapManagerContext} that owns this listener.  
	 * (all Backing {@link MapListener}s require a {@link BackingMapManagerContext})</p>
	 */

	private BackingMapManagerContext context;
	
	/*
	 * return the current backing map context.
	 */
	public BackingMapManagerContext getBackingMapManagerContext() {
		return context; 
	}
	
	/**
	 * <p>Standard Constructor</p>
	 * 
	 * <p>The {@link BackingMapManagerContext} will be injected by Coherence during
	 * initialization and construction of the {@link BackingMapManager}.</p>
	 * 
	 * @param context
	 */
	public AbstractBackingMapListener (BackingMapManagerContext context) {
		this.context = context;
	}
	
	
	/**
	 * Translate a key to its original form from its internal serialized form
	 * @param event
	 * @return the key in its original form
	 */
	protected Object getConvertedKey (MapEvent event) {
		return context.getKeyFromInternalConverter().convert(event.getKey());}
	
	/**
	 * Translate a value to its original form from its internal serialized form
	 * @param event
	 * @return the object in its original form
	 */
	private Object getConvertedValue (Object internal) {
		/*if (debug) {
			System.out.println("getConvertedValue called with '" + internal
					+ "'");
		}
		if (internal == null)
			return null;
		if (debug) {
			Object o = context.getValueFromInternalConverter()
					.convert(internal);
			System.out.println("getConvertedValue returning with '" + o + "'");
			return o;

		} else
			return context.getValueFromInternalConverter().convert(internal);*/
		return internal;
	}
	
	/**
	 * Translate the old value to its original form from its internal serialized form
	 * @param event
	 * @return the object in its original form
	 */
	protected Object getOldValue(MapEvent event) {
		return  getConvertedValue (event.getOldValue());
		}
	
	/**
	 * Translate the new value to its original form from its internal serialized form
	 * @param event
	 * @return the object in its original form
	 */
	protected Object getNewValue(MapEvent event) {
		return  getConvertedValue (event.getNewValue());    } 
	
	
}
