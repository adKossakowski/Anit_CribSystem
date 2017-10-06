package Structures;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.*;
import Main_app.Main_app_interface;
import Values_package.*;

public class IF_Else_structure implements Initialization {
	
	private static HashMap<String,ArrayList<String>> if_structMap = new HashMap<>();
	private static final String basedName = "IFElseStruct";
	private static int strCount = 0;
	
	
	public void initialization (BufferedWriter bw, BufferedReader br, ArrayList<String> stringArray, HashMap<String, Object> valuesMap, int isStructure) throws IOException {
		bw.newLine();
		StringBuilder sb = new StringBuilder("");
		if(stringArray.get(0).contains("else")) {
			sb.append("Else");
		}else {
			sb.append("IF-scope(");
		
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
		}
		bw.write(sb.toString());
		InnerStructure ifElseInner = new InnerStructure();
		String t_str = basedName+strCount;
		if_structMap.put(t_str,new ArrayList<String>());
		if_structMap.get(t_str).add(sb.toString());
		ifElseInner.action(br, bw, ++isStructure, t_str);
		
	}
	
	public static class InnerStructure implements Action {
		
		public void action(BufferedReader br, BufferedWriter bw, int isStructure, String t_str) {
			
			String streamString;
			ArrayList <String> stringArray = new ArrayList<>();
			int openBrace=0,closedBrace=0;
			try {
			
			while((streamString = br.readLine() ) != null) {
			
				if(isStructure!=0) {
					if(streamString.contains("{")) {
						openBrace++;
						continue;
					}else if (streamString.contains("}")) {
						closedBrace++;
					}
				}
				
				if(openBrace==closedBrace)
					break;
				
				stringArray = Main_app_interface.partingString(streamString);
				
				if(Main_app_interface.expressionAction.containsKey(stringArray.get(0))) {
					if(!((Initialization) Main_app_interface.expressionAction.get(stringArray.get(0))).initialization(bw, stringArray, Main_app_interface.valuesMap)) {
						((Initialization) Main_app_interface.expressionAction.get(stringArray.get(0))).initialization(bw, br, stringArray, Main_app_interface.valuesMap, isStructure++);
					}
				}else if(Main_app_interface.valuesMap.containsKey(stringArray.get(0))) {
					if_structMap.get(t_str).add(((Action) Main_app_interface.valuesMap.get(stringArray.get(0))).action(bw, stringArray, Main_app_interface.valuesMap, isStructure));
				}else {
					
				}
				
			}
			
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
