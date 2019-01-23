package org.firstinspires.ftc.teamcode;

import android.widget.Switch;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;

@Autonomous(name="ImageRecognition", group="Linear Opmode")
public class ImageRecognitionTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    DcMotor tl, tr, bl, br, arm, lift;
    private CRServo intake1, intake2, intake3, intake4;
    private GoldAlignDetector detector;
    private OpenGLMatrix lastLocation = null;
    private ElapsedTime runtime2 = new ElapsedTime();

    public void runOpMode() {
        new GoldAlignDetector();

        detector = new GoldAlignDetector(); // Create detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
        detector.useDefaults(); // Set detector to use default settings
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 310; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment

        detector.enable(); // Start the detector!

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        tl = hardwareMap.dcMotor.get("top_left_wheel");
        tr = hardwareMap.dcMotor.get("top_right_wheel");
        bl = hardwareMap.dcMotor.get("bottom_left_wheel");
        br = hardwareMap.dcMotor.get("bottom_right_wheel");
        arm = hardwareMap.dcMotor.get("arm");
        lift = hardwareMap.dcMotor.get("lift");
        intake1 = hardwareMap.crservo.get("intake1");
        intake2 = hardwareMap.crservo.get("intake2");
        intake3 = hardwareMap.crservo.get("intake3");
        intake4 = hardwareMap.crservo.get("intake4");

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        lift.setDirection(DcMotorSimple.Direction.REVERSE);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (detector.isFound()) {
            EncoderDrive(0.001, 1190, tl, tr, bl, br);
            tl.setPower(0);
            tr.setPower(0);
            br.setPower(0);
            bl.setPower(0);
        } else {
            sleep(1000);
            tl.setPower(1);
            tr.setPower(1);
            br.setPower(1);
            bl.setPower(1);
            sleep(200);
            tl.setPower(0);
            tr.setPower(0);
            br.setPower(0);
            bl.setPower(0);
            sleep(250);
        }
      /*  if (detector.isFound()) {
            telemetry.addLine("Found");
            telemetry.update();
            tl.setPower(0.5);
            tr.setPower(-0.5);
            br.setPower(0.5);
            bl.setPower(-0.5);
            sleep(1000);
            tl.setPower(0);
            tr.setPower(0);
            br.setPower(0);
            bl.setPower(0);
        } else {
            sleep(1000);
            tl.setPower(-1);
            tr.setPower(-1);
            br.setPower(-1);
            bl.setPower(-1);
            sleep(400);
            tl.setPower(0);
            tr.setPower(0);
            br.setPower(0);
            bl.setPower(0);

            sleep(100);

            tl.setPower(0.5);
            tr.setPower(-0.5);
            br.setPower(0.5);
            bl.setPower(-0.5);
            sleep(1000);
            tl.setPower(0);
            tr.setPower(0);
            br.setPower(0);
            bl.setPower(0);
        }*/
    }
    private void pEncoderMotorRun(double kP, double target, DcMotor driveMotor) { //nate
        double error = Math.abs(target - driveMotor.getCurrentPosition());//obtains the robot's position
        double time;
        time = runtime.time();
        while (error > 1 && opModeIsActive()) {//allows the robot to continually operate
            driveMotor.setPower(kP * error);
            error = Math.abs(target - driveMotor.getCurrentPosition());
        }
    }
    private void EncoderDrive(double kP, double target, DcMotor driveMotor, DcMotor driveMotor2, DcMotor driveMotor3, DcMotor driveMotor4) { //nate
        double error = Math.abs(target - driveMotor4.getCurrentPosition());//obtains the robot's position
       // double error2 = Math.abs(target - driveMotor2.getCurrentPosition());//obtains the robot's position
        //double error3 = Math.abs(target - driveMotor3.getCurrentPosition());//obtains the robot's position
        //double error4 = Math.abs(target - driveMotor4.getCurrentPosition());//obtains the robot's position
        double time;
        time = runtime.time();
        while (error > 1 && opModeIsActive()) {//allows the robot to continually operate
            driveMotor.setPower(kP * error);
            driveMotor2.setPower(kP * -error);
            driveMotor3.setPower(kP * -error);
            driveMotor4.setPower(kP * error);
            error = Math.abs(target - driveMotor.getCurrentPosition());
            telemetry.addData("error = ", error);
        }
        driveMotor4.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while (error > 1 && opModeIsActive()) {//allows the robot to continually operate
            driveMotor.setPower(kP * error);
            driveMotor2.setPower(kP * error);
            driveMotor3.setPower(kP * error);
            driveMotor4.setPower(kP * error);
            error = Math.abs(target - driveMotor4.getCurrentPosition());
        }
      /*  while (error2 > 1 && opModeIsActive()) {//allows the robot to continually operate
            driveMotor2.setPower(kP * error);
            error = Math.abs(target - driveMotor2.getCurrentPosition());
        }
        while (error3 > 1 && opModeIsActive()) {//allows the robot to continually operate
            driveMotor3.setPower(kP * error);
            error = Math.abs(target - driveMotor3.getCurrentPosition());
        }
        while (error4 > 1 && opModeIsActive()) {//allows the robot to continually operate
            driveMotor4.setPower(kP * error);
            error = Math.abs(target - driveMotor4.getCurrentPosition());
        }*/
    }
}