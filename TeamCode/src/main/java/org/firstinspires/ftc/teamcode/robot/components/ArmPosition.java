package org.firstinspires.ftc.teamcode.robot.components;

public class ArmPosition {
    public int getShoulder() {
        return shoulder;
    }

    public int getSlide() {
        return slide;
    }

    int shoulder, slide, wrist;

    public ArmPosition(int shoulder, int slide, int wrist) {
        this.shoulder = shoulder;
        this.slide = slide;
        this.wrist = wrist;
    }

    public int getWrist() {
        return wrist;
    }
}
