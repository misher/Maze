package DimensionPackage;

import static org.junit.Assert.assertEquals;




import org.junit.Test;

public class PassabilityDimensionTest {
	
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

	StrategyRecursionDimension startTestRecursion = new StrategyRecursionDimension();
	StrategyRecursionDimension startTestBifurcation = new StrategyRecursionDimension();
	
	int [] startPointArray = {2,0};
	PointDimension sp = new PointDimension(2, startPointArray);
	
	MapMazeDimension mapMazePass = new MapMazeDimension(mapPass);
	MapMazeDimension mapMazeNonPass = new MapMazeDimension(mapNonPass);


	@Test
	public void pointTestRecPass() {	
		assertEquals("equal true", true, startTestRecursion.findNewWay(mapMazePass, sp));
	}
	
	@Test
	public void pointTestRecNonPass() {		
		assertEquals("equal false", false, startTestRecursion.findNewWay(mapMazeNonPass, sp));
	}
	
	@Test
	public void pointTestBifPass() {	
		assertEquals("equal true", true, startTestBifurcation.findNewWay(mapMazePass, sp));
	}
	
	@Test
	public void pointTestBifNonPass() {		
		assertEquals("equal false", false, startTestBifurcation.findNewWay(mapMazeNonPass, sp));
	}
}
