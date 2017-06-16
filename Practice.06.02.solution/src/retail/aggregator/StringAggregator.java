package retail.aggregator;

import com.tangosol.util.ValueExtractor;
import com.tangosol.util.aggregator.AbstractAggregator;

//
// TODO: Implement Abstract Aggregator
//
public class StringAggregator extends AbstractAggregator{


	transient StringBuffer results;
	
	// required for serialization
	public StringAggregator() { super(); }
	
	//
	//TODO: implement the ValueExtractor constructor
	//
	public StringAggregator(ValueExtractor extractor) {
		super(extractor);
	}

	//
	//TODO: implement the init method
	//
	protected void init(boolean is) {
		results = new StringBuffer();
	}
	
	
	//
	// TODO: Implement the process method
	//
	protected void process(Object object, boolean is) {
		if (object == null ) return; 
		
		if (!is) {
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

	//
	// TODO: Complete the finalizeResults method
	//
	protected Object finalizeResult(boolean arg0) {
		return results.length() > 0? results.toString():null;
	}



}
