package Interfaces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface Action {

	default String action(BufferedWriter bw, ArrayList<String> stringArray, HashMap<String, Object> valuesMap, int isStructure) throws IOException {return null;}
	
	default void action(BufferedReader br, BufferedWriter bw, int isStructure, String t_str) {;}
}
