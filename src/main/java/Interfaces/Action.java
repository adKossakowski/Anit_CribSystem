package Interfaces;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface Action {

	String action(BufferedWriter bw, ArrayList<String> stringArray, HashMap<String, Object> valuesMap, int isStructure) throws IOException ;
	
}
