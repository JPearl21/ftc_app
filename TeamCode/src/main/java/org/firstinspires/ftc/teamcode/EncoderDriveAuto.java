package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name="Disclaimer: Nate does mechanical.")
public class EncoderDriveAuto extends LinearOpMode {


    DcMotor tl, tr, bl, br;

    @Override
    public void runOpMode() throws InterruptedException {
        tl = hardwareMap.dcMotor.get("top_left_wheel");
        tr = hardwareMap.dcMotor.get("top_right_wheel");
        bl = hardwareMap.dcMotor.get("bottom_left_wheel");
        br = hardwareMap.dcMotor.get("bottom_right_wheel");

        tr.setDirection(DcMotor.Direction.FORWARD);
        tl.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);

        setMotorTargets(1120, 1120, 1120, 1120);

        tl.setPower(1);
        tr.setPower(1);
        bl.setPower(1);
        br.setPower(1);
        while (tl.isBusy()) {
            Thread.yield();
        }
        tl.setPower(0);
        tr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    private void setMotorModes(DcMotor.RunMode mode) {
        tr.setMode(mode);
        tl.setMode(mode);
        bl.setMode(mode);
        br.setMode(mode);
    }

    private void setMotorTargets(int tlTarget, int trTarget, int blTarget, int brTarget) {
        tr.setTargetPosition(trTarget);
        tl.setTargetPosition(tlTarget);
        bl.setTargetPosition(blTarget);
        br.setTargetPosition(brTarget);
    }

}
