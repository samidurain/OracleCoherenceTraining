import com.tangosol.coherence.transaction.Connection;
import com.tangosol.coherence.transaction.DefaultConnectionFactory;
import com.tangosol.coherence.transaction.OptimisticNamedCache;

public class TestTransactionConnection {

	public static void main(String[] args) {

		// Get a connection
		Connection conn = new DefaultConnectionFactory().createConnection("TestTxnService");
		conn.setAutoCommit(false);
		conn.setEager(true);
		System.out.println("\nOPENING CONNECTION #1");

		// Get a transactional cache
		System.out.println("Getting tx-cache");
		OptimisticNamedCache cache = conn.getNamedCache("tx-cache");

		// INSERT AN ENTRY INTO THE CACHE
		System.out.println("\nINSERTING key AND key2 INTO CACHE (BEGIN TRANSACTION)");
		cache.insert("key", "value");
		cache.insert("key2", "value2");
		//assert "value".equals(cache.get("key"));
		//assert "value2".equals(cache.get("key2"));

		// Update the value of the entry
		System.out.println("UPDATING key value TO value2");
		cache.update("key", "value2", null);
		//assert "value2".equals(cache.get("key"));
		// Commit the transaction
		System.out.println("Committing transaction (END TRANSACTION)");
		conn.commit();
		// Verify that the correct value is set
		//assert "value2".equals(cache.get("key"));
		//assert "value2".equals(cache.get("key2"));

		// Second connection
		System.out.println("\nOPENING CONNECTION #2");
		Connection conn2 = new DefaultConnectionFactory().createConnection("TestTxnService");
		conn2.setAutoCommit(false);
		// Get the same transactional cache from the second connection
		System.out.println("Getting same tx-cache that connection #1 is using");
		OptimisticNamedCache cache2 = conn2.getNamedCache("tx-cache");
		// Verify the correct value from the second cache
		System.out.println("\nVERIFYING THAT CONNNECTION #2 CAN SEE THE SAME DATA INSERTED BY CONNECTION #1");
		//assert "value2".equals(cache2.get("key"));

		// Update the value through the first cache
		System.out.println("UPDATING key value2 TO value3 USING CONNECTION #1 (BEGIN TRANSACTION)");
		cache.update("key", "value3", null);
		// Verify that the correct value is set "value3".equals(cache.get("key"));
		System.out.println("Value for key that connection #2 sees=" + cache2.get("key"));
		// The update is not yet visible to the second cache
		//assert "value2".equals(cache2.get("key"));
		// Commit the update
		System.out.println("Committing the update on connection #1 (END TRANSACTION)");
		conn.commit();
		// Verify that the correct value is set
		//assert "value3".equals(cache.get("key"));
		// The update is now visible to the second cache
		System.out.println("Value for key that connection #2 sees after commit on connection #1="
				+ cache2.get("key"));
		//assert "value3".equals(cache2.get("key"));

		// Update the value through the first connection
		System.out.println("\nTESTING ROLLBACK SCENARIO");
		System.out.println("UPDATING key value3 TO value4 USING CONNECTION #1 (BEGIN TRANSACTION)");
		cache.update("key", "value4", null);
		// Verify that the correct value is set
		//assert "value4".equals(cache.get("key"));
		// The update is not yet visible to the second connection
		System.out.println("Value for key that connection #2 sees=" + cache2.get("key"));
		//assert "value3".equals(cache2.get("key"));
		// Rollback the update
		System.out.println("Rolling back the update on connection #1 (END TRANSACTION)");
		conn.rollback();
		// Verify that the value is rolled back
		//assert "value3".equals(cache.get("key"));
		// Verify that the correct value is set
		System.out.println("Value for key that connection #2 sees after rollback on connection #1="
				+ cache2.get("key"));
		//assert "value3".equals(cache2.get("key"));

		// close the connections
		conn.close();
		conn2.close();
	}
}
