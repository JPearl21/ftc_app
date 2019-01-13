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

        tl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        tl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.clear();

        waitForStart();

        while(opModeIsActive()){
            tl.setTargetPosition(100);
            tr.setTargetPosition(100);
            bl.setTargetPosition(100);
            br.setTargetPosition(100);

            tl.setPower(0.5);
            tr.setPower(-0.5);
            bl.setPower(-0.5);
            br.setPower(0.5);


            tl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            tr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("tl", tl.getCurrentPosition());
            telemetry.addData("tr", tr.getCurrentPosition());
            telemetry.addData("bl", bl.getCurrentPosition());
            telemetry.addData("br", br.getCurrentPosition());
            telemetry.update();

            tl.setPower(0);
            tr.setPower(0);
            bl.setPower(0);
            br.setPower(0);


        }

    }
}
