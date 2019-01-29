package org.firstinspires.ftc.teamcode;

/*
Ilan Sperber motor test
 */

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;

public class IlanMotorTest extends OpMode{
    private List<DcMotor> motorList;
    private DcMotor currentMotor;
    private int currentNumber;

    @Override
    public void init() {
        motorList = hardwareMap.getAll(DcMotor.class);
        currentNumber = 0;
    }

    @Override
    public void loop() {
        currentMotor = motorList.get(currentNumber);
        telemetry.addData("current motor is ", currentMotor.getDeviceName());
        telemetry.addData("Next: ", motorList.get(Math.abs(currentNumber) % motorList.size()-1).getDeviceName());
        telemetry.addData("Prev: ", motorList.get(Math.abs(currentNumber) % motorList.size()+1).getDeviceName());

        currentMotor.setPower(gamepad1.left_stick_y);
        if(gamepad1.right_stick_x == 1 || gamepad1.right_stick_y == 1) {
            currentMotor.setPower(0);
            currentNumber = (currentNumber + 1) % motorList.size();

        }
        if(gamepad1.right_stick_y == -1 || gamepad1.right_stick_x == -1) {
            currentMotor.setPower(0);
            currentNumber = (currentNumber - 1) % motorList.size();
        }
    }
}
