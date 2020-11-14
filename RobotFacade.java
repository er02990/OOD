import java.util.ArrayList;

import javax.swing.JTextArea;

public class RobotFacade {
//this class makes the process of automatically moving all robots to a specific part of the map 
//easy to call with a single method, instead of writing several lines of code each time to move
//multiple objects
	
//it takes in the GSUMap so it can use the move() method to simulate key presses
//it also takes in the robots it will be manipulating
//as well as the error message to check if automatically moving the robots would encounter any issues
	
	private GSUMap map;
	private ArrayList<DeliverRobot> robots;
	private JTextArea errMsg;
	
	
	public RobotFacade(GSUMap map, ArrayList<DeliverRobot> robots, JTextArea errMsg) {
		this.map = map;
		this.robots = robots;
		this.errMsg = errMsg;
	}
	
//this method moves all robots that are on to the north east corner
//it can only encounter the building while moving east since it moves east then north 
//so if it does it will move the robot north until it circumvents it
//it sleeps for a period of time so the robots can be seen moving
//it does not need to be used outside of the package, so it is given the default visibility
	void moveAllNE() throws InterruptedException {
		if (robots.isEmpty()) {
			errMsg.setText("There are no robots on\nthe map.");
		}
		for (DeliverRobot robot : robots) {
			if (!robot.isOn()) {
				continue;
			}
			map.current = robot;
			map.combox.setSelectedItem(map.current.getName());
			while ((int) robot.getE() < 1175) {
				map.move("D");
				if (errMsg.getText().equals("Collision!")) {
					map.move("W");
				}
				map.paintImmediately(0, 0, 1484, 769);
				Thread.sleep(20);
			}
			
			while ((int)robot.getS() > 5) {
				map.move("W");
				map.paintImmediately(0, 0, 1484, 769);
				Thread.sleep(20);
			}
			
		}
	}
	
//this method moves all robots that are on to the north west corner
//it can only encounter the building while moving west since it moves west then north 
//so if it does it will move the robot north until it circumvents it
//it sleeps for a period of time so the robots can be seen moving
//it does not need to be used outside of the package, so it is given the default visibility
	void moveAllNW() throws InterruptedException {
		if (robots.isEmpty()) {
			errMsg.setText("There are no robots on\nthe map.");
		}
		for (DeliverRobot robot : robots) {
			if (!robot.isOn()) {
				continue;
			}
			map.current = robot;
			map.combox.setSelectedItem(map.current.getName());
			while ((int) robot.getE() > 5) {
				map.move("A");
				if (errMsg.getText().equals("Collision!")) {
					map.move("W");
				}
				map.paintImmediately(0, 0, 1484, 769);
				Thread.sleep(20);
			}
			
			while ((int)robot.getS() > 5) {
				map.move("W");
				map.paintImmediately(0, 0, 1484, 769);
				Thread.sleep(20);
			}
			
		}
	}
	
//this method moves all robots that are on to the south east corner
//it can only encounter the building while moving east since it moves east then south
//so if it does it will move the robot south until it circumvents it
//it sleeps for a period of time so the robots can be seen moving
//it does not need to be used outside of the package, so it is given the default visibility
	void moveAllSE() throws InterruptedException {
		if (robots.isEmpty()) {
			errMsg.setText("There are no robots on\nthe map.");
		}
		for (DeliverRobot robot : robots) {
			if (!robot.isOn()) {
				continue;
			}
			map.current = robot;
			map.combox.setSelectedItem(map.current.getName());
			while ((int) robot.getE() < 1175) {
				map.move("D");
				if (errMsg.getText().equals("Collision!")) {
					map.move("S");
				}
				map.paintImmediately(0, 0, 1484, 769);
				Thread.sleep(20);
			}
			
			while ((int)robot.getS() < 715) {
				map.move("S");
				map.paintImmediately(0, 0, 1484, 769);
				Thread.sleep(20);
			}
			
		}
	}
	
//this method moves all robots that are on to the south west corner
//it can only encounter the building while moving west since it moves west then south
//so if it does it will move the robot south until it circumvents it
//it sleeps for a period of time so the robots can be seen moving
//it does not need to be used outside of the package, so it is given the default visibility
	void moveAllSW() throws InterruptedException {
		if (robots.isEmpty()) {
			errMsg.setText("There are no robots on\nthe map.");
		}
		for (DeliverRobot robot : robots) {
			if (!robot.isOn()) {
				continue;
			}
			map.current = robot;
			map.combox.setSelectedItem(map.current.getName());
			while ((int) robot.getE() > 5) {
				map.move("A");
				if (errMsg.getText().equals("Collision!")) {
					map.move("S");
				}
				map.paintImmediately(0, 0, 1484, 769);
				Thread.sleep(20);
			}
			
			while ((int)robot.getS() < 715) {
				map.move("S");
				map.paintImmediately(0, 0, 1484, 769);
				Thread.sleep(20);
			}
			
		}
	}
	

}
