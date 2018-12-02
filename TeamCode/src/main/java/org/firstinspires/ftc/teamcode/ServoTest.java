package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Servo Test", group="Test")
public class ServoTest extends OpMode{
    private CRServo intake1, intake2, intake3, intake4;

    public void init() {
        intake1 = hardwareMap.crservo.get("intake1");
        intake2 = hardwareMap.crservo.get("intake2");
        intake3 = hardwareMap.crservo.get("intake3");
        intake4 = hardwareMap.crservo.get("intake4");
    }

    public void loop(){
        if(gamepad1.a)
        intake1.setPower(-1);
        intake2.setPower(1);
        intake3.setPower(-1);
        intake4.setPower(1);

    }
}