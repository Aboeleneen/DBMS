package eg.edu.alexu.csd.oop.db;

import eg.edu.alexu.csd.oop.db.cs47.ConditionHandler;

public interface ConditionFactory {
	
	public Command createCondition(ConditionHandler condition,String oper);

}
