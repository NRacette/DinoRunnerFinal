package game;

import javax.swing.*;
import java.awt.*;

class UserInterface {
	JFrame frame = new JFrame("Dino Run");

	public static int width = 800;
	public static int height = 500;

	public void showGui() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = frame.getContentPane();

		UserPanel panel = new UserPanel();
		panel.addKeyListener(panel);
		panel.setFocusable(true);

		container.setLayout(new BorderLayout());
		container.add(panel, BorderLayout.CENTER);

		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new UserInterface().showGui();
			}
		});
	}
}