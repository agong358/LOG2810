public class RobotX {
    private int chargeMax = 5;
    private double cstKx;
    private int masse;
    private Commande commande;

    public void calculerCstKx() {
        cstKx = 1 + masse;
    }
}
