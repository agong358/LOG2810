public class RobotZ {
    private int chargeMax = 25;
    private double cstKz;
    private int masse;

    public void calculerCstKz() {
        cstKz = 2.5 + 0.2*masse;
    }
}