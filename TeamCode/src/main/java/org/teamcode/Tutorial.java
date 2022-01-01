package org.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="test2")
public class Tutorial extends OpMode {

    DcMotor motor;


    @Override
    public void init(){
        motor = hardwareMap.dcMotor.get("motor1"); // String is whatever we named the motor in the config on the phone

    }

    @Override
    public void loop(){
        motor.setPower(gamepad1.left_trigger); // This is how the motor is controlled by the controller
    }
}
