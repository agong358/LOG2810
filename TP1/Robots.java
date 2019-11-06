import java.util.LinkedList;

public abstract class Robots {
    protected static final int POIDS_A = 1;
    protected static final int POIDS_B = 3;
    protected static final int POIDS_C = 6;
    protected static final int TEMPS_CONSTANT = 10;

    protected Commande commande;
    protected double cstK;
    protected double tempsTotal = Double.MAX_VALUE;
    protected int chargeMax;
    protected int masse = 0;
    protected LinkedList<Sommet> cheminInverse = new LinkedList<>();

    private boolean estMin = false;

    //Getters
    public LinkedList<Sommet> getCheminInverse() {
        return cheminInverse;
    }

    public double getTempsTotal() {
        return tempsTotal;
    }

    //Setters
    public void setEstMin(boolean estMin) {
        this.estMin = estMin;
    }

    public void setCharge(int charge) {
        this.chargeMax = charge;
    }


    public boolean isEstMin() {
        return estMin;
    }

    abstract double calculerCstK(double masse);

    //Vérifier que la charge maximale n'est pas dépassée
    public boolean verifierChargeMax(){
        boolean chargeMaxOK = false;
        if (commande.getNbObjetsA_() * POIDS_A + commande.getNbObjetsB_() * POIDS_B + commande.getNbObjetsC_() * POIDS_C <= chargeMax){
            chargeMaxOK = true;
        }

        return chargeMaxOK;
    }

    //Calculer le temps total pour le robot en question
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
                tempsTotal += calculerCstK(masse) * distance;
                if (s.getNbObjetsA() <= commande.getNbObjetsA_()) {
                    if (commande.getNbObjetsA_() != 0)
                        s.setNbPrendreA(s.getNbObjetsA());
                    tempsTotal += TEMPS_CONSTANT * s.getNbObjetsA();
                    masse += s.getNbObjetsA() * POIDS_A;
                    commande.setNbObjetsA_(commande.getNbObjetsA_() - s.getNbObjetsA());
                }
                else {
                    s.setNbPrendreA(commande.getNbObjetsA_());
                    tempsTotal += TEMPS_CONSTANT * commande.getNbObjetsA_();
                    masse += commande.getNbObjetsA_() * POIDS_A;
                    commande.setNbObjetsA_(0);
                }

                if (s.getNbObjetsB() <= commande.getNbObjetsB_()) {
                    if (commande.getNbObjetsB_() != 0)
                        s.setNbPrendreB(s.getNbObjetsB());
                    tempsTotal += TEMPS_CONSTANT * s.getNbObjetsB();
                    masse += s.getNbObjetsB() * POIDS_B;
                    commande.setNbObjetsB_(commande.getNbObjetsB_() - s.getNbObjetsB());
                }
                else {
                    s.setNbPrendreB(commande.getNbObjetsB_());
                    tempsTotal += TEMPS_CONSTANT * commande.getNbObjetsB_();
                    masse += commande.getNbObjetsB_() * POIDS_B;
                    commande.setNbObjetsB_(0);
                }
                if (s.getNbObjetsC() <= commande.getNbObjetsC_()) {
                    if (commande.getNbObjetsC_() != 0)
                        s.setNbPrendreC(s.getNbObjetsC());
                    tempsTotal += TEMPS_CONSTANT * s.getNbObjetsC();
                    masse += s.getNbObjetsC() * POIDS_C;
                    commande.setNbObjetsC_(commande.getNbObjetsC_() - s.getNbObjetsC());
                }
                else {
                    s.setNbPrendreC(commande.getNbObjetsC_());
                    tempsTotal += TEMPS_CONSTANT* commande.getNbObjetsC_();
                    masse += commande.getNbObjetsC_() * POIDS_C;
                    commande.setNbObjetsC_(0);
                }
            }
        }
    }
}