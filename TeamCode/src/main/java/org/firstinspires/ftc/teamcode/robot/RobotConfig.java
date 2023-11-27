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
    public static final String ELBOW = "elbow";
    public static final String ROTATOR = "rotator";
    public static final String BUCKET = "wrist";
    public static final String SORTER = "sorter";

    public static final String MINIARM = "miniArm";

    public static final String DRONE_LAUNCHER = "droneLauncher";

    public static final double WRIST_STARTING_POSITION = .9;
    public static final double WRIST_DEPOSIT_POSITION_1 = .929;
    public static final double WRIST_DEPOSIT_POSITION_3 = .45;
    public static final double WRIST_INTAKE_POSITION = .699;
    public static final double WRIST_INTERIM_TRAVEL_POSITION = .699;
    public static final double WRIST_TRAVEL_POSITION = .464;

    public static final double WRIST_DUMP_POSITION = 0.435;

    public static final double ROTATOR_STARTING_POSITION = 1;
    public static final double ROTATOR_TURNED_OVER_POSITION = .269;

    public static final double SORTER_LEFT_POSITION = 0.66;
    public static final double SORTER_RIGHT_POSITION = .24;

    public static final double DRONE_TRIGGER_INITIAL_POSITION = 1;
    public static final double DRONE_TRIGGER_RELEASE_POSITION = 0.3;
    public static final double TRIGGER_INCREMENT = 0.01;

    public static final double MINIARM_INITIAL_POSITION = 0.39;
    public static final double MINIARM_MIDDLE_POSITION = 0.5;
    public static final double MINIARM_DROP_POSITION = 0.93;

    public static final double CAUTIOUS_SPEED = 0.35;

    //Robot center from back is five and half inches away
    public static double ROBOT_CENTER_FROM_BACK = 5.5 * Field.MM_PER_INCH;

    //Robot center from front is four and a half inches
    public static double ROBOT_CENTER_FROM_FRONT = 4.5 * Field.MM_PER_INCH;
    public static final double ROBOT_WIDTH = 14.5 * Field.MM_PER_INCH;

    public static final double ROBOT_LENGTH = ROBOT_CENTER_FROM_BACK + ROBOT_CENTER_FROM_FRONT;

    public static final long SERVO_REQUIRED_TIME = 500; //500 milli-seconds for servo to function

    public static final int ACCEPTABLE_ELBOW_ERROR = 10;
    public static final double MAX_ELBOW_POWER = 1;

    public static final int ACCEPTABLE_SHOULDER_ERROR = 10;
    public static final double MAX_SHOULDER_POWER = 1.0;

    public static final int SHOULDER_STARTING_POSITION = 0;
    public static final int SHOULDER_INTAKE_POSITION = 0;
    public static final int SHOULDER_INTERIM_TRAVEL_POSITION = 0;
    public static final int SHOULDER_TRAVEL_POSITION = 560;
    public static final int SHOULDER_DEPOSIT_POSITION = 1730;

    public static final int ELBOW_STARTING_POSITION = 0;
    public static final int ELBOW_INTAKE_POSITION = -2272;
    public static final int ELBOW_TRAVEL_POSITION = 0;
    public static final int ELBOW_INTERIM_TRAVEL_POSITION = -1000;
    public static final int ELBOW_INTERIM_DEPOSIT_POSITION = -1400;
    public static final int ELBOW_DEPOSIT_POSITION = -2365;


    public static final ArmPosition ARM_STARTING_POSITION = new ArmPosition(
            SHOULDER_STARTING_POSITION,
            ELBOW_STARTING_POSITION,
            ROTATOR_STARTING_POSITION,
            WRIST_STARTING_POSITION,
            SORTER_LEFT_POSITION);
    public static final ArmPosition ARM_INTAKE_POSITION = new ArmPosition(
            SHOULDER_INTAKE_POSITION,
            ELBOW_INTAKE_POSITION,
            ROTATOR_STARTING_POSITION,
            WRIST_INTAKE_POSITION,
            SORTER_LEFT_POSITION);
    public static final ArmPosition ARM_DEPOSIT_POSITION_1 = new ArmPosition(
            SHOULDER_DEPOSIT_POSITION,
            ELBOW_INTERIM_DEPOSIT_POSITION,
            ROTATOR_TURNED_OVER_POSITION,
            WRIST_TRAVEL_POSITION,
            SORTER_LEFT_POSITION);
    public static final ArmPosition ARM_DEPOSIT_POSITION_2 = new ArmPosition(
            SHOULDER_DEPOSIT_POSITION,
            ELBOW_INTERIM_DEPOSIT_POSITION,
            ROTATOR_TURNED_OVER_POSITION,
            WRIST_INTERIM_TRAVEL_POSITION,
            SORTER_LEFT_POSITION);
    public static final ArmPosition ARM_DEPOSIT_POSITION_3 = new ArmPosition(
            SHOULDER_DEPOSIT_POSITION,
            ELBOW_DEPOSIT_POSITION,
            ROTATOR_TURNED_OVER_POSITION,
            WRIST_DEPOSIT_POSITION_3,
            SORTER_LEFT_POSITION);
    public static final ArmPosition ARM_TRAVEL_POSITION = new ArmPosition(
            SHOULDER_TRAVEL_POSITION,
            ELBOW_TRAVEL_POSITION,
            ROTATOR_TURNED_OVER_POSITION,
            WRIST_TRAVEL_POSITION,
            SORTER_LEFT_POSITION);
    public static final ArmPosition ARM_INTERIM_TRAVEL_POSITION = new ArmPosition(
            SHOULDER_INTERIM_TRAVEL_POSITION,
            ELBOW_INTERIM_TRAVEL_POSITION,
            ROTATOR_STARTING_POSITION,
            WRIST_INTERIM_TRAVEL_POSITION,
            SORTER_LEFT_POSITION);


    public static final String INOUT_TAKE_MOTOR = "inoutMotor";
    public static final double MAX_INOUT_SPEED = 1.0;

    public static final int X_PIXEL_COUNT = 2304;
    public static final int Y_PIXEL_COUNT = 1536;

    public static final double IN_OUT_TAKE_IN_POWER = -1;
    public static final double IN_OUT_TAKE_OUT_POWER = 1;
    public static final double IN_OUT_TAKE_STOP_POWER = 0;
 }
