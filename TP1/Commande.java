package LOG2810.TP1;

import java.util.Scanner;

public class Commande {
    int nbObjetsA_, nbObjetsB_, nbObjetsC_;

    public void Commmande(){
        nbObjetsA_ = 0;
        nbObjetsB_ = 0;
        nbObjetsC_ = 0;
    }

    /** TODO
     * Permet à l’utilisateur de rentrer un nombre d'objets de chaque
     * type que le robot devra aller chercher.
     */
    public void prendreCommande(){
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 3; i ++) {
            System.out.println("Entrez le nombre d'objets de type A: ");
        }


//        nbObjetsA_ = nbObjetsA;
//        nbObjetsB_ = nbObjetsB;
//        nbObjetsC_ = nbObjetsC;
    }

    /** TODO
     * Permet de voir la commande en mémoire.
     */
    public void afficherCommande(){
        System.out.println(nbObjetsA_ + nbObjetsB_+ nbObjetsC_);
    }



}