
package Anti_Crib_main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AC_Integer extends DefaultClass {
	
	public static Map <String, AC_Integer>integerMap = new HashMap <String, AC_Integer>();
	
	private static int int_counter = 0;
	private static final String constString = "intVal";
	
	private ArrayList<Integer> integer_value = new ArrayList<Integer>();
	private String integer_name;//name of value in the testing program
	private String integer_programName;//name of value in  this program
	private int mountOfCalling;
	
	public static int getInt_counter() {
		return int_counter;
	}

	public ArrayList<Integer> getInteger_value() {
		return integer_value;
	}

	public void setInteger_value(ArrayList<Integer> integer_value) {
		this.integer_value = integer_value;
	}

	public String getInteger_name() {
		return integer_name;
	}

	public String getInteger_programName() {
		return integer_programName;
	}

	public int getMountOfCalling() {
		return mountOfCalling;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((integer_name == null) ? 0 : integer_name.hashCode());
		result = prime * result + ((integer_programName == null) ? 0 : integer_programName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AC_Integer other = (AC_Integer) obj;
		if (integer_name == null) {
			if (other.integer_name != null)
				return false;
		} else if (!integer_name.equals(other.integer_name))
			return false;
		if (integer_programName == null) {
			if (other.integer_programName != null)
				return false;
		} else if (!integer_programName.equals(other.integer_programName))
			return false;
		return true;
	}

	public AC_Integer(){
		integer_value = null;
		integer_name = "default";
		integer_programName = "default";
		mountOfCalling = 0;
	}
	
	public AC_Integer(int first_int, String name_value){
		integer_value.add(first_int);
		integer_name = name_value;
		integer_programName = constString + int_counter;
		int_counter++;
		mountOfCalling = 1;
	}
	
	
	public String equalsExpression(ArrayList<String> stringArray){
		
		
		if(mountOfEquals(stringArray) == 0)
			integerMap.put(stringArray.get(1), new AC_Integer(0, stringArray.get(1)));
		else if(mountOfEquals(stringArray) == 1)
			integerMap.put(stringArray.get(1), new AC_Integer(Integer.parseInt(stringArray.get(3)), stringArray.get(1)));
		else{
			//pusta zaleznosc do zrobienia w przyszlosci
		}
		return integerMap.get(stringArray.get(1)).integer_programName + " = " + integerMap.get(stringArray.get(1)).integer_value.get(integer_value.size() - 1);
	}
	
	public boolean checkingName(String ch_name, boolean change_value, int newValue){
		if(integer_name.equals(ch_name)){
			mountOfCalling++;
			if(change_value){
				if(integer_value.get(integer_value.size() - 1).intValue() != newValue){
					integer_value.add(newValue);
				}
			}
			return true;
		}
		return false;
	}
	

}
