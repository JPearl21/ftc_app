package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;

/**
 * hi iit is Eliezer in 09; Dis now Jacob 18
 * douglas edit
 * Owen Edit
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
    private void testMotor(float cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4 ) {
        if(Math.abs(cond) > 0) { dc.setPower(1); } else { dc.setPower(0); }  //changed from 1 to -1
        if(Math.abs(cond) > 0) { dc2.setPower(1); } else { dc2.setPower(0); }
        if(Math.abs(cond) > 0) { dc3.setPower(1); } else { dc3.setPower(0); } //no change
        if(Math.abs(cond) > 0) { dc4.setPower(1); } else { dc4.setPower(0); } //no change
    }
    private void testMotorback(float cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4 ) {
        if(Math.abs(cond) > 0) { dc.setPower(-1); } else { dc.setPower(0); }   //changed from -1 to 1
        if(Math.abs(cond) > 0) { dc2.setPower(-1); } else { dc2.setPower(0); } //changed from -1 to 1
        if(Math.abs(cond) > 0) { dc3.setPower(-1); } else { dc3.setPower(0); }    //no change
        if(Math.abs(cond) > 0) { dc4.setPower(-1); } else { dc4.setPower(0); }    //no change
    }

    private void testMotorright(float cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4 ) {
        if(Math.abs(cond) > 0) { dc.setPower(1); } else { dc.setPower(0); }   //changed from 1 to -1
        if(Math.abs(cond) > 0) { dc2.setPower(1); } else { dc2.setPower(0); }    // no change
        if(Math.abs(cond) > 0) { dc3.setPower(-1); } else { dc3.setPower(0); } //changed from 1 to -1
        if(Math.abs(cond) > 0) { dc4.setPower(1); } else { dc4.setPower(0); } // no change
    }
    private void testMotorleft(float cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4 ) {
        if(Math.abs(cond) > 0) { dc.setPower(-1); } else { dc.setPower(0); }  //no change
        if(Math.abs(cond) > 0) { dc2.setPower(1); } else { dc2.setPower(0); }    //changed from 1 to -1
        if(Math.abs(cond) > 0) { dc3.setPower(-1); } else { dc3.setPower(0); }    //no change
        if(Math.abs(cond) > 0) { dc4.setPower(1); } else { dc4.setPower(0); }    //changed from 1 to -1
    }


    // Runs repeatedly
    public void loop(){
        // Check if buttons are being pressed and run motors
        while (gamepad1.left_stick_y > 0 && ((gamepad1.left_stick_x < gamepad1.left_stick_y))) {
            testMotor(gamepad1.left_stick_y, tl, tr, bl, br);
        }
        while (gamepad1.left_stick_y <0 && (gamepad1.left_stick_y < gamepad1.left_stick_x)) {
            testMotorback(gamepad1.left_stick_y, tl, tr, bl, br);
        }
        while (gamepad1.left_stick_x > 0 && (gamepad1.left_stick_x > gamepad1.left_stick_y)){
            testMotorright(gamepad1.left_stick_x, tl, tr, bl, br);
        }
        while (gamepad1.left_stick_x <0 && (gamepad1.left_stick_x < gamepad1.left_stick_y)) {
            testMotorleft(gamepad1.left_stick_x, tl, tr, bl, br);
        }

        // double lift = gamepad1.left_stick_y;
        double power;
        power    = Range.clip(-gamepad1.right_stick_y,-0.45, 0.45) ;  //change min between -1 and 0, max between 0 and 1 to make slower/faster
        arm.setPower(power);

        if(gamepad1.a){
            intake1.setPower(1);
            intake2.setPower(1);
        }
        if(gamepad1.b) {
            intake1.setPower(-1);
            intake2.setPower(-1);
        }
        while(gamepad1.y){
            intake1.setPower(1);
            intake2.setPower(1);
            intake3.setPower(0.5);
            intake4.setPower(0.5);
        }



    }
}

