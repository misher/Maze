

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
	
	
	@Override
	public boolean equals(Object obj) {
		Point pnt = (Point) obj;
		Integer ix = new Integer(this.x);
		Integer iy = new Integer(this.y);
		return (ix.equals(pnt.x) & iy.equals(pnt.y)); 
	}
	
	@Override
	public String toString() {
		return "("+x+"; "+y+")";
	}
}
