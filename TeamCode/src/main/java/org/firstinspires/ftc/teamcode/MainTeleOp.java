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

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //motor will brake when not moving

        // Note: motors spin clockwise by default


    }

    // Runs given motor at 1 when cond is true
    private void testMotor(boolean cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4 ) {
        if(cond) { dc.setPower(-1); } else { dc.setPower(0); }  //changed from 1 to -1
        if(cond) { dc2.setPower(1); } else { dc2.setPower(0); }
        if(cond) { dc3.setPower(1); } else { dc3.setPower(0); } //no change
        if(cond) { dc4.setPower(-1); } else { dc4.setPower(0); } //no change
    }
    private void testMotorback(boolean cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4 ) {
        if(cond) { dc.setPower(1); } else { dc.setPower(0); }   //changed from -1 to 1
        if(cond) { dc2.setPower(-1); } else { dc2.setPower(0); } //changed from -1 to 1
        if(cond) { dc3.setPower(-1); } else { dc3.setPower(0); }    //no change
        if(cond) { dc4.setPower(1); } else { dc4.setPower(0); }    //no change
    }

    private void testMotorright(boolean cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4 ) {
        if(cond) { dc.setPower(1); } else { dc.setPower(0); }   //changed from 1 to -1
        if(cond) { dc2.setPower(1); } else { dc2.setPower(0); }    // no change
        if(cond) { dc3.setPower(1); } else { dc3.setPower(0); } //changed from 1 to -1
        if(cond) { dc4.setPower(1); } else { dc4.setPower(0); } // no change
    }
    private void testMotorleft(boolean cond, DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4 ) {
        if(cond) { dc.setPower(-1); } else { dc.setPower(0); }  //no change
        if(cond) { dc2.setPower(-1); } else { dc2.setPower(0); }    //changed from 1 to -1
        if(cond) { dc3.setPower(-1); } else { dc3.setPower(0); }    //no change
        if(cond) { dc4.setPower(-1); } else { dc4.setPower(0); }    //changed from 1 to -1
    }


    // Runs repeatedly
    public void loop(){
        // Check if buttons are being pressed and run motors
        testMotor(gamepad1.dpad_up, tl,tr,bl,br);
        testMotorback(gamepad1.dpad_down, tl,tr,bl,br);
        testMotorright(gamepad1.dpad_right, tl,tr,bl,br);
        testMotorleft(gamepad1.dpad_left, tl,tr,bl,br);

        // double lift = gamepad1.left_stick_y;
        double power;
        power    = Range.clip(-gamepad1.left_stick_y,-0.1, 0.1) ;  //change min between -1 and 0, max between 0 and 1 to make slower/faster
        arm.setPower(power);


    }
}

