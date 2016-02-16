import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PassabilityOfLabiritntsTest {
	
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

	private StrategyRecursion startTestPassRecursion = new StrategyRecursion();
	private StrategyRecursion startTestNonPassRecursion = new StrategyRecursion();
	private StrategyBifurcation startTestPassBif = new StrategyBifurcation(mapPass);
	private StrategyBifurcation startTestNonPassBif = new StrategyBifurcation(mapNonPass);
	
	private Point start = new Point(2,0);
	private Point moneyCoordinate = new Point(4,7);
	private Point deadRes = new Point(-1,-1);	
	
	MapMaze recMapPassMaze = new MapMaze(mapPass);
	MapMaze recMapNonPassMaze = new MapMaze(mapNonPass);

	
	@Test
	public void pointTestRecPass() {	
		// assert statements	
		assertEquals("equal true", true, startTestPassRecursion.findNewWay(recMapPassMaze, start));
	}
	
	@Test
	public void pointTestRecNonPass() {	
		// assert statements	
		assertEquals("equal false", false, startTestNonPassRecursion.findNewWay(recMapNonPassMaze, start));
	}
	
	@Test
	public void pointTestBifPass() {	
		// assert statements	
		assertEquals("equal money coordinate point", moneyCoordinate, startTestPassBif.findNewWay(2,0));
	}
	
	@Test
	public void pointTestBifNonPass() {	
		// assert statements	
		assertEquals("equal dead result point", deadRes, startTestNonPassBif.findNewWay(2,0));
	}
}


