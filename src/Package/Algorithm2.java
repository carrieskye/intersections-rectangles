package Package;

import java.util.ArrayList;
import java.util.Comparator;

public class Algorithm2 {
	public static int numberOfRectangles = Input.getNumberOfRectangles();
	public static int intersectionsIndex = 0;
	public static boolean intersectionsFound = false;
	public static double[][] leftCoordinates = new double[numberOfRectangles][2];
	public static double[][] rightCoordinates = new double[numberOfRectangles][2];
	public static double[][] lowerCoordinates;
	public static double[][] upperCoordinates;
	public static double[][] sortedXCoordinates = new double[numberOfRectangles * 2][3];
	public static double[][] sortedYCoordinates = new double[numberOfRectangles * 2][3]; // in
																							// T
	public static Point[] intersections;
	public static Rectangle[] allRectangles = new Rectangle[numberOfRectangles];
	public static ArrayList<Rectangle> rectanglesBeingInspected = new ArrayList<Rectangle>();
	public static Rectangle rect;
	public static Point[] pLeftBottom = new Point[numberOfRectangles * 2];
	public static Point[] pRightTop = new Point[numberOfRectangles * 2];
	public static ArrayList<Point> allIntersections = new ArrayList<Point>();

	public static void run() {
		intersections = new Point[(int) (Math.pow(
				Input.getNumberOfRectangles(), 2) * 4)];
		makeObjects();
		makeSortedListOfXCoords();
		executeAlgorithm();
	}
	

	public static boolean executeAlgorithm() {
		for (int x = 0; x < sortedXCoordinates.length; x++) {
			rect = Rectangle
					.getRectangleByIndex((int) sortedXCoordinates[x][1]);
			if (sortedXCoordinates[x][2] == 0) {
				doIntersect();
				addRectangle(rect);
			}
			if (sortedXCoordinates[x][2] == 1) {
				delete(rect);
				doIntersect();
			}
		}
		if (doIntersect()) {
			return true;
		}
		return false;
	}

	public static void makeObjects() {
		for (int i = 0; i < Input.getNumberOfRectangles(); i++) {
			pLeftBottom[i] = new Point(Input.getCorner(i, 0),
					Input.getCorner(i, 1));
			pRightTop[i] = new Point(Input.getCorner(i, 2),
					Input.getCorner(i, 3));
			allRectangles[i] = new Rectangle(i, pLeftBottom[i],
					pRightTop[i]);
		}
	}

	public static void makeSortedListOfXCoords() {
		for (int i = 0; i < Input.getNumberOfRectangles(); i++) {
			leftCoordinates[i][0] = pLeftBottom[i].getxCoordinate();
			leftCoordinates[i][1] = i;
			rightCoordinates[i][0] = pRightTop[i].getxCoordinate();
			rightCoordinates[i][1] = i;
		}

		sort(leftCoordinates);
		sort(rightCoordinates);

		int j = 0;
		int k = 0;
		for (int i = 0; i < sortedXCoordinates.length; i++) {
			if (j < leftCoordinates.length && k < rightCoordinates.length) {
				if (leftCoordinates[j][0] <= rightCoordinates[k][0]) {
					addNextCoordinate(sortedXCoordinates,i, leftCoordinates[j][0],
							leftCoordinates[j][1], 0);
					j++;
				} else {
					addNextCoordinate(sortedXCoordinates,i, rightCoordinates[k][0],
							rightCoordinates[k][1], 1);
					k++;
				}
			} else if (j == leftCoordinates.length) {
				addNextCoordinate(sortedXCoordinates,i, rightCoordinates[k][0],
						rightCoordinates[k][1], 1);
				k++;
			} else if (k == rightCoordinates.length) {
				addNextCoordinate(sortedXCoordinates,i, leftCoordinates[j][0],
						leftCoordinates[j][1], 0);
				j++;
			}
		}
	}
	
