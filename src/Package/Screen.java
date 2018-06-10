package Package;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Screen extends JComponent {

	private double lox;
	private double loy;
	private double rbx;
	private double rby;

	public void paint(Graphics g) {
		for (int i = 0; i < Input.getNumberOfRectangles(); i++) {
			lox = Input.getOneRectangle(i)[0] * 300;
			loy = Input.getOneRectangle(i)[1] * 300;
			rbx = Input.getOneRectangle(i)[2] * 300;
			rby = Input.getOneRectangle(i)[3] * 300;
			double width = rbx - lox;
			double height = rby - loy;
			g.drawRect((int) lox, (int) loy, (int) width, (int) height);
			g.drawString("Number of intersections found: " + Output.intersectionsFound  + ".", 300, 50);	
			g.drawString("Intersections found in " + Output.timeElapsed + " milliseconds.", 300, 70);
		}
	}
	
	public static void makeStartScreen() {
		final JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(0, 0, 300, 300);
		window.setLocationRelativeTo(null);
		
		JButton b1 = new JButton("Algorithm 1");
		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Input.algorithm = 1;
				try {
					Output.randomRun(1, 50);
					makeScreen();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				window.dispose();
			}
		});
		b1.setLocation(100, 100);
		b1.setSize(100, 20);
		JButton b2 = new JButton("Algorithm 2");
		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Input.algorithm = 2;		
				try {
					Output.randomRun(2, 50);
					makeScreen();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				window.dispose();
			}
		});
		b2.setLocation(100, 130);
		b2.setSize(100, 20);
	
		window.add(b1);
		window.add(b2);
		window.getContentPane().add(new Screen());
		window.setResizable(false);
		window.setVisible(true);
		window.setTitle("");
	}
	
	public static void makeScreen() throws FileNotFoundException {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(0, 0, 550, 400);
		window.setLocationRelativeTo(null);
		
		window.getContentPane().add(new Screen());
		window.setResizable(false);
		window.setVisible(true);
		window.setTitle("Rechthoeken");
	
		//window.pack();
	}
}

