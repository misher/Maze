import java.util.ArrayList;

enum abstractDirection {left, right}

public class PointDimension{
	
	ArrayList<Integer> coordinates = new ArrayList<Integer>();
	
	public PointDimension(ArrayList<Integer> coordinates) {
		super();
		this.coordinates = coordinates;
	}
	
	public PointDimension(int [] coordArray) {
		super();
		for (int i = 0; i < coordArray.length; i++){
			this.coordinates.add(coordArray[i]);
		}
	}


	public void setPoint(int dimension, int [] coordArray, int value) {
		coordinates.clear();
		coordinates.add(dimension);
		if (coordArray.length != dimension) {
			System.out.print("Size not match to quentity of coordinates, all missing coordinates will be field by null");
		}
		for (int i=0; i<coordArray.length; i++){
			coordinates.add(coordArray[i]);
		}
		coordinates.add(value);
	}
	

	public int getCoordinate(int coord){
		return coordinates.get(coord);
	}
	
	
	public int getDimension(){
		return coordinates.get(0);
	}
	

	public PointDimension getDirPoint(int dimension, abstractDirection Dir) {
		if (dimension>coordinates.get(0)){
			return null;
		}
		ArrayList<Integer> coordOfNewPnt = new ArrayList<Integer>();
		coordOfNewPnt = coordinates;
		if (abstractDirection.left.equals(Dir)) {
			int curCoord = coordOfNewPnt.get(dimension);
			coordOfNewPnt.set(dimension, (curCoord-1));
		}
		if (abstractDirection.right.equals(Dir)) {
			int curCoord = coordOfNewPnt.get(dimension);
			coordOfNewPnt.set(dimension, (curCoord+1));
		}
		PointDimension retPnt = new PointDimension(coordOfNewPnt);
		return retPnt;
	}
}
