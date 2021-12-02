/**
 * Each corner has 2 distance sensors and 1 gyro
 * The radar piece on each corner is ofset by 90 deg
 * 
 * Use: 
 * Drive.xPos, Drive.yPos - doubles representing the position of the center of the robot relative to the field
 *      1 unit is an inch, (0,0) is blue side away from goal
 * Drive.angle - double reprenting the the angle of the robot relative to the field
 *      facing goals = 90 deg, facing blue side = 180deg, etc.
 * 
 * frGyro, flGyro, brGyro, blGyro;
 * frDist, flDist, brDist, blDist;
 */
import java.awt.geometry.*;
import java.util.Map;
import java.util.Timer;
public abstract class Radar {
    public final double range = 2000; // range of dist sensor in mm
	public Timer timer = new Timer(5, this);
	public int time;
	public boolean radarIsOn;
	public void activate(){
		//Create a thread
		time = 0; 
		radarIsOn=true;
		timer.start();
	}
	public void ActionPerformed (ActionEvent e){
		time++;
		scan();
		if(radarIsOn) timer.start();
	}
    public void scan(){
        // Step 1: Figure out the positions of all the radar axles. Use Robot.xPos/yPos. 
        //      Use Robot.angle & trig. Assume axle is 192 away in x & y directions
		double cenToRadAx = 192*Math.sqrt(2)*Robot.inPerMm;

		double FRX = Robot.xPos + cenToRadAx * Math.cos(Math.toRadians(Robot.angle+45)), 
		                FRY = Robot.yPos + cenToRadAx * Math.sin(Math.toRadians(Robot.angle+45)),
			   FLX = Robot.xPos + cenToRadAx * Math.cos(Math.toRadians(Robot.angle+135)), 
						FLY = Robot.yPos + cenToRadAx * Math.sin(Math.toRadians(Robot.angle+135),
			   BLX = Robot.xPos + cenToRadAx * Math.cos(Math.toRadians(Robot.angle+225)), 
						BLY = Robot.yPos + cenToRadAx * Math.sin(Math.toRadians(Robot.angle+225),
			   BRX = Robot.xPos + cenToRadAx * Math.cos(Math.toRadians(Robot.angle+315)), 
						BRY = Robot.yPos + cenToRadAx * Math.sin(Math.toRadians(Robot.angle+315));
        // Step 2: Figure out the x and y coordinates of the actual sensor.
        //      Use (corresponding gyro's y axis reading + 180) % 360
		// 		X += 14 * Robot.inPerMm * Math.cos(Math.toRadians(gyro reading))
        FRX += 14 * Hardware.inPerMm * Math.cos(Math.toRadians(gyro reading));
        FRY += 14 * Hardware.inPerMm * Math.sin(Math.toRadians(gyro reading));
		
		
        /** Step 3: 
            3.1) if the given value (distance sensor) is the range - 10 mm away ignore it
            3.2) If the top sensor is ignored but the bottom isn't, calculate point of detection and put one parameter in the PointGuess constructor as true. 
    	    3.3) If there are 2 values, differing by < 10 mm, calculate point of detection and put one parameter in the PointGuess constructor as true.
		 			3.2.1) Use Math.cos(Math.toRadians(gyroreading))/Math.sin(Math.toRadians(gyroreading)) to find slope (m).
					3.2.2) use radar position as x and y to solve for b in b = y - m * x
					3.2.3) Use (cos(gyroreading) + radar X pos) as x in y = mx + b and solve for y

                3.3 example
                double m = Math.cos(Math.toRadians(gyroreading))/Math.sin(Math.toRadians(gyroreading));
                double b = FRY - m*FRX;
                double xPoint = Math.cos(Math.toRadians(gyro reading)) + FRX;
                double yPoint = x*m+b;
		**/

    }
}