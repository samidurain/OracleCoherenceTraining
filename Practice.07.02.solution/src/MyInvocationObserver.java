import com.tangosol.net.InvocationObserver;
import com.tangosol.net.Member;

public class MyInvocationObserver implements InvocationObserver {

	@Override
	public void invocationCompleted() {
		System.out.println("\nMyInvocationObserver processing completed\n");
	}

	@Override
	public void memberCompleted(Member member, Object result) {
		System.out.println("In Async Observer: Member: " + member + " Free: " + result);
	}

	@Override
	public void memberFailed(Member member, Throwable throwable) {}

	@Override
	public void memberLeft(Member member) {System.out.println("Member left");}

}
