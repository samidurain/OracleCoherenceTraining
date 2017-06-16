
public class StopWatch {

	private long startTime;   	//time in nanoseconds since the EPOC
	private long finishTime;	//time in nanoseconds since the EPOC
	private boolean isRunning;	
	
	public StopWatch() {
		this.startTime = -1;
		this.finishTime = -1;
		isRunning = false;
	}
	
	
	public synchronized void start() {
		if (!isRunning) {
			this.startTime = System.nanoTime();
			isRunning = true;
		}
	}
	
	
	public synchronized void stop() {
		if (isRunning) {
			this.finishTime = System.nanoTime();
			isRunning = false;
		}
	}
	
	
	public boolean isRunning() {
		return isRunning;
	}
	
	
	public long getElapsedTime() {
		if (isRunning) {
			return System.nanoTime() - startTime;
		} else if (startTime != -1) {
			return finishTime - startTime;
		} else {
			throw new IllegalStateException("StopWatch hasn't been started as yet.  Not possible to determine the elapsed time.");
		}
	}
	
	public String toString(int count) {
		return String.format("StopWatch{startTime=%d, finishTime=%d, elapsedTime=%d ns (%f sec), rate=%f per sec}",
							 startTime,
							 finishTime,
							 getElapsedTime(),
							 getElapsedTime() / 1000000000.0,
							 (double)count * 1000000000.0 / getElapsedTime());
	}
	
	public String toString() {
		return String.format("StopWatch{startTime=%d, finishTime=%d, elapsedTime=%d ns (%f sec)}",
							 startTime,
							 finishTime,
							 getElapsedTime(),
							 getElapsedTime() / 1000000000.0);
	}
}
