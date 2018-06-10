package Package;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class Output {
	public static double timeElapsed;
	public static int algorithm;
	public static int intersectionsFound = 0;

	public static void run() throws FileNotFoundException {
		Input.InputReader();
		Input.InputVerwerker();
		algorithm = Input.getAlgorithm();
		Screen.makeScreen();

		Stopwatch timer = new Stopwatch();
		timer.start();

		if (algorithm == 1) {
			Algorithm1.goThroughVerticals();
			timeElapsed = timer.stop();
			System.out.println("Time elapsed: " + timeElapsed + " ms");
			if (Algorithm1.intersectionsFound) {
				printOutput();
				intersectionsFound = Algorithm1.getIntersections().length;
				System.out.println("Number of intersections: "
						+ Algorithm1.getIntersections().length);
				System.out.println("Intersections: "
						+ Arrays.deepToString(Algorithm1.getIntersections()));
			} else {
				System.out.println("There are no intersections.");
			}
		}

		else if (algorithm == 2) {
			Algorithm2.run();
			timeElapsed = timer.stop();

			System.out.println("Time elapsed: " + timeElapsed + " ms");
			if (Algorithm2.intersectionsFound) {
				printOutput();
				intersectionsFound = Algorithm2.allIntersections.size();
				System.out.println("Number of intersections: "
						+ intersectionsFound);
				System.out.print("Intersections: [");
				for (int i = 0; i < Algorithm2.allIntersections.size(); i++) {
					System.out.print("["
							+ Algorithm2.allIntersections.get(i)
									.getxCoordinate()
							+ ", "
							+ Algorithm2.allIntersections.get(i)
									.getyCoordinate() + "]");
					if (i < Algorithm2.allIntersections.size() - 1) {
						System.out.print(", ");
					}
				}
				System.out.print("]");
			} else {
				System.out.println("There are no intersections.");
			}
		}

		else {
			System.out.println("???");
		}
	}

	public static void randomRun(int k, int l) throws FileNotFoundException {
		Input.randomInputGenerator(k, Main.numberOfRectangles);
		Stopwatch timer = new Stopwatch();
		timer.start();
		algorithm = k;

		if (algorithm == 1) {
			Algorithm1.goThroughVerticals();
			timeElapsed = timer.stop();

			System.out.println("Time elapsed: " + timeElapsed + " ms");
			if (Algorithm1.intersectionsFound) {
				printOutput();
				intersectionsFound = Algorithm1.getIntersections().length;
				System.out.println("Number of intersections: "
						+ Algorithm1.getIntersections().length);
				System.out.println("Intersections: "
						+ Arrays.deepToString(Algorithm1.getIntersections()));
			} else {
				System.out.println("There are no intersections.");
			}
		}

		else if (algorithm == 2) {
			Algorithm2.run();
			timeElapsed = timer.stop();

			System.out.println("Time elapsed: " + timeElapsed + " ms");
			if (Algorithm2.intersectionsFound) {
				printOutput();
				intersectionsFound = Algorithm2.allIntersections.size();
				System.out.println("Number of intersections: "
						+ intersectionsFound);
				System.out.print("Intersections: [");
				for (int i = 0; i < Algorithm2.allIntersections.size(); i++) {
					System.out.print("["
							+ Algorithm2.allIntersections.get(i)
									.getxCoordinate()
							+ ", "
							+ Algorithm2.allIntersections.get(i)
									.getyCoordinate() + "]");
					if (i < Algorithm2.allIntersections.size() - 1) {
						System.out.print(", ");
					}
				}
				System.out.print("]");
			} else {
				System.out.println("There are no intersections.");
			}
		}

		else {
			System.out.println("???");
		}
		// Screen.makeScreen();
	}

	public static void printOutput() {
		try {
			PrintStream out = new PrintStream(
					new FileOutputStream("output_R"
							+ Input.getNumberOfRectangles() + "_A" + algorithm
							+ ".txt"));
			if (algorithm == 1) {
				for (int k = 0; k < Algorithm1.getIntersections().length; k++) {
					out.println(Algorithm1.getIntersections()[k][0] + " "
							+ Algorithm1.getIntersections()[k][1]);
				}
			}
			if (algorithm == 2) {
				for (int k = 0; k < Algorithm2.allIntersections.size(); k++) {
					out.println(Algorithm2.allIntersections.get(k)
							.getxCoordinate()
							+ " "
							+ Algorithm2.allIntersections.get(k)
									.getyCoordinate());
				}
			}
			out.println();
			out.println(timeElapsed);
			out.close();
		} catch (FileNotFoundException e) {
		}
	}
}
