import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class DeliverRobot {
//this is a delivery robot that can be turned on and off, as well as instructed to move
//it keeps track of its power state as well as its location via units south and units east of the NW edge of an area
	private boolean on;
	private int S;
	private int E;
	Rectangle box;
	Image robotImage;
	BufferedImage robotSprite;
	public String name;
	
//when created this robot will have a starting position and power state	
	public DeliverRobot(int S, int E, boolean on, String name) {
		this.setS(S);
		this.setE(E);
		this.on = on;
		this.name = name;
		
		box = new Rectangle(S, E, 50, 50);
		getImages("res\\robot.png");
	}
	
	//get image method
	public void getImages(String robotPath) {
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

//this method moves the robot to a specific location
//it returns 0 if successful, -1 if it is already at the location supplied, and -2 if it is turned off
	public int moveTo(int S, int E) {
		if (!on)
			return -2;
		else if (this.getS() == S && this.getE() == E) {
			return -1;
		}
		else {
			this.setS(S);
			this.setE(E);
			return 0;
		}
	}
	
//this method moves the robot to the West a specified number of units relative to its current location
//it returns 0 if successful, -1 if the distance specified is non-positive(invalid), and -2 if it is turned off
	public int moveWest(int W) {
		if (!on)
			return -2;
		else if (W <= 0)
			return -1;
		else {
			setE(getE() - W);
			return 0;
		}
	}

//this method moves the robot to the East a specified number of units relative to its current location
//it returns 0 if successful, -1 if the distance specified is non-positive(invalid), and -2 if it is turned off
	public int moveEast(int E) {
		if (!on)
			return -2;
		else if (E <= 0)
			return -1;
		else {
			this.setE(this.getE() + E);
			return 0;
		}
	}

//this method moves the robot to the North a specified number of units relative to its current location
//it returns 0 if successful, -1 if the distance specified is non-positive(invalid), and -2 if it is turned off
	public int moveNorth(int N) {
		if (!on)
			return -2;
		else if (N <= 0)
			return -1;
		else {
			this.setS(this.getS() - N);
			return 0;
		}
	}
	
//this method moves the robot to the South a specified number of units relative to its current location
//it returns 0 if successful, -1 if the distance specified is non-positive(invalid), and -2 if it is turned off
	public int moveSouth(int S) {
		if (!on)
			return -2;
		else if (S <= 0)
			return -1;
		else {
			this.setS(this.getS() + S);
			return 0;
		}
	}

//this method moves the robot to the Northeast a specified number of units relative to its current location
//it returns 0 if successful, -1 if the distance specified is non-positive(invalid), and -2 if it is turned off
	public int moveNE(int NE) {
		if (!on)
			return -2;
		else if (NE <= 0)
			return -1;
		else {
			setS(getS() - NE);
			setE(getE() + NE);
			return 0;
		}
	}

//this method moves the robot to the Northwest a specified number of units relative to its current location
//it returns 0 if successful, -1 if the distance specified is non-positive(invalid), and -2 if it is turned off
	public int moveNW(int NW) {
		if (!on)
			return -2;
		else if (NW <= 0)
			return -1;
		else {
			setS(getS() - NW);
			setE(getE() - NW);
			return 0;
		}
	}
	
//this method moves the robot to the Southeast a specified number of units relative to its current location
//it returns 0 if successful, -1 if the distance specified is non-positive(invalid), and -2 if it is turned off
	public int moveSE(int SE) {
		if (!on)
			return -2;
		else if (SE <= 0)
			return -1;
		else {
			setS(getS() + SE);
			setE(getE() + SE);
			return 0;
		}
	}
	
//this method moves the robot to the Southwest a specified number of units relative to its current location
//it returns 0 if successful, -1 if the distance specified is non-positive(invalid), and -2 if it is turned off
	public int moveSW(int SW) {
		if (!on)
			return -2;
		else if (SW <= 0)
			return -1;
		else {
			setS(getS() + SW);
			setE(getE() - SW);
			return 0;
		}
	}

	public int getE() {
		return E;
	}

	public void setE(int e) {
		E = e;
	}

	public int getS() {
		return S;
	}

	public void setS(int s) {
		S = s;
	}
}
