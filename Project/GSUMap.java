import javax.swing.JFrame;
import javax.swing.*;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GSUMap extends Canvas{
	JFrame f;
	Rectangle building;
	Rectangle robot;
	Image mapBackground;
	Image robotImage;


	//constructor to call init
	public GSUMap() {
		init();
	}

	public void init() {
		
		//creating jFrame
        f = new JFrame();  
        f.add(this);  
        f.setSize(770,440);  
        f.setVisible(true);  
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        //setting collision rectangles
		building = new Rectangle(200, 200, 100, 100);
		robot = new Rectangle(100, 100, 50, 50);
		
		
		//setting keyListeners
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);

		//setting images
		Toolkit t = Toolkit.getDefaultToolkit();  
		mapBackground = t.getImage("res\\tempMap.png");
		robotImage = t.getImage("res\\robot.png").getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		
		

		
	}
	
	//paint function
//	public void paint(Graphics g) {  
//        g.drawImage(mapBackground, 0,0,this); 
//        g.drawImage(robotImage, (int)robot.getX(),(int)robot.getY(),this); 
//        g.drawRect(200, 200, 100, 100);
//	}

	
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
				repaint();
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
