import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.Graphics;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;


public class GSUMap extends JPanel{
	JFrame f;
	KeyListener listener;
	Rectangle building;
	Rectangle robot;
	Image mapImage;
	Image robotImage;
	BufferedImage mapSprite;
	BufferedImage robotSprite;


	//constructor to call INIT
	public GSUMap() {
		init();
	}
	
	

	public void init() {
        createFrame(770,440);	//create frame
		
        //setting collision rectangles
		building = new Rectangle(200, 200, 100, 100);
		robot = new Rectangle(100, 100, 50, 50);
		
		keyChecker();	//set key listener
		
		getImages("res\\tempMap.png", "res\\robot.png");	//make sure you have a a res folder with the assets outside of the project folder
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mapSprite, 0, 0, null);
		g.drawImage(robotSprite,(int)robot.getX(),(int)robot.getY(), null);
		g.drawRect(200, 200, 100, 100);
		
	}
	
	//creates a JFrame
		public void createFrame(int x, int y) {
			f = new JFrame();  
	        f.add(this);  
	        f.setSize(x,y);  
	        f.setVisible(true);  
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		//creates a keyListener using myKeyListener
		public void keyChecker() {
			listener = new MyKeyListener();
			addKeyListener(listener);
			setFocusable(true);
		}
		
		//Loads images from the res folder
		public void getImages(String mapPath, String robotPath) {
			try {
				//first load the robot as an image then scale it down then draw it onto a buffered image
				Toolkit t = Toolkit.getDefaultToolkit();  
				robotImage = t.getImage(robotPath).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
				BufferedImage img = ImageIO.read(new File(robotPath));
				Image image = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				robotSprite = new BufferedImage(50, 50, BufferedImage.TRANSLUCENT);
				robotSprite.getGraphics().drawImage(image, 0, 0 , null);	
			} catch(Exception e) {
				System.out.println("failed to get robot image");
			}
			
			try {
				mapSprite = ImageIO.read(new File(mapPath));
			} catch(Exception e) {
				System.out.println("cant find image at: " + mapPath);
			}	
		}
	
	//move method that will determine what movement key was pressed
	public void move(String s) {
		char c = s.charAt(0);
		if(c == 'W') {
			robot.setLocation((int)robot.getX(), (int)robot.getY()-5);
			if (robot.intersects(building)) {
			       System.out.println("collision");
			       robot.setLocation((int)robot.getX(), (int)robot.getY()+5);
			} else {
				repaint();
			}
		} else if(c == 'S') {
			robot.setLocation((int)robot.getX(), (int)robot.getY()+5);
			if (robot.intersects(building)) {
			       System.out.println("collision");
			       robot.setLocation((int)robot.getX(), (int)robot.getY()-5);
			} else {
				this.repaint();
			}
		} else if(c == 'A') {
			robot.setLocation((int)robot.getX()-5, (int)robot.getY());
			if (robot.intersects(building)) {
			       System.out.println("collision");
			       robot.setLocation((int)robot.getX()+5, (int)robot.getY());
			} else {
				repaint();
			}
		} else if(c == 'D') {
			robot.setLocation((int)robot.getX()+5, (int)robot.getY());
			if (robot.intersects(building)) {
			       System.out.println("collision");
			       robot.setLocation((int)robot.getX()-5, (int)robot.getY());
			} else {
				repaint();
			}
		}
	}

	//listener class that will call move
	class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			move(KeyEvent.getKeyText(e.getKeyCode()));
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}
}
