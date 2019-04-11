package parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.QuerySyntax;

public class syntaxChecker implements QuerySyntax {

	private String[] QueriesMatching;

	public syntaxChecker() {
		QueriesMatching = new String[12];
		// create databse
		QueriesMatching[0] = "(?i)\\s*create\\s+database\\s+(if\\s+not\\s+exists\\s+)?\\w+\\s*;\\s*";
		// drop database
		QueriesMatching[1] = "(?i)\\s*drop\\s+database\\s+(if\\s+exists\\s+)?\\w+\\s*;\\s*";
		// create table
		QueriesMatching[2] = "(?i)\\s*create\\s+table\\s+(if\\s+not\\s+exists\\s+)?\\w+\\s*\\((\\s*\\w+\\s+(int|varchar)\\s*(\\s+not\\s+null\\s*)?,?)+\\)\\s*;\\s*";
		// drop table
		QueriesMatching[3] = "(?i)\\s*drop\\s+table\\s+(if\\s+exists\\s+)?\\w+\\s*;\\s*";
		// insert into without determining columns
		QueriesMatching[4] = "(?i)\\s*insert\\s+into\\s+\\w+\\s+values\\s*\\(\\s*(\\'?\\\"?\\w+\\'?\\\"?,?\\s*)+\\)(?<=\\w{1,100}\\s{0,100}\\));\\s*"; // columns
		// insert into with determining columns
		QueriesMatching[5] = "(?i)\\s*insert\\s+into\\s+\\w+\\s*\\(\\s*(\\w+,?\\s*)+\\)\\s*values\\s*\\(\\s*(\\'?\\\"?\\w+\\'?\\\"?,?\\s*)+\\)(?<=\\w{1,100}\\s{0,100}\\));\\s*";
		// select all in tables
		QueriesMatching[6] = "(?i)\\s*select\\s*\\*\\s*from\\s+\\w+\\s*;\\s*";
		// select specific columns
		QueriesMatching[7] = "(?i)\\s*select(\\s*\\w+\\s*,?)+(?<=\\w{1,20}\\s{1,20})from\\s+\\w+\\s*(\\s+where\\s+\\w+\\s*(>|<|=|or|and)\\s*\\w+)?\\s*;\\s*";
		// update columns in table
		QueriesMatching[8] = "(?i)\\s*update\\s+\\w+\\s+set\\s+(\\s*\\w+\\s*=\\s*\\'?\\w+\\'?\\s*,?\\s*)+(?<=\\w{1,100})(\\s+where\\s+\\w+\\s*(\\>|\\<|\\=|\\<\\=|\\>\\=|or|and)\\s*\\w+)?\\s*;\\s*";
		// drop column from table
		QueriesMatching[9] = "(?i)\\s*alter\\s+table\\s+\\w+\\s+drop\\s+\\w+\\s*;\\s*";
		// show tables;
		QueriesMatching[10] = "(?i)\\s*show\\s+tables\\s*;\\s*";
		// add columns to table
		QueriesMatching[11] = "(?i)\\s*alter\\s+table\\s+\\w+\\s+add\\s*\\(?(\\w+\\s+(int|varchar)\\s*,?\\s*)+\\)?(?<=\\w{1,100}\\s{0,100}\\)?);\\s*";

	}

	@Override
	public int isValid(String query) {
		for (int i = 0; i < QueriesMatching.length; i++) {
			if (query.matches(QueriesMatching[i])) {
				return i + 1;
			}
		}
		return 0;
	}

	@Override
	public String getDBName(String query) {
		Pattern DBpattern = Pattern.compile("(?i)(?<=(create|drop)\\s{1,20}database\\s{1,20})\\w+(?=\\s*;)");
		Matcher DBmatcher = DBpattern.matcher(query);
		if (DBmatcher.find()) {
			return DBmatcher.group();
		}
		return null;
	}

	@Override
	public String getTableName(String query) {
		Pattern tablePattern = Pattern.compile("(?i)(?<=(create|drop|alter)\\s{1,20}table\\s{1,20})\\w+");
		Matcher tableMatcher = tablePattern.matcher(query);
		if (tableMatcher.find())
			return tableMatcher.group();
		return null;
	}

	@Override
	public ArrayList<String> getColumnsName(String query) {
		ArrayList<String> columnsNames = new ArrayList<>();
		Pattern create = Pattern.compile("(?i)(?<=(\\(|,)\\s*)\\w+(?=\\s+\\w+)");// create and add columns
		Pattern update = Pattern.compile("(?i)\\w+(?=\\s*\\=)");
		Pattern insertAndselect = Pattern.compile(
				"(?i)(?<=((insert\\s{1,30}into\\s{1,30}\\w{1,30}\\s{0,30}\\()|(select\\s{1,30}))).+(?=((\\s*\\)\\s*values)|(\\s+from)))");
		Matcher createMatcher = create.matcher(query);
		Matcher updateMatcher = update.matcher(query);
		Matcher inMatcher = insertAndselect.matcher(query);

		while (createMatcher.find()) {
			columnsNames.add(createMatcher.group().trim());
		}

		while (updateMatcher.find()) {
			columnsNames.add(updateMatcher.group().trim());
		}

		if (inMatcher.find()) {
			String[] arr = inMatcher.group().split(",");
			for (int i = 0; i < arr.length; i++) {
				columnsNames.add(arr[i].trim());
			}
		}
		return columnsNames;
	}

	@Override
	public ArrayList<String> getColumnsType(String query) {
		ArrayList<String> types = new ArrayList<>();
		Pattern typePattern = Pattern.compile("(?i)(?<=(\\(|,)\\s{0,100}\\w{1,100}\\s{1,100})(int|varchar)");
		Matcher typeMatcher = typePattern.matcher(query);
		while (typeMatcher.find()) {
			types.add(typeMatcher.group().trim());
		}
		return types;
	}

	@Override
	public ArrayList<String> getInsertingValues(String query) {
		ArrayList<String> values = new ArrayList<>();
		Pattern insert = Pattern.compile("(?i)(?<=values\\s{0,100}\\().+(?=\\))");
		Pattern update = Pattern.compile("(?i)(?<=\\=\\s{0,100})\\w+");
		Matcher inserMatcher = insert.matcher(query);
		Matcher updateMatcher = update.matcher(query);
		if (inserMatcher.find()) {
			String[] arr = inserMatcher.group().split(",");
			for (int i = 0; i < arr.length; i++) {
				values.add(arr[i].trim());
			}
		}

		while (updateMatcher.find()) {
			values.add(updateMatcher.group());
		}
		return values;
	}

	@Override
	public String getCondition(String query) {
		Pattern condition = Pattern.compile("(?i)(?<=where\\s{1,100})\\w+(\\<|\\>|\\=)\\w+");
		Matcher conditionMatcher = condition.matcher(query);
		if (conditionMatcher.find())
			return conditionMatcher.group().trim();

		return null;
	}

	@Override
	public String getDropedColumn(String query) {
		Pattern dropColumn = Pattern.compile("(?i)(?<=drop\\s{1,30})\\w+(?=\\s*;)");
		Matcher dropMatcher = dropColumn.matcher(query);
		if (dropMatcher.find()) {
			return dropMatcher.group();
		}
		return null;
	}

}
