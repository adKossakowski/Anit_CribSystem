package Values_package;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.*;
//import Interfaces.Initialization;
//import Interfaces.Operation_Interface;

public class AC_Integer implements Operation_Interface<Integer>, Action, Initialization {
	
	private static final String basedName = "intVal";
	private static int basedCounter = 0;

	private String program_name;//real name of variable in testing program
	private String testing_name;//name in this program
	private int mountOfCalling;
	
	private ArrayList <Integer> values = new ArrayList<>();
	
	public AC_Integer() {
		program_name = null;
		testing_name = null;
		mountOfCalling = 0;
		values = null;
	}
	
	public AC_Integer(String variableName) {
		program_name = variableName;
		testing_name = basedName + basedCounter;
		basedCounter++;
		mountOfCalling = 1;
		values.add(new Integer(0));
	}
	
	public AC_Integer(String variableName, int initializingValue) {
		program_name = variableName;
		testing_name = basedName + basedCounter;
		basedCounter++;
		mountOfCalling = 1;
		values.add(new Integer(initializingValue));
	}
	
	//hashcode & equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((program_name == null) ? 0 : program_name.hashCode());
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
		if (program_name == null) {
			if (other.program_name != null)
				return false;
		} else if (!program_name.equals(other.program_name))
			return false;
		return true;
	}
	
	
	///operation interface
	
	public Integer addition(Integer left, Integer right) {
		return new Integer(left.intValue() + right.intValue());
	}

	public Integer substraction(Integer left, Integer right) {
		return new Integer(left.intValue() - right.intValue());
	}
	
	public Integer multiplication(Integer left, Integer right) {
		return new Integer(left.intValue() * right.intValue());
	}
	
	public Integer division(Integer left, Integer right) {
		return new Integer(left.intValue() / right.intValue());
	}
	
	public Integer attribution() {
		return values.get(values.size()-1);
	}
	
	//action interface
	
	public void action(BufferedWriter bw, ArrayList<String> stringArray, HashMap<String, Object> valuesMap) throws IOException {
		if(stringArray.size()==1) {
			//inkrementacja dekrementacja
		}else if(stringArray.size()==3) {
			bw.newLine();
			bw.write(this.initialization(stringArray, valuesMap));
		}else {
			bw.newLine();
			bw.write(this.operation_method(stringArray, valuesMap));
		}
		
	}
	
	/*public void action(BufferedReader br, BufferedWriter bf, ArrayList<String> arrayString) {
		
	}*/
	
	public void initialization(BufferedWriter bw, ArrayList<String> stringArray, HashMap<String, Object> valuesMap) throws IOException {
		
		if(stringArray.contains("=")) {
			valuesMap.put(stringArray.get(1), new AC_Integer(stringArray.get(1), Integer.parseInt(stringArray.get(3)))); 
			AC_Integer tmpAC = (AC_Integer)valuesMap.get(stringArray.get(1));
			StringBuilder sb = new StringBuilder("int " + tmpAC.testing_name + " = " + tmpAC.getValues(tmpAC.values.size()-1));
			bw.newLine();
			bw.write(sb.toString());
		}else {
			valuesMap.put(stringArray.get(1), new AC_Integer(stringArray.get(1)));
			AC_Integer tmpAC = (AC_Integer)valuesMap.get(stringArray.get(1));
			StringBuilder sb = new StringBuilder("int " + tmpAC.testing_name + " = " + tmpAC.getValues(tmpAC.values.size()-1));
			bw.newLine();
			bw.write(sb.toString());
		}
		
	}
	
	public String initialization(ArrayList<String> stringArray, HashMap<String, Object> valuesMap) {
		
		AC_Integer target, opt_second;
		target = (AC_Integer) valuesMap.get(stringArray.get(0));
		
		StringBuilder sb = new StringBuilder(target.testing_name);// + "(" + target.attribution() + ")" + " = ");
		if(valuesMap.containsKey(stringArray.get(2))) {
			opt_second = (AC_Integer)valuesMap.get(stringArray.get(2));
			target.values.add(opt_second.attribution());
			sb.append("(" + target.attribution() + ")" + " = ");
			sb.append(opt_second.testing_name + "(" + opt_second.attribution().toString() + ")");
		}else {
			target.values.add(Integer.valueOf(stringArray.get(2)));
			sb.append("(" + target.attribution() + ")" + " = ");
			sb.append(stringArray.get(2));
		}
		
		return sb.toString();
	}
	
	///getters & setters
	
	public String getProgram_name() {
		return program_name;
	}

	public String getTesting_name() {
		return testing_name;
	}

	public int getMountOfCalling() {
		return mountOfCalling;
	}
	
	public Integer getValues(int i) {
		return values.get(i);
	}
	
	/////////////////////////////////


	public String operation_method(ArrayList<String> stringArray, HashMap<String, Object> valuesMap) {
		
		AC_Integer tmpAC = (AC_Integer)valuesMap.get(stringArray.get(0));
		Integer left, right;
		AC_Integer one = null, two = null;
		boolean b_left = false, b_right = false;
		if(valuesMap.containsKey(stringArray.get(2))) {
			b_left = true;
			one = (AC_Integer)valuesMap.get(stringArray.get(2));
			left = one.attribution();
		}else {
			left = new Integer(stringArray.get(2));
		}
		
		if(valuesMap.containsKey(stringArray.get(4))) {
			b_right = true;
			two = (AC_Integer)valuesMap.get(stringArray.get(4));
			right = two.attribution();
		}else {
			right = new Integer(stringArray.get(4));
		}
		
		tmpAC.values.add(checkAction(stringArray.get(3).charAt(0), left, right));
		
		StringBuilder sb = new StringBuilder(tmpAC.testing_name + "(" + tmpAC.attribution() + ")" + " = ");
		
		if(b_left) {
			sb.append(one.testing_name + "(" + left + ")");
		}else {
			sb.append(left);
		}
		
		sb.append(stringArray.get(3));
		
		if(b_right) {
			sb.append(two.testing_name + "(" + right + ")");
		}else {
			sb.append(right);
		}
		
		return sb.toString();
	}
	
	private Integer checkAction(char act, Integer left, Integer right){
		if(act=='-') {
			return substraction(left,right);
		}else if(act=='+') {
			return addition(left, right);
		}else if(act=='*') {
			return multiplication(left, right);
		}else{
			return division(left,right);
		}
	}
}

