package eg.edu.alexu.csd.oop.db;

import java.io.File;
import java.util.ArrayList;

public interface FileBuilding {
	/*
	 * it takes the path or the name of database and create dictionary to it into
	 * databases file it return boolean that is true if possible or false if not
	 */
	public Boolean createDB(String name);

	/*
	 * it takes the path or the name of database and delete dictionary to it into
	 * databases file it return boolean that is true if possible or false if not
	 */
	public Boolean dropDB(String name);

	/*
	 * it takes 4 parameters content that's the data of the table fields that's the
	 * names of fields table name database name it return boolean that is true if
	 * possible or false if not
	 */
	public boolean write(Object[][] content, String[] fields, String TableName, String DBName);

	/*
	 * it takes 2 parameters table name database name it return 2d array which first
	 * array of it is fields if it return null then the table is empty you should
	 * call gettypeList() to get types and getArrayList to get fields.
	 */
	public Object[][] read(String TableName, String DBName);

	/*
	 * it takes 1 parameters database name it return arraylist of tables in this
	 * database
	 */
	public ArrayList getTables(String Db);

	/*
	 * it return array of fields of the last table has been read
	 */
	public String[] getArrayList();

	/*
	 * it return array of type of the fields of the last table has been read
	 */
	public String[] getTypesList();

	/*
	 * it takes array of types , another has fields , table name , and database name
	 * it creates a xml file that is empty till now
	 *
	 */
	public void createTable(String types[], String[] fields, String TableName, String DBName);

}
