/**
 * Représente les commandes du client.
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
public class Commande {
    // Nombre d'objets de chaque type commandé par le client
    private int nbObjetsA, nbObjetsB, nbObjetsC;

    /**
     *  Constructeur par paramètres
     *
     * @param a Nombre d'objets de type A
     * @param b Nombre d'objets de type B
     * @param c Nombre d'objets de type C
     */
    public Commande(int a, int b, int c) {
        nbObjetsA = a;
        nbObjetsB = b;
        nbObjetsC = c;
    }

    /**
     *  Getter pour chercher l'attribut nbObjetsA
     *
     * @return Nombre d'objets de type A
     */
    public int getNbObjetsA() {
        return nbObjetsA;
    }

    /**
     *  Getter pour chercher l'attribut nbObjetsB
     *
     * @return Nombre d'objets de type B
     */
    public int getNbObjetsB() {
        return nbObjetsB;
    }

    /**
     *  Getter pour chercher l'attribut nbObjetsC
     *
     * @return Nombre d'objets de type C
     */
    public int getNbObjetsC() {
        return nbObjetsC;
    }

    /**
     *  Setter pour mettre à jour l'attribut nbObjetsA
     */
    public void setNbObjetsA(int nbObjetsA_) {
        this.nbObjetsA = nbObjetsA;
    }

    /**
     *  Setter pour mettre à jour l'attribut nbObjetsB
     */
    public void setNbObjetsB(int nbObjetsB) {
        this.nbObjetsB = nbObjetsB;
    }

    /**
     *  Setter pour mettre à jour l'attribut nbObjetsC
     */
    public void setNbObjetsC(int nbObjetsC) {
        this.nbObjetsC = nbObjetsC;
    }
}