package Main_app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import Interfaces.*;
import Structures.IF_Else_structure;
import Values_package.*;

public class Main_app_interface {
	
	private static HashMap<String, Object> expressionAction = new HashMap<>();
	
	public static HashMap<String, Object> valuesMap = new HashMap<>();//? extends Initialization<Object>
	
	public static void fileSystem(final String fileName) {
		
		try (BufferedReader readingStream = new BufferedReader(new FileReader(fileName))){
			
			try (BufferedWriter writerStream = new BufferedWriter(new FileWriter("testOutput.txt"))){
				String streamString = null;
				
				while((streamString = readingStream.readLine() )!=null) {
					
					if(streamString.contains("int main()")){
						writerStream.write("+++Main+++");
						InnerMain inner = new InnerMain();
						inner.action(readingStream, writerStream, 0);
					}
					
			}
				
			}catch (IOException e){
				e.printStackTrace();
			}
			
		}catch (IOException e){
			e.printStackTrace();
		} 
		System.out.println(valuesMap.size());
	}
	
	public static ArrayList<String> partingString(String inputString) {
		StringTokenizer st = new StringTokenizer(inputString);
		ArrayList <String> returningArray= new ArrayList<>();
		
		while(st.hasMoreTokens()) {
			returningArray.add(st.nextElement().toString());
		}
		
		returningArray.set(returningArray.size()-1, returningArray.get(returningArray.size()-1).substring(0, returningArray.get(returningArray.size()-1).length() - 1));
		
		return returningArray;
	}
	
	public static class InnerMain {
		
		public void action(BufferedReader br, BufferedWriter bw, int isStructure) {
			
			String streamString;
			ArrayList <String> stringArray = new ArrayList<>();
			int openBrace=1,closedBrace=0;
			try {
			
			while((streamString = br.readLine() ) != null) {
				
				if(streamString.contains("return"))
					break;
				
				if(isStructure!=0) {
					if(streamString.contains("{") && (openBrace-closedBrace<1)) {
						openBrace++;
					}else if (streamString.contains("}")) {
						closedBrace++;
					}
				}
				
				if(openBrace==closedBrace)
					break;
				
				stringArray = partingString(streamString);
				
				if(expressionAction.containsKey(stringArray.get(0))) {
					if(!((Initialization) expressionAction.get(stringArray.get(0))).initialization(bw, stringArray, valuesMap)) {
						((Initialization) expressionAction.get(stringArray.get(0))).initialization(bw, br, stringArray, valuesMap, isStructure++);
					}
				}else if(valuesMap.containsKey(stringArray.get(0))) {
					((Action) valuesMap.get(stringArray.get(0))).action(bw, stringArray, valuesMap, isStructure);;
				}else {
					
				}
				
			}
			
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String []args) {
		expressionAction.put("int", new AC_Integer());
		expressionAction.put("if", new IF_Else_structure());
		
		fileSystem("test.txt");
	}
}

/*<dependencies>

<!-- https://mvnrepository.com/artifact/junit/junit -->
	<dependency>
  		<groupId>jUnit</groupId>
 		 <artifactId>jUnit</artifactId>
  		<version>4.12</version>
  		<scope>test</scope>
	</dependency> 

</dependencies>*/
