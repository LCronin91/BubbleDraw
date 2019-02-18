package bubbledraw;

import javax.swing.JFrame;

public class BubbleDrawApp extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) 
	{
		// Set up the frame for Bubble Draw app
		JFrame frame = new JFrame("Laura's Bubble Draw App");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new BubblePanel());
		
		frame.pack();
		frame.setVisible(true);
	}

}
