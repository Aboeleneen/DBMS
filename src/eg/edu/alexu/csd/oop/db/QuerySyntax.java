/**
 * 
 */
package eg.edu.alexu.csd.oop.db;

import java.util.ArrayList;

/**
 * @author mahmoud sharshar :D
 *
 */
public interface QuerySyntax {
	/**
	 * this method to validate sql query
	 * 
	 * @param query
	 * @return zero if statement is invalid and other int greater than zero if it's
	 *         valid ,this int represent the number of operation.
	 */
	public int isValid(String query);

	/**
	 * this method to determine database name while creating or deleting
	 * 
	 * @param query : statment create\drop database database_name;
	 * @return string represent database name
	 */
	public String getDBName(String query);

	/**
	 * this method to determine table name while creating or deleting
	 * 
	 * @param query : statment create\drop table table_name .........;
	 * @return table name
	 */
	public String getTableName(String query);

	/**
	 * to determine columns names in statement creating table , inserting into
	 * table in specific columns , selecting specific columns from table
	 * and updating table 
	 * statements:
	 * 			1- create table table_name (column1_name column1_type ,...........)and so on
	 * 			2- insert into table_name (column_name,......)values(column_value,......)and so on
	 * 			3- select column1_name,column2_name,...... from table_name
	 * 			4-update table_name set column1_name = value1 , column2_name = value2 ,...... where [conditions];
	 * @param query : statment creating table
	 * @return columns names in specific table
	 */
	public ArrayList<String> getColumnsName(String query);

	/**
	 * to get columns type while creating table
	 * 
	 * @param query : statement create table table_name (column1_name Column1_type ,
	 *              ..........)and so on
	 * @return columns type
	 */
	public ArrayList<String> getColumnsType(String query);

	/**
	 * get  values that be set in columns 
	 * @param query : three statments 
	 * 1- insert into table_name values(column1_value ,......)and so on
	 * 2- insert into table_name (column_name,......)values(column_value,......)and so on
	 * 3-update table_name set column1_name = value1 , column2_name = value2 ,...... where [conditions];
	 * @return updated values
	 */
	public ArrayList<String> getInsertingValues(String query);
	/**
	 * get conditions while updating table
	 * @param query
	 * @return
	 */
	public String getCondition(String query);
	/**
	 * 
	 * @param query
	 * @return
	 */
	public String getDropedColumn(String query);
	
}
