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
        File file = new File("C:\\Users\\nhien\\Documents\\Session Automne 2019\\LOG2810\\TP\\LOG2810\\TP1\\entrepot.txt");
        String sommetNbObjets = new String("");
        String voisinDistance = new String("");
        String pattern = ".*,.*,.*,.*";
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNext(pattern)) {
                sommetNbObjets += scan.next() + "\n";
            }
            while(scan.hasNextLine()) {
                voisinDistance += scan.next() + "\n";
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println(sommetNbObjets + "\n" + voisinDistance);

        String[] arraySommetNbObjets = sommetNbObjets.split("\n");
        String[] arrayVoisinDistance = voisinDistance.split("\n");
        for (String s : arraySommetNbObjets) {
            System.out.println(s);
        }

//        String test = "allo,bonjour,oui";
//        String[] a = test.split(",");
//        for (String s : a) {
//            System.out.println(s);
//        }



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
