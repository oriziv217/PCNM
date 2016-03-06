package PCNMServer.ServerResources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is a static class that provides DB connection services
 * @author ori ziv
 *
 */

public class DBConnect {
	
    private static final String mySQLDriver = "com.mysql.jdbc.Driver";
    private static String connectionString;
    private static String DBuser;
    private static String DBpswrd;
	
	/**
	 * 
	 * @param server - DB server name
	 * @param DB - DB name
	 * @param user - user name to connect to the DB server
	 * @param pswrd - user's password
	 * 
	 * This method sets the static variables of this class to their respective values
	 */
	public static void setMySQLConnection (String server, String DB, String user, String pswrd) {
		connectionString = "jdbc:mysql://" + server + "/" + DB;
		DBuser = user;
		DBpswrd = pswrd;
	}
	
	/**
	 * This method set a connection to a MySQL DB using DBConnect class members
	 * @return - Connection object
	 */
	public static Connection mySQLConnection () {
		try 
		{
            Class.forName(mySQLDriver).newInstance();
        } catch (Exception e) {
        	return null;
        }
		
		try {
			return DriverManager.getConnection(connectionString,DBuser,DBpswrd);
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * This method set a connection to a MySQL DB using method's parameters:
	 * @param server - DB server host name or IP address
	 * @param DB - DB schema to connect to
	 * @param user - DB user name
	 * @param pswrd - DB user's password
	 * @return - Connection object
	 */
	public static Connection mySQLConnection (String server, String DB, String user, String pswrd) {
		setMySQLConnection (server, DB, user, pswrd);
		return mySQLConnection();
	}
	
	/**
	 * This method executes an SQL select query on a specific table in a specific schema with a specific filter
	 * 
	 * @param conDB - connection to the DB schema - must be active and open connection
	 * @param table - table name - must be a name of an existing table.
	 * 					Table string shell look like this: "table_name"
	 * @param fields - fields to select from the table.
	 * 					If null is supplied then the query is "SELECT *"
	 * 					You can use SQL aggregation functions here if you need to as long as you keep SQL syntax.
	 * 					Fields string shell look like this: "field1, field2" or: "MAX(field1)"
	 * @param filter - query filter.
	 * 					If null is supplied then no WHERE clause will be added to the query.
	 * 					You can add ORDER BY clause here as long as you keep SQL syntax.
	 * 					You can add GROUP BY clause here if necessary as you keep SQL syntax.
	 * 					Filter string shell look like this: field1 = 'value1' AND field2 = 'value2'"
	 * @return ResultSet of the query execution.
	 * 					Please notice that the returned object might be an empty ResultSet.
	 * @throws SQLException
	 */
	public static ResultSet selectWithFilter (Connection conDB, String table, String fields, String filter) throws SQLException {
		// if connection is closed throw exception
		if (conDB.isClosed()) throw new SQLException("Connection is closed");
		// if no table supplied throw exception
		if (table == null) throw new SQLException("No table specified");
		
		// if no fields specified get all fields
		String select;
		if (fields == null) select = "*";
		else select = fields;
		
		// create query
		String query = "SELECT " + select + " FROM " + table;
		
		// add filter if necessary
		if (filter != null) query = query + " WHERE " + filter;
		
		// execute query
		Statement stmnt = conDB.createStatement();
		return stmnt.executeQuery(query);
	}

	/**
	 * This method execute an SQL update query on a specific table in a specific schema.
	 * It update only 1 record.
	 * 
	 * @param conDB - connection to the DB schema - must be active and open connection
	 * @param table - table name - must be a name of an existing table.
	 * 					Table string shell look like this: "table_name"
	 * @param fields - array of field names to update.
	 * 					Must be at list 1 field to update.
	 * 					Field names should come as a string. The method will add the ' modifier
	 * @param values - array of field values to update.
	 * 					Array length must be identical to the fields array
	 * 					Field names should come as a string method add the ' modifier
	 * 					The method will match fields[i] with values[i]
	 * @param keyName - array of PK field names
	 * 					You must supply a full PK in order to update a single record.
	 * @param keyVal - array of PK field values
	 * 					Array length must be identical to the keyName array
	 * 					The method will match keyName[i] with keyVal[i]
	 * @return - true if update was successful otherwise return false
	 * @throws SQLException
	 */
	public static boolean updateSingleRecord (Connection conDB, String table, String[] fields, String[] values, String[] keyName, String[] keyVal) throws SQLException {
		// if connection is closed throw exception
		if (conDB.isClosed()) throw new SQLException("Connection is closed");
		// if no table supplied throw exception
		if (table == null) throw new SQLException("No table specified");
		// if no fields supplied throw exception
		if (fields == null || fields.length == 0) throw new SQLException("No field names specified");
		// if no values supplied throw exception
		if (values == null || values.length != fields.length) throw new SQLException("No field values error");
		// if no primary key supplied throw exception
		if (keyName == null || keyName.length == 0) throw new SQLException("No keys specified");
		// if no primary key value supplied throw exception
		if (keyVal == null || keyVal.length != keyName.length) throw new SQLException("Key's values error");
		
		// set table to update
		String update = "UPDATE " + table + " SET";
		
		// set fields and their new values
		for (int i = 0 ; i < fields.length ; i ++) {
			 update = update + " " + fields[i] + " = " + "'" + values[i] + "'";
			 if (i < fields.length - 1)
				 update = update + ",";
		}
		
		// set primary key filter
		update = update + " WHERE";
		for (int i = 0 ; i < keyName.length ; i ++) {
			update = update + " " + keyName[i] + " = " + "'" + keyVal[i] + "'";
			if (i < keyName.length - 1)
				update = update + " AND";
		}
		
		// execute update
		Statement stmnt = conDB.createStatement();
		if (stmnt.executeUpdate(update) == 1) return true;
		return false;
	}
	
	/**
	 * This method execute an SQL insert query on a specific table in a specific schema.
	 * It inserts only 1 record.
	 * @param conDB - connection to the DB schema - must be active and open connection
	 * @param table - table name - must be a name of an existing table.
	 * 					Table string shell look like this: "table_name"
	 * @param fields - array of field names to insert - this is an optional parameter.
	 * @param values - array of values to insert
	 * 					Must be at list 1 field to insert
	 * 					Must cover all fields defined as "not null" in the DB schema
	 * 					The method will add the ' modifier
	 * @return - true if insert was successful otherwise return false
	 * @throws SQLException
	 */
	public static boolean insertSingleRecord (Connection conDB, String table, String[] fields, String[] values) throws SQLException {
		// if connection is closed throw exception
		if (conDB.isClosed()) throw new SQLException("Connection is closed");
		// if no table supplied throw exception
		if (table == null) throw new SQLException("No table specified");
		// if no records supplied throw exception
		if (values == null || values.length == 0) throw new SQLException("No field names specified");
		
		// set table to insert the record to
		String insert = "INSERT INTO " + table;
		
		// add field names if parameter was supplied
		if (fields != null) {
			insert = insert + " (";
			for (int i = 0 ; i < fields.length ; i ++) {
				insert = insert + fields[i];
				if (i < fields.length - 1)
					insert = insert + ", ";
			}
			insert = insert + ")";
		}
		
		// set values to insert
		insert = insert + " VALUES (";
		for (int i = 0 ; i < values.length ; i ++) {
			insert = insert + "'" + values[i] + "'";
			if (i < values.length - 1)
				insert = insert + ", ";
		}
		insert = insert + ")";
		
		// execute insert
		Statement stmnt = conDB.createStatement();
		if (stmnt.executeUpdate(insert) == 1) return true;
		return false;
	}
	
	/**
	 * This method executes an SQL delete query on a specific table in a specific schema with a specific filter
	 * 
	 * @param conDB - connection to the DB schema - must be active and open connection
	 * @param table - table name - must be a name of an existing table.
	 * 					Table string shell look like this: "table_name"
	 * @param filter - query filter.
	 * 					Filter string shell look like this: field1 = 'value1' AND field2 = 'value2'"
	 * @return true if the method deleted any record otherwise return false
	 * @throws SQLException
	 */
	public static boolean DeleteRecords (Connection conDB, String table, String filter) throws SQLException {
		// if connection is closed throw exception
		if (conDB.isClosed()) throw new SQLException("Connection is closed");
		// if no table supplied throw exception
		if (table == null) throw new SQLException("No table specified");
		// if no filter supplied throw exception
		if (filter == null) throw new SQLException("No filter specified");
		
		String delete = "DELETE FROM " + table + " WHERE " + filter;
		Statement stmnt = conDB.createStatement();
		if (stmnt.executeUpdate(delete) > 0) return true;
		return false;
	}
}
