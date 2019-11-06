public class RobotZ extends Robots{
    private int chargeMax = 25;

    //Calculer la constante pour robot z
    public double calculerCstK(double masse) {
        cstK = 2.5 + 0.2 * masse;
        return cstK;
    }

    //Constructeur par paramètres
    public RobotZ(Commande commande) {
        setCharge(chargeMax);
        this.commande = commande;
    }
}
