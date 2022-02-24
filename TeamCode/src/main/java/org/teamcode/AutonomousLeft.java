package org.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Mecanum: Autonomous Left", group = "Mecanum")
public class AutonomousLeft extends LinearOpMode {
    HardwareMecanum robot = new HardwareMecanum();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized robot hardwaremap");
        telemetry.update();

        waitForStart();
        robot.frontLeftDrive.setPower(-0.5);
        robot.backRightDrive.setPower(-0.5);
        robot.frontRightDrive.setPower(0.5);
        robot.backLeftDrive.setPower(0.5);
        sleep(10000);
        robot.frontLeftDrive.setPower(0);
        robot.backRightDrive.setPower(0);
        robot.frontRightDrive.setPower(0);
        robot.backLeftDrive.setPower(0);
    }

}


