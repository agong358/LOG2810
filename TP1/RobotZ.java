import java.util.LinkedList;

public class RobotZ {
    private int chargeMax = 25;
    private double cstKz;
    private int masse = 0;
    private Commande commande;
    private int tempsConstant = 10;
    private int poidsA = 1;
    private int poidsB = 3;
    private int poidsC = 6;
    private double tempsTotal = 100000000000000.0;
    private boolean estMin = false;

    public void setEstMin(boolean estMin) {
        this.estMin = estMin;
    }

    public boolean isEstMin() {
        return estMin;
    }

    public double calculerCstKz(double masse) {
        cstKz = 2.5 + 0.2*masse;
        return cstKz;
    }

    public RobotZ(Commande commande) {
        this.commande = commande;
    }

    public boolean verifierChargeMax(){
        boolean chargeMaxOK = false;
        if (commande.getNbObjetsA_()*poidsA + commande.getNbObjetsB_()*poidsB + commande.getNbObjetsC_()*poidsC <= chargeMax){
            chargeMaxOK = true;
        }
        return chargeMaxOK;
    }

    public double getTempsTotal() {
        return tempsTotal;
    }

    public void calculerTempsTotal(LinkedList<Sommet> chemin){

        tempsTotal = 999.0;

        if (verifierChargeMax()) {
            tempsTotal = 0;
            Sommet sommetTemp = new Sommet(30,0,0,0); //noeud qui n'existe pas
            for (Sommet s : chemin) {
                int distance = sommetTemp.getDistanceArc(s);
                sommetTemp = s;
                tempsTotal += calculerCstKz(masse)*distance;
            }
            LinkedList<Sommet> cheminTemp = new LinkedList<>(chemin);
            LinkedList<Sommet> cheminInverse = new LinkedList<>();
            while (!cheminTemp.isEmpty()) {
                cheminInverse.add(cheminTemp.pollLast());
            }

            Sommet sommetTest = new Sommet(50,0,0,0);
            for (Sommet s : cheminInverse) {
                int distance = sommetTest.getDistanceArc(s);
                sommetTest = s;
                tempsTotal += calculerCstKz(masse)*distance;
                if (s.getNbObjetsA() <= commande.getNbObjetsA_()) {
                    tempsTotal += tempsConstant*s.getNbObjetsA();
                    masse += s.getNbObjetsA()*poidsA;
                    commande.setNbObjetsA_(commande.getNbObjetsA_() - s.getNbObjetsA());
                }
                else {
                    tempsTotal += tempsConstant*commande.getNbObjetsA_();
                    masse += commande.getNbObjetsA_()*poidsA;
                    commande.setNbObjetsA_(0);
                }

                if (s.getNbObjetsB() <= commande.getNbObjetsB_()) {
                    tempsTotal += tempsConstant*s.getNbObjetsB();
                    masse += s.getNbObjetsB()*poidsB;
                    commande.setNbObjetsB_(commande.getNbObjetsB_() - s.getNbObjetsB());
                }
                else {
                    tempsTotal += tempsConstant*commande.getNbObjetsB_();
                    masse += commande.getNbObjetsB_()*poidsB;
                    commande.setNbObjetsB_(0);
                }
                if (s.getNbObjetsC() <= commande.getNbObjetsC_()) {
                    tempsTotal += tempsConstant*s.getNbObjetsC();
                    masse += s.getNbObjetsC()*poidsC;
                    commande.setNbObjetsC_(commande.getNbObjetsC_() - s.getNbObjetsC());
                }
                else {
                    tempsTotal += tempsConstant*commande.getNbObjetsC_();
                    masse += commande.getNbObjetsC_()*poidsC;
                    commande.setNbObjetsC_(0);
                }
            }
        }
        System.out.println(tempsTotal);
    }

}
