package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import java.util.List;

/**
 * hi iit is Eliezer in 09; Dis now Jacob 18
 * Douglas Edit (Duggo prefers geek rather than Nerd, a.k.a what Owen is)
 * Owen Edit (Owen is Nerd though)
 */

@TeleOp(name="DuggoArmEncoder", group="Test")
public class DuggoArmEncoder extends OpMode{//cool bearl real cool

    /*
    Part declarations.
    Add parts here as we progress
    */

    // Top left, top right, bottom left, bottom right, arm motors
    private DcMotor tl, tr, bl, br, arm, lift;
    private CRServo intake1, intake2, intake3, intake4; //1 is tr, 2 is tl, 3 is br, 4 is bl
    private float   leftPower, rightPower, xValue, yValue;


    // Code to run after init is hit
    public void init(){

        /*
        Register all parts. Note that the string passed
        is what the phone looks for in its configuration
        to map software motors to their actual hardware motor
        */
        tl = hardwareMap.dcMotor.get("top_left_wheel");
        tr = hardwareMap.dcMotor.get("top_right_wheel");
        bl = hardwareMap.dcMotor.get("bottom_left_wheel");
        br = hardwareMap.dcMotor.get("bottom_right_wheel");
        arm = hardwareMap.dcMotor.get("arm");
        intake1 = hardwareMap.crservo.get("intake1");
        intake2 = hardwareMap.crservo.get("intake2");
        intake3 = hardwareMap.crservo.get("intake3");
        intake4 = hardwareMap.crservo.get("intake4");
        lift = hardwareMap.dcMotor.get("lift");

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);


        // Note: motors spin clockwise by default


    }
    // Runs repeatedly
    public void loop(){
        telemetry.addData("Mode", "running");
        telemetry.addData("Duggo", "Be cool");
        telemetry.update();

        reboundMecanumDrive(-(gamepad1.left_stick_x), gamepad1.left_stick_y, gamepad1.right_stick_x); //left right, forwards backwards, turning

        /*double power;
        power    = Range.clip(-gamepad1.right_stick_y, -0.6, 0.6) ;//change min between -1 and 0, max between 0 and 1 to make slower/faster
        arm.setPower(power);*/

        if(gamepad2.dpad_up){arm.setPower(-1);
        telemetry.addLine(String.valueOf(arm.getCurrentPosition()));}
        if(gamepad2.dpad_down){arm.setPower(1);}
        if(gamepad2.y){
            if (arm.getCurrentPosition() >= 0){
                pArmToLanderFromRest(0.0018, -4048, arm);
            }
        }
        if(gamepad2.x){
            if (arm.getCurrentPosition() <= 0){
                pArmToLanderFromGround(0.0018, 0, arm);
            }
        } //automatically brings arm up un lander position

        if(!(gamepad2.dpad_up || gamepad2.dpad_down || gamepad2.x)){arm.setPower(0);}

        // Check if buttons are being pressed and run servos
        if(gamepad2.a){
            intake1.setPower(1);
            intake2.setPower(-1);
        }
        else{if(!(gamepad2.right_bumper || gamepad2.left_bumper || gamepad2.a || gamepad2.b )){ intake1.setPower(0); intake2.setPower(0);}}

        if(gamepad2.b){
            intake3.setPower(1);
            intake4.setPower(-1);
        }
        else{if(!(gamepad2.right_bumper || gamepad2.left_bumper || gamepad2.a || gamepad2.b )){ intake3.setPower(0); intake4.setPower(0);}}

        if(gamepad2.right_bumper){
            intake1.setPower(-1);
            intake2.setPower(1);
        }
        else{if(!(gamepad2.right_bumper || gamepad2.left_bumper || gamepad2.a || gamepad2.b )){ intake1.setPower(0); intake2.setPower(0);}}

        if(gamepad2.left_bumper){
            intake3.setPower(-1);
            intake4.setPower(1);
        }
        else{if(!(gamepad2.right_bumper || gamepad2.left_bumper || gamepad2.a || gamepad2.b )){ intake3.setPower(0); intake4.setPower(0);}}

        if(gamepad2.right_trigger > 0){lift.setPower(gamepad2.right_trigger);}
        else{if(gamepad2.left_trigger > 0){lift.setPower(-gamepad2.left_trigger);}
        else{lift.setPower(0);}}

        telemetry.addLine(String.valueOf(lift.getCurrentPosition()));

    }

    public void reboundMecanumDrive(double vtX, double vtY, double vR) {
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

    private void pArmToLanderFromGround(double kP, double target, DcMotor driveMotor) {
        double error = Math.abs(target - driveMotor.getCurrentPosition());//obtains the arm's position
        while (error > 1) {//allows the robot to continually operate
            driveMotor.setPower(kP * error);
            error = Math.abs(target - driveMotor.getCurrentPosition());
        }
        arm.setPower(0);
    }

    private void pArmToLanderFromRest(double kP, double target, DcMotor driveMotor) {
        double error = Math.abs(target - driveMotor.getCurrentPosition());//obtains the arm's position
        while (error > 1 ) {//allows the robot to continually operate
            driveMotor.setPower(kP * -error);
            error = Math.abs(target - driveMotor.getCurrentPosition());
        }
        arm.setPower(0);
    }
}
