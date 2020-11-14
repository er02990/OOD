public class RobotFactory {
//this class is responsible for creating objects of the DeliverRobot class
//to avoid potential conflict, it is a singleton, so it will hold the only instance of itself that is allowed to exist
	
	private static RobotFactory rf;
	private boolean on;
	private int S;
	private int E;
	private String name;
	
//this constructor is private so that only this class can instantiate itself
	private RobotFactory() {
		
	}
	
//this method is synchronized to avoid two separate users gaining access to different instances of RobotFactory
//if RobotFactory already exists as an instance it simply returns it
	public synchronized static RobotFactory getFactory() {
		if (rf == null)
			rf = new RobotFactory();
		return rf;
	}
	
//a factory can store attributes to be used when making robots
//this is convenient if multiple robots with the same attributes need to be made
	public void setAttributes(int S, int E, boolean on, String name) {
		this.S = S;
		this.E = E;
		this.on = on;
		this.name = name;
	}
	
//without any parameters, the factory will produce a robot with the attributes it has stored
	public DeliverRobot makeRobot() {
		return new DeliverRobot(S, E, on, name);
	}

//with parameters the factory will create a robot with the attributes specified
	public DeliverRobot makeRobot(int S, int E, boolean on, String name) {
		return new DeliverRobot(S, E, on, name);
	}

}
