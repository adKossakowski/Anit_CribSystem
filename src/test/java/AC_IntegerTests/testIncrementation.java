package AC_IntegerTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Values_package.AC_Integer;

public class testIncrementation {

	@Test
	public void test() {
		AC_Integer test = new AC_Integer("test", 0);
		
		assertEquals("0", test.getLastValue().toString());
		test.incrementation();
		assertEquals("1", test.getLastValue().toString());
		test.decrementation();
		assertEquals("0", test.getLastValue().toString());
	}

}
