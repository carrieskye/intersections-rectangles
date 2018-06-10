package Package;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Rectangle {
	public Point LeftBottom;
	public Point RightTop;
	public int index;
	public int sortedIndex;

	static HashMap<Integer, Rectangle> rectangleHashMap = new HashMap<Integer, Rectangle>();

	public Rectangle(int i, Point p1, Point p2) {
		this.index = i;
		this.LeftBottom = p1;
		this.RightTop = p2;
		rectangleHashMap.put(i, this);
	}

	public Point getLeftBottom() {
		return LeftBottom;
	}

	public Point getRightTop() {
		return RightTop;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int i) {
		this.index = i;
	}

	public static Rectangle getRectangleByIndex(int index) {
		return (rectangleHashMap.get(index));
	}

	public static Comparator<Rectangle> RectangleComparator = new Comparator<Rectangle>() {
		public int compare(Rectangle rectangle1, Rectangle rectangle2) {
			Double x1 = rectangle1.getLeftBottom().getxCoordinate();
			Double x2 = rectangle2.getLeftBottom().getxCoordinate();
			return x1.compareTo(x2);
		}
	};

	public ArrayList<Point> compareTo(Rectangle rectangle) {
		ArrayList<Point> inters = new ArrayList<Point>();
		double R3minX = Math.max(rectangle.LeftBottom.getxCoordinate(),
				this.LeftBottom.getxCoordinate());
		double R3maxX = Math.min(rectangle.RightTop.getxCoordinate(),
				this.RightTop.getxCoordinate());
		double R3minY = Math.max(rectangle.LeftBottom.getyCoordinate(),
				this.LeftBottom.getyCoordinate());
		double R3maxY = Math.min(rectangle.RightTop.getyCoordinate(),
				this.RightTop.getyCoordinate());
		Point[] R3 = new Point[4];
		R3[0] = new Point(R3minX, R3minY);
		R3[1] = new Point(R3maxX, R3minY);
		R3[2] = new Point(R3minX, R3maxY);
		R3[3] = new Point(R3maxX, R3maxY);
		for (int i = 0; i < 4; i++) {
			if ((R3[i].getxCoordinate() != rectangle.LeftBottom.getxCoordinate() || R3[i].getyCoordinate() != rectangle.LeftBottom.getyCoordinate())
					&& (R3[i].getxCoordinate() != rectangle.RightTop.getxCoordinate() || R3[i].getyCoordinate() != rectangle.LeftBottom.getyCoordinate())
					&& (R3[i].getxCoordinate() != rectangle.LeftBottom.getxCoordinate() || R3[i].getyCoordinate() != rectangle.RightTop.getyCoordinate())
					&& (R3[i].getxCoordinate() != rectangle.RightTop.getxCoordinate() || R3[i].getyCoordinate() != rectangle.RightTop.getyCoordinate())
					&& (R3[i].getxCoordinate() != this.LeftBottom.getxCoordinate() || R3[i].getyCoordinate() != this.LeftBottom.getyCoordinate())
					&& (R3[i].getxCoordinate() != this.RightTop.getxCoordinate() || R3[i].getyCoordinate() != this.LeftBottom.getyCoordinate())
					&& (R3[i].getxCoordinate() != this.LeftBottom.getxCoordinate() || R3[i].getyCoordinate() != this.RightTop.getyCoordinate())
					&& (R3[i].getxCoordinate() != this.RightTop.getxCoordinate() || R3[i].getyCoordinate() != this.RightTop.getyCoordinate())) {
				inters.add(R3[i]);
			}
		}
		return inters;
	}
}
