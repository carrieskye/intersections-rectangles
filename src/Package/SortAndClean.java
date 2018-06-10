package Package;

import java.util.Comparator;

public class SortAndClean {
	public static double[][] intersections;
	public static double[][] intersectionsNoZeros;
	public static double[][] intersectionsNoDoubles;
	
	public static void cleanUpIntersections(){
		returnAllIntersections();
		sortAllIntersections();
		deleteDoubleIntersections();
	}
	
	public static void returnAllIntersections() {
		intersectionsNoZeros = new double[Algorithm1.index][2];
		for (int i = 0; i < Algorithm1.index; i++) {
			intersectionsNoZeros[i][0] = intersections[i][0];
			intersectionsNoZeros[i][1] = intersections[i][1];
		}
	}

	public static void sortAllIntersections() {
		java.util.Arrays.sort(intersectionsNoZeros, new Comparator<double[]>() {
			public int compare(double[] o1, double[] o2) {
				if (o1[0] == o2[0]) {
					return Double.compare(o1[1], o2[1]);
				} else {
					return Double.compare(o1[0], o2[0]);
				}
			}
		});
	}

	public static void deleteDoubleIntersections() {
		intersectionsNoDoubles = new double[Algorithm1.index][2];
		int j = 0;
		int k = 0;
		for (int i = 0; i < Algorithm1.index; i++) {
			if (i > 0) {
				if (!(intersectionsNoZeros[i][0] == intersectionsNoZeros[i - 1][0] && intersectionsNoZeros[i][1] == intersectionsNoZeros[i - 1][1])
					) {
					intersectionsNoDoubles[j][0] = intersectionsNoZeros[i][0];
					intersectionsNoDoubles[j][1] = intersectionsNoZeros[i][1];
					j++;
				} else {
					k++;
				}
			} else if (i == 0) {
				intersectionsNoDoubles[j][0] = intersectionsNoZeros[0][0];
				intersectionsNoDoubles[j][1] = intersectionsNoZeros[0][1];
				j++;
			}
		}
		intersections = new double[Algorithm1.index - k][2];
		for (int i = 0; i < Algorithm1.index - k; i++) {
			intersections[i][0] = intersectionsNoDoubles[i][0];
			intersections[i][1] = intersectionsNoDoubles[i][1];
		}
	}	

}