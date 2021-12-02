class Accel2 {
	public final double dist, angle; 
			//distance & angle from center of robot in mm. 3:00 of the robot is 0 degrees. 12:00 is 90. 9:00 is 180, 6:00 is 270.
	public Accel2(String n, double xDist, double yDist){
		this(n, Math.sqrt(xDist*xDist + yDsit*yDist), Math.arctan(yDist/xDist),true); 
	}
	public Accel2(String n, double dist, double angle, boolean b){
		this.dist = dist;
		this.angle = angle;
	}
}
