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

    public LinkedList<Sommet> getCheminInverse() {
        return cheminInverse;
    }

    public void setEstMin(boolean estMin) {
        this.estMin = estMin;
    }

    public boolean isEstMin() {
        return estMin;
    }

    public void setCharge(int charge) {
        this.chargeMax = charge;
    }

    abstract double calculerCstK(double masse);

    public double getTempsTotal() {
        return tempsTotal;
    }

    public boolean verifierChargeMax(){
        boolean chargeMaxOK = false;
        if (commande.getNbObjetsA_() * POIDS_A + commande.getNbObjetsB_() * POIDS_B + commande.getNbObjetsC_() * POIDS_C <= chargeMax){
            chargeMaxOK = true;
        }

        return chargeMaxOK;
    }

    public void calculerTempsTotal(LinkedList<Sommet> chemin){
        if (verifierChargeMax()) {
            tempsTotal = 0;
            Sommet sommetTemp = new Sommet(30,0,0,0); //noeud qui n'existe pas
            for (Sommet s : chemin) {
                int distance = sommetTemp.getDistanceArc(s);
                sommetTemp = s;
                tempsTotal += calculerCstK(masse) * distance;
            }

            LinkedList<Sommet> cheminTemp = new LinkedList<>(chemin);
            while (!cheminTemp.isEmpty()) {
                cheminInverse.add(cheminTemp.pollLast());
            }

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