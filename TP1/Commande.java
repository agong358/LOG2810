//package LOG2810.TP1;

import java.util.Scanner;

public class Commande {
    private int nbObjetsA_, nbObjetsB_, nbObjetsC_;

    public Commande(){
        nbObjetsA_ = 0;
        nbObjetsB_ = 0;
        nbObjetsC_ = 0;
    }

    public Commande(int a, int b, int c) {
        nbObjetsA_ = a;
        nbObjetsB_ = b;
        nbObjetsC_ = c;
    }

    public int getNbObjetsA_() {
        return nbObjetsA_;
    }

    public int getNbObjetsB_() {
        return nbObjetsB_;
    }

    public int getNbObjetsC_() {
        return nbObjetsC_;
    }

    //deplace dans graphe pour simplifier
    /** TODO
     * Permet à l’utilisateur de rentrer un nombre d'objets de chaque
     * type que le robot devra aller chercher.
     */
//    public void prendreCommande(){
//
//        System.out.println("Entrez le nombre d'objets de type A: ");
//        Scanner scan = new Scanner(System.in);
//        while(!scan.hasNextInt()) {
//                    String input = scan.next();
//                    System.out.println(input + " n'est pas une option valide!");
//                    System.out.println("Veuillez saisir le nombre d'objets de type A: ");
//                    System.out.println("---------------------------------------");
//        }
//        nbObjetsA_ = scan.nextInt();
//
//        System.out.println("Entrez le nombre d'objets de type B: ");
//        while(!scan.hasNextInt()) {
//            String input = scan.next();
//            System.out.println(input + " n'est pas une option valide!");
//            System.out.println("Veuillez saisir le nombre d'objets de type B: ");
//            System.out.println("---------------------------------------");
//        }
//        nbObjetsB_ = scan.nextInt();
//
//        System.out.println("Entrez le nombre d'objets de type C: ");
//        while(!scan.hasNextInt()) {
//            String input = scan.next();
//            System.out.println(input + " n'est pas une option valide!");
//            System.out.println("Veuillez saisir le nombre d'objets de type C: ");
//            System.out.println("---------------------------------------");
//        }
//        nbObjetsC_ = scan.nextInt();
//    }


    //deplace dans graphe.java pour simplifier
    /** TODO
     * Permet de voir la commande en mémoire.
     */
//    public void afficherCommande(){
//        System.out.println("La commande en mémoire est de : ");
//        System.out.println("Nombre d'objets de type A : " + nbObjetsA_);
//        System.out.println("Nombre d'objets de type B : " + nbObjetsB_);
//        System.out.println("Nombre d'objets de type C : " + nbObjetsC_);
//    }
}