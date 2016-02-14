import static org.junit.Assert.*;
import org.junit.Test;



public class PointTest {

	int x = 0;
	int y = 0;
	Point tester = new Point(x, y);
	Point example = new Point(0,0);
	Point exampleTwo = new Point(1,1);
	Point testPoint = new Point(5,5);
	Point expectedLeft = new Point(4,5);
	Point expectedTop = new Point(5,4);
	Point expectedRight = new Point(6,5);
	Point expectedBottom = new Point(5,6);


	@Test
	public void pointTestEqual() {
		// tests for overrided method equals
		assertEquals("equal true", true, tester.equals(example));
		assertEquals("equal false", false, tester.equals(exampleTwo));
	}


	@Test
	public void pointTestNeighbours() {
		// test for neighbors points
		assertEquals("equal left", expectedLeft, testPoint.getLeftPoint());
		assertEquals("equal top", expectedTop, testPoint.getTopPoint());
		assertEquals("equal rigth", expectedRight, testPoint.getRightPoint());
		assertEquals("equal bottom", expectedBottom, testPoint.getBottomPoint());
	}


	@Test
	public void pointTestEnum() {
		// test for enum method
		assertEquals("equal left", expectedLeft, testPoint.getDirPoint(Direction.left));
	}

}


