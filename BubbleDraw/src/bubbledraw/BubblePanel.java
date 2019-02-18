package bubbledraw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

public class BubblePanel extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Initialise an array list to store bubbles that are drawn
	private ArrayList<Bubble> bubbleList;
	
	// Bubbles start at width 30 pixels
	private int size = 30;
	
	// Timer to allow for animation
	private Timer timer;
	
	// Milliseconds of delay for 30 frames per second
	private final int DELAY = 33; 
	
	private JTextField txtSize;
	private JTextField txtSpeed;
	private JCheckBox chkGroup;
	
	public BubblePanel()
	{
		bubbleList = new ArrayList<Bubble>();
		
		// Listeners in BubbleListener class
		addMouseListener(new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener(new BubbleListener());
		
		timer = new Timer(DELAY, new BubbleListener());
		
		// Background color and size of panel
		setBackground(Color.black);
		setPreferredSize(new Dimension(720, 400));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(255, 0, 255));
		add(panel);
		
		JLabel lblSize = new JLabel("Bubble Size: ");
		lblSize.setForeground(new Color(255, 255, 255));
		lblSize.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setFont(new Font("Candara", Font.BOLD, 14));
		panel.add(lblSize);
		
		txtSize = new JTextField();
		txtSize.setForeground(new Color(255, 255, 255));
		txtSize.setBackground(new Color(255, 0, 255));
		// Highlight text when text box has focus
		txtSize.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusGained(FocusEvent e) 
			{
				txtSize.selectAll();
			}
		});
		txtSize.setFont(new Font("Candara", Font.BOLD, 14));
		txtSize.setHorizontalAlignment(SwingConstants.CENTER);
		txtSize.setText("30");
		txtSize.setColumns(3);
		panel.add(txtSize);
		
		JLabel lblSpeed = new JLabel("Animation Speed (fps): ");
		lblSpeed.setForeground(new Color(255, 255, 255));
		lblSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeed.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSpeed.setFont(new Font("Candara", Font.BOLD, 14));
		panel.add(lblSpeed);
		
		txtSpeed = new JTextField();
		txtSpeed.setForeground(new Color(255, 255, 255));
		txtSpeed.setBackground(new Color(255, 0, 255));
		// Highlight text when text box has focus 
		txtSpeed.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusGained(FocusEvent e) 
			{
				txtSpeed.selectAll();
			}
		});
		txtSpeed.setFont(new Font("Candara", Font.BOLD, 14));
		txtSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		txtSpeed.setText("30");
		txtSpeed.setColumns(3);
		panel.add(txtSpeed);
		
		JButton btnUpdate = new JButton("Update");
		// Keyboard shortcut for button
		btnUpdate.setMnemonic(KeyEvent.VK_U);
		btnUpdate.setForeground(new Color(255, 0, 255));
		btnUpdate.setBackground(new Color(255, 255, 255));
		btnUpdate.setVerticalAlignment(SwingConstants.BOTTOM);
		btnUpdate.setFont(new Font("Candara", Font.BOLD, 14));
		// Update size and speed to reflect values in txtSize and txtSpeed
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// Get the bubble size
				int newSize = Integer.parseInt(txtSize.getText());
				
				// Get the animation speed
				int newSpeed = Integer.parseInt(txtSpeed.getText());
				
				// Set the bubble size
				size = newSize;
				
				// Set the animation speed
				timer.setDelay(1000 / newSpeed);
				repaint();
			}
		});
		panel.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		// Keyboard shortcut for button
		btnClear.setMnemonic(KeyEvent.VK_C);
		btnClear.setForeground(new Color(255, 0, 255));
		btnClear.setBackground(new Color(255, 255, 255));
		btnClear.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClear.setFont(new Font("Candara", Font.BOLD, 14));
		// Clear all buttons on screen
		btnClear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				bubbleList = new ArrayList<Bubble>();
				repaint();				
			}
		});
		panel.add(btnClear);
		
		chkGroup = new JCheckBox("Group Bubbles");
		chkGroup.setForeground(new Color(255, 255, 255));
		chkGroup.setBackground(new Color(255, 0, 255));
		// Keyboard shortcut for checkbox
		chkGroup.setMnemonic(KeyEvent.VK_G);
		chkGroup.setVerticalAlignment(SwingConstants.BOTTOM);
		chkGroup.setHorizontalAlignment(SwingConstants.CENTER);
		chkGroup.setFont(new Font("Candara", Font.BOLD, 14));
		panel.add(chkGroup);

		JButton btnPause = new JButton("Pause");
		// Keyboard shortcut for button
		btnPause.setMnemonic(KeyEvent.VK_P);
		btnPause.setForeground(new Color(255, 0, 255));
		btnPause.setBackground(new Color(255, 255, 255));
		btnPause.setVerticalAlignment(SwingConstants.BOTTOM);
		btnPause.setFont(new Font("Candara", Font.BOLD, 14));
		// Pauses animation and turns into 'Play' button and vice versa
		btnPause.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(btnPause.getText().equals("Pause"))
				{
					timer.stop();
					btnPause.setText("  Play  ");
				}
				else if(btnPause.getText().equals("  Play  "))
				{
					timer.start();
					btnPause.setText("Pause");
				}
			}
		});
		panel.add(btnPause);
		
		timer.start();
	}
	
	public void paintComponent(Graphics page) 
	{
		super.paintComponent(page);
		
		// Draw all the bubbles from bubbleList
		for(Bubble bubble:bubbleList)
		{
			// Generates random color
			page.setColor(bubble.color);
			// Generates a bubble so that centre of bubble is where mouse was clicked
			page.fillOval(bubble.x - bubble.size/2, bubble.y - bubble.size/2, bubble.size, bubble.size);
		}
	}
	
	private class BubbleListener implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener
	{
		@Override
		public void mouseClicked(MouseEvent e) 
		{	
		}

		@Override
		public void mousePressed(MouseEvent e) 
		{
			// Add mouse location to the bubbleList 
			bubbleList.add(new Bubble(e.getX(), e.getY(), size));
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) 
		{
		}

		@Override
		public void mouseEntered(MouseEvent e) 
		{
		}

		@Override
		public void mouseExited(MouseEvent e) 
		{
		}

		@Override
		public void mouseDragged(MouseEvent e) 
		{
			// Add mouse location to the bubbleList 
			bubbleList.add(new Bubble(e.getX(), e.getY(), size));
			
			// Check to see if Group Bubbles check box is selected
			if (chkGroup.isSelected())
			{
				// Set the x speed and y speed of this bubble to previous bubble's speed
				bubbleList.get(bubbleList.size() - 1).xSpeed = bubbleList.get(bubbleList.size() - 2).xSpeed;
				bubbleList.get(bubbleList.size() - 1).ySpeed = bubbleList.get(bubbleList.size() - 2).ySpeed;
			}
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) 
		{
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) 
		{
			// Change the bubble size whenever mouse wheel is moved
			size -= e.getWheelRotation();
			// Show size in txtSize
			txtSize.setText("" + size);
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// Update the location of each bubble for the animation
			for(Bubble bubble:bubbleList)
			{
				bubble.update();
			}
			// Repaint the screen
			repaint();
		}	
	}
	
	private class Bubble
	{
		/** A bubble needs an x, y location and a size */
		public int x;
		public int y;
		public int size;
		public Color color;
		public int xSpeed;
		public int ySpeed;
		private final int MAX_SPEED = 5;
		
		public Bubble(int newX, int newY, int newSize)
		{
			x = newX;
			y = newY;
			size = newSize;
			// Random rgba color
			color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random());
			
			// Give positive and negative range for speed
			// Ensure all bubbles move
			do 
			{
				xSpeed = (int)(Math.random() * MAX_SPEED * 2 - MAX_SPEED);
				ySpeed = (int)(Math.random() * MAX_SPEED * 2 - MAX_SPEED);
			} while(xSpeed == 0 && ySpeed == 0);
		}
		
		public void update()
		{
			// Float each bubble around screen by random speed
			x += xSpeed;
			y += ySpeed;
			
			// Collision detection with edges of the panel. Ensures edge of bubble is detected rather than centre
			if(x - size/2 < 0 || x + size/2 > getWidth())
			{
				xSpeed = -xSpeed;
			}
			if(y - size/2 < 0 || y + size/2 > getHeight())
			{
				ySpeed = -ySpeed;
			}
		}
	}
}
