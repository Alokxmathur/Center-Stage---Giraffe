package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.RobotConfig;
import org.firstinspires.ftc.teamcode.robot.operations.ArmOperation;

import java.util.Locale;

public class Arm {
    public static final int CORE_HEX_MOTOR_COUNT_PER_REV = 288;
    public static final int INOUT_GEAR_RATIO = 3;

    DcMotorEx slide, inOutMotor, wrist;

    Servo pixelReleaser;

    DigitalChannel shoulderLimitSwitch, slideLimitSwitch;
    boolean shoulderRetained,
            slideRetained,
            wristRetained,
            shoulderReset = true,
            slideReset,
            shoulderLowered,
            slideLowered;

    public Arm(HardwareMap hardwareMap) {
        this.slideLimitSwitch = hardwareMap.get(DigitalChannel.class, RobotConfig.SLIDE_LIMIT_SWITCH);
        //initialize our slide motor
        this.slide = hardwareMap.get(DcMotorEx.class, RobotConfig.SLIDE);
        this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //initialize our intake motor
        this.inOutMotor = hardwareMap.get(DcMotorEx.class, RobotConfig.INOUT_MOTOR);
        this.inOutMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.inOutMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.inOutMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //initialize our wrist motor
        this.wrist = hardwareMap.get(DcMotorEx.class, RobotConfig.WRIST);
        this.wrist.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.wrist.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.wrist.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.pixelReleaser = hardwareMap.get(Servo.class, RobotConfig.Pixel_Releaser);
        ensureMotorDirections();
        assumeInitialPosition();
    }

    public boolean resetArm() {
        return true;
        /*
        if (!slideReset) {
            //initialize slide (lower and then raise) unless that's already done
            lowerThenRaiseSlide();
            return false;
        }
        else if (!shoulderReset) {
            //initialize shoulder (lower and then raise) unless that's already done
            lowerThenRaiseShoulder();
            return false;
        }
        else if (!intakeReset) {
            abstain();
            intakeReset = true;
            return false;
        }
        return true;

         */
    }
    private boolean lowerThenRaiseSlide() {
        //if the slide limit switch has not yet been pressed
        if (!slideLowered) {
            //find the state of the slide limit switch: true means it is pressed
            if (!(slideLowered = slideLimitSwitch.getState()))
            {
                setSlidePower(.4);
            }
            else {

                setSlidePower(0);
            }
            return false;
        }
        else if (!slideReset) {
            //if the slide had been lowered but not raised since,
            //find if the limit switch has been raised since it was pressed
            if (!(slideReset = !slideLimitSwitch.getState())) {
                setSlidePower(-.1);
                return false;
            }
            else {
                //the limit switch is now not pressed, we are done. reset encoder and return true
                this.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                return true;
            }
        }
        return slideReset;
    }

