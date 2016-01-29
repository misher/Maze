import static org.junit.Assert.*;

import org.junit.Test;


public class PassabilityOfLabiritntsTest {

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
		StrategyRecursion startTestNonPassRecursion = new StrategyRecursion(mapNonPass);
		StrategyBifurcation startTestPassBif = new StrategyBifurcation(mapPass);
		StrategyBifurcation startTestNonPassBif = new StrategyBifurcation(mapNonPass);
		// assert statements
		Point start = new Point(2,0);
		Point moneyCoordinate = new Point(4,7);
		Point deadRes = new Point(-1,-1);		
		assertEquals("equal true", true, startTestPassRecursion.findNewWay(start));
		assertEquals("equal false", false, startTestNonPassRecursion.findNewWay(start));
		assertEquals("equal money coordinate point", moneyCoordinate, startTestPassBif.findNewWay(2,0));
		assertEquals("equal dead result point", deadRes, startTestNonPassBif.findNewWay(2,0));
	}
}


