package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="EncoderTest", group="Linear Opmode")
public class EncoderTest extends LinearOpMode {
    DcMotor tl, tr, bl, br, arm, lift;
    public void runOpMode(){
        tl = hardwareMap.dcMotor.get("top_left_wheel");
        tr = hardwareMap.dcMotor.get("top_right_wheel");
        bl = hardwareMap.dcMotor.get("bottom_left_wheel");
        br = hardwareMap.dcMotor.get("bottom_right_wheel");
        lift = hardwareMap.dcMotor.get("lift");

        tl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        tr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry.clear();

        waitForStart();

        while(opModeIsActive()){
            // drive one rotation forward
            double posCache = 0;
            posCache = tl.getCurrentPosition();
            /*while (posCache + 1120 > tl.getCurrentPosition()) {
                reboundMecanumDrive(0, 1, 0);
            }*/
            pReboundYDrive(0.04, 1120, tl);
            sleep(2000);
            pReboundXDrive(0.04, 1120*Math.sqrt(2), tl); // compensating for vectors
            sleep(2000);
            pReboundTurn(0.04, 1120, tl);
        }
    }

    private void pReboundXDrive(double kP, double target, DcMotor driveMotor) { //nate
        double error = Math.abs(target - driveMotor.getCurrentPosition());//obtains the robot's position
        while (error > 1 && opModeIsActive()) {//allows the robot to continually operate
            reboundMecanumDrive(-kP*error, 0, 0);
            error = Math.abs(target - driveMotor.getCurrentPosition());
            telemetry.addLine(String.valueOf(error));
        }
        driveMotor.setPower(0);
    }
    private void pReboundYDrive(double kP, double target, DcMotor driveMotor) { //nate
        double error = Math.abs(target - driveMotor.getCurrentPosition());//obtains the robot's position
        while (error > 1 && opModeIsActive()) {//allows the robot to continually operate
            reboundMecanumDrive(0, kP*error,0);
            error = Math.abs(target - driveMotor.getCurrentPosition());
            telemetry.addLine(String.valueOf(error));
        }
        driveMotor.setPower(0);
    }
    private void pReboundTurn(double kP, double target, DcMotor driveMotor) { //nate
        double error = Math.abs(target - driveMotor.getCurrentPosition());//obtains the robot's position
        while (error > 1 && opModeIsActive()) {//allows the robot to continually operate
            reboundMecanumDrive(0, 0, kP*error);
            error = Math.abs(target - driveMotor.getCurrentPosition());
            telemetry.addLine(String.valueOf(error));
        }
        driveMotor.setPower(0);
    }

    public void reboundMecanumDrive(double vtX, double vtY, double vR) {
        // vtX is strafe, vtY is forward and backward, vR is turning
        // calculate motor powers
        double tlPower = vtY + vtX - vR;
        double trPower = vtY - vtX + vR;
        double blPower = -(vtY) - vtX - vR;
        double brPower = -(vtY) + vtX + vR;
        // set motor powers
        tl.setPower(-tlPower);
        tr.setPower(-trPower);
        bl.setPower(blPower);
        br.setPower(brPower);
    }
}
