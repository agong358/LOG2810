public class RobotY extends Robots{
    private int chargeMax = 10;

    public double calculerCstK(double masse) {
        cstK = 1.5 + 0.6 * masse;
        return cstK;
    }

    public RobotY(Commande commande) {
        setCharge(chargeMax);
        this.commande = commande;
    }
}