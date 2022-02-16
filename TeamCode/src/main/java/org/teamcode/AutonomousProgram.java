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

import android.os.Build;
import android.util.Pair;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
import org.teamcode.HardwareMecanum;

import java.time.Duration;

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

@Autonomous(name = "Mecanum: Autonomous", group = "Mecanum")
public class AutonomousProgram extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareMecanum robot = new HardwareMecanum();   // Use a Mecanum's hardware
    /* Public OpMode members. */


    @RequiresApi(api = Build.VERSION_CODES.O)
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

        try {
            drive(Direction.LEFT, Duration.ofSeconds(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // rotate left
        // pick up block
        // rotate right
        try {
            drive(Direction.UP, Duration.ofSeconds(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // drop block
    }

    enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void spin(Direction direction, Duration length) {
        switch (direction) {
            case LEFT:
                robot.frontRightDrive.setPower(-1);
                robot.backRightDrive.setPower(-1);
                robot.frontLeftDrive.setPower(1);
                robot.backLeftDrive.setPower(1);
            case RIGHT:
                robot.frontRightDrive.setPower(1);
                robot.backRightDrive.setPower(1);
                robot.frontLeftDrive.setPower(-1);
                robot.backLeftDrive.setPower(-1);
        }

        sleep(length.toMillis());

        stop();
    }

    void halt() {
        for (DcMotor m : new DcMotor[]{robot.frontLeftDrive, robot.backRightDrive, robot.frontRightDrive, robot.backLeftDrive}) {
            m.setPower(0);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void drive(Direction direction, Duration length) throws Exception {
        double[] powers = getPowers(direction);
        double x = powers[0];
        double y = powers[1];

        robot.frontLeftDrive.setPower(x);
        robot.backRightDrive.setPower(x);
        robot.frontRightDrive.setPower(y);
        robot.backLeftDrive.setPower(y);

        sleep(length.toMillis());

        stop();
    }

    double[] getPowers(Direction direction) throws Exception {
        switch (direction) {
            case LEFT:
                return new double[]{-1, 1};
            case RIGHT:
                return new double[]{1, -1};
            case UP:
                return new double[]{1, 1};
            case DOWN:
                return new double[]{-1, -1};
        }

        throw new Exception();
    }
}
