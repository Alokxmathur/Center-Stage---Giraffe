package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import org.firstinspires.ftc.teamcode.game.Field;
import org.firstinspires.ftc.teamcode.game.Match;
import org.firstinspires.ftc.teamcode.robot.operations.State;

public abstract class GiraffeAutonomous extends AutonomousHelper {
    double DISTANCE_TO_LEAVE_WALL = 5.0 * Field.MM_PER_INCH;
    double DISTANCE_TO_CENTER = 30.0 * Field.MM_PER_INCH;
    double DISTANCE_TO_STRAFE = 48*Field.MM_PER_INCH;
    double DISTANCE_TO_STRAFE_RIGHT_PUSH = (15*Field.MM_PER_INCH);

    @Override
    public void start() {
        super.start();
        State state = new State("Spin while rotating");
        /*
        state.addPrimaryOperation(
                new DriveInDirectionOperation(DISTANCE_TO_LEAVE_WALL, 0, RobotConfig.CAUTIOUS_SPEED, "Leave wall"));

        state.addPrimaryOperation(
                new DriveWhileSpinningOperation(5000, 1, "Spin while driving"));
        states.add(state);
        Match.log("Created and added state");

         */
    }
}
