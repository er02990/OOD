public class DeliverRobot {
//this is a delivery robot that can be turned on and off, as well as instructed to move
//it keeps track of its power state as well as its location via units south and units east of the NW edge of an area
	private boolean on;
	private int S;
	private int E;
//when created this robot will have a starting position and power state	
	public DeliverRobot(int S, int E, boolean on) {
		this.S = S;
		this.E = E;
		this.on = on;
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
		else if (this.S == S && this.E == E) {
			return -1;
		}
		else {
			this.S = S;
			this.E = E;
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
			E -= W;
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
			this.E += E;
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
			this.S -= N;
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
			this.S += S;
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
			S -= NE;
			E += NE;
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
			S -= NW;
			E -= NW;
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
			S += SE;
			E += SE;
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
			S += SW;
			E -= SW;
			return 0;
		}
	}
}
