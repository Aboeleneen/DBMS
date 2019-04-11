package eg.edu.alexu.csd.oop.db;

import java.util.ArrayList;

public interface Column {
       
	public void addNewRecord(Object values);

	public String getColumnName();
	
	public ArrayList<Object> getRows();
	
	public String getDataType();
	
	public void setColumnName(String columnName);

	public String getTableName();

	public void setTableName(String tableName);

	public void setDataType(String dataType);

	public void setRows(ArrayList<Object> rows);
}
