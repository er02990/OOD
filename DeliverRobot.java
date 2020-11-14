import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class DeliverRobot {
//this is a delivery robot that can be turned on and off, as well as instructed to move
//it keeps track of its power state as well as its location via units south and units east of the NW edge of an area
//it also stores its box for being displayed on the GUI, the image it uses as well as its sprite
//this are given the default visibility to make them easier to use from within this package only
//a robot also stores its name
	private boolean on;
	private int S;
	private int E;
	Rectangle box;
	Image robotImage;
	BufferedImage robotSprite;
	private String name;
	
//when created this robot will have a starting position and power state, as well as a name
	public DeliverRobot(int S, int E, boolean on, String name) {
		this.S = S;
		this.E = E;
		this.on = on;
		this.name = name;
		
		box = new Rectangle(E, S, 50, 50);
		getImages("res\\robot.png");
	}
	
	//get image method
	//it does not need to be used outside the package, so it is set to default visibility
	void getImages(String robotPath) {
		try {
			// first load the robot as an image then scale it down then draw it onto a
			// buffered image
			Toolkit t = Toolkit.getDefaultToolkit();
			robotImage = t.getImage(robotPath).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
			BufferedImage img = ImageIO.read(new File(robotPath));
			Image image = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			robotSprite = new BufferedImage(50, 50, BufferedImage.TRANSLUCENT);
			robotSprite.getGraphics().drawImage(image, 0, 0, null);
		} catch (Exception e) {
			System.out.println("failed to get robot image");
		}
	}	
	
	
//this method turns the robot on
//it returns 0 if successful, -1 if it was already on
	public int turnOn() {
		if (on)
			return -1;
		else {
			on = true;
			return 0;
		}
	}

//this method turns the robot off
//it returns 0 if successful, -1 if it was already off
	public int turnOff() {
		if (!on)
			return -1;
		else {
			on = false;
			return 0;
		}
	}

	//returns the robot's number of units East
	public int getE() {
		return E;
	}

	//sets the robot's number of units East
	//only visible inside the package to prevent improper external use of this method
	void setE(int e) {
		E = e;
	}

	//returns the robot's number of units South
	public int getS() {
		return S;
	}

	//sets the robot's number of units South
	//only visible inside the package to prevent improper external use of this method
	void setS(int s) {
		S = s;
	}
	
	//returns true if the robot is on, false if it is off
	public boolean isOn() {
		return on;
	}
	
	//returns the name of the robot
	public String getName() {
		return name;
	}
	
}
