package Package;

import java.util.ArrayList;

public class Algorithm1 extends SortAndClean {
	public static boolean intersectionsFound;
	public static int rectangleIndex;
	public static int index = 0;
	public static ArrayList<Point> allIntersections = new ArrayList<Point>();

	public static void goThroughVerticals() {
		intersections = new double[(int) (Math.pow(
				Input.getNumberOfRectangles(), 2) * 4)][2];
		intersectionsFound = false;
		for (int i = 0; i < Input.getNumberOfRectangles(); i++) {
			rectangleIndex = i;
			verticalCutsHorizontal(Input.getCorner(i, 0));
			verticalCutsHorizontal(Input.getCorner(i, 2));
		}
		/**
		 * Met deze if worden de waarden gesorteerd en de dubbels eruit gehaald.
		 * Optioneel, want dit verhoogt de uitvoertijd aanzienlijk.
		 * 
		 */
		if (intersectionsFound)
			cleanUpIntersections();

	}

	public static void verticalCutsHorizontal(double x) {
		// Voor x wordt gezocht naar horizontale lijn waarvan x tussen
		// het begin en eindpunt ligt. Dan wordt gekeken of de hoogte van
		// deze horizontale lijn tussen het begin en eindpunt van deze x-lijn
		// ligt.
		boolean betweenXCoordinates = false;
		double ymin = 0;
		double ymax = 0;
		for (int i = 0; i < Input.getNumberOfRectangles(); i++) {
			if (i != rectangleIndex) {
				if (x >= Input.getCorner(i, 0) && x <= Input.getCorner(i, 2)) {
					betweenXCoordinates = true;
					ymin = Input.getCorner(i, 1);
					ymax = Input.getCorner(i, 3);
				}
				if (betweenXCoordinates) {
					if (ymin >= Input.getCorner(rectangleIndex, 1)
							&& ymin <= Input.getCorner(rectangleIndex, 3)) {
						addIntersection(x, ymin);
					}
					if (ymax >= Input.getCorner(rectangleIndex, 1)
							&& ymax <= Input.getCorner(rectangleIndex, 3)) {
						addIntersection(x, ymax);
					}
				}
			}
		}
	}

	public static void addIntersection(double x, double y) {
		intersectionsFound = true;
		intersections[index][0] = x;
		intersections[index][1] = y;
		index++;
	}

	public static double[][] getIntersections() {
		return intersections;
	}

}