package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

@Autonomous(name = "NAME", group = "Sensor")
  public class NAME extends LinearOpMode {

    hardwareMap Pichu = new hardwareMap();
    /* VISION PROCESSOR INITIALIZATION */

    // create apriltag processor + assign variable
    AprilTagProcessor SillyTagProcessor;
    SillyTagProcessor = AprilTagProcessor.easyCreateWithDefaults()
    // create tensorflow object detection processor + assign variable
    TfodProcessor SillyTensorProcessor;
    SillyTensorProcessor = TfodProcessor.easyCreateWithDefaults();

    /* VISIONPORTAL INITIALIZATION */

    // create a visionportal w/ specified camera and tag processor + assign variable
    VisioPortal SillyVisionPortal;
    SillyVisionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WEBCAMNAME.class, "WEBCAM NAME"), SillyTagProcessor);
    //SillyVisionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WEBCAMNAME.class, "WEBCAM NAME"), SillyTagProcessor, SillyTensorProcessor);

    /* BEGINNING DETECTION AFTER INITING SILLINESS */

    //AprilTagDetection SillyTagDetection;
    //int TAGIDCODE = SillyTagDetection.id;

    static final double FEET_PER_METER = 3.28084;

    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;
    double tagsize = 0.166;

    int numFramesWithoutDetection = 0;

    final float DECIMATION_HIGH = 3;
    final float DECIMATION_LOW = 2;
    final float THRESHOLD_HIGH_DECIMATION_RANGE_METERS = 1.0f;
    final int THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION = 4;

      @override
      public void runOpMode() throws InterruptedException {
        Pichu.InitializeRobot(hardwareMap);

        AprilTagDetection SillyTagDetection;
        int TAGIDCODE = SillyTagDetection.id;

    

      }
}
    
