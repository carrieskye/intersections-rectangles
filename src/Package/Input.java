package Package;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Input {
	private static Double myDouble;
	public static int algorithm;
	private static int numberOfRectangles;// = Main.numberOfRectangles;
	private static double[][] corners;
	private static List<Double> doubles = new ArrayList<Double>();

	public static void InputReader() throws FileNotFoundException {
		Scanner fileScanner = new Scanner(new FileReader(
				Main.inputLocation));
		while (fileScanner.hasNextDouble()) {
			doubles.add(fileScanner.nextDouble());
		}
		fileScanner.close();
	}

	public static double[][] randomInputGenerator(int numberOfAlgorithm,
			int wantedNumberOfRectangles) {
		Random random = new Random();
		//algorithm = numberOfAlgorithm;
		numberOfRectangles = wantedNumberOfRectangles;
		corners = new double[numberOfRectangles][4];
		for (int i = 0; i < numberOfRectangles; i++) {
			corners[i][0] = Math.floor((random.nextDouble() - 0.1) * 1000) / 1000;
			while (corners[i][0] < 0.1) {
				corners[i][0] = Math.floor((random.nextDouble() - 0.1) * 1000) / 1000;
			}
			corners[i][1] = Math.floor((random.nextDouble() - 0.1) * 1000) / 1000;
			while (corners[i][1] < 0.1) {
				corners[i][1] = Math.floor((random.nextDouble() - 0.1) * 1000) / 1000;
			}
			corners[i][2] = Math.floor((random.nextDouble() - 0.1) * 1000) / 1000;
			while (corners[i][2] <= corners[i][0]) {
				corners[i][2] = Math.floor((random.nextDouble() - 0.1) * 1000) / 1000;
			}
			corners[i][3] = Math.floor((random.nextDouble() - 0.1) * 1000) / 1000;
			while (corners[i][3] <= corners[i][1]) {
				corners[i][3] = Math.floor((random.nextDouble() - 0.1) * 1000) / 1000;
			}
		}
		System.out.println("Corners: " + Arrays.deepToString(corners));
		return corners;
	}

	public static double[][] InputVerwerker() {
		myDouble = Input.getList().get(0);
		algorithm = Integer.valueOf(myDouble.intValue());
		Input.getList().remove(0);

		myDouble = Input.getList().get(0);
		numberOfRectangles = Integer.valueOf(myDouble.intValue());
		Input.getList().remove(0);


		corners = new double[numberOfRectangles][4];
		int k = 0;
		for (int j = 0; j < numberOfRectangles; j++) {
			for (int i = 0; i < 4; i++) {
				corners[j][i] = doubles.get(k);
				k++;
			}
		}
		System.out.println("Corners: " + Arrays.deepToString(corners));
		return corners;
	}

	public static int getAlgorithm() {
		return algorithm;
	}

	public static List<Double> getList() {
		return doubles;
	}

	public static double[] getOneRectangle(int i) {
		double[] oneRectangle = new double[4];
		for (int j = 0; j < 4; j++) {
			oneRectangle[j] = corners[i][j];
		}
		return oneRectangle;
	}

	public static int getNumberOfRectangles() {
		return numberOfRectangles;
	}

	public static double getCorner(int i, int j) {
		return corners[i][j];
	}
}
