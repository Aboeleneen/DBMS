package eg.edu.alexu.csd.oop.db.cs47;

import java.util.ArrayList;


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
		String [] field = new String[]{
				"varchar","int","varchar","int"
		};
		String TableName="test8";
		FileBuilder build= FileBuilder.getInstance();
		//build.dropDB("kimo5");
		//build.createDB("kimo5");
		build.write(content, fields, TableName, "DB");
        ArrayList n =build.getTables("kimo5");
		int hh=1;
		hh++;
	}
	
}
