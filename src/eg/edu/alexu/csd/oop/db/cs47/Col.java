package eg.edu.alexu.csd.oop.db.cs47;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.Column;

public class Col implements Column{
	
    private ArrayList<Object> rows ;
    private String dataType ;
    private String columnName ;
    private String tableName ;
    
    public Col() {
    	rows=new ArrayList<>();
    	dataType=null;
    	columnName=null;
    	tableName=null;
    }
    
    @Override
    public void setRows(ArrayList<Object> rows) {
		this.rows = rows;
	}

	@Override
	public void addNewRecord(Object row) {
		// TODO Auto-generated method stub
		rows.add(row);
	}
    @Override
	public String getColumnName() {
		return columnName;
	}
    @Override
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
    @Override
	public String getTableName() {
		return tableName;
	}
    @Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Override
	public String getDataType() {
		return dataType;
	}
	@Override
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	@Override
	public ArrayList<Object> getRows() {
		return rows;
	}
	
	
	

}
