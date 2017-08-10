package Anti_Crib_main;

import java.util.Map;
import java.io.PrintWriter;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

public class MainProgram {
	
	
	private Map <String, AC_Double>doubleMap = new HashMap <String, AC_Double>();
	private Map <String, AC_String>stringMap = new HashMap <String, AC_String>();
	
	public static HashMap<String, DefaultClass> constExpression = new HashMap<>();
	
	public String checkCalss(String checkString){
		
		ArrayList <String> st_list = new ArrayList<String>();
		st_list = partingStringFunction(checkString);
		
		if(constExpression.containsKey(st_list.get(0))){
			return constExpression.get(st_list.get(0)).equalsExpression(st_list);
		}else{
			return "true";
		}
		
		/*if(checkString.contains("int")){
			if(integerMap.isEmpty()){
				if(mountOfEquals(st_list) == 0)
					integerMap.put(st_list.get(1), new AC_Integer(0, st_list.get(1)));
				else if(mountOfEquals(st_list) == 1)
					integerMap.put(st_list.get(1), new AC_Integer(Integer.parseInt(st_list.get(3)), st_list.get(1)));
				else{
					//pusta zaleznosc do zrobienia w przyszlosci
				}
			}else{
				if(mountOfEquals(st_list) == 0)
					integerMap.put(st_list.get(1), new AC_Integer(0, st_list.get(1)));
				else if(mountOfEquals(st_list) == 1)
					integerMap.put(st_list.get(1), new AC_Integer(Integer.parseInt(st_list.get(3)), st_list.get(1)));
				else{
					
				}
			}
		}else if(checkString.contains("double")){
			
		}else if(checkString.contains("String")){
			
		}*/
	}
	
	public int mountOfEquals(ArrayList <String> equalsList){
		int counter = 0;
		for(String st : equalsList){
			if(st.equals("="))
				counter++;
		}
		return counter;
	}
	
	private ArrayList <String> partingStringFunction(String insertingString){
		ArrayList<String> returning = new ArrayList<String>();
		StringTokenizer stTok = new StringTokenizer(insertingString);
		while(stTok.hasMoreTokens()){
			returning.add(stTok.nextToken());
		}
		return returning;
	}
	
	public void readFile(String fileName){
		
		BufferedReader br = null;
		FileReader fr = null;
		PrintWriter inputFileStream = null;
	
		try{
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			inputFileStream = new PrintWriter("AntiCribTest.txt");
			String dataString = null;
			while((dataString = br.readLine()) != null){
				inputFileStream.println(checkCalss(dataString));
			}
			
		}catch( IOException e ){
			e.printStackTrace();
		}finally{
			
			try{
				
				if( br!=null ){
					br.close();
				}
			
				if( fr!=null ){
					fr.close();
				}
				
				if(inputFileStream!=null){
					inputFileStream.close();
				}
				
			}catch(IOException e){
				//e.printStackTrace();
			}
		}
	}

	public static void main(String []args){
		
		constExpression.put("int", new AC_Integer());
		constExpression.put("double", new AC_Double());
		constExpression.put("String", new AC_String());
		//constExpression.put("cout");
		//constExpression.put("printf");
	}

}
