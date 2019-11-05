public class RobotY {
    private int chargeMax = 10;
    private double cstKy;
    private int masse;

    public void calculerCstKy() {
        cstKy = 1.5 + 0.6*masse;
    }

    public double calculerTemps(int distance){
        double temps = cstKy * distance;
        return temps;
    }
}
