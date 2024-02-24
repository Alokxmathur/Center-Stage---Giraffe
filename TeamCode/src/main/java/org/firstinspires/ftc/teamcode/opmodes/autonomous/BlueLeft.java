package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import org.firstinspires.ftc.teamcode.game.Alliance;
import org.firstinspires.ftc.teamcode.game.Field;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="BlueLeft", group="Aliyah", preselectTeleOp="Aliyah: Driver Controlled")
public class BlueLeft extends Autonomous {
    //comment added by Arjun
    @Override
    public void init() {
        super.init(telemetry, Alliance.Color.BLUE, Field.StartingPosition.Left);
    }
}
