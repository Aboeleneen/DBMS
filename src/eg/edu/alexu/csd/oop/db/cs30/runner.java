package eg.edu.alexu.csd.oop.db.cs30;


public class runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object [][]content =new Object[][]{
				{"2","10","12",20}
				,{"15","21","55",6},{"1000","151515","123",154}
				,{"152","2241","545",6666},{"10065650","15165655515","16523",1545544}
		};
		String [] fields = new String[]{
				"english","engineering","physics","chemistry"
		};
		String TableName="students";
		FileBuilder build= FileBuilder.getInstance();
		Object [][] v=build.read("adress", "kimo5");

		String [] h =build.getTypesList();
		
		int hh=1;
		hh++;
	}
	
}
