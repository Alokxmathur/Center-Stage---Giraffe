package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Field;
import org.firstinspires.ftc.teamcode.game.Match;
import org.firstinspires.ftc.teamcode.robot.RobotConfig;
import org.firstinspires.ftc.teamcode.robot.operations.ArmOperation;
import org.firstinspires.ftc.teamcode.robot.operations.BearingOperation;
import org.firstinspires.ftc.teamcode.robot.operations.DriveForDistanceOperation;
import org.firstinspires.ftc.teamcode.robot.operations.DriveInDirectionOperation;
import org.firstinspires.ftc.teamcode.robot.operations.DriveToAprilTag;
import org.firstinspires.ftc.teamcode.robot.operations.LedOperation;
import org.firstinspires.ftc.teamcode.robot.operations.State;
import org.firstinspires.ftc.teamcode.robot.operations.StrafeLeftForDistanceOperation;
import org.firstinspires.ftc.teamcode.robot.operations.StrafeLeftToAprilTagOperation;
import org.firstinspires.ftc.teamcode.robot.operations.StrafeRightForDistanceOperation;
import org.firstinspires.ftc.teamcode.robot.operations.StrafeRightToAprilTagOperation;

public abstract class GiraffeAutonomous extends AutonomousHelper {
    double DISTANCE_TO_LEAVE_WALL = 5.0 * Field.MM_PER_INCH;
    double DISTANCE_TO_CENTER = 30.0 * Field.MM_PER_INCH;
    double DISTANCE_TO_STRAFE = 48*Field.MM_PER_INCH;
    double DISTANCE_TO_STRAFE_RIGHT_PUSH = (15*Field.MM_PER_INCH);

    @Override
    public void start() {
        super.start();

        State state = new State("Navigate");

        state.addPrimaryOperation(
                new DriveInDirectionOperation((DISTANCE_TO_CENTER+5), 0, RobotConfig.CAUTIOUS_SPEED, "Push Center"));
        state.addPrimaryOperation(
                new DriveInDirectionOperation((-5), 0, RobotConfig.CAUTIOUS_SPEED, "Back To Center"));
        state.addPrimaryOperation(
                new StrafeRightForDistanceOperation(DISTANCE_TO_STRAFE_RIGHT_PUSH, RobotConfig.CAUTIOUS_SPEED, "Push Right"));
        state.addPrimaryOperation(
                new StrafeRightForDistanceOperation(-2*DISTANCE_TO_STRAFE_RIGHT_PUSH, RobotConfig.CAUTIOUS_SPEED, "Push Left"));
        state.addPrimaryOperation(
                new StrafeRightForDistanceOperation(-DISTANCE_TO_STRAFE_RIGHT_PUSH, RobotConfig.CAUTIOUS_SPEED, "Back To Center Two"));
        state.addPrimaryOperation(
                new DriveInDirectionOperation((-DISTANCE_TO_CENTER), 0, RobotConfig.CAUTIOUS_SPEED, "Back To Wall"));

        state.addPrimaryOperation(
                new DriveInDirectionOperation(DISTANCE_TO_LEAVE_WALL, 0, RobotConfig.CAUTIOUS_SPEED, "Leave wall"));
        if (match.getAlliance() == Alliance.Color.RED) {
            state.addPrimaryOperation(new StrafeRightForDistanceOperation(DISTANCE_TO_STRAFE, RobotConfig.CAUTIOUS_SPEED, "Reach backdrop"));
        }
        else {
            state.addPrimaryOperation(new StrafeLeftForDistanceOperation(DISTANCE_TO_STRAFE, RobotConfig.CAUTIOUS_SPEED, "Reach backdrop"));
        }
        states.add(state);

        Match.log("Created and added state");
    }
}
