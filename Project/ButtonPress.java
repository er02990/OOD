import java.awt.Rectangle;

import javax.swing.JTextArea;

public abstract class ButtonPress {
	
	protected DeliverRobot current;
	protected Rectangle building;
	protected JTextArea errMsg;
	
	public ButtonPress(DeliverRobot current, Rectangle building, JTextArea errMsg) {
		this.current = current;
		this.building = building;
		this.errMsg = errMsg;
	}
	
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
	
	protected boolean isIntersecting() {
		return current.box.intersects(building) || building.intersects(current.box);
	}
	
	protected void displayCollision() {
		errMsg.setText("Collision!");
	}
	
	protected void displayNotOn() {
		errMsg.setText("This robot is not turned on.");
	}
	
	protected void displayOutOfBounds() {
		errMsg.setText("Out of bounds!");
	}
	
	protected void clearErrorMessage() {
		errMsg.setText("");
	}
	
	protected boolean isOff() {
		return !current.isOn();
	}
	
	protected boolean outOfBounds() {
		return current.getS() >= 720 || current.getS() <= 0 || current.getE() >= 1180 || current.getE() <= 0;
	}

	
	
	protected abstract void initialMove();
	protected abstract void moveBack();
	protected abstract void changeRobotCoordinate();
	protected abstract void moveCoordinateBack();

}
