/**
 * Robot.xPos, Robot.yPos, Robot.angle;
 * Robot.frDrive, Robot.flDrive, Robot.brDrive, Robot.blDrive
 * Robot.fAccel, Robot.rAccel, Robot.lAccel, Robot.bAccel;
 * Robot.robotGyro
 */
/** 
public abstract class GPS extends Thread implements Timer {
    public final int time = 5; //milliseconds till action event is thrown
        Timer t = new Timer (time, this);
    public double xVel = 0, yVel = 0;
    public double xPos, yPos, angle;

    public void actionPerformed(ActionEvent e){
        Drive.GPSon = true;
        //calculate change in velocity and therefore change in position
        // Step 1: get the average x & y according to the sensors (rel to robot)
        double avgXAcc,
               avgYAcc;
        
        // Step 2: get the average x & y according to the sensors (rel to field)
        double totalAccel = Math.sqrt(avgXAcc * avgXAcc + avgYAcc * avgYAcc);
        double xAcc = totalAccel * Math.cos(Math.toRadians(Robot.angle));
        double yAcc = totalAccel * Math.sin(Math.toRadians(Robot.angle));
        
        // Step 3: update velocity
        // new_Vel = _Vel + time/1000.0 * _Acc;
        double newXVel;
        double newYVel;
        // Step 4: update xPos/yPos
        // _Pos += (_Vel + new_Vel)/2 * time/1000.0

        // Step 5: update angle (use gyro, don't bother calculate)

        // Step 6: update xVel/yVel


        t.start();
    }
}