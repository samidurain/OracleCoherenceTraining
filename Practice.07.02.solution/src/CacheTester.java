import java.util.Map;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.InvocationService;
import com.tangosol.net.Member;

public class CacheTester {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		InvocationService is = (InvocationService)CacheFactory.getService("InvocationService");

		System.out.println("Invoking the service synchronously");
		Map<Member, Integer> freeMemMap = is.query(new FreeMemoryAgent(), null);

		for (Map.Entry<Member, Integer> freeMem : freeMemMap.entrySet()) {
			System.out.println("Member: " + freeMem.getKey() + " Free: " + freeMem.getValue());
		}

		System.out.println("\nInvoking the service asynchronously");
		is.execute(new FreeMemoryAgent(), null, new MyInvocationObserver());
		System.out.println("Asynchronous execution finished\n");
	}
}
