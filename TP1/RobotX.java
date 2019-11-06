public class RobotX extends Robots{
    private int chargeMax = 5;

    //Calculer la constante de robot x
    public double calculerCstK(double masse) {
        cstK = 1 + masse;
        return cstK;
    }

    //Constructeur par paramètres
    public RobotX(Commande commande) {
        setCharge(chargeMax);
        this.commande = commande;
    }
}
