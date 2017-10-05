package Values_package;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.Action;
import Interfaces.Incrementation;
import Interfaces.Initialization;
import Interfaces.Operation_Interface;

public class AC_Double implements Operation_Interface<Double>, Action, Initialization, Incrementation{
	

	private static final String basedName = "doubVal";
	private static int basedCounter = 0;

	protected String program_name;//real name of variable in testing program
	protected String testing_name;//name in this program
	protected int mountOfCalling;
	
	private ArrayList <Double> values = new ArrayList<>();
	
	public AC_Double() {
		program_name = null;
		testing_name = null;
		mountOfCalling = 0;
		values = null;
	}
	
	public AC_Double(String variableName) {
		program_name = variableName;
		testing_name = basedName + basedCounter;
		basedCounter++;
		mountOfCalling = 1;
		values.add(new Double(0));
	}
	
	public AC_Double(String variableName, double initializingValue) {
		program_name = variableName;
		testing_name = basedName + basedCounter;
		basedCounter++;
		mountOfCalling = 1;
		values.add(new Double(initializingValue));
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
		AC_Double other = (AC_Double) obj;
		if (program_name == null) {
			if (other.program_name != null)
				return false;
		} else if (!program_name.equals(other.program_name))
			return false;
		mountOfCalling++;
		return true;
	}
	
	
	///operation interface
	
	public Double addition(Double left, Double right) {
		mountOfCalling++;
		return new Double(left.intValue() + right.intValue());
	}

	public Double substraction(Double left, Double right) {
		mountOfCalling++;
		return new Double(left.intValue() - right.intValue());
	}
	
	public Double multiplication(Double left, Double right) {
		mountOfCalling++;
		return new Double(left.intValue() * right.intValue());
	}
	
	public Double division(Double left, Double right) {
		mountOfCalling++;
		return new Double(left.intValue() / right.intValue());
	}
	
	public Double attribution() {
		mountOfCalling++;
		return values.get(values.size()-1);
	}
	
	//incrementation decrementation interface
	
	public void incrementation() {
		double i = Double.parseDouble(getLastValue().toString());
		this.mountOfCalling++;
		values.add(new Double(++i));
	}
	
	public void decrementation() {
		double i = Double.parseDouble(getLastValue().toString());
		this.mountOfCalling++;
		values.add(new Double(--i));
	}
	
	//action interface
	
	public String action(BufferedWriter bw, ArrayList<String> stringArray, HashMap<String, Object> valuesMap, int isStructure) throws IOException {
		bw.newLine();
		StringBuilder sb = new StringBuilder("");
		if(stringArray.size()==2) {
			if(stringArray.toString().contains("[" + program_name + ", ++]") || stringArray.toString().contains("[++, " + program_name + "]")) {
				this.incrementation();
				int count = 0;
				while(count<isStructure) {
					sb.append("\t");
					count++;
				}
				sb.append(this.testing_name + "(" + this.getLastValue().toString() + ")" + "++");
			}else if(stringArray.toString().contains("[" + program_name + ", --]") || stringArray.toString().contains("[--, " + program_name + "]")) {
				this.decrementation();
				int count = 0;
				while(count<isStructure) {
					sb.append("\t");
					count++;
				}
				sb.append(this.testing_name + "(" + this.getLastValue().toString() + ")" + "--");
			}else {
				
			}
		}else if(stringArray.size()==3) {
			sb.append(this.initialization(stringArray, valuesMap, isStructure));
		}else {
			sb.append(this.operation_method(stringArray, valuesMap, isStructure));
		}
		
		if(isStructure==0) {
			bw.write(sb.toString());
			return null;
		}else {
			bw.write(sb.toString());
			return sb.toString();
		}
		
	}
	
	public boolean initialization(BufferedWriter bw, ArrayList<String> stringArray, HashMap<String, Object> valuesMap) throws IOException {
		
		if(stringArray.contains("=")) {
			valuesMap.put(stringArray.get(1), new AC_Double(stringArray.get(1), Double.parseDouble(stringArray.get(3)))); 
			AC_Double tmpAC = (AC_Double)valuesMap.get(stringArray.get(1));
			StringBuilder sb = new StringBuilder("int " + tmpAC.testing_name + " = " + tmpAC.getValues(tmpAC.values.size()-1));
			bw.newLine();
			bw.write(sb.toString());
		}else {
			valuesMap.put(stringArray.get(1), new AC_Double(stringArray.get(1)));
			AC_Double tmpAC = (AC_Double)valuesMap.get(stringArray.get(1));
			StringBuilder sb = new StringBuilder("int " + tmpAC.testing_name + " = " + tmpAC.getValues(tmpAC.values.size()-1));
			bw.newLine();
			bw.write(sb.toString());
		}
		return true;
	}
	
	public String initialization(ArrayList<String> stringArray, HashMap<String, Object> valuesMap, int isStructure) {
		
		AC_Double target, opt_second;
		target = (AC_Double) valuesMap.get(stringArray.get(0));
		StringBuilder sb;
		if(isStructure==0)
			sb = new StringBuilder(target.testing_name);// + "(" + target.attribution() + ")" + " = ");
		else {
			sb = new StringBuilder("\t");
			int count = 1;
			while(count<isStructure) {
				sb.append("\t");
				count++;
			}
			sb.append(target.testing_name);
		}
			//sb = new StringBuilder("\t" + target.testing_name);
		if(valuesMap.containsKey(stringArray.get(2))) {
			opt_second = (AC_Double)valuesMap.get(stringArray.get(2));
			target.values.add(opt_second.attribution());
			sb.append("(" + target.attribution() + ")" + " = ");
			sb.append(opt_second.testing_name + "(" + opt_second.attribution().toString() + ")");
		}else {
			target.values.add(Double.valueOf(stringArray.get(2)));
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
	
	public Double getValues(int i) {
		return values.get(i);
	}
	
	public Double getLastValue() {
		return values.get(values.size()-1);
	}
	
	/////////////////////////////////


	public String operation_method(ArrayList<String> stringArray, HashMap<String, Object> valuesMap, int isStructure) {
		
		AC_Double tmpAC = (AC_Double)valuesMap.get(stringArray.get(0));
		Double left, right;
		AC_Double one = null, two = null;
		boolean b_left = false, b_right = false;
		if(valuesMap.containsKey(stringArray.get(2))) {
			b_left = true;
			one = (AC_Double)valuesMap.get(stringArray.get(2));
			left = one.attribution();
		}else {
			left = new Double(stringArray.get(2));
		}
		
		if(valuesMap.containsKey(stringArray.get(4))) {
			b_right = true;
			two = (AC_Double)valuesMap.get(stringArray.get(4));
			right = two.attribution();
		}else {
			right = new Double(stringArray.get(4));
		}
		
		tmpAC.values.add(checkAction(stringArray.get(3).charAt(0), left, right));
		
		StringBuilder sb;
		if(isStructure == 0)
			sb = new StringBuilder(tmpAC.testing_name + "(" + tmpAC.attribution() + ")" + " = ");
		else {
			sb = new StringBuilder("\t ");
			int count = 1;
			while(count<isStructure) {
				sb.append("\t ");
				count++;
			}
			sb.append(tmpAC.testing_name + "(" + tmpAC.attribution() + ")" + " = ");
		}
		
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
	
	private Double checkAction(char act, Double left, Double right){
		if(act =='-') {
			return substraction(left,right);
		}else if(act == '+') {
			return addition(left, right);
		}else if(act == '*') {
			return multiplication(left, right);
		}else{
			return division(left,right);
		}
	}

}
