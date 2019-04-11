package conditions;

import eg.edu.alexu.csd.oop.db.Command;
import eg.edu.alexu.csd.oop.db.ConditionFactory;
import eg.edu.alexu.csd.oop.db.cs47.ConditionHandler;

public class conditionFactory implements ConditionFactory {
    
	@Override
	public Command createCondition(ConditionHandler condition,String oper) {
		// TODO Auto-generated method stub
		if(oper.equals("=")) return new EqualOper(condition);
		else if(oper.equals(">")) return new greaterOper(condition);
		else if(oper.equals("<")) return new smallerOper(condition);
		else if(oper.equals("<=")) return new smallerOrEqual(condition);
		else if(oper.equals(">=")) return new greaterOrEqual(condition);
		else return new notEqualOper(condition);
	}

}
