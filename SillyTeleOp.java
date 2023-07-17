package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp

public class SillyTeleOp extends LinearOpMode{
    public DcMotor motorFrontRight = null;
    public DcMotor motorFrontLeft = null;
    public DcMotor motorBackRight = null;
    public DcMotor motorBackLeft = null;
    public Servo claw;
    public DcMotorEx slidesLeft;
    public DcMotorEx slidesRight;
    
    @Override
    public void runOpMode() throws InterruptedException {
        
        motorFrontRight = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorFrontLeft = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorBackRight = hardwareMap.get(DcMotor.class, "motorBackRight");
        motorBackLeft = hardwareMap.get(DcMotor.class, "motorBackLeft");

        slidesLeft = hardwareMap.get(DcMotorEx.class, "slidesLeft");
        slidesRight = hardwareMap.get(DcMotorEx.class, "slidesRight");
        claw = hardwareMap.servo.get("claw");

        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //motorBackRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        
        // reversing right motor!
        slidesRight.setDirection(DcMotor.Direction.REVERSE);

        // will retrieve imu from hardware map
        // specifices as radians just in case
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        // without this, data retrieving from the IMU throws an exception
        imu.initialize(parameters);
        
        waitForStart();

        while (opModeIsActive()) {
            
            // sticks were reversed for POWEPLAY
            // normally double y should be = -gamepad (we removed negativein 2023)
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Read inverse IMU heading, as the IMU heading is CW positive
            double botHeading = -imu.getAngularOrientation().firstAngle;

            double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
            double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);
            
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);

            // opening the claw!
            if (gamepad1.right_bumper) {
               claw.setPosition(0.2); }
            // closing the claw!
            else if (gamepad1.left_bumper) {
               claw.setPosition(1); }

            // silly claw telemetry!
            telemetry.addData("Servo Position", claw.getPosition());
            telemetry.update();
            
            // going down!
            if (gamepad1.a) {
                slidesLeft.setPower(-.75);
                slidesRight.setPower(-.75); }
            // going down but slower!
            else if (gamepad1.x) {
                slidesLeft.setPower(-0.5);
                slidesRight.setPower(-0.5); }
            // going up!
            else if (gamepad1.y) {
                slidesLeft.setPower(.75);
                slidesRight.setPower(.75); }
            // going up but slower!
            else if (gamepad1.b) {
                slidesLeft.setPower(0.5);
                slidesRight.setPower(0.5); }
            else {
                slidesLeft.setPower(0);
                slidesRight.setPower(0); }
            
            // silly imu!
            if (gamepad1.start) {
                imu.reset(); }
            
        }
    }
}
