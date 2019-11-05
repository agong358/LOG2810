import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;

public class RobotX {
    private int chargeMax = 5;
    private double cstKx;
    private int masse = 0;
    private Commande commande;
    private int tempsConstant = 10;
    private int poidsA = 1;
    private int poidsB = 3;
    private int poidsC = 6;
    private double tempsTotal = Double.MAX_VALUE;
    private boolean estMin = false;
    private LinkedList<Sommet> cheminInverse = new LinkedList<>();

    public LinkedList<Sommet> getCheminInverse() {
        return cheminInverse;
    }

    public void setEstMin(boolean estMin) {
        this.estMin = estMin;
    }

    public boolean isEstMin() {
        return estMin;
    }

    public double calculerCstKx(double masse) {
        cstKx = 1 + masse;
        return cstKx;
    }

    public double getTempsTotal() {
        return tempsTotal;
    }

    public RobotX(Commande commande) {
        this.commande = commande;
    }

    public boolean verifierChargeMax(){
        boolean chargeMaxOK = false;
        if (commande.getNbObjetsA_()*poidsA + commande.getNbObjetsB_()*poidsB + commande.getNbObjetsC_()*poidsC <= chargeMax){
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
                tempsTotal += calculerCstKx(masse)*distance;
            }
            LinkedList<Sommet> cheminTemp = new LinkedList<>(chemin);
            while (!cheminTemp.isEmpty()) {
                cheminInverse.add(cheminTemp.pollLast());
            }

            Sommet sommetTest = new Sommet(50,0,0,0);
            for (Sommet s : cheminInverse) {
                int distance = sommetTest.getDistanceArc(s);
                sommetTest = s;
                tempsTotal += calculerCstKx(masse)*distance;
                if (s.getNbObjetsA() <= commande.getNbObjetsA_()) {
                    if (commande.getNbObjetsA_() != 0)
                        s.setNbPrendreA(s.getNbObjetsA());
                    tempsTotal += tempsConstant*s.getNbObjetsA();
                    masse += s.getNbObjetsA()*poidsA;
                    commande.setNbObjetsA_(commande.getNbObjetsA_() - s.getNbObjetsA());
                }
                else {
                    s.setNbPrendreA(commande.getNbObjetsA_());
                    tempsTotal += tempsConstant*commande.getNbObjetsA_();
                    masse += commande.getNbObjetsA_()*poidsA;
                    commande.setNbObjetsA_(0);
                }

                if (s.getNbObjetsB() <= commande.getNbObjetsB_()) {
                    if (commande.getNbObjetsB_() != 0)
                        s.setNbPrendreB(s.getNbObjetsB());
                    tempsTotal += tempsConstant*s.getNbObjetsB();
                    masse += s.getNbObjetsB()*poidsB;
                    commande.setNbObjetsB_(commande.getNbObjetsB_() - s.getNbObjetsB());
                }
                else {
                    s.setNbPrendreB(commande.getNbObjetsB_());
                    tempsTotal += tempsConstant*commande.getNbObjetsB_();
                    masse += commande.getNbObjetsB_()*poidsB;
                    commande.setNbObjetsB_(0);
                }
                if (s.getNbObjetsC() <= commande.getNbObjetsC_()) {
                    if (commande.getNbObjetsC_() != 0)
                        s.setNbPrendreC(s.getNbObjetsC());
                    tempsTotal += tempsConstant*s.getNbObjetsC();
                    masse += s.getNbObjetsC()*poidsC;
                    commande.setNbObjetsC_(commande.getNbObjetsC_() - s.getNbObjetsC());
                }
                else {
                    s.setNbPrendreC(commande.getNbObjetsC_());
                    tempsTotal += tempsConstant*commande.getNbObjetsC_();
                    masse += commande.getNbObjetsC_()*poidsC;
                    commande.setNbObjetsC_(0);
                }
            }
        }
    }

}
