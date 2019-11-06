public class RobotX extends Robots{
    private int chargeMax = 5;

    public double calculerCstK(double masse) {
        cstK = 1 + masse;
        return cstK;
    }

    public RobotX(Commande commande) {
        setCharge(chargeMax);
        this.commande = commande;
    }
}
