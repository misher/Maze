package DimensionPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointDemensionTest {

	

	int [] axesArray = {2,4,7};
	int [] axesArrayNonEqual = {2,4,7,8};
	PointDimension testPointOriginalEqual = new PointDimension(3, axesArray);
	PointDimension testPointOriginalNonEqual = new PointDimension(4, axesArrayNonEqual);

	
	@Test
	public void pointDimensionTestConstructor() {
		PointDimension testPoint = new PointDimension(3, axesArray);
		assertEquals("dimension", 3, testPoint.getDimension());
		assertEquals("first axis", 2, testPoint.getAxis(0));
		assertEquals("second axis", 4, testPoint.getAxis(1));
		assertEquals("third axis", 7, testPoint.getAxis(2));
	}
	
	@Test
	public void pointDimensionTestEqual() {
		// tests for overrided method equals
		PointDimension testPoint = new PointDimension(3, axesArray);
		assertEquals("equal true", true, testPoint.equals(testPointOriginalEqual));
		assertEquals("equal false", false, testPoint.equals(testPointOriginalNonEqual));
	}
	
	@Test
	public void getDirDimansionTest() {
		PointDimension testPoint = new PointDimension(3, axesArray);
		assertEquals("equal true", 1, testPoint.getDirPoint(0, AbstractDirection.LEFT).getAxis(0));
		assertEquals("equal true", 3, testPoint.getDirPoint(0, AbstractDirection.RIGHT).getAxis(0));
		assertEquals("equal true", 3, testPoint.getDirPoint(1, AbstractDirection.LEFT).getAxis(1));
		assertEquals("equal true", 5, testPoint.getDirPoint(1, AbstractDirection.RIGHT).getAxis(1));
		assertEquals("equal true", 6, testPoint.getDirPoint(2, AbstractDirection.LEFT).getAxis(2));
		assertEquals("equal true", 8, testPoint.getDirPoint(2, AbstractDirection.RIGHT).getAxis(2));
	}
	
}




