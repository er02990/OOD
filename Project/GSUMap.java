import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.awt.Color;

public class GSUMap extends JPanel {
	JFrame f;
	KeyListener listener;
	Rectangle building;
	//Rectangle robot;
	Image mapImage;
	//Image robotImage;
	BufferedImage mapSprite;
	//BufferedImage robotSprite;
	//private DeliverRobot[] robots;
	ArrayList<DeliverRobot> robots;
	DeliverRobot current;
	public JComboBox<String> combox;
	private JTextArea errMsg = new JTextArea();

	// constructor to call INIT
	public GSUMap() {
		init();
	}

	public void init() {
		this.setLayout(null);
		robots = new ArrayList<DeliverRobot>();
		createFrame(1500, 808); // create frame

		// setting collision rectangles
		building = new Rectangle(200, 200, 100, 100);
		//robot = new Rectangle(100, 100, 50, 50);

		keyChecker(); // set key listener

		getMapImages("res\\newMap.png"); // make sure you have a a res folder with the assets outside of
															// the project folder

		// UI elements
		createUI();

	}

	//paints objects
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mapSprite, 0, 0, null);
		g.drawRect(200, 200, 100, 100);
		g.drawRect(0, 0, 1227, 768);
		for(DeliverRobot var : robots) {
			g.drawImage(var.robotSprite, var.getE(), var.getS(), null);
		}

	}
	
	public void createUI() {
		
		//comboBox that will hold the names of the robots in robots
		//will update current with selected robot when it changes
		combox = new JComboBox<String>();
		DefaultComboBoxModel<String> dml= new DefaultComboBoxModel<String>();
		for (DeliverRobot var : robots) {
		  dml.addElement(var.name);
		}
		
		combox.setModel(dml);
		
		combox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(DeliverRobot temp : robots) {
					if(temp.name == combox.getSelectedItem()) {
						current = temp;
						System.out.println(current);
					}
				}
			}
		});
		
		combox.setBounds(1288, 60, 150, 25);
		combox.setFocusable(false);
		this.add(combox);
		
		GSUMap temp = this;
		
		//create robot will launch a robotCreation window and then create a robot and display it
		JButton btnCreateRobot = new JButton("CREATE ROBOT");
		btnCreateRobot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RobotCreation rc = new RobotCreation(720, 1180, robots, temp, combox);
				rc.setVisible(true);
				repaint();					
			}
		});
		btnCreateRobot.setBounds(1288, 11, 150, 25);
		btnCreateRobot.setFocusable(false);
		this.add(btnCreateRobot);
		
		
		//creates a delete button and creates an action listener
		//listener will delete the current robot from robots and from combobox
		JButton btnDelete = new JButton("Delete current robot");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(current != null) {
					String removedName = current.name;
					combox.setSelectedItem(null);
					combox.removeItem(removedName);
					robots.remove(current);
					current = null;
					repaint();	
				}
								
			}
		});
		btnDelete.setBounds(1288, 110, 150, 25);
		btnDelete.setFocusable(false);
		this.add(btnDelete);
		errMsg.setForeground(Color.RED);
		
		errMsg.setBackground(UIManager.getColor("Button.background"));
		errMsg.setBounds(1288, 157, 150, 69);
		add(errMsg);
		
		JButton btnTogglePower = new JButton("Toggle Power");
		btnTogglePower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (current == null) {
					errMsg.setText("There is no robot selected.");
					return;
				}
				errMsg.setText("");
				if (current.isOn()) {
					current.turnOff();
				}
				else {
					current.turnOn();
				}
			}
		});
		btnTogglePower.setBounds(1308, 237, 119, 23);
		btnTogglePower.setFocusable(false);
		add(btnTogglePower);
		
	}

	// creates a JFrame
	public void createFrame(int x, int y) {
		f = new JFrame();
		f.setTitle("GSU Map");
		f.getContentPane().add(this);
		f.setSize(x, y);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// creates a keyListener using myKeyListener
	public void keyChecker() {
		listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
	}

	// Loads images from the res folder
	public void getMapImages(String mapPath) {
		try {
			mapSprite = ImageIO.read(new File(mapPath));
		} catch (Exception e) {
			System.out.println("cant find image at: " + mapPath);
		}
	}

	// move method that will determine what movement key was pressed
	//uses the currents robots boxes locations. could be switched to changing the robots cords
	//and updating the box location in the robot
	public void move(String s) {
		char c = s.charAt(0);
		if (c == 'W') {
			
			current.box.setLocation((int) current.box.getX(), (int) current.box.getY() - 5);
			if (current.box.intersects(building)) {
				errMsg.setText("Collision!");
				current.box.setLocation((int) current.box.getX(), (int) current.box.getY() + 5);
			} 
			
			else if(!current.isOn()) {
				errMsg.setText("This robot is not turned on.");
				current.box.setLocation((int) current.box.getX(), (int) current.box.getY() + 5);
			}
			
			else {
				current.setS(current.getS() - 5);
				if (current.getS() <= 0) {
					errMsg.setText("Out of bounds!");
					current.setS(current.getS() + 5);
					current.box.setLocation((int) current.box.getX(), (int) current.box.getY() + 5);
				}
				else {
					errMsg.setText("");
					repaint();					
				}

			}
		
		} else if (c == 'S') {
			current.box.setLocation((int) current.box.getX(), (int) current.box.getY() + 5);
			if (current.box.intersects(building)) {
				errMsg.setText("Collision!");
				current.box.setLocation((int) current.box.getX(), (int) current.box.getY() - 5);
			} 
			
			else if(!current.isOn()) {
				errMsg.setText("This robot is not turned on.");
				current.box.setLocation((int) current.box.getX(), (int) current.box.getY() - 5);
			}
			
			else {
				current.setS(current.getS() + 5);
				if (current.getS() >= 720) {
					errMsg.setText("Out of bounds!");
					current.setS(current.getS() - 5);
					current.box.setLocation((int) current.box.getX(), (int) current.box.getY() - 5);
				}
				else {
					errMsg.setText("");
					repaint();					
				}
			}
		} else if (c == 'A') {
			current.box.setLocation((int) current.box.getX() - 5, (int) current.box.getY());
			if (current.box.intersects(building)) {
				errMsg.setText("Collision!");
				current.box.setLocation((int) current.box.getX() + 5, (int) current.box.getY());
			} 
			
			else if(!current.isOn()) {
				errMsg.setText("This robot is not turned on.");
				current.box.setLocation((int) current.box.getX() + 5, (int) current.box.getY());
			}
			
			else {
				current.setE(current.getE() - 5);
				if (current.getE() <= 0) {
					errMsg.setText("Out of bounds!");
					current.setE(current.getE() + 5);
					current.box.setLocation((int) current.box.getX() + 5, (int) current.box.getY());
				}
				else {
					errMsg.setText("");
					repaint();					
				}
			}
		} else if (c == 'D') {
			current.box.setLocation((int) current.box.getX() + 5, (int) current.box.getY());
			if (current.box.intersects(building)) {
				errMsg.setText("Collision!");
				current.box.setLocation((int) current.box.getX() - 5, (int) current.box.getY());
			} 
			
			else if(!current.isOn()) {
				errMsg.setText("This robot is not turned on.");
				current.box.setLocation((int) current.box.getX() - 5, (int) current.box.getY());
			}
			
			else {
				current.setE(current.getE() + 5);
				if (current.getE() >= 1180) {
					errMsg.setText("Out of bounds!");
					current.setE(current.getE() - 5);
					current.box.setLocation((int) current.box.getX() - 5, (int) current.box.getY());
				}
				else {
					errMsg.setText("");
					repaint();					
				}
			}
		}
	}

	// listener class that will call move
	class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(current != null) {
				move(KeyEvent.getKeyText(e.getKeyCode()));
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}
}
