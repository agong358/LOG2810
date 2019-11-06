import java.util.LinkedList;

/**
 * Classe pour contenant des méthodes pour les robots en général
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
public abstract class Robots {
    //Poids des différents objets à prendre
    protected static final int POIDS_A = 1;
    protected static final int POIDS_B = 3;
    protected static final int POIDS_C = 6;
    //Temps mis pour prendre un objet
    protected static final int TEMPS_CONSTANT = 10;
    //Commande entrée par l'utilisateur
    protected Commande commande;
    //Constante K unique à chaque robot
    protected double cstK;
    //Temps total suite au parcours
    protected double tempsTotal = Double.MAX_VALUE;
    //Charge maximale pour le robot en question
    protected int chargeMax;
    //masse d'objets actuel su robot
    protected int masse = 0;
    //liste de sommets pour le chemin de retour
    protected LinkedList<Sommet> cheminInverse = new LinkedList<>();
    //Vérifie si c'est le chemin optimal
    private boolean estMin = false;

    /**
     *  Getter pour chercher l'attribut cheminInverse
     *
     * @return attribut cheminInverse
     */
    public LinkedList<Sommet> getCheminInverse() {
        return cheminInverse;
    }

    /**
     *  Getter pour chercher l'attribut tempsTotal
     *
     * @return attribut tempsTotal
     */
    public double getTempsTotal() {
        return tempsTotal;
    }

    /**
     *  Setter pour mettre à jour l'attribut estMin
     */
    public void setEstMin(boolean estMin) {
        this.estMin = estMin;
    }

    /**
     *  Setter pour mettre à jour l'attribut charge
     */
    public void setCharge(int charge) {
        this.chargeMax = charge;
    }

    /**
     *  Setter pour mettre à jour l'attribut estMin
     */
    public boolean isEstMin() {
        return estMin;
    }

    /**
     *  Calcul la constante K du robot en question
     *
     * @param masse
     */
    abstract double calculerCstK(double masse);

    //Vérifier que la charge maximale n'est pas dépassée
    /**
     *  Vérifie que la charge maximale n'est pas dépassée
     *
     * @return chargeMaxOK
     */
    public boolean verifierChargeMax(){
        boolean chargeMaxOK = false;
        if (commande.getNbObjetsA() * POIDS_A + commande.getNbObjetsB() * POIDS_B + commande.getNbObjetsC() * POIDS_C <= chargeMax){
            chargeMaxOK = true;
        }

        return chargeMaxOK;
    }

    /**
     *  Calcule le temps total pour le robot en question
     *
     * @param chemin chemin parcouru par le robot
     */
    public void calculerTempsTotal(LinkedList<Sommet> chemin){
        //Tant que le robot peut prendre des objets
        if (verifierChargeMax()) {
            tempsTotal = 0;
            Sommet sommetTemp = new Sommet(30,0,0,0); //noeud qui n'existe pas
            //Calculer le temps pris par le robot pour faire le chemin
            for (Sommet s : chemin) {
                int distance = sommetTemp.getDistanceArc(s);
                sommetTemp = s;
                tempsTotal += calculerCstK(masse) * distance;
            }
            //Créer le chemin de retour
            LinkedList<Sommet> cheminTemp = new LinkedList<>(chemin);
            while (!cheminTemp.isEmpty()) {
                cheminInverse.add(cheminTemp.pollLast());
            }

            //Calculer le temps total pour le chemin de retour avec tous les objets
            Sommet sommetTest = new Sommet(50,0,0,0);
            for (Sommet s : cheminInverse) {
                int distance = sommetTest.getDistanceArc(s);
                sommetTest = s;
                //Utiliser la formule T=k*D
                tempsTotal += calculerCstK(masse) * distance;
                //Pour l'objet A
                if (s.getNbObjetsA() <= commande.getNbObjetsA()) {
                    if (commande.getNbObjetsA() != 0)
                        s.setNbPrendreA(s.getNbObjetsA());
                    tempsTotal += TEMPS_CONSTANT * s.getNbObjetsA();
                    masse += s.getNbObjetsA() * POIDS_A;
                    commande.setNbObjetsA(commande.getNbObjetsA() - s.getNbObjetsA());
                }
                else {
                    s.setNbPrendreA(commande.getNbObjetsA());
                    tempsTotal += TEMPS_CONSTANT * commande.getNbObjetsA();
                    masse += commande.getNbObjetsA() * POIDS_A;
                    commande.setNbObjetsA(0);
                }

                //Pour l'objet B
                if (s.getNbObjetsB() <= commande.getNbObjetsB()) {
                    if (commande.getNbObjetsB() != 0)
                        s.setNbPrendreB(s.getNbObjetsB());
                    tempsTotal += TEMPS_CONSTANT * s.getNbObjetsB();
                    masse += s.getNbObjetsB() * POIDS_B;
                    commande.setNbObjetsB(commande.getNbObjetsB() - s.getNbObjetsB());
                }
                else {
                    s.setNbPrendreB(commande.getNbObjetsB());
                    tempsTotal += TEMPS_CONSTANT * commande.getNbObjetsB();
                    masse += commande.getNbObjetsB() * POIDS_B;
                    commande.setNbObjetsB(0);
                }

                //Pour l'objet C
                if (s.getNbObjetsC() <= commande.getNbObjetsC()) {
                    if (commande.getNbObjetsC() != 0)
                        s.setNbPrendreC(s.getNbObjetsC());
                    tempsTotal += TEMPS_CONSTANT * s.getNbObjetsC();
                    masse += s.getNbObjetsC() * POIDS_C;
                    commande.setNbObjetsC(commande.getNbObjetsC() - s.getNbObjetsC());
                }
                else {
                    s.setNbPrendreC(commande.getNbObjetsC());
                    tempsTotal += TEMPS_CONSTANT* commande.getNbObjetsC();
                    masse += commande.getNbObjetsC() * POIDS_C;
                    commande.setNbObjetsC(0);
                }
            }
        }
    }
}