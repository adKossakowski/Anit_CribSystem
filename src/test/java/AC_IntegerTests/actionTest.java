package AC_IntegerTests;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import Values_package.AC_Integer;

public class actionTest {

	@Test
	public void test() throws IOException {
		try (BufferedWriter writerStream = new BufferedWriter(new FileWriter("testAction.txt"))){
		AC_Integer test = new AC_Integer("a", 13);
		HashMap <String,Object> hashMap = new HashMap<>();
		hashMap.put("b", new AC_Integer("b",12));
		ArrayList<String> stringArray = new ArrayList<>();
		stringArray.add("a");
		stringArray.add("++");
		assertEquals(new Integer(13),test.getLastValue());
		assertEquals("\tintVal0(14)++", test.action(writerStream, stringArray, null, 1));
		stringArray.clear();
		assertTrue(stringArray.isEmpty());
		stringArray.add("a");
		stringArray.add("--");
		assertEquals("\tintVal0(13)--", test.action(writerStream, stringArray, null, 1));
		stringArray.clear();
		assertTrue(stringArray.isEmpty());
		stringArray.add("++");
		stringArray.add("a");
		assertEquals("\tintVal0(14)++", test.action(writerStream, stringArray, null, 1));
		stringArray.clear();
		assertTrue(stringArray.isEmpty());
		stringArray.add("--");
		stringArray.add("a");
		assertEquals("\tintVal0(13)--", test.action(writerStream, stringArray, null, 1));
		//test for stringArray ==3
		hashMap.put("a", test);
		stringArray.clear();
		assertTrue(stringArray.isEmpty());
		stringArray.add("a");
		stringArray.add("=");
		stringArray.add("2");
		assertEquals("\tintVal0(2) = 2", test.action(writerStream, stringArray, hashMap, 1));
		stringArray.clear();
		assertTrue(stringArray.isEmpty());
		stringArray.add("a");
		stringArray.add("=");
		stringArray.add("b");
		assertEquals("\tintVal0(12) = intVal1(12)", test.action(writerStream, stringArray, hashMap, 1));
		//test for else
		hashMap.put("c", new AC_Integer("c",11));
		stringArray.add("+");
		stringArray.add("c");
		assertEquals("\t intVal0(23) = intVal1(12)+intVal2(11)", test.action(writerStream, stringArray, hashMap, 1));
		}catch (IOException e){
			e.printStackTrace();
		}
		
	}

}
