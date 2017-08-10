package Anti_Crib_main;

import java.util.ArrayList;

public interface Equals_Interface {

	public String equalsExpression(ArrayList<String> stringArray);
	
	default int mountOfEquals(ArrayList <String> equalsList){
		int counter = 0;
		for(String st : equalsList){
			if(st.equals("="))
				counter++;
		}
		return counter;
	}
}
