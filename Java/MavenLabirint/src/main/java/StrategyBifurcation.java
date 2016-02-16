import java.util.ArrayList;


public class StrategyBifurcation {


	public char[][] map;
	
	
	public StrategyBifurcation(char[][] map){
		super();
		this.map = map;
	}


	public Point findNewWay(IMaze maze, Point sp) {
		
		// Creating points of running
		Point currentPoint = sp;
		Point previousPoint = new Point(-1, -1);
		Point moneyPoint = new Point(-1, -1);
		Point bifurcationPoint = new Point(-1, -1);

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
		int iterLoopVal = 0;
		int dirCounter = 0;
		int hitCounter = 0;

		// Initialization of boolean constants
		boolean stateOfWhile = true;
		boolean stateCorRoad; // state1, state2, state3, state4;
		boolean stateMoney = false;
//		boolean stateMoney1 = false;
//		boolean stateMoney2 = false;
//		boolean stateMoney3 = false;
//		boolean stateMoney4 = false;

		
		// Loop for finding moneys
		while(stateOfWhile == true){
			
			// To null all automate states
//			state1 = false;
//			state2 = false;
//			state3 = false;
//			state4 = false;
			
//			Point bifurcationPoint = new Point(-1, -1);
			
			for (Direction dir : Direction.values()) {
				dirCounter++;
				// Look out for a new ways
				stateMoney = maze.isTargetPoint(sp);
				Point huy = currentPoint.getDirPoint(dir);
				stateCorRoad = ((currentPoint.getDirPoint(dir) != previousPoint) && (maze.isRoadPoint(currentPoint.getDirPoint(dir))));
				if (stateCorRoad == true){
					iterLoopVal++;
				} 
				if (stateCorRoad == false){
					hitCounter++;
				}
				// Find bifurcation point
				if (iterLoopVal > 1) {
					bifurcationPoint = currentPoint;
					pointsOfBifurcation.add(bifurcationPoint);
					bifurcationDelta = 1;
				}
				// Moneys founded
				if (stateMoney == true){
					System.out.println("Moneys are found "+"\n"+"Coordinates: "+"\n"+(currentPoint.x+1)+" "+(currentPoint.y));
					stateOfWhile = false;
					moneyPoint = currentPoint.getDirPoint(dir);
				}
				// Way state handler
				if (stateCorRoad == true){
					if (bifurcationPoint.equals(currentPoint)){
						map[currentPoint.getDirPoint(dir).getY()][currentPoint.getDirPoint(dir).getX()]='X';
					}
					traceList.add(currentPoint);
					previousPoint = currentPoint;
					currentPoint = currentPoint.getDirPoint(dir);
					hitCounter = 0;
				}
				// If hit a dead end
				if ((maze.isRoadPoint(currentPoint.getDirPoint(dir)) == false) && (hitCounter == 4)){
					if (pointsOfBifurcation.size() > 0){
						bifurcationPoint = pointsOfBifurcation.get(pointsOfBifurcation.size()-bifurcationDelta);
						currentPoint = bifurcationPoint;
					}
					if (pointsOfBifurcation.size() == bifurcationDelta) {
						Point resultPoint = new Point(-1,-1);
						System.out.println("Labirinit has not ways to money");
						return resultPoint;
					}
					bifurcationDelta++;
				}
				if (dirCounter == 4){
					dirCounter = 0;
					iterLoopVal = 0;
					hitCounter = 0;
				}
			}
			
			
//			// Look out for a new ways
//			if ((currentPoint.x+1) < (borderX)) {
//				stateMoney1 = (map[currentPoint.y][currentPoint.x+1] == '$');
//				state1 = (((currentPoint.x+1) != previousPoint.x) & (map[currentPoint.y][currentPoint.x+1] == '0'));
//			}
//			if ((currentPoint.y+1) < (borderY)) {
//				stateMoney2 = (map[currentPoint.y+1][currentPoint.x] == '$');
//				state2 = (((currentPoint.y+1) != previousPoint.y) & (map[currentPoint.y+1][currentPoint.x] == '0'));
//			}
//			if ((currentPoint.x-1) != -1) {
//				stateMoney3 = (map[currentPoint.y][currentPoint.x-1] == '$');
//				state3 = (((currentPoint.x-1) != previousPoint.x) & (map[currentPoint.y][currentPoint.x-1] == '0'));
//			}
//			if ((currentPoint.y-1) != -1) {
//				stateMoney4 = (map[currentPoint.y-1][currentPoint.x] == '$');
//				state4 = (((currentPoint.y-1) != previousPoint.y) & (map[currentPoint.y-1][currentPoint.x] == '0'));
//			}
//			
//	
//			// Find bifurcation point
//			if ((state1 & state2) | (state1 & state3) | (state1 & state4) | (state2 & state3) | (state2 & state4) | (state3 & state4)){
//				bifurcationPoint = currentPoint;
//				pointsOfBifurcation.add(bifurcationPoint);
//				bifurcationDelta = 1;
//			}
//
//			// Moneys founded in state 1
//			if (stateMoney1 == true){
//				System.out.println("Moneys are found 1"+"\n"+"Coordinates: "+"\n"+(currentPoint.x+1)+" "+(currentPoint.y));
//				stateOfWhile = false;
//				moneyPoint = currentPoint.getRightPoint();
//			}
//			
//			// Moneys founded in state 2
//			if (stateMoney2 == true){
//				System.out.println("Moneys are found 2"+"\n"+"Coordinates: "+"\n"+(currentPoint.x)+" "+(currentPoint.y+1));
//				stateOfWhile = false;
//				moneyPoint = currentPoint.getBottomPoint();
//			}
//			
//			// Moneys founded in state 3
//			if (stateMoney3 == true){
//				System.out.println("Moneys are found 2"+"\n"+"Coordinates: "+"\n"+(currentPoint.x-1)+" "+(currentPoint.y));
//				stateOfWhile = false;
//				moneyPoint = currentPoint.getLeftPoint();
//			}
//			
//			// Moneys founded in state 4
//			if (stateMoney4 == true){
//				System.out.println("Moneys are found 2"+"\n"+"Coordinates: "+"\n"+(currentPoint.x)+" "+(currentPoint.y-1));
//				stateOfWhile = false;
//				moneyPoint = currentPoint.getTopPoint();
//			}
//
//			// Way state 1 handler
//			if (state1 == true){
//				if (bifurcationPoint.x == currentPoint.x){
//					map[currentPoint.y][currentPoint.x+1]='X';
//				}
//				traceList.add(currentPoint);
//				previousPoint = currentPoint;
//				currentPoint = currentPoint.getRightPoint();
//				state2 = false;
//				state3 = false;
//				state4 = false;
//			}
//
//			// Way state 2 handler
//			if (state2 == true){
//				if (bifurcationPoint.x == currentPoint.x){
//					map[currentPoint.y+1][currentPoint.x]='X';
//				}
//				traceList.add(currentPoint);
//				previousPoint = currentPoint;
//				currentPoint = currentPoint.getBottomPoint();
//				state1 = false;
//				state3 = false;
//				state4 = false;
//			}
//
//			// Way state 3 handler
//			if (state3 == true){
//				if (bifurcationPoint.x == currentPoint.x){
//					map[currentPoint.y][currentPoint.x-1]='X';
//				}
//				traceList.add(currentPoint);
//				previousPoint = currentPoint;
//				currentPoint = currentPoint.getLeftPoint();
//				state1 = false;
//				state2 = false;
//				state4 = false;
//			}
//
//			// Way state 4 handler
//			if (state4 == true){
//				if (bifurcationPoint.x == currentPoint.x){
//					map[currentPoint.y-1][currentPoint.x]='X';
//				}
//				traceList.add(currentPoint);
//				previousPoint = currentPoint;
//				currentPoint = currentPoint.getTopPoint();
//				state1 = false;
//				state2 = false;
//				state3 = false;
//			}
//
//			// If hit a dead end
//			if ((state1 == false) & (state2 == false) & (state3 == false) & (state4 == false) & (stateMoney1 == false) & (stateMoney2 == false) & (stateMoney3 == false) & (stateMoney4 == false)){
//				if (pointsOfBifurcation.size() > 0){
//					bifurcationPoint = pointsOfBifurcation.get(pointsOfBifurcation.size()-bifurcationDelta);
//					currentPoint = bifurcationPoint;
//				}
//				if (pointsOfBifurcation.size() == bifurcationDelta) {
//					Point resultPoint = new Point(-1,-1);
//					System.out.println("Labirinit has not ways to money");
//					return resultPoint;
//				}
//				bifurcationDelta++;
//			}
		}
		return moneyPoint;
	}
}
