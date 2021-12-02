/**
 * GPS.xPos, GPS.yPos, GPS.angle;
 * Robot.frDrive, Robot.flDrive, Robot.brDrive, Robot.blDrive
 * Robot.fAccel, Robot.rAccel, Robot.lAccel, Robot.bAccel;
 * Robot.robotGyro
 */

public abstract class Drive{
    /**
     * Drive methods
     * 
     * 
     * 
     * 
     * 
     */
    public void setPosition(double x, double y, double a){
        moveTo(x,y);
        turnTo(a);
    }
    boolean GPSon = false;
    public void moveTo(double x, double y){
        // Step 1: figure out total distance

        while(true){
            if(GPSon){
                // Step 1: figure out current distance
        
                // Step 2: figure out angle

                
            }
        }
    }
    public void move(double x, double y){
        //call moveTo()
    }
    public void turnTo(double a){
        int cw = (a - GPS.angle+360)%360 > 180 ? 1 : -1; //turn clockwise or anticlockwise

        while(true){
            if(GPSon){
                GPSon = false;
                //turn on all the motors
                double power;
                // motor.setPower(cw*power)
            }
        }
    }
    public void turn(double a){
        //call turnTo()
    }

}
