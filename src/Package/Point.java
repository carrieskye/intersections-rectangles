package Package;

public class Point {
	private double xCoordinate;
	private double yCoordinate;
	
	public Point(double x, double y) {
		this.xCoordinate = x;
		this.yCoordinate = y;
	}

	public double getxCoordinate() {
		return xCoordinate;
	}

	public double getyCoordinate() {
		return yCoordinate;
	}

	public void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
}