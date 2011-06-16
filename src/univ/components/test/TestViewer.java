package univ.components.test;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TestViewer {
	
	public static void showOnFrame(JComponent c) {
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(c);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}