	public static void sortRectanglesBeingInspected() {
		double lowerCoordinates[][] = new double[rectanglesBeingInspected.size()][2];
		double upperCoordinates[][] = new double[rectanglesBeingInspected.size()][2];
		for (int i = 0; i < rectanglesBeingInspected.size(); i++) {
			lowerCoordinates[i][0] = pLeftBottom[i].getyCoordinate();
			lowerCoordinates[i][1] = i;
			upperCoordinates[i][0] = pRightTop[i].getyCoordinate();
			upperCoordinates[i][1] = i;

		}
		sort(lowerCoordinates);
		sort(upperCoordinates);
		int j = 0;
		int k = 0;
		for (int i = 0; i < rectanglesBeingInspected.size(); i++) {
			if (j < lowerCoordinates.length && k < upperCoordinates.length) {
				if (lowerCoordinates[j][0] <= upperCoordinates[k][0]) {
					addNextCoordinate(sortedYCoordinates, i, lowerCoordinates[j][0],
							lowerCoordinates[j][1], 0);
					j++;
				} else {
					addNextCoordinate(sortedYCoordinates, i, upperCoordinates[k][0],
							upperCoordinates[k][1], 1);
					k++;
				}
			} else if (j == lowerCoordinates.length) {
				addNextCoordinate(sortedYCoordinates, i, upperCoordinates[k][0],
						upperCoordinates[k][1], 1);
				k++;
			} else if (k == upperCoordinates.length) {
				addNextCoordinate(sortedYCoordinates, i, lowerCoordinates[j][0],
						lowerCoordinates[j][1], 0);
				j++;
			}
		}
	}
	
	public static void addNextCoordinate(double[][] coordinates, int i, double a, double b, double c) {
		coordinates[i][0] = a;
		coordinates[i][1] = b;
		coordinates[i][2] = c;
	}
	
	public static boolean doIntersect(){
		for (int i = 0; i < rectanglesBeingInspected.size(); i++) {
			if (!(rectanglesBeingInspected.get(i).getLeftBottom().getyCoordinate() > rect
					.getRightTop().getyCoordinate() || rectanglesBeingInspected.get(i)
					.getRightTop().getyCoordinate() < rect
					.getLeftBottom().getyCoordinate())) {
				ArrayList<Point> list = rect.compareTo(rectanglesBeingInspected.get(i));
				if (list.size() != 0) {
					for (int j = 0; j < list.size(); j++) {
						if (checkIfUnique(list.get(j))) {
							allIntersections.add(list.get(j));
							intersectionsFound = true;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkIfUnique(Point point) {
		for (int i = 0; i < allIntersections.size(); i++) {
			if (point.getxCoordinate() == allIntersections.get(i).getxCoordinate() 
					&& point.getyCoordinate() == allIntersections.get(i).getyCoordinate()){
				return false;
			}
		}
		return true;
	}

	public static void addRectangle(Rectangle R) {
		int i = 0;
		while (i < rectanglesBeingInspected.size()) {
			if (R.getLeftBottom().getxCoordinate() > rectanglesBeingInspected.get(i).getLeftBottom()
					.getxCoordinate()) {
				i++;
			} else {
				break;
			}
		}
		rectanglesBeingInspected.add(i, R);
		sortRectanglesBeingInspected();
	}

	public static void delete(Rectangle R) {
		int i = 0;
		while (i < rectanglesBeingInspected.size()) {
			if (R.getIndex() != rectanglesBeingInspected.get(i).getIndex()) {
				i++;
			} else {
				break;
			}
		}
		int k[] = { i };
		for (int j = k.length - 1; j >= 0; j--) {
			rectanglesBeingInspected.remove(k[j]);
		}
	}

	public static void addIntersection(Point p) {
		intersections[intersectionsIndex] = p;
		intersectionsIndex++;
	}
	
	public static void sort(double[][] coordinates){
		java.util.Arrays.sort(coordinates, new Comparator<double[]>() {
			public int compare(double[] o1, double[] o2) {
				if (o1[0] == o2[0]) {
					return Double.compare(o1[1], o2[1]);
				} else {
					return Double.compare(o1[0], o2[0]);
				}
			}
		});
	}

}