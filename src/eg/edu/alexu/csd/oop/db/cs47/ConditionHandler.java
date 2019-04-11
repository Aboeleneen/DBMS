package eg.edu.alexu.csd.oop.db.cs47;

import eg.edu.alexu.csd.oop.db.Conditions;

public class ConditionHandler implements Conditions {

	@Override
	public Boolean equal(String column, String value) {
		// TODO Auto-generated method stub
		return column.equals(value);
	}

	@Override
	public Boolean greater(String column, String value) {
		// TODO Auto-generated method stub
		if(column.compareTo(value) > 0) return true ;
		else return false ;
	}

	@Override
	public Boolean smaller(String column, String value) {
		// TODO Auto-generated method stub
		if(column.compareTo(value) < 0) return true ;
		else return false ;
	}

	@Override
	public Boolean greaterOrEqual(String column, String value) {
		// TODO Auto-generated method stub
		return equal(column,value) || greater(column,value);
	}

	@Override
	public Boolean smallerOrEqual(String column, String value) {
		// TODO Auto-generated method stub
		return equal(column,value) || smaller(column,value);
	}

}