    public void ensureMotorDirections() {
        this.slide.setDirection(DcMotorSimple.Direction.FORWARD);
        this.inOutMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void assumeInitialPosition() {
        setPositions(RobotConfig.ARM_STARTING_POSITION);
    }

    public void raiseWristIncrementally() {
        setWristPosition(wrist.getCurrentPosition() - 5);
    }

    public void lowerWristIncrementally() {
        setWristPosition(wrist.getCurrentPosition() + 5);
    }

    public void intakePositionWrist() {
        setWristPosition(RobotConfig.WRIST_INTAKE_POSITION);
    }

    public void dumpPositionWrist() {
        setWristPosition(RobotConfig.WRIST_DUMP_POSITION);
    }


    public void stop() {
    }

    public void setPositions(ArmOperation.Type type) {
        switch (type) {
            case Intake: {
                setPositions(RobotConfig.ARM_INTAKE_POSITION);
                break;
            }
            case Deposit1: {
                setPositions(RobotConfig.ARM_DEPOSIT_POSITION_1);
                break;
            }
            case Deposit2: {
                setPositions(RobotConfig.ARM_DEPOSIT_POSITION_2);
                break;
            }
            case Deposit3: {
                setPositions(RobotConfig.ARM_DEPOSIT_POSITION_3);
                break;
            }
            case AutoDeposit: {
                setPositions(RobotConfig.ARM_AUTO_DEPOSIT_POSITION);
                break;
            }
            case Travel: {
                setPositions(RobotConfig.ARM_TRAVEL_POSITION);
                break;
            }
            case InterimTravel:Travel: {
                setPositions(RobotConfig.ARM_INTERIM_TRAVEL_POSITION);
                break;
            }
            case Travel_From_Deposit: {
                setPositions(RobotConfig.ARM_INTERIM_TRAVEL_POSITION);
                break;
            }
            case PreHang: {
                setPositions(RobotConfig.ARM_PRE_HANG_POSITION);
                break;
            }
            case Hang1: {
                setPositions(RobotConfig.ARM_HANG_POSITION_1);
                break;
            }
            case Hang2: {
                setPositions(RobotConfig.ARM_HANG_POSITION_2);
                break;
            }
            case Raised: {
                setPositions(RobotConfig.ARM_RAISED_POSITION);
                break;
            }
        }
    }

    private void setPositions(ArmPosition armPosition) {
        setSlidePosition(armPosition.getSlide());
        setWristPosition(armPosition.getWrist());
        pixelReleaser.setPosition(armPosition.getPixelReleaser());
    }

    /**
     * Set the slide position
     * @param position
     */
    public void setSlidePosition(int position) {
        this.slide.setTargetPosition(position);
        this.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.slide.setPower(RobotConfig.MAX_SLIDE_POWER);
    }

    /**
     * Retain slide in its current position
     */
    public void retainSlide() {
        if (!slideRetained) {
            setSlidePosition(slide.getCurrentPosition());
            slideRetained = true;
        }
    }

    /**
     * Set the slide power
     * @param power
     */
    public void setSlidePower(double power) {
        this.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slide.setPower(power);
        slideRetained = false;
    }

    /**
     * Set the wrist motor position
     * @param position
     */
    public void setWristPosition(int position) {
        this.wrist.setTargetPosition(position);
        this.wrist.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.wrist.setPower(RobotConfig.MAX_SHOULDER_POWER);
    }

    /**
     * Retain shoulder in its current position
     */
    public void retainWrist() {
        if (!wristRetained) {
            setWristPosition(wrist.getCurrentPosition());
            wristRetained = true;
        }
    }

    /**
     * Set the shoulder power
     * @param power
     */
    public void setWristPower(double power) {
        this.wrist.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.wrist.setPower(power*RobotConfig.MAX_WRIST_POWER);
        wristRetained = false;
    }

    /**
     * Set the inout motor power
     * @param power
     */
    public void setInOutPower(double power) {
        this.inOutMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.inOutMotor.setPower(power);
    }

    /**
     * Returns true if slide and shoulder are within range
     * @return
     */
    public boolean isWithinRange() {
        return slideIsWithinRange();
    }

    private boolean slideIsWithinRange() {
        return Math.abs(slide.getTargetPosition() - slide.getCurrentPosition()) <= RobotConfig.ACCEPTABLE_SLIDE_ERROR;
    }

    private boolean wristIsWithinRange() {
        return Math.abs(wrist.getTargetPosition() - wrist.getCurrentPosition()) <= RobotConfig.ACCEPTABLE_WRIST_ERROR;
    }

    public void eat() {
        //Set position of releaser so pixels can NOT come out of the rear of the intake
        this.pixelReleaser.setPosition(RobotConfig.RELEASER_RELEASE_POSITION);
        //set power of intake so it rotates at full speed to bring pixels in
        this.setInOutPower(1);
    }
    public void abstain() {
        //Set position of releaser so pixels can NOT come out of the rear of the intake
        this.pixelReleaser.setPosition(RobotConfig.RELEASER_RELEASE_POSITION);
        this.inOutMotor.setTargetPosition(
                this.inOutMotor.getCurrentPosition());
        this.inOutMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.inOutMotor.setPower(1);
    }
    public void throwUp() {
        this.setInOutPower(-.3);
    }
    public void release() {
        //Set position of releaser so pixel can come out of the rear of the intake
        this.pixelReleaser.setPosition(RobotConfig.RELEASER_RELEASE_POSITION);
        //rotate intake in manner tha pushes pixels towards the rear of the receptacle
        //so they can come out on the other side of the intake
        this.inOutMotor.setTargetPosition(
                this.inOutMotor.getCurrentPosition()
                        + (int) (CORE_HEX_MOTOR_COUNT_PER_REV/INOUT_GEAR_RATIO*2));
        this.inOutMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.inOutMotor.setPower(.5);
    }
    /**
     * Returns the status of the arm
     * Reports the current position, target position and power of the shoulder,
     * current position, target position and power of the slide,
     * the in-out motor's speed
     * the position of the wrist and the position of the sorter
     * the state of the shoulder limit switch
     * @return
     */
    public String getStatus() {
        String shoulderInit = shoulderReset ? "Shoulder initialized" : "Shoulder initializing";
        String slideInit = slideReset ? "Slide initialized" : "Slide initializing";
        String intakeInit = slideReset ? "Intake initialized" : "Intake initializing";


        return String.format(Locale.getDefault(),
                "Slide:%d->%d@%.2f, Wrist:%d->%d@%.2f, In:%d->%d@%.2f, Rel:%.3f",
                slide.getCurrentPosition(), slide.getTargetPosition(), slide.getPower(),
                wrist.getCurrentPosition(), wrist.getTargetPosition(), wrist.getPower(),
                inOutMotor.getCurrentPosition(), inOutMotor.getTargetPosition(), inOutMotor.getPower(),
                pixelReleaser.getPosition());
    }

    public boolean intakeWithinRange() {
        return Math.abs(inOutMotor.getTargetPosition() - inOutMotor.getCurrentPosition()) < 5;
    }

    public void pixelReleasePosition() {
        this.pixelReleaser.setPosition(RobotConfig.RELEASER_RELEASE_POSITION);
    }
    public void pixelRetainPosition() {
        this.pixelReleaser.setPosition(RobotConfig.RELEASER_RETAIN_POSITION);
    }

    public void incrementReleaserPosition() {
        this.pixelReleaser.setPosition(this.pixelReleaser.getPosition() + RobotConfig.SERVO_INCREMENT);
    }
    public void decrementReleaserPosition() {
        this.pixelReleaser.setPosition(this.pixelReleaser.getPosition() - RobotConfig.SERVO_INCREMENT);
    }
}
