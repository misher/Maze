import static org.junit.Assert.*;

import org.junit.Test;


public class PointTests {

	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

	public void pointTestEqual() {
		int x = 0;
		int y = 0;
		// MyClass is tested
		Point tester = new Point(x, y);
		Point example = new Point(0,0);
		Point exampleTwo = new Point(1,1);
		// assert statements
		assertEquals("equal true", true, tester.equals(example));
		assertEquals("equal false", false, tester.equals(exampleTwo));
	}
}


