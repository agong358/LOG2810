package LOG2810.TP1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Graphe {
    private Sommet[] liste;

    // constructeur par defaut
    public Graphe(){
        liste = null;
    }
    /** TODO
     * Créer un graphe représentant les différentes section de l'entrepôt, ainsi
     * que les chemins reliants ces sections entre elles à partir d'un fichier texte.
     */
    public void creerGraphe(String fichier){
//        File file = new File(fichier);
//        try {
//            Scanner scan = new Scanner(file);
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        Scanner scan = new Scanner("0,0,0,0 \n" +
                "0,1,1,1");
        scan.useDelimiter(",");
        String allo = new String("0,0,0,0 \n 0,0" +
                "0,1,1,1");

        while(scan.hasNext()) {
            System.out.println(scan.next());
        }
        //System.out.println(scan.next());
        System.out.println(allo);

    }

    /** TODO
     * Affiche la représentation du graphe sauvegardé en mémoire
     */
    private void afficherGraphe(){

    }



    /** TODO
     * Affiche le type de robot utilisé, la liste des noeuds traversés,
     * spécifie lorsqu'un objet est pris à un noeud et le temps
     * total que le robot a pris pour aller chercher la commande.
     *
     * Si le chemin est impossible, la fonction doit en informer l'utilisateur.
     */
    private void plusCourtChemin(){}


    private void quitter(){}

//    private void afficherInterface() {
//        System.out.println("Veuillez sélectionner une option");
//        System.out.println("[] Créer le graphe");
//        System.out.println("[] Afficher le graphe");
//        System.out.println("[] Prendre une commande");
//        System.out.println("[] Afficher la commande");
//        System.out.println("[] Trouver le plus court chemin");
//        System.out.println("[] Quitter");
//    }

    static class Voisin {
        int noeudVoisin;
        int distance;
    }

    static class Sommet {
        int noeud;
        int nbObjetsA = 0;
        int nbObjetsB = 0;
        int nbObjetsC = 0;
        Voisin[] voisins = null;
    }



}
