/**
 * Classe pour contenant des méthodes pour le robot X
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
public class RobotX extends Robots{
    private int chargeMax = 5;

    /**
     * Calcule la constante pour le robot X
     *
     * @param masse masse des objets portés par le robot
     * @return cstK
     */
    public double calculerCstK(double masse) {
        cstK = 1 + masse;
        return cstK;
    }

    /**
     * Contructeur par paramètres
     *
     * @param commande données entrées par l'utilisateur
     */
    public RobotX(Commande commande) {
        setCharge(chargeMax);
        this.commande = commande;
    }
}
