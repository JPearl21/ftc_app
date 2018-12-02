package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;

/**
 * hi iit is Eliezer in 09; Dis now Jacob 18
 * Douglas Edit
 * Owen Edit (Owen is Nerd though)
 */

@TeleOp(name="Part test", group="Test")
public class MainTeleOp extends OpMode{//cool bearl real cool

    /*
    Part declarations.
    Add parts here as we progress
    */

    // Top left, top right, bottom left, bottom right, arm motors
    private DcMotor tl, tr, bl, br, arm;
    private CRServo intake1, intake2, intake3, intake4; //1 is tr, 2 is tl, 3 is br, 4 is bl
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

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Note: motors spin clockwise by default


    }

    // Runs given motor at 1 when cond is true

    //top left and top right switch
    private void testMotor(boolean cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4 ) {
        if(cond) { dc.setPower(1); } else { dc.setPower(0); }  //changed from 1 to -1
        if(cond) { dc2.setPower(1); } else { dc2.setPower(0); }
        if(cond) { dc3.setPower(1); } else { dc3.setPower(0); } //no change
        if(cond) { dc4.setPower(1); } else { dc4.setPower(0); } //no change
    }
    private void testMotorback(boolean cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4 ) {
        if(cond) { dc.setPower(-1); } else { dc.setPower(0); }   //changed from -1 to 1
        if(cond) { dc2.setPower(-1); } else { dc2.setPower(0); } //changed from -1 to 1
        if(cond) { dc3.setPower(-1); } else { dc3.setPower(0); }    //no change
        if(cond) { dc4.setPower(-1); } else { dc4.setPower(0); }    //no change
    }

    private void testMotorright(boolean cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4 ) {
        if(cond) { dc.setPower(-1); } else { dc.setPower(0); }   //changed from 1 to -1
        if(cond) { dc2.setPower(1); } else { dc2.setPower(0); }    // no change
        if(cond) { dc3.setPower(1); } else { dc3.setPower(0); } //changed from 1 to -1
        if(cond) { dc4.setPower(-1); } else { dc4.setPower(0); } // no change
    }
    private void testMotorleft(boolean cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4 ) {
        if(cond) { dc.setPower(1); } else { dc.setPower(0); }  //no change
        if(cond) { dc2.setPower(-1); } else { dc2.setPower(0); }    //changed from 1 to -1
        if(cond) { dc3.setPower(-1); } else { dc3.setPower(0); }    //no change
        if(cond) { dc4.setPower(1); } else { dc4.setPower(0); }    //changed from 1 to -1
    }
    private void strafe(double cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4){
        if(cond < 0) { dc.setPower(1); } else {dc.setPower(0);}
        if(cond < 0) { dc2.setPower(-1); } else {dc2.setPower(0);}
        if(cond < 0) { dc3.setPower(1); } else {dc3.setPower(0);}
        if(cond < 0) { dc4.setPower(-1); } else {dc2.setPower(0);}
        if(cond > 0) { dc.setPower(-1); } else {dc.setPower(0);}
        if(cond > 0) { dc2.setPower(1); } else {dc2.setPower(0);}
        if(cond > 0) { dc3.setPower(-1);} else {dc3.setPower(0);}
        if(cond > 0) { dc4.setPower(1); } else {dc2.setPower(0);}
    }


    // Runs repeatedly
    public void loop(){
        // Check if buttons are being pressed and run motors
        testMotor(gamepad1.dpad_up, tl, tr, bl, br);
        testMotorback(gamepad1.dpad_down, tl, tr, bl, br);
        testMotorright(gamepad1.dpad_right, tl, tr, bl, br);
        testMotorleft(gamepad1.dpad_left, tl, tr, bl, br);

        strafe(gamepad1.left_stick_x, tl,tr,bl,br);

        double power;
        power    = Range.clip(-gamepad1.right_stick_y, -0.6, 0.6) ;  //change min between -1 and 0, max between 0 and 1 to make slower/faster
        arm.setPower(power);

        // Check if buttons are being pressed and run servos
        if(gamepad1.a){
            intake1.setPower(1);
            intake2.setPower(-1);
        }
        else{if(!(gamepad1.right_bumper || gamepad1.left_bumper || gamepad1.a || gamepad1.b )){ intake1.setPower(0); intake2.setPower(0);}}

        if(gamepad1.b){
            intake3.setPower(1);
            intake4.setPower(-1);
        }
        else{if(!(gamepad1.right_bumper || gamepad1.left_bumper || gamepad1.a || gamepad1.b )){ intake3.setPower(0); intake4.setPower(0);}}

        if(gamepad1.right_bumper){
            intake1.setPower(-1);
            intake2.setPower(1);
        }
        else{if(!(gamepad1.right_bumper || gamepad1.left_bumper || gamepad1.a || gamepad1.b )){ intake1.setPower(0); intake2.setPower(0);}}

        if(gamepad1.left_bumper){
            intake3.setPower(-1);
            intake4.setPower(1);
        }
        else{if(!(gamepad1.right_bumper || gamepad1.left_bumper || gamepad1.a || gamepad1.b )){ intake3.setPower(0); intake4.setPower(0);}}


    }
}
