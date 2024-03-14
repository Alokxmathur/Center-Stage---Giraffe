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
        double DISTANCE_TO_MIDDLE_OF_SPIKES = 24.0 * Field.MM_PER_INCH;
        State state = new State("challenge");
        new DriveInDirectionOperation(DISTANCE_TO_MIDDLE_OF_SPIKES, 0, RobotConfig.CAUTIOUS_SPEED, "Leave wall"));
        state.addPrimaryOperation(new DriveInDirectionOperation(6   * Field.MM_PER_INCH,0, .5, "Straight 1"));
        state.addPrimaryOperation(new BearingOperation(Math.toRadians(90), robot.getDriveTrain(), "1Left"));
        state.addPrimaryOperation(new DriveInDirectionOperation(6   * Field.MM_PER_INCH,Math.toRadians(90), .5, "Straight 2"));
        state.addPrimaryOperation(new BearingOperation(Math.toRadians(180), robot.getDriveTrain(), "2Left"));
        state.addPrimaryOperation(new DriveInDirectionOperation(6 * Field.MM_PER_INCH,Math.toRadians(180), .5, "Straight 3"));
        state.addPrimaryOperation(new BearingOperation(Math.toRadians(270), robot.getDriveTrain(), "3Left"));
        state.addPrimaryOperation(new DriveInDirectionOperation(6 * Field.MM_PER_INCH,Math.toRadians(270), .5, "Straight 4"));
        state.addPrimaryOperation(new BearingOperation(Math.toRadians(0), robot.getDriveTrain(), "4Left"));

        states.add(state);

        Match.log("Created and added state");
    }
}
