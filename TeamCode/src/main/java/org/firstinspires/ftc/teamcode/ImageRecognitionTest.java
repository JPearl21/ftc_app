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

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        lift.setDirection(DcMotorSimple.Direction.REVERSE);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (detector.isFound()) {
            pEncoderMotorRun(0.001, );
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
        if (detector.isFound()) {
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
        }
        }
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
}