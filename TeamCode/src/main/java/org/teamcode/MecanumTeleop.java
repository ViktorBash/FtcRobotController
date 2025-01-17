/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a POV Game style Teleop for a PushBot
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Mecanum: Teleop", group="Mecanum")
public class MecanumTeleop extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareMecanum robot           = new HardwareMecanum();   // Use a Mecanum's hardware

    @Override
    public void runOpMode() {
        double x1 = 0; // left/right, set the initial value that is changed when the joystick is
        // used
        double y1 = 0; // front/back

        final double fortyFiveInRads = -Math.PI/4;
        final double cosine45 = Math.cos(fortyFiveInRads);
        final double sine45 = Math.sin(fortyFiveInRads);

        double x2 = 0;
        double y2 = 0;


        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            double spin = gamepad1.right_stick_x; // for controlling the spin

            // B Button is pressed, move the motor UP
            if(gamepad1.b) {
                robot.attachmentDrive.setPower(0.5);
            }

            // X Button is pressed, move the motor DOWN
            if(gamepad1.x){
                robot.attachmentDrive.setPower(-0.5);
            }
            // Neither A or X are pressed, motor should be stopped
            // TODO: check that robot.attachmentDrive.getPower() is not already 0 before we set it
            // to 0 to avoid uselessly setting this variable constantly
            if(!gamepad1.b && !gamepad1.x) {
                robot.attachmentDrive.setPower(0);
            }


            // If someone is moving the right joystick, spin
            if(Math.abs(spin) > 0.1){
                robot.frontRightDrive.setPower(-spin);
                robot.backRightDrive.setPower(-spin);

                robot.frontLeftDrive.setPower(spin);
                robot.backLeftDrive.setPower(spin);
            }
            // If no one is pressing the right joystick, do the normal driving code
            else {

                y1 = -gamepad1.left_stick_y;
                x1 = gamepad1.left_stick_x;

                // need to rotate 45 degrees
                y2 = y1 * cosine45 + x1 * sine45;
                x2 = x1 * cosine45 - y1 * sine45;


                // Output the safe vales to the motor drives.
                robot.frontLeftDrive.setPower(x2);
                robot.backRightDrive.setPower(x2);

                robot.frontRightDrive.setPower(y2);
                robot.backLeftDrive.setPower(y2);
            }

            // Send telemetry message to signify robot running;
            telemetry.addData("Y Value:",  "%.2f", y2);
            telemetry.addData("X Value:", "%.2f", x2);
            telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            sleep(50);
        }
    }
}
