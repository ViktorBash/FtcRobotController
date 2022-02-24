
package org.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 * <p>
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name = "Auto: P3", group = "Mecanum")
public class AutonomousProgram extends LinearOpMode {


    // Variables:

    // Left or Right start: (which direction to move first)
    private static final boolean IS_LEFT_START = true;

    // Left end or Right end: (which direction to move to wall at end)
    private static final boolean IS_LEFT_END = true;

    /*

    (Parking area on top)
    ______________
    |  _|    |_  |
    |[1]      [2]|
    |   O    O   |
    |[3]      [4]|
    |____________|
    
    1 = Right Start, Left End
    2 = Left Start, Right End
    3 = Left Start, Left End
    4 = Right Start, Right End
    
    */
    
    // Power levels:
    private static final float LIFT_POWER = 0.3f;
    private static final float ATTACHMENT_POWER = 0.5f;
    private static final float DRIVE_POWER = 0.5f;
    
    /*  Autonomous motion timings: 
    
    1. Move (left/right) until in front of the stacky thing
    2. Move forward until almost touching the stacky thing
    3. Lift attachment with preloaded block
    4. Set intake to reverse to drop block in stacky thing
    5. Move backwards to wall
    6. Move (left/right) until robot hits the other wall
    Stop motors
    
    */
    
    // [1] Crabwalk (Left/Right) until aligned with stacky thing for x milliseconds:
    private static final long CRABWALK_TO_ALIGN = 1200; 
    // [2] Move forward to stacky thing for x milliseconds:
    private static final long MOVE_OFF_WALL = 600;
    // [3] Lift attachment to level of stacky thing for x milliseconds:
    private static final long LIFT_ATTACHMENT = 500;
    // [4] Reverse intake to drop block for x milliseconds:
    private static final long REVERSE_INTAKE = 4000;
    // [5] Move backwards to wall for x milliseconds:
    private static final long MOVE_TO_WALL = 800;
    // [6] Crabwalk (Left/Right) until hit side wall for x milliseconds:
    private static final long CRABWALK_TO_WALL = 5200;
    // Stop motors

    
    /* Declare OpMode members. */
    HardwareMecanum robot = new HardwareMecanum();   // Use a Mecanum's hardware
    /* Public OpMode members. */


    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        Direction startDirection = IS_LEFT_START ? Direction.LEFT : Direction.RIGHT;
        Direction endDirection = IS_LEFT_END ? Direction.LEFT : Direction.RIGHT;


        try {
            drive(startDirection, DRIVE_POWER, CRABWALK_TO_ALIGN); // [1] crabwalk (left/right) to line up with stacky thing
        // pick up block
            drive(Direction.FORWARD, DRIVE_POWER, MOVE_OFF_WALL); // [2] move forward to stacky thing
            liftAttachment(LIFT_POWER, LIFT_ATTACHMENT); // [3] lift attachment to stack level
            intake(-ATTACHMENT_POWER, REVERSE_INTAKE); // [4] reverse intake to drop block
            drive(Direction.BACKWARD, DRIVE_POWER, MOVE_TO_WALL); // [5] move backwards to wall
            drive(endDirection, DRIVE_POWER, CRABWALK_TO_ALIGN); // [6] crabwalk (left/right) to hit wall
            halt(); // halt motors to be safe
        } catch (Exception e) {
            e.printStackTrace();
        }
        // drop block
    }

    enum Direction {
        LEFT, // or counterclockwise
        RIGHT, // or clockwise
        FORWARD,
        BACKWARD
    }



    void spin(Direction direction, long ms) {
        switch (direction) {
            case LEFT:
                robot.frontRightDrive.setPower(-0.5);
                robot.backRightDrive.setPower(-0.5);
                robot.frontLeftDrive.setPower(0.5);
                robot.backLeftDrive.setPower(0.5);
            case RIGHT:
                robot.frontRightDrive.setPower(0.5);
                robot.backRightDrive.setPower(0.5);
                robot.frontLeftDrive.setPower(-0.5);
                robot.backLeftDrive.setPower(-0.5);
        }

        sleep(ms);

        halt();
    }

    void halt() {
        for (DcMotor m : new DcMotor[] { robot.frontLeftDrive, robot.backRightDrive, robot.frontRightDrive,
                robot.backLeftDrive }) {
            m.setPower(0);
        }
    }
    
    // direction -> LEFT, RIGHT, FORWARD, BACKWARD
    // power -> 0.0 to 1.0
    // length -> in milliseconds
    void drive(Direction direction, float power, long ms, boolean shouldHalt) throws Exception {
        double[] powers = getPowers(direction, power);
        double x = powers[0];
        double y = powers[1];

        robot.frontLeftDrive.setPower(x);
        robot.backRightDrive.setPower(x);
        robot.frontRightDrive.setPower(y);
        robot.backLeftDrive.setPower(y);

        sleep(ms);

        if (shouldHalt) {
            halt();
        }
    }

    double[] getPowers(Direction direction, float power) throws Exception {
        switch (direction) {
            case LEFT:
                return new double[] { -power, power };
            case RIGHT:
                return new double[] { power, -power };
            case FORWARD:
                return new double[] { power, power };
            case BACKWARD:
                return new double[] { -power, -power };
        }

        throw new Exception();
    }
    

    // Smoothly transitions robot from one speed to another
    void rampDrive(Direction direction, float startPower, float endPower, long ms) throws Exception {
        int rasterizationFactor = 10; // how many ms per ramp step
        double powerStep = ((endPower - startPower) / ms) * rasterizationFactor;
        for (int i = 0; i < ms / rasterizationFactor; i++) {
            float power = startPower + powerStep * i;
            drive(direction, power, rasterizationFactor, false);
        }
    }

    void liftAttachment(float power, long ms) throws Exception {
        liftDriveLeft.setPower(power);
        liftDriveRight.setPower(power);
        sleep(ms);
        liftDriveLeft.setPower(0);
        liftDriveRight.setPower(0);
    }


    // Negative power yields release of items
    void intake(float power, long ms) throws Exception {
        attachmentDrive.setPower(power);
        sleep(ms);
        attachmentDrive.setPower(0);
    }
}
