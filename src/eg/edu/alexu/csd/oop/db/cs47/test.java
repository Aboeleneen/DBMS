package eg.edu.alexu.csd.oop.db.cs47;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.Column;
import eg.edu.alexu.csd.oop.db.cs26.DatabaseImp;

public class test {
             
	public static void main(String[] args) {
		/* ArrayList<String> columns = new ArrayList<>();
		columns.add("firstName");
		columns.add("lastName");
		columns.add("address");
		columns.add("age");
		DataTable table = new DataTable("Social","users",columns);
		ArrayList<String> cols = new ArrayList<>();
		cols.add("firstName");
		cols.add("age");
		ArrayList<String> conditions = new ArrayList<>();
		conditions.add("age >= 2 ");
		String[][] out = table.select(cols, conditions);
		for(int i=0;i<out.length;i++) {
			for(int j=0;j<2;j++) {
				System.out.print(out[i][j] + "  ");
			}
			System.out.println("\n");
		}
		ConditionHandler con = new ConditionHandler();
		System.out.println(con.equal("1","2"));
		System.out.println(con.smaller("1","2"));
		System.out.println(con.greater("1","2"));
		System.out.println(con.greaterOrEqual("2","2"));
		System.out.println(con.smallerOrEqual("1","2"));
		ArrayList<Column> cols = new ArrayList<>();
		Column col1 = new Col();
		col1.setColumnName("FirstName");
		col1.setDataType("varChar");
		col1.addNewRecord("mahmoud");
		col1.addNewRecord("hamdy");
		cols.add(col1);
		Column col2 = new Col();
		col2.setColumnName("LastName");
		col2.setDataType("varChar");
		col2.addNewRecord("fathy");
		col2.addNewRecord("mahmad");
		cols.add(col2);
		Column col3 = new Col();
		col3.setColumnName("Age");
		col3.setDataType("int");
		col3.addNewRecord("20");
		col3.addNewRecord("21");
		cols.add(col3);
		DataTable table = new DataTable("Social","users",cols);
		ArrayList<String> columnsNames = new ArrayList<>();
		columnsNames.add("FirstName");
		columnsNames.add("LastName");
		columnsNames.add("Age");
		ArrayList<String> values = new ArrayList<>();
		values.add("Karim");
		
		values.add("ibrahim");
		values.add("2");
		String[][] out = table.getContent();
		for(int i=0;i<out.length;i++) {
			for(int j=0;j<out[i].length;j++) {
				System.out.print(out[i][j] + "  ");
			}
			System.out.println("\n");
		}
		table.insert(columnsNames, values);
		out = table.getContent();
		for(int i=0;i<out.length;i++) {
			for(int j=0;j<out[i].length;j++) {
				System.out.print(out[i][j] + "  ");
			}
			System.out.println("\n");
		}*/
		
		/* check karim */ 
	/*	ArrayList<Column> cols = new ArrayList<>();
		Column col1 = new Col();
		col1.setColumnName("english");
		col1.setDataType("int");
		Column col2 = new Col();
		col2.setColumnName("engineering");
		col2.setDataType("int");
		Column col3 = new Col();
		col3.setColumnName("physics");
		col3.setDataType("int");
		Column col4 = new Col();
		col4.setColumnName("chemistry");
		col4.setDataType("int");
		cols.add(col1);
		cols.add(col2);
		cols.add(col3);
		cols.add(col4);
		ArrayList<String> columnsNames = new ArrayList<>();
		columnsNames.add("english");
		columnsNames.add("engineering");
		columnsNames.add("physics");
		ArrayList<String> values = new ArrayList<>();
		values.add("1");
		values.add("2");
		values.add("3");
		DataTable table = new DataTable("DB","students.xml",cols);
		
		ArrayList<String> col = new ArrayList<>();
		// "english","engineering","physics","chemistry"
		col.add("english");
		col.add("engineering");
		col.add("physics");
		col.add("chemistry");
		ArrayList<String> conditions = new ArrayList<>();
		System.out.println(table.select(col, conditions).length);
		System.out.println(table.insert(columnsNames, values));
		System.out.println(table.select(col, conditions).length);
		/* check Karoim */ 
       
		/* check delete *
		ArrayList<Column> cols = new ArrayList<>();
		Column col1 = new Col();
		col1.setColumnName("FirstName");
		col1.setDataType("varChar");
		col1.addNewRecord("mahmoud");
		col1.addNewRecord("hamdy");
		cols.add(col1);
		Column col2 = new Col();
		col2.setColumnName("LastName");
		col2.setDataType("varChar");
		col2.addNewRecord("fathy");
		col2.addNewRecord("mahmad");
		cols.add(col2);
		Column col3 = new Col();
		col3.setColumnName("Age");
		col3.setDataType("int");
		col3.addNewRecord("20");
		col3.addNewRecord("21");
		cols.add(col3);
		DataTable table = new DataTable("Social","users",cols);
		ArrayList<String> columnsNames = new ArrayList<>();
	    columnsNames.add("FirstName");
		columnsNames.add("LastName") ;
		System.out.println(table.getContent()[0].length);
		System.out.println(table.delete(columnsNames));
		System.out.println(table.getContent()[0].length);
		String[][] out = table.getContent();
		for(int i=0;i<out.length;i++) {
			for(int j=0;j<out[i].length;j++) {
				System.out.print(out[i][j] + "  ");
			}
			System.out.println("");
		}
		
		/* check delete */
		
		/* check for sawy */
		  
		FileBuilder build = FileBuilder.getInstance();
		ArrayList b=build.getTables("DB");
	//	System.out.println(b.toString());
		ArrayList<String> columnsNames = new ArrayList<>();
		columnsNames.add("english = 2");
		System.out.println(DatabaseImp.getTables("DB").get(1).deleteRows(columnsNames));
		/*Object[][] n =  build.read("student", "kimo5");
		System.out.println(n[0][1]);*/
		
		/* check for sawy */
		
	}
}
