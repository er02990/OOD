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
import java.awt.Font;

public class GSUMap extends JPanel {
	private JFrame f;
	KeyListener listener;
	Rectangle building;
	//Rectangle robot;
	Image mapImage;
	//Image robotImage;
	BufferedImage mapSprite;
	//BufferedImage robotSprite;
	//private DeliverRobot[] robots;
	private ArrayList<DeliverRobot> robots;
	DeliverRobot current;
	JComboBox<String> combox;
	private JTextArea errMsg = new JTextArea();
	private GSUMap entireMap;
	private boolean running;

	// constructor to call INIT
	public GSUMap() {
		entireMap = this;
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
		  dml.addElement(var.getName());
		  errMsg.setText("");
		}
		
		combox.setModel(dml);
		
		combox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(DeliverRobot temp : robots) {
					if(temp.getName() == combox.getSelectedItem()) {
						current = temp;
						errMsg.setText("");
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
					String removedName = current.getName();
					combox.setSelectedItem(null);
					combox.removeItem(removedName);
					robots.remove(current);
					current = null;
					repaint();	
				}
				else {
					errMsg.setText("There is no robot selected.");
				}
								
			}
		});
		btnDelete.setBounds(1288, 110, 150, 25);
		btnDelete.setFocusable(false);
		this.add(btnDelete);
		errMsg.setEditable(false);
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
		
		//this button will use the RobotFacade class to easily move all robots to the north east corner
		JButton NEButton = new JButton("ALL NE");
		NEButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		NEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RobotFacade rf = new RobotFacade(entireMap, robots, errMsg);
				try {
					running = true;
					rf.moveAllNE();
					running = false;
				} catch (InterruptedException e1) {
					running = false;
					e1.printStackTrace();
				}
			}
		});
		NEButton.setFocusable(false);
		NEButton.setBounds(1373, 300, 79, 37);
		add(NEButton);
		
		//this button will use the RobotFacade class to easily move all robots to the north west corner
		JButton NWButton = new JButton("ALL NW");
		NWButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		NWButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RobotFacade rf = new RobotFacade(entireMap, robots, errMsg);
				try {
					running = true;
					rf.moveAllNW();
					running = false;
				} catch (InterruptedException e1) {
					running = false;
					e1.printStackTrace();
				}
			}
		});
		NWButton.setFocusable(false);
		NWButton.setBounds(1284, 300, 79, 37);
		add(NWButton);
		
		//this button will use the RobotFacade class to easily move all robots to the south east corner
		JButton SEButton = new JButton("ALL SE");
		SEButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		SEButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RobotFacade rf = new RobotFacade(entireMap, robots, errMsg);
				try {
					running = true;
					rf.moveAllSE();
					running = false;
				} catch (InterruptedException e1) {
					running = false;
					e1.printStackTrace();
				}
			}
		});
		SEButton.setFocusable(false);
		SEButton.setBounds(1373, 348, 79, 37);
		add(SEButton);
		
		//this button will use the RobotFacade class to easily move all robots to the south west corner
		JButton SWButton = new JButton("ALL SW");
		SWButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		SWButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RobotFacade rf = new RobotFacade(entireMap, robots, errMsg);
				try {
					running = true;
					rf.moveAllSW();
					running = false;
				} catch (InterruptedException e1) {
					running = false;
					e1.printStackTrace();
				}
			}
		});
		SWButton.setFocusable(false);
		SWButton.setBounds(1284, 348, 79, 37);
		add(SWButton);
		
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
	//depending on the key pressed the appropriate button press class will be created and used
	//if the button class used successfully moved the current robot then the map needs to be repainted
	public void move(String s) {
		char c = s.charAt(0);
		boolean ans = false;
		if (c == 'W') {
			WButtonPress buttonW = new WButtonPress(current, building, errMsg);
			ans = buttonW.react();
		
		} 
		
		else if (c == 'S') {
			SButtonPress buttonS = new SButtonPress(current, building, errMsg);
			ans = buttonS.react();
			
		} 
		
		else if (c == 'A') {
			AButtonPress buttonA = new AButtonPress(current, building, errMsg);
			ans = buttonA.react();
			
		} 
		
		else if (c == 'D') {
			DButtonPress buttonD = new DButtonPress(current, building, errMsg);
			ans = buttonD.react();
		}
		
		if (ans) {
			paintImmediately(0, 0, 1484, 769);
		}
	}

	// listener class that will call move
	class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (running) {
				e.consume();
			}
			else if(current != null) {
				move(KeyEvent.getKeyText(e.getKeyCode()));
			}
			else {
				errMsg.setText("There is no robot selected.");
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}
}
