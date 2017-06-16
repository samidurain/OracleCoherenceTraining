import com.tangosol.net.AbstractInvocable;

public class FreeMemoryAgent extends AbstractInvocable {
	private static final long serialVersionUID = 1L;

	public void run() {
		Runtime rt = Runtime.getRuntime();
		setResult(rt.freeMemory());
	}
}
