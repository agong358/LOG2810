import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class RobotX {
    private int chargeMax = 5;
    private double cstKx;
    private int masse = 0;
    private Commande commande;
    private int tempsConstant = 10;
    private int poidsA = 1;
    private int poidsB = 3;
    private int poidsC = 6;
    private double tempsTotal = 100000000000.0;
    private boolean estMin = false;

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
        System.out.println(commande.getNbObjetsA_()*poidsA + commande.getNbObjetsB_()*poidsB + commande.getNbObjetsC_()*poidsC);
        if (commande.getNbObjetsA_()*poidsA + commande.getNbObjetsB_()*poidsB + commande.getNbObjetsC_()*poidsC <= chargeMax){
            chargeMaxOK = true;
        }
        return chargeMaxOK;
    }

//    public double calculerTempsTotal(LinkedList<Arc> distance, int nombreObjetsA, int nombreObjetsB, int nombreObjetsC, LinkedList<Sommet> chemin){
        public void calculerTempsTotal(LinkedList<Sommet> chemin){

        tempsTotal = 999.0;

        if (verifierChargeMax()) {
            tempsTotal = 0;
//            Sommet sommetNext = new Sommet(0,0,0,0);
//            for (Sommet s : chemin) {
//                for (Arc a : s.getVoisins()) {
//                    if (chemin.contains(a.getVoisin())) {
//                        sommetNext = a.getVoisin();
//                    }
//                }
//                temps += calculerCstKx(masse)*s.getDistanceArc(sommetNext);
//                System.out.println(temps);
//            }
            Sommet sommetTemp = new Sommet(30,0,0,0); //noeud qui n'existe pas
            for (Sommet s : chemin) {
                int distance = sommetTemp.getDistanceArc(s);
                sommetTemp = s;
                tempsTotal += calculerCstKx(masse)*distance;
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
                tempsTotal += calculerCstKx(masse)*distance;
                if (s.getNbObjetsA() <= commande.getNbObjetsA_()) {
                    tempsTotal += tempsConstant*s.getNbObjetsA();
                    System.out.println(tempsTotal + "et " + s.getNbObjetsA());
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
                    System.out.println(tempsTotal);
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
//                int distance = sommetTest.getDistanceArc(s);
//                System.out.println(distance);
//                sommetTest = s;
//                temps += calculerCstKx(masse)*distance;
//                System.out.println(temps);
            }

//            while (!chemin.isEmpty()) {
//                Sommet temp = chemin.pollLast();
//                if (temp.getNbObjetsA() <= commande.getNbObjetsA_()) {
//                    temps += tempsConstant*temp.getNbObjetsA();
//                    System.out.println(temps);
//                    masse += temp.getNbObjetsA()*poidsA;
//                    commande.setNbObjetsA_(commande.getNbObjetsA_() - temp.getNbObjetsA());
//                }
//                else {
//                    temps += tempsConstant*commande.getNbObjetsA_();
//                    System.out.println(temps);
//                    masse += commande.getNbObjetsA_()*poidsA;
//                    commande.setNbObjetsA_(0);
//                }
//
//                if (temp.getNbObjetsB() <= commande.getNbObjetsB_()) {
//                    temps += tempsConstant*temp.getNbObjetsB();
//                    System.out.println(temps);
//                    masse += temp.getNbObjetsB()*poidsB;
//                    commande.setNbObjetsB_(commande.getNbObjetsB_() - temp.getNbObjetsB());
//                }
//                else {
//                    temps += tempsConstant*commande.getNbObjetsB_();
//                    System.out.println(temps);
//                    masse += commande.getNbObjetsB_()*poidsB;
//                    commande.setNbObjetsB_(0);
//                }
//                if (temp.getNbObjetsC() <= commande.getNbObjetsC_()) {
//                    temps += tempsConstant*temp.getNbObjetsC();
//                    System.out.println(temps);
//                    masse += temp.getNbObjetsC()*poidsC;
//                    commande.setNbObjetsC_(commande.getNbObjetsC_() - temp.getNbObjetsC());
//                }
//                else {
//                    temps += tempsConstant*commande.getNbObjetsC_();
//                    System.out.println(temps);
//                    masse += commande.getNbObjetsC_()*poidsC;
//                    commande.setNbObjetsC_(0);
//                }
//                //temps += calculerCstKx(masse)*temp.getSommetDistance();
//                Sommet sommetAutre = new Sommet(40,0,0,0); //noeud qui existe pas
////                for (Arc a : temp.getVoisins()) {
////                    if (chemin.contains(a.getVoisin())) {
////                        sommetAutre = a.getVoisin();
////                    }
////                }
////                temps += calculerCstKx(masse)*temp.getDistanceArc(sommetAutre);
////                System.out.println(temps);
//                for (Sommet s : chemin) {
//                    int distance = sommetTemp.getDistanceArc(s);
//                    sommetTemp = s;
//                    temps += calculerCstKx(masse)*distance;
//                    System.out.println(temps);
//                }
//            }

        }

//        double masseTotale = 0.0;
//        double tempsTotal = 0.0;
//        double nombreObjetAActuel = 0.0;
//        double nombreObjetBActuel = 0.0;
//        double nombreObjetCActuel = 0.0;
//
//        //Pour chaque sommet que le robot croise
//        for(Sommet s : chemin){
//            masse = nombreObjetsA * poidsA + nombreObjetsB * poidsB + nombreObjetsC * poidsC;
//            double ajouterA = 0;
//            double ajouterB = 0;
//            double ajouterC = 0;
//
//
//            //Vérifier que le robot ne prend pas plus d'objets que la commande
//            if((nombreObjetAActuel + nombreObjetsA)<=commande.getNbObjetsA_()){
//               ajouterA = commande.getNbObjetsA_();
//            }
//            else{
//                ajouterA = (nombreObjetAActuel + nombreObjetsA) - commande.getNbObjetsA_();
//            }
//            if((nombreObjetBActuel + nombreObjetsB)<=commande.getNbObjetsB_()){
//                ajouterB = commande.getNbObjetsB_();
//            }
//            else{
//                ajouterB = (nombreObjetBActuel + nombreObjetsB) - commande.getNbObjetsB_();
//            }
//            if((nombreObjetCActuel + nombreObjetsC)<=commande.getNbObjetsC_()){
//                ajouterC = commande.getNbObjetsC_();
//            }
//            else{
//                ajouterC = (nombreObjetCActuel + nombreObjetsC) - commande.getNbObjetsC_();
//            }
//
//            //Vérifier que le poids est respecté en commençant avec l'objet le plus lourd
//            for(int c = 1; c <= ajouterC; c++){
//                if(masseTotale + poidsC <= chargeMax){
//                    masseTotale += poidsC;
//                }
//            }
//
//            for(int b = 1; b <= ajouterB; b++){
//                if(masseTotale + poidsB <= chargeMax){
//                    masseTotale += poidsB;
//                }
//            }
//
//            for(int a = 1; a <= ajouterA; a++){
//                if(masseTotale + poidsA <=chargeMax){
//                    masseTotale += poidsA;
//                }
//            }
//
//            //À chaque fois qu'on visite un nouveau sommet, on ajoute du temps selon la masse
//            double cste_kx = calculerCstKx(masseTotale);
//            tempsTotal += cste_kx * distance[/*TODO la variable dans le for loop de départ?????*/];
//        }
//
//        //Ajouter le temps par défaut pour prendre chaque objet
//        double tempsPrendre = tempsConstant * (nombreObjetsA + nombreObjetsB + nombreObjetsC);
//        tempsTotal += tempsPrendre;
//
//        return tempsTotal;
        System.out.println(tempsTotal);
    }

}
