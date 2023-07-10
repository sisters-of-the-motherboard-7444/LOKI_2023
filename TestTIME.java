package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp

// created by ME Zifchak - updated 7/7/23 15:59:56

public class TestTIME extends LinearOpMode {
    private DcMotor slidesLeft;
    private DcMotor slidesRight;
    
    @Override
    public void runOpMode() throws InterruptedException {
        slidesLeft = hardwareMap.get(DcMotorEx.class, "slidesLeft");
        slidesRight = hardwareMap.get(DcMotorEx.class, "slidesRight");

        // reversing right motor!
        slidesRight.setDirection(DcMotor.Direction.REVERSE);
        slidesLeft.setDirection(DcMotor.Direction.FORWARD);
        
        // adorable little telemetry messages from old tele-ops
        //System.out.printIn("Starting up...");
        //telemetry.addData("init pressed", "about to initialize");
        //telemetry.update();
        
        //System.out.printIn("Initialize Robot");
        //Pichu.initializeRobot(hardwareMap);
        //System.out.printIn("Robot Initialized");
        
        telemetry.addData("Status", "Ready to run!");
        telemetry.update();
        
        waitForStart();
        
        while (opModeIsActive()) {
            
            // going down!
            if (gamepad1.a) {
                slidesLeft.setPower(-.75);
                slidesRight.setPower(-.75);
            }
            // going down but slower!
            else if (gamepad1.x) {
                slidesLeft.setPower(-0.5);
                slidesRight.setPower(-0.5);
            }
            // going up!
            else if (gamepad1.y) {
                slidesLeft.setPower(.75);
                slidesRight.setPower(.75);
            }
            // going up but slower!
            else if (gamepad1.b) {
                slidesLeft.setPower(0.5);
                slidesRight.setPower(0.5);
            }
            else {
                slidesLeft.setPower(0);
                slidesRight.setPower(0);
            }
        }
    }

}
