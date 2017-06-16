package retail.cachestore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;

import com.oracle.education.retail.Address;
import com.oracle.education.retail.Customer;

/**
 * CustomerJDBC Support class
 * 
 * @author Oracle Education
 */
public class CustomerJdbcSupport {
	private OracleDataSource dataSource;

	/**
	 * Constructor for Customer JDBC utility
	 * 
	 * @param connection
	 *            JDBC URL connection string
	 * @param user
	 *            user name
	 * @param password
	 *            password
	 */
	public CustomerJdbcSupport(String connection, String user, String password) {
		if (connection == null || user == null || password == null) {
			throw new IllegalArgumentException(
					"Must provide all three required arguments");
		}
		try {
			init(connection, user, password);
		} catch (SQLException ex) {
			throw new RuntimeException("Error initializing CacheStore", ex);
		}
	}

	/**
	 * Initialize the connection pool
	 * 
	 * @param connection
	 * @param user
	 * @param password
	 * 
	 * @throws SQLException
	 */
	protected void init(String connection, String user, String password)
			throws SQLException {
		dataSource = new OracleDataSource();
		dataSource.setURL(connection);
		dataSource.setUser(user);
		dataSource.setPassword(password);

		Properties properties = new Properties();
		properties.setProperty("InitialLimit", "1");
		properties.setProperty("MinLimit", "1");
		properties.setProperty("MaxLimit", "5");

		dataSource.setConnectionCacheProperties(properties);
	}

	/**
	 * @return a pooled database connection
	 * 
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * Ensure that resources are cleaned up; this should be invoked in a finally
	 * block
	 * 
	 * @param rs
	 *            ResultSet (can be null)
	 * @param ps
	 *            PreparedStatement (can be null)
	 * @param c
	 *            Connection (can be null)
	 */
	protected void cleanup(ResultSet rs, PreparedStatement ps, Connection c) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("Error closing statement");
			e.printStackTrace();
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			System.out.println("Error closing statement");
			e.printStackTrace();
		}
		try {
			if (c != null) {
				c.close();
			}
		} catch (SQLException e) {
			System.out.println("Error closing connection");
			e.printStackTrace();
		}
	}

	/**
	 * Load customer by id
	 * 
	 * @param key
	 * 
	 * @return customer
	 */
	public Customer loadCustomer(Long key) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = null;
		try {
			String sql = "SELECT id, name, street, city, state, zip FROM customer WHERE id = ?";

			connection = getConnection();
			ps = connection.prepareStatement(sql);
			ps.setLong(1, key);
			rs = ps.executeQuery();

			if (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("name");
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");

				Address address = new Address(street, city, state, zip);
				customer = new Customer(id, name, address);
			}
			rs.close();
			rs = null;
			ps.close();
			ps = null;
			connection.close();
			connection = null;
		} catch (SQLException e) {
			throw new RuntimeException("Error executing statement", e);
		} finally {
			cleanup(rs, ps, connection);
		}
		return customer;
	}

	/**
	 * Insert/update customers
	 * 
	 * @param customers
	 */
	public void saveCustomers(Collection<Customer> customers) {
		String sql = "MERGE INTO customer c USING "
				+ "(SELECT ? id, ? name, ? street, ? city, ? state, ? zip FROM dual) "
				+ "c_new ON (c.id = c_new.id)"
				+ "WHEN MATCHED THEN UPDATE SET c.name = c_new.name, "
				+ "  c.street = c_new.street, c.city = c_new.city, "
				+ "  c.state = c_new.state, c.zip = c_new.zip "
				+ "WHEN NOT MATCHED THEN INSERT (c.id, c.name, c.street, c.city, c.state, c.zip) "
				+ "VALUES (c_new.id, c_new.name, c_new.street, c_new.city, c_new.state, c_new.zip)";

		System.out.println(sql);

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = getConnection();
			ps = connection.prepareStatement(sql);

			for (Iterator<Customer> iter = customers.iterator(); iter.hasNext();) {
				Customer customer = iter.next();
				Address address = customer.getAddress();
				int i = 0;

				System.out.println(customer.getId());

				ps.setLong(++i, customer.getId());
				ps.setString(++i, customer.getName());
				ps.setString(++i, address.getStreet());
				ps.setString(++i, address.getCity());
				ps.setString(++i, address.getState());
				ps.setString(++i, address.getZip());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			cleanup(null, ps, connection);
		}
	}

	/**
	 * Delete customers (by key)
	 * 
	 * @param keys
	 */
	public void deleteCustomers(Collection<Long> keys) {
		if (keys == null || keys.size() == 0) {
			throw new IllegalArgumentException("Missing parameter");
		}
		StringBuilder sql = new StringBuilder(
				"DELETE FROM CUSTOMER where ID in (");
		for (int i = 0; i < keys.size(); i++) {
			sql.append("?");
			if (i + 1 < keys.size()) {
				sql.append(",");
			}
		}
		sql.append(")");

		System.out.println(sql.toString());

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = getConnection();
			ps = connection.prepareStatement(sql.toString());
			int n = 0;
			for (Iterator<Long> iter = keys.iterator(); iter.hasNext();) {
				Long key = iter.next();
				ps.setLong(++n, key);
			}
			ps.execute();
			ps.close();
			ps = null;
		} catch (SQLException e) {
			System.out.println("Unexpected exception '" + e.getMessage() + "'");
			e.printStackTrace();
		} finally {
			cleanup(null, ps, connection);
		}
	}
}
