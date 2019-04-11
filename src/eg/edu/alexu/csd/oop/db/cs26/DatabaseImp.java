package eg.edu.alexu.csd.oop.db.cs26;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.db.Column;
import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.QuerySyntax;
import eg.edu.alexu.csd.oop.db.Table;
//import eg.edu.alexu.csd.oop.db.cs30.FileBuilder;
import eg.edu.alexu.csd.oop.db.cs47.*;
import parser.syntaxChecker;

public class DatabaseImp implements Database {
	private QuerySyntax controller;
	private static FileBuilder builder = FileBuilder.getInstance();
	private String path = null;
	private String databaseName = null;
	private ArrayList<String> tablesNames;
	private ArrayList<DataTable> tables;

	public DatabaseImp() {
		controller = new syntaxChecker();
		tables = new ArrayList<DataTable>();
		// builder = FileBuilder.getInstance();
		tablesNames = new ArrayList<String>();
	}

	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		if (dropIfExists) {
			builder.dropDB(databaseName);
		}
		try {
			executeStructureQuery("create database " + databaseName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File file = new File(databaseName);
		path = file.getAbsolutePath();
		return path;
	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		int operation = controller.isValid(query);
		if (operation == 0) {
			return false;
		}
		switch (operation) {
		case 1:
			databaseName = controller.getDBName(query);
			File file = new File(databaseName);
			path = file.getAbsolutePath();
			return builder.createDB(databaseName);
		case 2:
			databaseName = controller.getDBName(query);
			return builder.dropDB(databaseName);
		case 3:
			tablesNames = builder.getTables(databaseName);
			if (databaseName == null) {
				return false;
			}
			return createTable(query);
		case 4:
			tablesNames = builder.getTables(databaseName);
			if (databaseName == null) {
				return false;
			}
			return dropTable(query);
		}
		return false;
	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		int operation = controller.isValid(query);
		if (operation == 0) {
			return null;
		}
		if (databaseName == null) {
			return null;
		}
		switch (operation) {
		case 7:
			selectFromTable(query);
		case 8:
			selectFromTable(query);
		}
		return null;
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		int operation = controller.isValid(query);
		if (operation == 0) {
			return (Integer) null;
		}
		switch (operation) {
		case 5:
		case 6:
			return insertIntoTable(query);
		}
		return 0;
	}

	private int insertIntoTable(String query) {
		String tableName = controller.getTableName(query);
		if (!checkTableName(tableName)) {
			return (Integer) null;
		}
		int index = tablesNames.indexOf(tableName);
		DataTable t = tables.get(index);
		ArrayList<String> conditions = new ArrayList<String>();
		conditions.add(controller.getCondition(query));
		return 0;
	}

	private Object[][] selectFromTable(String query) {
		String tableName = controller.getTableName(query);
		if (!checkTableName(tableName)) {
			return null;
		}
		int index = tablesNames.indexOf(tableName);
		DataTable t = tables.get(index);
		ArrayList<String> conditions = new ArrayList<String>();
		conditions.add(controller.getCondition(query));
		return t.select(controller.getColumnsName(query), conditions);
	}

	private boolean createTable(String query) {
		String tableName = controller.getTableName(query);
		if (checkTableName(tableName)) {
			return false;
		}
		tablesNames.add(tableName);
		ArrayList<String> colNames = new ArrayList<String>();
		ArrayList<String> colTypes = new ArrayList<String>();
		colNames = controller.getColumnsName(query);
		colTypes = controller.getColumnsType(query);
		String[] fields = new String[colNames.size()];
		String[] types = new String[colNames.size()];
		for (int i = 0; i < colNames.size(); i++) {
			Column column = new Col();
			fields[i] = colNames.get(i);
			types[i] = colTypes.get(i);
		}
		builder.createTable(types, fields, tableName, databaseName);
		return true;
	}

	/**
	 * check if the table exists in the DB or not. sawy.
	 * 
	 * @param tableName
	 * @return true if table exists, else return false.
	 */
	private boolean checkTableName(String tableName) {
		if (tablesNames != null) {
			tables = getTables(databaseName);
			for (String s : tablesNames) {
				if (s.equals(tableName))
					return true;
			}
		}
		return false;

	}

	private boolean dropTable(String query) {
		String tableName = controller.getTableName(query);
		if (!checkTableName(tableName)) {
			return false;
		}
		File xmlFile = new File(path + File.separator + tableName + ".xml");
		File dtdFile = new File(path + File.separator + tableName + ".dtd");
		return builder.deleteDirectory(xmlFile) && builder.deleteDirectory(dtdFile);

	}

	public static ArrayList<DataTable> getTables(String dbName) {
		ArrayList<DataTable> tables = new ArrayList<DataTable>();
		ArrayList<String> tNames = builder.getTables(dbName);
		for (int i = 0; i < tNames.size(); i++) {
			ArrayList<Column> colsTable = DataTable.getColumnsFromXml(tNames.get(i), dbName);
			// System.out.println(tNames[i]);
			DataTable newTable = new DataTable(dbName, tNames.get(i), colsTable);
			tables.add(newTable);
		}
		return tables;
	}
}
