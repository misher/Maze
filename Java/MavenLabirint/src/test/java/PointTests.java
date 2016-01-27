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
		Point testPoint = new Point(5,5);
		Point expectedLeft = new Point(4,5);
		Point expectedTop = new Point(5,4);
		Point expectedRight = new Point(6,5);
		Point expectedBottom = new Point(5,6);
		// assert statements
		assertEquals("equal true", true, tester.equals(example));
		assertEquals("equal false", false, tester.equals(exampleTwo));
		assertEquals("equal left", expectedLeft, testPoint.getLeftPoint());
		assertEquals("equal top", expectedTop, testPoint.getTopPoint());
		assertEquals("equal rigth", expectedRight, testPoint.getRightPoint());
		assertEquals("equal bottom", expectedBottom, testPoint.getBottomPoint());
	}
}


