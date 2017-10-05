package AC_IntegerTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Values_package.AC_Integer;

public class testIncrementation {

	@Test
	public void test() {
		AC_Integer testInc = new AC_Integer("test", 0);
		
		assertEquals("0", testInc.getLastValue().toString());
		testInc.incrementation();
		assertEquals("1", testInc.getLastValue().toString());
		testInc.decrementation();
		assertEquals("0", testInc.getLastValue().toString());
	}

}
