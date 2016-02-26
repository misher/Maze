package SomePackage;

import static org.junit.Assert.assertEquals;





import org.junit.Test;

import SomePackage.MapMaze;
import SomePackage.Point;
import SomePackage.StrategyRecursion;

public class PassabilityTest {
	
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

	StrategyRecursion startTestRecursion = new StrategyRecursion();
	StrategyRecursion startTestBifurcation = new StrategyRecursion();
	
	int [] startPointArray = {2,0};
	Point sp = new Point(2, startPointArray);
	
	MapMaze mapMazePass = new MapMaze(mapPass);
	MapMaze mapMazeNonPass = new MapMaze(mapNonPass);


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
