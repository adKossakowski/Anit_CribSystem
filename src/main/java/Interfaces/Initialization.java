package Interfaces;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface Initialization {

	default void initialization(BufferedWriter bf, ArrayList<String> stringArray, HashMap<String, Object> valuesMap) throws IOException {}
	
	default String initialization(ArrayList<String> stringArray, HashMap<String, Object> valuesMap) {return null;}
}
