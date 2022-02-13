package org.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
import org.teamcode.HardwareMecanum;

@Autonomous(name = "Mecanum: Autonomous 2", group = "Mecanum")
public class AutonomousProgram2 extends LinearOpMode {
    HardwareMecanum robot = new HardwareMecanum();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized robot hardwaremap");
        telemetry.update();

        waitForStart();
        robot.frontLeftDrive.setPower(1);
        robot.backRightDrive.setPower(1);
        robot.frontRightDrive.setPower(-1);
        robot.backLeftDrive.setPower(-1);
        telemetry.addData("Status", "Set power to robot motors");
        telemetry.update();
        sleep(1000);
        robot.frontLeftDrive.setPower(0);
        robot.backRightDrive.setPower(0);
        robot.frontRightDrive.setPower(0);
        robot.backLeftDrive.setPower(0);
    }

}


