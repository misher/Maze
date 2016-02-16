enum Direction { left, top, right, bottom  } //TODO: rename enum fields with CAPITAL_CASE


public class Point {

	public final int x;
	public final int y;


	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}


	public Point getLeftPoint() {
		Point pointLeft = new Point (this.x-1, this.y);
		return pointLeft;
	}
	public Point getTopPoint() {
		Point pointTop = new Point (this.x, this.y-1);
		return pointTop;
	}
	public Point getRightPoint() {
		Point pointRight = new Point (this.x+1, this.y);
		return pointRight;
	}
	public Point getBottomPoint() {
		Point pointBottom = new Point (this.x, this.y+1);
		return pointBottom;
	}


	public Point getDirPoint(Direction dir) {
		Point retPnt = new Point(0,0);
		if (Direction.left.equals(dir)) {
			retPnt = getLeftPoint();
		}
		if (Direction.top.equals(dir)) {
			retPnt = getTopPoint();
		}
		if (Direction.right.equals(dir)) {
			retPnt = getRightPoint();
		}
		if (Direction.bottom.equals(dir)) {
			retPnt = getBottomPoint();
		}
		return retPnt;
		//throw new RuntimeException("Programming Error");
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Point)) {
			return false;
		}
		Point pnt = (Point) obj;
		Integer ix = new Integer(this.x);
		Integer iy = new Integer(this.y);
		boolean xEquals = ix.equals(pnt.x);
		boolean yEquals = iy.equals(pnt.y);
		return (xEquals && yEquals);
	}

	@Override
	public String toString() {
		return "("+x+"; "+y+")";
	}
}
