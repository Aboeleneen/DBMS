package eg.edu.alexu.csd.oop.db;

import java.util.ArrayList;


public interface Table {
	
	/**
	 * to select some records from table based on condition
	 * 
	 * @param List of columns'names and List of conditions for selection
	 * @return an ArrayList of selected columns from this table 
	 */    
	public Object[][] select(ArrayList<String> columns ,ArrayList<String> conditions);
	/**
	 * to add a new record to the table
	 * 
	 * @param List of columns'names and List of their values 
	 * @return True if the process completed or false if there is an error in columns name of values
	 */
	public int  insert(ArrayList<String> columns , ArrayList<String> values);
	/**
	 * to update some records in the table based on condition
	 * 
	 * @param List of columns'names ,List of new values and List of conditions
	 * @return true if the process completed or false if there is an error in columns name of values
	 */
	public int update(ArrayList<String> columns , ArrayList<String> values , ArrayList<String> conditions);
	/**
	 * to delete columns from table
	 * 
	 * @param List of columns'names 
	 * @return true if all columns are deleted else it return true
	 */
	public Boolean deleteColumn(ArrayList<String> columns);
	/**
	 * to delete rows from table based on condition
	 * 
	 * @param condition
	 * @return number of deleted records
	 */
	public int deleteRows(ArrayList<String> condition);
	
	
}
