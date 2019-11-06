public class RobotY extends Robots{
    private int chargeMax = 10;

    //Calculer la constante pour robot y
    public double calculerCstK(double masse) {
        cstK = 1.5 + 0.6 * masse;
        return cstK;
    }

    //Constructeur par paramètres
    public RobotY(Commande commande) {
        setCharge(chargeMax);
        this.commande = commande;
    }
}