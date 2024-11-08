package org.firstinspires.ftc.teamcode.robot.components;

/**
 * This represents the position of our slide with the elbow and the claw
 */
public class ArmPosition {
    public double getClaw() {
        return claw;
    }

    public int getSlide() {
        return slide;
    }

    int slide, elbow;
    double claw;

    public ArmPosition(int slide, int elbow, double clawPosition) {
        this.slide = slide;
        this.elbow = elbow;
        this.claw = clawPosition;
    }

    public int getElbow() {
        return elbow;
    }
}
