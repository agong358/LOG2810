import java.util.LinkedList;

public class RobotX {
    private int chargeMax = 5;
    private double cstKx;
    private int masse;
    private Commande commande;
    private int tempsConstant = 10;
    private int poidsA = 1;
    private int poidsB = 3;
    private int poidsC = 6;

    public double calculerCstKx(double masse) {
        cstKx = 1 + masse;
        return cstKx;
    }

    public boolean verifierChargeMax(){
        boolean chargeMaxOK = false;
        if (commande.getNbObjetsA_()*poidsA + commande.getNbObjetsB_()*poidsB + commande.getNbObjetsC_()*poidsC <= chargeMax){
            chargeMaxOK = true;
        }
        return chargeMaxOK;
    }

    public double calculerTempsTotale(LinkedList<Arc> distance, int nombreObjetsA, int nombreObjetsB, int nombreObjetsC, LinkedList<Sommet> chemin){
        double masseTotale = 0.0;
        double tempsTotal = 0.0;
        double nombreObjetAActuel = 0.0;
        double nombreObjetBActuel = 0.0;
        double nombreObjetCActuel = 0.0;

        //Pour chaque sommet que le robot croise
        for(/*TODO linkedlist avec la nouvelle version de sommet*/){
            masse = nombreObjetsA * poidsA + nombreObjetsB * poidsB + nombreObjetsC * poidsC;
            double ajouterA = 0;
            double ajouterB = 0;
            double ajouterC = 0;


            //Vérifier que le robot ne prend pas plus d'objets que la commande
            if((nombreObjetAActuel + nombreObjetsA)<=commande.getNbObjetsA_()){
               ajouterA = commande.getNbObjetsA_();
            }
            else{
                ajouterA = (nombreObjetAActuel + nombreObjetsA) - commande.getNbObjetsA_();
            }
            if((nombreObjetBActuel + nombreObjetsB)<=commande.getNbObjetsB_()){
                ajouterB = commande.getNbObjetsB_();
            }
            else{
                ajouterB = (nombreObjetBActuel + nombreObjetsB) - commande.getNbObjetsB_();
            }
            if((nombreObjetCActuel + nombreObjetsC)<=commande.getNbObjetsC_()){
                ajouterC = commande.getNbObjetsC_();
            }
            else{
                ajouterC = (nombreObjetCActuel + nombreObjetsC) - commande.getNbObjetsC_();
            }

            //Vérifier que le poids est respecté en commençant avec l'objet le plus lourd
            for(int c = 1; c <= ajouterC; c++){
                if(masseTotale + poidsC <= chargeMax){
                    masseTotale += poidsC;
                }
            }

            for(int b = 1; b <= ajouterB; b++){
                if(masseTotale + poidsB <= chargeMax){
                    masseTotale += poidsB;
                }
            }

            for(int a = 1; a <= ajouterA; a++){
                if(masseTotale + poidsA <=chargeMax){
                    masseTotale += poidsA;
                }
            }

            //À chaque fois qu'on visite un nouveau sommet, on ajoute du temps selon la masse
            double cste_kx = calculerCstKx(masseTotale);
            tempsTotal += cste_kx * distance[/*TODO la variable dans le for loop de départ?????*/];
        }

        //Ajouter le temps par défaut pour prendre chaque objet
        double tempsPrendre = tempsConstant * (nombreObjetsA + nombreObjetsB + nombreObjetsC);
        tempsTotal += tempsPrendre;

        return tempsTotal;
    }

}
