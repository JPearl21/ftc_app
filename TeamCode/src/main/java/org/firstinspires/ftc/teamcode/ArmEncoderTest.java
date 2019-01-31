package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp
public class ArmEncoderTest extends OpMode{
    private DcMotor arm;
    @Override
    public void init() {
        arm = hardwareMap.dcMotor.get("arm");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    @Override
    public void loop() {
        if (gamepad1.a) {
            pEncoderMotorRun(0.001,-4048,arm);


            if(gamepad1.dpad_up){arm.setPower(-1);}
            if(gamepad1.dpad_down){arm.setPower(1);}
            telemetry.addLine(String.valueOf(arm.getCurrentPosition()));
        }
    }
    private void pEncoderMotorRun(double kP, double target, DcMotor driveMotor) { //nate
        double error = Math.abs(target - driveMotor.getCurrentPosition());//obtains the robot's position
        while (error > 1) {//allows the robot to continually operate
            driveMotor.setPower(kP * error);
            error = Math.abs(target - driveMotor.getCurrentPosition());
        }
}}
