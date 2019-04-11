package conditions;

import eg.edu.alexu.csd.oop.db.Command;
import eg.edu.alexu.csd.oop.db.cs47.ConditionHandler;

public class smallerOrEqual implements Command {
	
	private ConditionHandler condition = new ConditionHandler();
	
	public smallerOrEqual(ConditionHandler condition) {
		this.condition = condition ;
	}

	
	@Override
	public Boolean execute(String column, String value) {
		// TODO Auto-generated method stub
		return condition.smallerOrEqual(column, value);
	}

}
