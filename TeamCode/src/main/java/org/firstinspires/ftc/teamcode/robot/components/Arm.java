package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.RobotConfig;
import org.firstinspires.ftc.teamcode.robot.operations.ArmOperation;

import java.util.Locale;

public class Arm {
    public static final int CORE_HEX_MOTOR_COUNT_PER_REV = 288;
    public static final int INOUT_GEAR_RATIO = 3;

    DcMotorEx slide, shoulder;

    Servo claw;

    boolean
            slideRetained,
            elbowRetained;

    public Arm(HardwareMap hardwareMap) {
        //initialize our slide motor
        this.slide = hardwareMap.get(DcMotorEx.class, RobotConfig.SLIDE);
        this.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //initialize our wrist motor
        this.shoulder = hardwareMap.get(DcMotorEx.class, RobotConfig.SHOULDER);
        this.shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.claw = hardwareMap.get(Servo.class, RobotConfig.CLAW);
        ensureMotorDirections();
        assumeInitialPosition();
    }


    public void ensureMotorDirections() {
        this.slide.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void assumeInitialPosition() {
        setPositions(RobotConfig.ARM_STARTING_POSITION);
    }

    public void raiseShoulderIncrementally() {
        setShoulderPosition(shoulder.getCurrentPosition() - 5);
    }

    public void lowerShoulderIncrementally() {
        setShoulderPosition(shoulder.getCurrentPosition() + 5);
    }

    public void stop() {
    }

    public void setPositions(ArmOperation.Type type) {
        switch (type) {
            case Initial: {
                setPositions(RobotConfig.ARM_STARTING_POSITION);
                break;
            }
            case Lower_Basket: {
                setPositions(RobotConfig.ARM_LOWER_BASKET);
                break;
            }
            case Higher_Basket: {
                setPositions(RobotConfig.ARM_HIGHER_BASKET);
                break;
            }
        }
    }

    private void setPositions(ArmPosition armPosition) {
        setSlidePosition(armPosition.getSlide());
        setShoulderPosition(armPosition.getElbow());
        claw.setPosition(armPosition.getClaw());
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
    public void setShoulderPosition(int position) {
        this.shoulder.setTargetPosition(position);
        this.shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.shoulder.setPower(RobotConfig.MAX_SHOULDER_POWER);
    }

    /**
     * Retain shoulder in its current position
     */
    public void retainElbow() {
        if (!elbowRetained) {
            setShoulderPosition(shoulder.getCurrentPosition());
            elbowRetained = true;
        }
    }

    /**
     * Set the shoulder power
     * @param power
     */
    public void setElbowPower(double power) {
        this.shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.shoulder.setPower(power*RobotConfig.MAX_WRIST_POWER);
        elbowRetained = false;
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
        return Math.abs(shoulder.getTargetPosition() - shoulder.getCurrentPosition()) <= RobotConfig.ACCEPTABLE_WRIST_ERROR;
    }

    public void release() {
        //Set position of releaser so pixel can come out of the rear of the intake
        this.claw.setPosition(RobotConfig.CLAW_RELEASE_POSITION);
    }
    public void hold() {
        //Set position of releaser so pixel can come out of the rear of the intake
        this.claw.setPosition(RobotConfig.CLAW_HOLD_POSITION);
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
        return String.format(Locale.getDefault(),
                "Slide:%d->%d@%.2f, Wrist:%d->%d@%.2f",
                slide.getCurrentPosition(), slide.getTargetPosition(), slide.getPower(),
                shoulder.getCurrentPosition(), shoulder.getTargetPosition(), shoulder.getPower(),
                claw.getPosition());
    }

    public void clawReleasePosition() {
        this.claw.setPosition(RobotConfig.CLAW_RELEASE_POSITION);
    }
    public void clawRetainPosition() {
        this.claw.setPosition(RobotConfig.CLAW_HOLD_POSITION);
    }

    public void incrementReleaserPosition() {
        this.claw.setPosition(this.claw.getPosition() + RobotConfig.SERVO_INCREMENT);
    }
    public void decrementReleaserPosition() {
        this.claw.setPosition(this.claw.getPosition() - RobotConfig.SERVO_INCREMENT);
    }
}
