package conditions;

import eg.edu.alexu.csd.oop.db.Command;
import eg.edu.alexu.csd.oop.db.cs47.ConditionHandler;

public class smallerOper implements Command {
	
	private ConditionHandler condition = new ConditionHandler();
	
	public smallerOper(ConditionHandler condition) {
		this.condition = condition ;
	}

	
	@Override
	public Boolean execute(String column, String value) {
		// TODO Auto-generated method stub
		return condition.smaller(column, value);
	}

}
