import java.awt.Rectangle;

import javax.swing.JTextArea;

public abstract class ButtonPress {
	
//this class represents the program responding to a button being pressed
//this class uses the template pattern
//it has variables for accessing the current robot, the building that could cause a collision,
//and the error message so it can output errors as needed

	
	protected DeliverRobot current;
	protected Rectangle building;
	protected JTextArea errMsg;
	
	public ButtonPress(DeliverRobot current, Rectangle building, JTextArea errMsg) {
		this.current = current;
		this.building = building;
		this.errMsg = errMsg;
	}
	
//this method represents the react algorithm
//it is final so subclasses of ButtonPress cannot override it
/*in the algorithm the robot is initially moved
 * if it is colliding with a building it moves back, displays the collision error message, and false is returned
 * if the robot is not on the user is told, the robot moves back, and false is returned
 * otherwise the robot has its coordinates changed
 * If this change moves the robot out of bounds and error message is displayed, the coordinates are changed back, the robot moves back, and false is returned
 * Otherwise any error messages can be cleared and true is returned, since the robot was successfully moved
 */
	
	public final boolean react() {
		initialMove();
		if (isIntersecting()) {
			displayCollision();
			moveBack();
			return false;
		}
		else if (isOff()) {
			displayNotOn();
			moveBack();
			return false;
		}
		else {
			changeRobotCoordinate();
			if (outOfBounds()) {
				displayOutOfBounds();
				moveCoordinateBack();
				moveBack();
				return false;
			}
			else {
				clearErrorMessage();
				return true;
			}
		}
	}
	
	
	//tests if the robot is intersecting the building
	//it is the same for all button presses, so it is implemented here
	protected boolean isIntersecting() {
		return current.box.intersects(building) || building.intersects(current.box);
	}
	
	//sets an error message for a collision
	//it is the same for all button presses, so it is implemented here
	protected void displayCollision() {
		errMsg.setText("Collision!");
	}
	
	//sets an error message for the current robot being turned off
	//it is the same for all button presses, so it is implemented here
	protected void displayNotOn() {
		errMsg.setText("This robot is not turned on.");
	}
	
	//sets an error message for the robot attempting to go out of bounds
	//it is the same for all button presses, so it is implemented here
	protected void displayOutOfBounds() {
		errMsg.setText("Out of bounds!");
	}
	
	//removes any error messages
	//it is the same for all button presses, so it is implemented here
	protected void clearErrorMessage() {
		errMsg.setText("");
	}
	
	//tests if the robot is turned off
	//it is the same for all button presses, so it is implemented here
	protected boolean isOff() {
		return !current.isOn();
	}
	
	//tests if the robot's would-be coordinates are out of bounds
	//it is the same for all button presses, so it is implemented here
	protected boolean outOfBounds() {
		return current.getS() >= 720 || current.getS() <= 0 || current.getE() >= 1180 || current.getE() <= 0;
	}

	
	//these methods are implemented differently depending on the button, so they are abstract here
	protected abstract void initialMove();
	protected abstract void moveBack();
	protected abstract void changeRobotCoordinate();
	protected abstract void moveCoordinateBack();

}
