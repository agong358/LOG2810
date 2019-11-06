/**
 * Classe pour contenant des méthodes pour le robot Y
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
public class RobotY extends Robots{
    private int chargeMax = 10;

    /**
     * Calcule la constante pour le robot Y
     *
     * @param masse masse des objets portés par le robot
     * @return cstK
     */
    public double calculerCstK(double masse) {
        cstK = 1.5 + 0.6 * masse;
        return cstK;
    }

    /**
     * Contructeur par paramètres
     *
     * @param commande données entrées par l'utilisateur
     */
    public RobotY(Commande commande) {
        setCharge(chargeMax);
        this.commande = commande;
    }
}