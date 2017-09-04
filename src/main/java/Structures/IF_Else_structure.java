package Structures;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.*;
import Main_app.Main_app_interface.InnerMain;
import Values_package.*;

public class IF_Else_structure implements Initialization, isNumeric{

	public void initialization (BufferedWriter bw, BufferedReader br, ArrayList<String> stringArray, HashMap<String, Object> valuesMap, int isStructure) throws IOException {
		bw.newLine();
		StringBuilder sb = new StringBuilder("IF-scope(");
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
		InnerMain ifElseInner = new InnerMain();
		ifElseInner.action(br, bw, ++isStructure);
		
	}
}
