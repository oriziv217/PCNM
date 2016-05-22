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
     * This method sets the static variables of this class to their respective values
     * @param server - DB server name
     * @param DB - DB name
     * @param user - user name to connect to the DB server
     * @param pswrd - user's password
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
        // Create instance
        try {
            Class.forName(mySQLDriver).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            return null;
        }

        // Return Connection object
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
            setMySQLConnection (server, DB, user, pswrd);       // set parameters
            return mySQLConnection();                           // create connection object
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
         * 					If null is supplied or if filter is an empty string then no WHERE clause will be added to the query.
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
                if (filter != null && !filter.isEmpty()) query = query + " WHERE " + filter;

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
         *                                      If values[i] = null then null value would be inserted.
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
                    if (values[i] == null)
                        insert = insert + "NULL";
                    else
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
        
    /**
     * This method executes a SQL select query with a single inner join.
     * @param conDB - connection to the DB schema - must be active and open connection
     * @param leftTable - table name to join with - must be a name of an existing table
     * @param rightTable - table name to join on - must be a name of an existing table
     * @param leftKeys - left table's attributes to join with - must be at list 1 attribute
     * @param rightKeys - right table's attributes to join on - must the same number of attributes as the left keys.
     *                                                          The method will match: leftKeys[i] = rightKeys[i]
     * @param fields - list of attributes to project - if this parameter is empty or equal to null then method assumes "SELECT *"
     *                                                          You can also add SQL functions and constants to this parameter.
     * @param labels - list of column labels to project - if this parameter is empty or equal to null then no labels will be used.
     *                                                          The method will match: fields[i] AS labels[i]
     * @param filter - a set of logical filters
     * @param options - a set of optional SQL clauses such as ORDER BY, GROUP BY...
     *                                                          If this parameter is null or an empty string then the method will
     *                                                          not include it in the query.
     * @return - query results
     * @throws SQLException
     */
    public static ResultSet innerJoin (Connection conDB,
                                            String leftTable, String rightTable,
                                            String[] leftKeys, String[] rightKeys,
                                            String[] fields, String[]labels,
                                            String filter, String options) throws SQLException {
        // input verification
        if (conDB.isClosed()) throw new SQLException("Connection is closed");
        if (leftTable == null || rightTable == null) throw new SQLException("Missing tables to join");
        if (rightKeys == null || rightKeys.length == 0
            || leftKeys == null || leftKeys.length == 0
            || rightKeys.length != leftKeys.length) throw new SQLException("Missing or invalid join keys");
        
        // building query string
        String joinQuery = "SELECT ";
        boolean isFirst = true;
        // case query define column labels
        if (fields != null && labels != null) {
            // input verification
            if (fields.length != labels.length) throw new SQLException("Error matching fields to labels");
            for (int i = 0 ; i < fields.length ; i ++) {
                if (!isFirst) joinQuery = joinQuery + ", ";
                else isFirst = false;
                joinQuery = joinQuery + fields[i];
                if (labels[i] != null && !labels[i].isEmpty())
                    joinQuery = joinQuery + " AS " + labels[i];
            }
        }
        // case no column labels
        if (fields != null && labels == null) {
            for (int i = 0 ; i < fields.length ; i ++) {
                if (!isFirst) joinQuery = joinQuery + ", ";
                else isFirst = false;
                joinQuery = joinQuery + fields[i];
            }                
        }
        // case select *
        if (fields == null || fields.length == 0)
            joinQuery = joinQuery + "*";
        // define the join clause    
        joinQuery = joinQuery + " FROM " + leftTable;
        joinQuery = joinQuery + " JOIN " + rightTable;
        joinQuery = joinQuery + " ON ";
        // define the join keys
        isFirst = true;
        for (int i = 0 ; i < leftKeys.length ; i ++) {
            if (!isFirst) joinQuery = joinQuery + " AND ";
            else isFirst = false;
            // input verification
            if (rightKeys[i] == null || rightKeys[i].isEmpty()
                || leftKeys[i] == null || leftKeys[i].isEmpty()) throw new SQLException("Missing or invalid join keys");
            joinQuery = joinQuery + leftKeys[i] + " = " + rightKeys[i];
        }
        // define filter
        if (filter != null && !filter.isEmpty())
            joinQuery = joinQuery + " WHERE " + filter;
        // define ORDER BY, GROUP BY, etc'
        if (options != null && !options.isEmpty())
            joinQuery = joinQuery + " " + options;
        
        // create and execute SQL statement
        Statement stmnt = conDB.createStatement();
        return stmnt.executeQuery(joinQuery);
        }
    
    /**
     * 
     * @param conDB - connection to the DB schema - must be active and open connection
     * @param fields - Array of attributes to project
     *                 If this parameter is empty or equal to null then method assumes "SELECT *"
     *                 You can also add SQL functions and constants to this parameter.
     * @param labels - Array of column labels to project
     *                 If this parameter is empty or equal to null then no labels will be used.
     *                 The method will match: fields[i] AS labels[i].
     *                 If this label[i] is empty (or null) then the method will not match a label to fields[i].
     *                 Labels array must not be longer then fields array.
     * @param fromTable - Table to be used in the FROM clause
     * @param joinOns - Matrix of tables to join with and their keys.
     *                  The first cell of each row contains the joined table: JOIN joinOns[i][0]
     *                  Every couple of cells will be used as join-key: ON joinOns[i][j] = joinOns[i][j+1] AND... (j >=1)
     *                  Row's length must be odd. All cells must not be empty (or null).
     *                  This parameter is optional (can be null or empty).
     * @param filter - A set of logical filters.
     *                 This parameter is optional (can be null or empty).
     * @param options - A set of optional SQL clauses such as ORDER BY, GROUP BY...
     *                  This parameter is optional (can be null or empty).
     * @return - Query results
     * @throws SQLException
     */
    public static ResultSet multiJoin ( Connection conDB,
                                        String[] fields, String[] labels,
                                        String fromTable, String[][] joinOns,
                                        String filter, String options) throws SQLException {
        // input verification
        if (conDB.isClosed()) throw new SQLException("Connection is closed");
        if (fromTable == null || fromTable.isEmpty()) throw new SQLException("Missing tables");
        if (fields != null && labels != null && fields.length < labels.length) throw new SQLException("Error matching fields to labels");

        // start building query string
        String joinQuery = "SELECT ";
        boolean isFirst = true;
        
        // set the select clause, optionaly with colomn labels
        // case 1: SELECT *
        if (fields == null || fields.length == 0 || fields[0].equals("*")) {
            joinQuery = joinQuery + "*";
            
        // case 2: SELECT colomn1, colomn2,...
        } else if (labels == null || labels.length == 0) {
            for (String field : fields) {
                if (field != null && !field.isEmpty()) {
                    if (!isFirst) joinQuery = joinQuery + ", ";
                    else isFirst = false;
                    joinQuery = joinQuery + field;
                }
            }
            
        // case 3: SELECT colomn1 AS label1, colomn2 AS label2,...
        } else {
            // run over all labels
            for (int i = 0 ; i < labels.length ; i ++) {
                if (!isFirst) joinQuery = joinQuery + ", ";
                else isFirst = false;
                joinQuery = joinQuery + fields[i];
                // case specific colomn has no label
                if (labels[i] != null && !labels[i].isEmpty())
                    joinQuery = joinQuery + " AS " + labels[i];
            }
            // case more colomns then labels
            for (int i = labels.length ; i < fields.length ; i ++) {
                if (!isFirst) joinQuery = joinQuery + ", ";
                else isFirst = false;
                joinQuery = joinQuery + fields[i];
            }
        }
        
        // define the from clause    
        joinQuery = joinQuery + " FROM " + fromTable;
        // define the join clause
        if (joinOns != null && joinOns.length > 0) {
            for (String[] joinOn : joinOns) {
                if (joinOn.length != 0) {
                    if (joinOn.length % 2 == 0 || joinOn[0] == null || joinOn[0].isEmpty()) throw new SQLException("Invalid join definition");
                    joinQuery = joinQuery + " JOIN " + joinOn[0];
                    isFirst = true;
                    for (int i = 1 ; i < joinOn.length - 1 ; i += 2) {
                        if (joinOn[i] == null || joinOn[i].isEmpty()
                                || joinOn[i+1] == null || joinOn[i+1].isEmpty()) throw new SQLException("Invalid join definition");
                        if (isFirst) {
                            joinQuery = joinQuery + " ON " + joinOn[i] + " = " + joinOn[i+1];
                            isFirst = false;
                        } else {
                            joinQuery = joinQuery + " AND " + joinOn[i] + " = " + joinOn[i+1];
                        }
                    }
                }
            }
        }
        // define filter
        if (filter != null && !filter.isEmpty())
            joinQuery = joinQuery + " WHERE " + filter;
        // define ORDER BY, GROUP BY, etc'
        if (options != null && !options.isEmpty())
            joinQuery = joinQuery + " " + options;
        
        // create and execute SQL statement
        Statement stmnt = conDB.createStatement();
        return stmnt.executeQuery(joinQuery);
        }
}
