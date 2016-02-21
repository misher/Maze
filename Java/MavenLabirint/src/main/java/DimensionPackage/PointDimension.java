package DimensionPackage;
import java.util.ArrayList;

enum AbstractDirection {LEFT, RIGHT}

public class PointDimension{


//	Position pointPosition;
//
//	public PointDimension(int dimensionCount, int... coordinates) throws Exception {
//		this.pointPosition = new Position(dimensionCount, coordinates);
//	}
//	
//
//	private class Position {
//
//		int dimensionCount;
//
//		ArrayList<Integer> coordinates;
//
//		public Position(int dimensionCount, int... coordinates) throws Exception {
//			if (coordinates.length > dimensionCount) {
//				throw new Exception("ne nado tak");
//			}
//			this.dimensionCount = dimensionCount;
//			this.coordinates = new ArrayList<Integer>(dimensionCount);
//			for (Integer coordinate : coordinates) {
//				this.coordinates.add(coordinate);
//			}
//
//		}
//	}
//
//	public PointDimension getDirPoint(int dimension, AbstractDirection Dir) throws Exception {
//		if (dimension>pointPosition.dimensionCount){
//			return null;
//		}
//		ArrayList<Integer> coordOfNewPnt = new ArrayList<Integer>();
//		coordOfNewPnt.addAll(pointPosition.coordinates);
//		if (AbstractDirection.LEFT.equals(Dir)) {
//			int curCoord = coordOfNewPnt.get(dimension);
//			coordOfNewPnt.set(dimension, (curCoord-1));
//		}
//		if (AbstractDirection.RIGHT.equals(Dir)) {
//			int curCoord = coordOfNewPnt.get(dimension);
//			coordOfNewPnt.set(dimension, (curCoord+1));
//		}
//		int[] adapterArray;
//		adapterArray = new int[coordOfNewPnt.size()];
//		for (int i = 0; i<coordOfNewPnt.size(); i++){
//			adapterArray[i] = coordOfNewPnt.get(i);
//		}
//		PointDimension retPnt = new PointDimension(dimension, adapterArray);
//		return retPnt;
//	}


		
		
		
		public ArrayList<Integer> coordinates = new ArrayList<Integer>();
		
		public PointDimension(ArrayList<Integer> coordinates) {
			super();
			this.coordinates = coordinates;
		}
		
		public PointDimension(int [] coordArray) {//Test for this constructor is missing
			super();
			for (int i = 0; i < coordArray.length; i++){
				this.coordinates.add(coordArray[i]);
			}
		}
	
	
		public void setPoint(int dimension, int [] coordArray) {
			coordinates.clear();
			coordinates.add(dimension);//Why? what about responsibility?
			if (coordArray.length != dimension) {
				System.out.print("Size not match to quentity of coordinates, all missing coordinates will be field by null");//maybe it is better to throw exception
			}
			for (int i=0; i<coordArray.length; i++){
				coordinates.add(coordArray[i]);
			}
	
		}
		
	
		public int getCoordinate(int coord){
			return coordinates.get(coord);
		}//Better to remove coord to axis
		
		
		public int getDimension(){
			return coordinates.get(0);
		}
		
	
		public PointDimension getDirPoint(int dimension, AbstractDirection Dir) {//Better to rename dimension variable to something else, once dimension is treated by number of axis of Maze
			if (dimension>coordinates.get(0)){//Isn't point dont knows the dimension? Why you supply dimension?
				return null;
			}
			ArrayList<Integer> coordOfNewPnt = new ArrayList<Integer>();
			coordOfNewPnt.addAll(coordinates);
			if (AbstractDirection.LEFT.equals(Dir)) {
				int curCoord = coordOfNewPnt.get(dimension);
				coordOfNewPnt.set(dimension, (curCoord-1));
			}
			if (AbstractDirection.RIGHT.equals(Dir)) {
				int curCoord = coordOfNewPnt.get(dimension);
				coordOfNewPnt.set(dimension, (curCoord+1));
			}
			PointDimension retPnt = new PointDimension(coordOfNewPnt);
			return retPnt;
		}
}
