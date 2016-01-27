

import java.util.ArrayList;


public class StrategyBifurcation {

	public char[][] map;
	
	public StrategyBifurcation(char[][] map){
		super();
		this.map = map;
	}


	public Point findNewWay(int startX, int startY) {
		
		// Creating points of running
		Point currentPoint = new Point(startX, startY);
		Point previousPoint = new Point(-1, -1);
		Point moneyPoint = new Point(-1, -1);

		// Initialization of bifurcation points array		
		ArrayList<Point> pointsOfBifurcation = new ArrayList<Point>();

		// Calculate sizes of map
		int borderY = map.length;
		int borderX = map[0].length; 
		
		// Print to screen sizes of map
		System.out.println("Size of map x: "+borderY);
		System.out.println("Size of map y: "+borderX);

		// Initialization of trace array
		ArrayList<Point> traceList = new ArrayList<Point>();
		
		// Initialization of bifurcation calculating counters
		int bifurcationDelta = 0;

		// Initialization of boolean constants
		boolean stateOfWhile = true;
		boolean state1, state2, state3, state4;
		boolean stateMoney1 = false;
		boolean stateMoney2 = false;
		boolean stateMoney3 = false;
		boolean stateMoney4 = false;

		
		// Loop for finding moneys
		while(stateOfWhile == true){
			
			// To null all automate states
			state1 = false;
			state2 = false;
			state3 = false;
			state4 = false;
			
			Point bifurcationPoint = new Point(-1, -1);
			
			// Look out for a new ways
			if ((currentPoint.x+1) < (borderX)) {
				stateMoney1 = (map[currentPoint.y][currentPoint.x+1] == '$');
				state1 = (((currentPoint.x+1) != previousPoint.x) & (map[currentPoint.y][currentPoint.x+1] == '0'));
			}
			if ((currentPoint.y+1) < (borderY)) {
				stateMoney2 = (map[currentPoint.y+1][currentPoint.x] == '$');
				state2 = (((currentPoint.y+1) != previousPoint.y) & (map[currentPoint.y+1][currentPoint.x] == '0'));
			}
			if ((currentPoint.x-1) != -1) {
				stateMoney3 = (map[currentPoint.y][currentPoint.x-1] == '$');
				state3 = (((currentPoint.x-1) != previousPoint.x) & (map[currentPoint.y][currentPoint.x-1] == '0'));
			}
			if ((currentPoint.y-1) != -1) {
				stateMoney4 = (map[currentPoint.y-1][currentPoint.x] == '$');
				state4 = (((currentPoint.y-1) != previousPoint.y) & (map[currentPoint.y-1][currentPoint.x] == '0'));
			}
			
	
			// Find bifurcation point
			if ((state1 & state2) | (state1 & state3) | (state1 & state4) | (state2 & state3) | (state2 & state4) | (state3 & state4)){
//				bifurcationPoint.setX(currentPoint.x);
//				bifurcationPoint.setY(currentPoint.y);
				bifurcationPoint = currentPoint;
				pointsOfBifurcation.add(bifurcationPoint);
				bifurcationDelta = 1;
			}

			// Moneys founded in state 1
			if (stateMoney1 == true){
				System.out.println("Moneys are found 1"+"\n"+"Coordinates: "+"\n"+(currentPoint.x+1)+" "+(currentPoint.y));
				stateOfWhile = false;
//				moneyPoint.x = currentPoint.x+1;
//				moneyPoint.y = currentPoint.y;
				moneyPoint = currentPoint.getRightPoint();
			}
			
			// Moneys founded in state 2
			if (stateMoney2 == true){
				System.out.println("Moneys are found 2"+"\n"+"Coordinates: "+"\n"+(currentPoint.x)+" "+(currentPoint.y+1));
				stateOfWhile = false;
//				moneyPoint.x = currentPoint.x;
//				moneyPoint.y = currentPoint.y+1;
				moneyPoint = currentPoint.getBottomPoint();
			}
			
			// Moneys founded in state 3
			if (stateMoney3 == true){
				System.out.println("Moneys are found 2"+"\n"+"Coordinates: "+"\n"+(currentPoint.x-1)+" "+(currentPoint.y));
				stateOfWhile = false;
//				moneyPoint.x = currentPoint.x-1;
//				moneyPoint.y = currentPoint.y;
				moneyPoint = currentPoint.getLeftPoint();
			}
			
			// Moneys founded in state 4
			if (stateMoney4 == true){
				System.out.println("Moneys are found 2"+"\n"+"Coordinates: "+"\n"+(currentPoint.x)+" "+(currentPoint.y-1));
				stateOfWhile = false;
//				moneyPoint.x = currentPoint.x;
//				moneyPoint.y = currentPoint.y-1;
				moneyPoint = currentPoint.getTopPoint();
			}

			// Way state 1 handler
			if (state1 == true){
				if (bifurcationPoint.x == currentPoint.x){
					map[currentPoint.y][currentPoint.x+1]='X';
				}
				traceList.add(currentPoint);
				previousPoint = currentPoint;
				currentPoint = currentPoint.getRightPoint();
//				previousPoint.setX(currentPoint.x); 
//				previousPoint.setY(currentPoint.y); 
//				currentPoint.setX(currentPoint.x+1);
//				currentPoint.setY(currentPoint.y);
				state2 = false;
				state3 = false;
				state4 = false;
			}

			// Way state 2 handler
			if (state2 == true){
				if (bifurcationPoint.x == currentPoint.x){
					map[currentPoint.y+1][currentPoint.x]='X';
				}
				traceList.add(currentPoint);
				previousPoint = currentPoint;
				currentPoint = currentPoint.getBottomPoint();
//				previousPoint.setX(currentPoint.x); 
//				previousPoint.setY(currentPoint.y); 
//				currentPoint.setX(currentPoint.x);
//				currentPoint.setY(currentPoint.y+1);
				state1 = false;
				state3 = false;
				state4 = false;
			}

			// Way state 3 handler
			if (state3 == true){
				if (bifurcationPoint.x == currentPoint.x){
					map[currentPoint.y][currentPoint.x-1]='X';
				}
				traceList.add(currentPoint);
				previousPoint = currentPoint;
				currentPoint = currentPoint.getLeftPoint();
//				previousPoint.setX(currentPoint.x); 
//				previousPoint.setY(currentPoint.y); 
//				currentPoint.setX(currentPoint.x-1);
//				currentPoint.setY(currentPoint.y);
				state1 = false;
				state2 = false;
				state4 = false;
			}

			// Way state 4 handler
			if (state4 == true){
				if (bifurcationPoint.x == currentPoint.x){
					map[currentPoint.y-1][currentPoint.x]='X';
				}
				traceList.add(currentPoint);
				previousPoint = currentPoint;
				currentPoint = currentPoint.getTopPoint();
//				previousPoint.setX(currentPoint.x); 
//				previousPoint.setY(currentPoint.y); 
//				currentPoint.setX(currentPoint.x);
//				currentPoint.setY(currentPoint.y-1);
				state1 = false;
				state2 = false;
				state3 = false;
			}

			// If hit a dead end
			if ((state1 == false) & (state2 == false) & (state3 == false) & (state4 == false) & (stateMoney1 == false) & (stateMoney2 == false) & (stateMoney3 == false) & (stateMoney4 == false)){
				if (pointsOfBifurcation.size() > 0){
					bifurcationPoint = pointsOfBifurcation.get(pointsOfBifurcation.size()-bifurcationDelta);
//					currentPoint.x = bifurcationPoint.x;
//					currentPoint.y = bifurcationPoint.y;
					currentPoint = bifurcationPoint;
				}
				if (pointsOfBifurcation.size() == bifurcationDelta) {
					Point resultPoint = new Point(-1,-1);
					System.out.println("Labirinit has not ways to money");
					return resultPoint;
				}
				bifurcationDelta++;
			}
		}
		return moneyPoint;
	}
}
