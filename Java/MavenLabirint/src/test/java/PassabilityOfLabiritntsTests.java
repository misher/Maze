import static org.junit.Assert.*;

import org.junit.Test;


public class PassabilityOfLabiritntsTests {

	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

	public void pointTestEqual() {
		// Draw some maps
		char[][] mapPass =	{{'1', '1', '0', '1', '1', '1', '1', '1', '1'},
							 {'1', '0', '0', '0', '0', '0', '0', '0', '1'},
							 {'1', '1', '1', '1', '0', '1', '1', '1', '1'},
							 {'1', '1', '1', '1', '0', '0', '0', '0', '1'},
							 {'1', '1', '0', '0', '0', '1', '0', '1', '1'},
							 {'1', '1', '0', '1', '0', '1', '0', '0', '1'},
							 {'1', '1', '0', '1', '0', '1', '1', '1', '1'},
							 {'1', '1', '1', '1', '$', '1', '1', '1', '1'}};
		
		char[][] mapNonPass = {{'1', '1', '0', '1', '1', '1', '1', '1', '1'},
							   {'1', '0', '0', '0', '0', '0', '0', '0', '1'},
							   {'1', '1', '1', '1', '0', '1', '1', '1', '1'},
							   {'1', '1', '1', '1', '0', '0', '0', '0', '1'},
							   {'1', '1', '0', '0', '0', '1', '0', '1', '1'},
							   {'1', '1', '0', '1', '0', '1', '0', '0', '1'},
							   {'1', '1', '0', '1', '0', '1', '1', '1', '1'},
							   {'1', '1', '1', '1', '1', '1', '1', '1', '1'}};
		
		// MyClass is tested
		StrategyRecursion startTestPassRecursion = new StrategyRecursion(mapPass);
//		StrategyRecursion startTestNonPassRecursion = new StrategyRecursion(mapNonPass);
		// assert statements
		Point start = new Point(2,0);
		assertEquals("equal true", true, startTestPassRecursion.findNewWay(start));
//		assertEquals("equal false", false, startTestNonPassRecursion.findNewWay(2, 0));
	}
}


