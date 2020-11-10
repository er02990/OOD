import java.awt.Rectangle;

import javax.swing.JTextArea;

public class AButtonPress extends ButtonPress {
//this class represents the A key being pressed
//it attempts to move the robot to the west, so the number of units east will decrease if it successfully moves a robot

	public AButtonPress(DeliverRobot current, Rectangle building, JTextArea errMsg) {
		super(current, building, errMsg);
	}

	@Override
	protected void initialMove() {
		current.box.setLocation((int) current.box.getX() - 5, (int) current.box.getY());
		
	}

	@Override
	protected void moveBack() {
		current.box.setLocation((int) current.box.getX() + 5, (int) current.box.getY());
		
	}

	@Override
	protected void changeRobotCoordinate() {
		current.setE(current.getE() - 5);
		
	}

	@Override
	protected void moveCoordinateBack() {
		current.setE(current.getE() + 5);
		
	}

}
