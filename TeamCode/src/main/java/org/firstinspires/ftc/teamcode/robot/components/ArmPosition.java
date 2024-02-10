package org.firstinspires.ftc.teamcode.robot.components;

public class ArmPosition {
    public double getPixelReleaser() {
        return pixelReleaser;
    }

    public int getSlide() {
        return slide;
    }

    int slide, wrist;
    double pixelReleaser;

    public ArmPosition(int slide, int wrist, double pixelReleaserPosition) {
        this.slide = slide;
        this.wrist = wrist;
        this.pixelReleaser = pixelReleaserPosition;
    }

    public int getWrist() {
        return wrist;
    }
}
