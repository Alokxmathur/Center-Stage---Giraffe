 package org.firstinspires.ftc.teamcode.robot;

 import org.firstinspires.ftc.teamcode.game.Field;
 import org.firstinspires.ftc.teamcode.robot.components.ArmPosition;

 public class RobotConfig {
    public static final double SERVO_INCREMENT = 0.005;
    //drive train motors
    public static final String LEFT_FRONT_DRIVE = "leftFrontDrive";
    public static final String LEFT_REAR_DRIVE = "leftRearDrive";
    public static final String RIGHT_REAR_DRIVE = "rightRearDrive";
    public static final String RIGHT_FRONT_DRIVE = "rightFrontDrive";
    public static final String WEBCAM_ID = "Webcam 1";
    public static final String BLINKIN = "blinkin";
    public static final String SHOULDER = "shoulder";
    public static final String SLIDE = "slide";
    public static final String INOUT_MOTOR = "inOutMotor";
    public static final String WRIST = "wrist";
    public static final String SHOULDER_LIMIT_SWITCH = "shoulderLimitSwitch";
    public static final String SLIDE_LIMIT_SWITCH = "slideLimitSwitch";
    public static final String Pixel_Releaser = "pixelReleaser";
    public static final String DRONE_LAUNCHER = "droneLauncher";
    public static final String DRONE_HOLDER = "droneHolder";

    public static final int WRIST_STARTING_POSITION = 0;
    public static final int WRIST_DEPOSIT_POSITION_3 = 0;
    public static final int WRIST_INTAKE_POSITION = 0;
    public static final int WRIST_INTERIM_TRAVEL_POSITION = 0;
    public static final int WRIST_TRAVEL_POSITION = 0;
    public static final int WRIST_DUMP_POSITION = 0;

    public static final double DRONE_TRIGGER_INITIAL_POSITION = 1;
    public static final double DRONE_TRIGGER_RELEASE_POSITION = 0.3;
    public static final double DRONE_HOLDER_INITIAL_POSITION = .289;
    public static final double DRONE_HOLDER_RELEASE_POSITION = 0.5;
    public static final double TRIGGER_INCREMENT = 0.01;
    public static final double CAUTIOUS_SPEED = 0.6;
    public static final double APRIL_TAG_SPEED = 0.3;


    //Robot center from back is five and half inches away
    public static double ROBOT_CENTER_FROM_BACK = 8 * Field.MM_PER_INCH;

    //Robot center from front is four and a half inches
    public static double ROBOT_CENTER_FROM_FRONT = 8 * Field.MM_PER_INCH;
    public static final double ROBOT_WIDTH = 14.5 * Field.MM_PER_INCH;

    public static final double ROBOT_LENGTH = ROBOT_CENTER_FROM_BACK + ROBOT_CENTER_FROM_FRONT;

    public static final long SERVO_REQUIRED_TIME = 500; //500 milli-seconds for servo to function

    public static final int ACCEPTABLE_SLIDE_ERROR = 10;
    public static final double MAX_SLIDE_POWER = 1;

    public static final int ACCEPTABLE_SHOULDER_ERROR = 10;
    public static final double MAX_SHOULDER_POWER = 0.5;

    public static final int ACCEPTABLE_WRIST_ERROR = 5;
    public static final double MAX_WRIST_POWER = 0.5;
    public static final int SHOULDER_STARTING_POSITION = 0;
    public static final int SHOULDER_INTAKE_POSITION = 180;
    public static final int SHOULDER_INTERIM_TRAVEL_POSITION = 0;
    public static final int SHOULDER_DEPOSIT_POSITION = 1630;
    public static final int SLIDE_STARTING_POSITION = 0;
    public static final int SLIDE_INTAKE_POSITION = 0;
    public static final int SLIDE_TRAVEL_POSITION = -1646;
    public static final int SLIDE_INTERIM_TRAVEL_POSITION = -1646;
    public static final int SLIDE_INTERIM_DEPOSIT_POSITION = -1400;
    public static final int SLIDE_DEPOSIT_POSITION = -2365;
    public static final int SLIDE_AUTO_DEPOSIT_POSITION = -1800;

    public static final double RELEASER_RETAIN_POSITION = .5;
    public static final double RELEASER_RELEASE_POSITION = .75;



    public static final ArmPosition ARM_STARTING_POSITION = new ArmPosition(
            0,
            0,
            0);
    public static final ArmPosition ARM_INTAKE_POSITION = new ArmPosition(
            50,
            -18,
            RELEASER_RETAIN_POSITION);
    public static final ArmPosition ARM_DEPOSIT_POSITION_1 = new ArmPosition(
            -300,
            321,
            RELEASER_RETAIN_POSITION);
    public static final ArmPosition ARM_DEPOSIT_POSITION_2 = new ArmPosition(
            -1467,
            295,
            RELEASER_RETAIN_POSITION);
    public static final ArmPosition ARM_DEPOSIT_POSITION_3 = new ArmPosition(
            0,
            SLIDE_DEPOSIT_POSITION,
            RELEASER_RETAIN_POSITION);
    public static final ArmPosition ARM_TRAVEL_POSITION = new ArmPosition(
            SLIDE_TRAVEL_POSITION,
            WRIST_TRAVEL_POSITION,
            RELEASER_RETAIN_POSITION);
    public static final ArmPosition ARM_RAISED_POSITION = new ArmPosition(
            140,
            -620,
            WRIST_TRAVEL_POSITION);
    public static final ArmPosition ARM_INTERIM_TRAVEL_POSITION = new ArmPosition(
            SHOULDER_INTERIM_TRAVEL_POSITION,
            SLIDE_INTERIM_TRAVEL_POSITION,
            WRIST_INTERIM_TRAVEL_POSITION);

    public static final ArmPosition ARM_AUTO_DEPOSIT_POSITION = new ArmPosition(
            500,
            -1490,
            0);

    public static final ArmPosition ARM_PRE_HANG_POSITION = new ArmPosition(
            2300,
            -2000,
            0);


    public static final ArmPosition ARM_HANG_POSITION_1 = new ArmPosition(
            2300,
            -50,
            0);
    public static final ArmPosition ARM_HANG_POSITION_2 = new ArmPosition(
            0,
            -50,
            0);

    public static final int X_PIXEL_COUNT = 1280;
    public static final int Y_PIXEL_COUNT = 720;
 }
