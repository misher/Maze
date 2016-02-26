package SomePackage;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import SomePackage.AbstractDirection;
import SomePackage.Point;

public class PointTest {

	

	int [] axesArray = {2,4,7};
	int [] axesArrayNonEqual = {2,4,7,8};
	Point testPointOriginalEqual = new Point(3, axesArray);
	Point testPointOriginalNonEqual = new Point(4, axesArrayNonEqual);

	
	@Test
	public void pointTestConstructor() {
		Point testPoint = new Point(3, axesArray);
		assertEquals("dimension", 3, testPoint.getDimension());
		assertEquals("first axis", 2, testPoint.getAxis(0));
		assertEquals("second axis", 4, testPoint.getAxis(1));
		assertEquals("third axis", 7, testPoint.getAxis(2));
	}
	
	@Test
	public void pointTestEqual() {
		// tests for overrided method equals
		Point testPoint = new Point(3, axesArray);
		assertEquals("equal true", true, testPoint.equals(testPointOriginalEqual));
		assertEquals("equal false", false, testPoint.equals(testPointOriginalNonEqual));
	}
	
	@Test
	public void getDirTest() {
		Point testPoint = new Point(3, axesArray);
		assertEquals("equal true", 1, testPoint.getDirPoint(0, AbstractDirection.LEFT).getAxis(0));
		assertEquals("equal true", 3, testPoint.getDirPoint(0, AbstractDirection.RIGHT).getAxis(0));
		assertEquals("equal true", 3, testPoint.getDirPoint(1, AbstractDirection.LEFT).getAxis(1));
		assertEquals("equal true", 5, testPoint.getDirPoint(1, AbstractDirection.RIGHT).getAxis(1));
		assertEquals("equal true", 6, testPoint.getDirPoint(2, AbstractDirection.LEFT).getAxis(2));
		assertEquals("equal true", 8, testPoint.getDirPoint(2, AbstractDirection.RIGHT).getAxis(2));
	}
	
	@Test
	public void pointGetNeighborPointTest() {
		Point testPoint = new Point(3, axesArray);
		ArrayList<IPoint> testArray = new ArrayList<IPoint>();
		testArray = testPoint.getNeighborPoints();
		assertEquals("compare of all points", testPoint.getDirPoint(0, AbstractDirection.RIGHT), testArray.get(0));
		assertEquals("compare of all points", testPoint.getDirPoint(0, AbstractDirection.LEFT), testArray.get(1));
		assertEquals("compare of all points", testPoint.getDirPoint(1, AbstractDirection.RIGHT), testArray.get(2));
		assertEquals("compare of all points", testPoint.getDirPoint(1, AbstractDirection.LEFT), testArray.get(3));
		assertEquals("compare of all points", testPoint.getDirPoint(2, AbstractDirection.RIGHT), testArray.get(4));
		assertEquals("compare of all points", testPoint.getDirPoint(2, AbstractDirection.LEFT), testArray.get(5));
//		boolean b = false;
//		for (int i = 0; i < testArray.size(); i++) {
//			if (b) {
//				assertEquals("compare of all points", testPoint.getDirPoint(i, AbstractDirection.RIGHT), testArray.get(i));
//			}
//			if (!b) {
//				assertEquals("compare of all points", testPoint.getDirPoint(i, AbstractDirection.LEFT), testArray.get(i));
//			}
//			b = !b;
//		}
	}
	
}




