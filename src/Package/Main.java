package Package;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class Main {
	public static String inputLocation;
	public static int numberOfRectangles;

	public static void main(String[] a) throws FileNotFoundException {
		/**
		 * Run dient voor het lezen van een textfile met invoer. RandomRun
		 * genereert een meegegeven aantal rechthoeken (tweede argument) en
		 * voert hier een bepaald algoritme op uit (eerste argument).
		 */
		// inputLocation =
		// "/Users/carolynepeelman/Downloads/invoerrechthoeken.txt";
		// Output.run();
		numberOfRectangles = Integer.parseInt(JOptionPane
				.showInputDialog("Please enter the number of rectangles:"));
		Screen.makeStartScreen();
	}

}
