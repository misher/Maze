import static org.junit.Assert.*;

import org.junit.Test;



public class PointTest {


	
	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	
	public void pointTestEqual() {
		int x = 0;
		int y = 0;
		// Some points
		Point tester = new Point(x, y);
		Point example = new Point(0,0);
		Point exampleTwo = new Point(1,1);
		Point testPoint = new Point(5,5);
		Point expectedLeft = new Point(4,5);
		Point expectedTop = new Point(5,4);
		Point expectedRight = new Point(6,5);
		Point expectedBottom = new Point(5,6);
		// tests for overrided method equals
		assertEquals("equal true", true, tester.equals(example));
		assertEquals("equal false", false, tester.equals(exampleTwo));
		// test for neighbors points
		assertEquals("equal left", expectedLeft, testPoint.getLeftPoint());
		assertEquals("equal top", expectedTop, testPoint.getTopPoint());
		assertEquals("equal rigth", expectedRight, testPoint.getRightPoint());
		assertEquals("equal bottom", expectedBottom, testPoint.getBottomPoint());
		// test for enum method
		assertEquals("equal left", expectedLeft, testPoint.getDirPoint(Direction.left));
	}
}


