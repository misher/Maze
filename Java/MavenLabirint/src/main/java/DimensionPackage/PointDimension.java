package DimensionPackage;


import java.util.ArrayList;

enum AbstractDirection {LEFT, RIGHT}

public class PointDimension{

		public final int dimension;
		public final ArrayList<Integer> axes = new ArrayList<Integer>();
		
		public PointDimension(int dimension, ArrayList<Integer> axes) {
			super();
			this.dimension = dimension;
			this.axes.addAll(axes);
		}
		
		public PointDimension(int dimension, int [] axesArray) {//Test for this constructor is missing
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
		
	
		public PointDimension getDirPoint(int numberOfAxis, AbstractDirection Dir) {//Better to rename dimension variable to something else, once dimension is treated by number of axis of Maze
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
			PointDimension returnPoint = new PointDimension(dimension, newPointAxes);
			return returnPoint;
		}
		
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof PointDimension)) {
				return false;
			}
			PointDimension pnt = (PointDimension) obj;
			ArrayList<Integer> iAxes = new ArrayList<Integer>();
			iAxes.addAll(axes);
			boolean axesEquals = iAxes.equals(pnt.axes);
			return axesEquals;
		}
}