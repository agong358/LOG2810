/**
 * Classe pour contenant des méthodes pour le robot Z
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
public class RobotZ extends Robots{
    private int chargeMax = 25;

    /**
     * Calcule la constante pour le robot Z
     *
     * @param masse masse des objets portés par le robot
     * @return cstK
     */
    public double calculerCstK(double masse) {
        cstK = 2.5 + 0.2 * masse;
        return cstK;
    }

    /**
     * Contructeur par paramètres
     *
     * @param commande données entrées par l'utilisateur
     */
    public RobotZ(Commande commande) {
        setCharge(chargeMax);
        this.commande = commande;
    }
}
