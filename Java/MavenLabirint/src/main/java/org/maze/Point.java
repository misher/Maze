package org.maze;


import java.util.ArrayList;


enum AbstractDirection {LEFT, RIGHT}

public class Point implements IPoint{

	public final int dimension;
	public final ArrayList<Integer> axes = new ArrayList<Integer>();

	public Point(int dimension, ArrayList<Integer> axes) {
		super();
		this.dimension = dimension;
		this.axes.addAll(axes);
	}

	public Point(int dimension, int [] axesArray) {//Test for this constructor is missing
		super();
		this.dimension = dimension;
		this.axes.clear();
		for (int i = 0; i < axesArray.length; i++){
			this.axes.add(axesArray[i]);
		}
	}


	public int getAxis(int axis){
		return axes.get(axis);
	}//Better to remove coord to axis


	public int getDimension(){
		return dimension;
	}


	public Point getDirPoint(int numberOfAxis, AbstractDirection Dir) {//Better to rename dimension variable to something else, once dimension is treated by number of axis of Maze
		if (numberOfAxis > dimension){//Isn't point dont knows the dimension? Why you supply dimension?
			return null;
		}
		ArrayList<Integer> newPointAxes = new ArrayList<Integer>();
		newPointAxes.addAll(axes);
		if (AbstractDirection.LEFT.equals(Dir)) {
			int currentAxis = newPointAxes.get(numberOfAxis);
			newPointAxes.set(numberOfAxis, (currentAxis-1));
		}
		if (AbstractDirection.RIGHT.equals(Dir)) {
			int currentAxis = newPointAxes.get(numberOfAxis);
			newPointAxes.set(numberOfAxis, (currentAxis+1));
		}
		Point returnPoint = new Point(dimension, newPointAxes);
		return returnPoint;
	}

	public ArrayList<IPoint> getNeighborPoints() {
		ArrayList<IPoint> neighborPointList = new ArrayList<IPoint>();
		for (int i = 0; i < this.dimension; i++) {
			ArrayList<Integer> newPointAxes = new ArrayList<Integer>();
			newPointAxes.addAll(this.axes);
			newPointAxes.set(i, this.axes.get(i)+1);
			Point addPoint = new Point (this.dimension, newPointAxes);
			neighborPointList.add(addPoint);
			newPointAxes.set(i, this.axes.get(i)-1);
			Point addPointSec = new Point (this.dimension, newPointAxes);
			neighborPointList.add(addPointSec);
		}
		return neighborPointList;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Point)) {
			return false;
		}
		Point pnt = (Point) obj;
		ArrayList<Integer> iAxes = new ArrayList<Integer>();
		iAxes.addAll(axes);
		boolean axesEquals = iAxes.equals(pnt.axes);
		return axesEquals;
	}

	@Override
	public int hashCode() {
		int returnValue = 0;
		for (int i = 0; i < this.axes.size(); i++){
			returnValue = returnValue*10;
			returnValue = returnValue + (this.axes.get(i));
		}
		returnValue = returnValue*10 + this.dimension;
		return returnValue;
	}

}