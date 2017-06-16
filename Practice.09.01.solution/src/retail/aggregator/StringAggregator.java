package retail.aggregator;

import com.tangosol.util.ValueExtractor;
import com.tangosol.util.aggregator.AbstractAggregator;

public class StringAggregator extends AbstractAggregator{


	transient StringBuffer results;
	
	// required for serialization
	public StringAggregator() { super(); }
	
	// Required to store provided value extractor
	public StringAggregator(ValueExtractor extractor) {
		super(extractor);
	}

	protected void init(boolean isFinal) {
		results = new StringBuffer();
	}
	
	
	protected void process(Object object, boolean isFinal) {
		if (object == null ) return; 
		
		if (!isFinal) {
			if (results.length() == 0) {
				results.append((String)object);
			} else {
				results.append(",");
				results.append((String)object);		
			}
		} else { // isFinal
			// basically the same thing
			// but with an entire set of values
			
			if (results.length()>0) {
				results.append(",");
			}
			results.append((String)object);		
		}
		
		
	}

	
	protected Object finalizeResult(boolean arg0) {
		return results.length() > 0? results.toString():null;
	}



}
