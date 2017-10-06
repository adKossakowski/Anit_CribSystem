package Structures;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.Action;
import Interfaces.Compare;
import Interfaces.Initialization;
import Main_app.Main_app_interface;
import Structures.IF_Else_structure.InnerStructure;
import Values_package.AC_Integer;

public class Loop_structure implements Initialization {
	
	private static HashMap<String,ArrayList<String>> loop_structMap = new HashMap<>();
	private static final String basedName = "LoopStruct";
	private static int strCount = 0;
	
	@SuppressWarnings({ "unlikely-arg-type" })
	public void initialization (BufferedWriter bw, BufferedReader br, ArrayList<String> stringArray, HashMap<String, Object> valuesMap, int isStructure) throws IOException {
		bw.newLine();
		StringBuilder sb = new StringBuilder("");
		if(stringArray.get(0).equals("while")) {
			
			bw.append("while(");
			
			if(stringArray.toString().contains("||") || stringArray.toString().contains("&&")) {
			
			}else {
				Integer int1, int2;
				Double doub1, doub2;
				if(valuesMap.containsKey(stringArray.get(2))) {
					sb.append(((AC_Integer)valuesMap.get(stringArray.get(2))).getTesting_name());
				}else {
				
					try {
					
						int1 = Integer.parseInt(stringArray.get(2));
						sb.append(Integer.toString(int1));
					
					} catch(NumberFormatException e) {
						try {
							doub1 = Double.parseDouble(stringArray.get(2));
							sb.append(Double.toString(doub1));
						}catch(NumberFormatException e2) {
						
						}
					}
				}
				sb.append(stringArray.get(3));
				if(valuesMap.containsKey(stringArray.get(4))) {
					sb.append(((AC_Integer) valuesMap.get(stringArray.get(4))).getTesting_name() + ")");
				}else {
				
					try {
					
						int2 = Integer.parseInt(stringArray.get(4));
						sb.append(Integer.toString(int2) + ")");
					}catch(NumberFormatException e) {
						try {
							doub2 = Double.parseDouble(stringArray.get(4));
							sb.append(Double.toString(doub2) + ")");
						}catch(NumberFormatException e2) {
						
						}
					}
				}
			}
			bw.write(sb.toString());
			
		}else if(stringArray.get(0).equals("do{") || stringArray.get(0).equals("do")) {
			
		}else if(stringArray.get(0).equals("for")){
			sb.append("for ( ");
			sb.append(((Initialization) valuesMap.get(2)).initialization((ArrayList<String>)stringArray.subList(2,5), valuesMap, isStructure) + " ;");
			sb.append(((Compare) valuesMap.get(6)).comparing(stringArray.subList(6, 12), valuesMap) + " ;");
			sb.append(((Action) valuesMap.get(10)).action(bw, (ArrayList<String>)stringArray.subList(10,12), valuesMap, isStructure) + " )");
		}
		
		InnerStructure loopInner = new InnerStructure();
		String t_str = basedName+strCount;
		loop_structMap.put(t_str,new ArrayList<String>());
		loop_structMap.get(t_str).add(sb.toString());
		loopInner.action(br, bw, ++isStructure, t_str);
	}

}
