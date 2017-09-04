package Interfaces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface Initialization {

	default boolean initialization(BufferedWriter bw, ArrayList<String> stringArray, HashMap<String, Object> valuesMap) throws IOException {return false;}
	
	default String initialization(ArrayList<String> stringArray, HashMap<String, Object> valuesMap, int isStructure) {return null;}
	
	default void initialization (BufferedWriter bw, BufferedReader br, ArrayList<String> stringArray, HashMap<String, Object> valuesMap, int isStructure) throws IOException{}
}
