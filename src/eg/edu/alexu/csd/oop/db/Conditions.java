package eg.edu.alexu.csd.oop.db;

public interface Conditions {
       
	public Boolean equal(String column , String value) ;
	public Boolean greater(String column , String value) ;
	public Boolean smaller(String column , String value) ;
	public Boolean greaterOrEqual(String column , String value) ;
	public Boolean smallerOrEqual(String column , String value) ;
}
