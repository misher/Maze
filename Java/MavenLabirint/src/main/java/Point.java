

public class Point {

	public int x;
	public int y;
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		Point pnt = (Point) obj;
		Integer ix = new Integer(this.x);
		Integer iy = new Integer(this.y);
		return (ix.equals(pnt.x) & iy.equals(pnt.y)); 
	}
}
