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

      @Override
      public void runOpMode() throws InterruptedException {
        Pichu.InitializeRobot(hardwareMap);

        //int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        //webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        //aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        //webcam.setPipeline(aprilTagDetectionPipeline);
        //webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {

        @Override
        public void onOpened() {
          //webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
        }

        @Override
        public void onError(int errorCode) {
          // if the camera is not opened
          //telemetry.addLine(String.format("\nCamera had an error=%d", errorCode));
          }
        });

        telemetry.addLine("Waiting for start");
        telemetry.update();

        waitForStart();

        telemetry.setMsTransmissionInterval(50);

        while (opModeIsActive()) {

          AprilTagProcessor SillyTagProcessor;
          List<AprilTagDetection> SillyTagDetections;
          AprilTagDetection SilyTagDetection;
          int SillyTagIdCode;

          // get a list of apriltag detections
          SillyTagDetections = SillyTagProcessor.getDetections();
          // cycle through the list and process each tag
          for (SillyTagDetection : SillyTagDetections) {
            if (SillyTagDetection.metadata != null) {
              SillyTagIdCode = SillyTagDetection.id;
              // now take action based on id code

          int signalCone = 0;

          if (detections != null) {
            telemetry.addData("FPS", webcam.getFps());
            telemetry.addData("Overhead ms", webcam.getOverheadTimeMs());
            telemetry.addData("Pipeline ms", webcam.getPipelineTimeMs());
            telemetry.addData("Pipeline ms", webcam.getPipelineTimeMs());

            // If we don't see any tags
            if (detections.size() == 0) {
              numFramesWithoutDetection++;

              // If we haven't seen a tag for a few frames, lower the decimation
              // so we can hopefully pick one up if we're e.g. far back
              if (numFramesWithoutDetection >= THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION) {
                aprilTagDetectionPipeline.setDecimation(DECIMATION_LOW);
              }
            }
            // We do see tags!
            else {
              numFramesWithoutDetection = 0;

              // If the target is within 1 meter, turn on high decimation to
              // increase the frame rate
              if (detections.get(0).pose.z < THRESHOLD_HIGH_DECIMATION_RANGE_METERS) {
                aprilTagDetectionPipeline.setDecimation(DECIMATION_HIGH);
              }

              for (AprilTagDetection detection : detections) {
                // TODO: make info accessible outside of this class: int aprilTagId = detection.id;

                signalCone = 0; //Check
                signalCone = detection.id;//Check

                telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
                telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x * FEET_PER_METER));
                telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y * FEET_PER_METER));
                telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z * FEET_PER_METER));
                telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
                telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
                telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
              }
            }

            telemetry.update();
          }
        

        }
    }
}
    
