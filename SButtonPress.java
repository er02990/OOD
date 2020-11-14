import java.awt.Rectangle;

import javax.swing.JTextArea;

public class SButtonPress extends ButtonPress {
//this class represents the S key being pressed
//it attempts to move the robot to the south, so the number of units south will increase if it successfully moves a robot

	public SButtonPress(DeliverRobot current, Rectangle building, JTextArea errMsg) {
		super(current, building, errMsg);
	}

	@Override
	protected void initialMove() {
		current.box.setLocation((int) current.box.getX(), (int) current.box.getY() + 5);
		
	}

	@Override
	protected void moveBack() {
		current.box.setLocation((int) current.box.getX(), (int) current.box.getY() - 5);
		
	}

	@Override
	protected void changeRobotCoordinate() {
		current.setS(current.getS() + 5);
		
	}

	@Override
	protected void moveCoordinateBack() {
		current.setS(current.getS() - 5);
		
	}

	
}
