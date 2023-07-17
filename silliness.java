/* VERSION WITHOUT A BUILDER */

/* VISION PROCESSOR INITIALIZATION */

// create apriltag processor + assign variable
AprilTagProcessor SillyTagProcessor;
SillyTagProcessor = AprilTagProcessor.easyCreateWithDefaults();

// create tensorflow object detection processor + assign variable
TfodProcessor SillyTensorProcessor;
SillyTensorProcessor = TfodProcessor.easyCreateWithDefaults();

// maybe enable and disabling step?

/* VISIONPORTAL INITIALIZATION */

// create a visionportal w/ specified camera and tag processor + assign variable
VisioPortal SillyVisionPortal;
SillyVisionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WEBCAMNAME.class, "WEBCAM NAME"), SillyTagProcessor);
//SillyVisionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WEBCAMNAME.class, "WEBCAM NAME"), SillyTagProcessor, SillyTensorProcessor);
