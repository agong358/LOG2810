//package LOG2810.TP1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Graphe {
    private List<Sommet> listeSommets = new ArrayList<>();
    private Commande commande = new Commande();
    private int maxA;
    private int maxB;
    private int maxC;
    private boolean nbAEstValide = false;

    public List<Sommet> getListeSommets() {
        return listeSommets;
    }

    public void calculerMaxObjets() {
        for (Sommet s : listeSommets) {
            maxA += s.getNbObjetsA();
            maxB += s.getNbObjetsB();
            maxC += s.getNbObjetsC();
        }
    }

    // constructeur par defaut
    public Graphe(){
    }
    /** TODO
     * Créer un graphe représentant les différentes section de l'entrepôt, ainsi
     * que les chemins reliants ces sections entre elles à partir d'un fichier texte.
     */
    public void creerGraphe(String fichier){
        File file = new File("C:\\Users\\nhien\\Documents\\Session Automne 2019\\LOG2810\\TP\\LOG2810\\TP1\\entrepot.txt");
        File fileAlice = new File("C:\\Users\\Alice G\\Documents\\Polytechnique\\Session 3\\LOG2810\\TP\\LOG2810\\TP1\\entrepot.txt");
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

        String[] arraySommetNbObjets = sommetNbObjets.split("\n");
        String[] arrayVoisinDistance = voisinDistance.split("\n");

        for (String s : arraySommetNbObjets) {
            String[] tempArray = s.split(",");
            listeSommets.add(new Sommet(Integer.parseInt(tempArray[0]), Integer.parseInt(tempArray[1]),
                    Integer.parseInt(tempArray[2]), Integer.parseInt(tempArray[3])));
        }

        //TESTS DAFFICHAGE
//        for (Sommet oui : listeSommets) {
//            System.out.println(oui.getNoeud() + "," + oui.getNbObjetsA() + "," + oui.getNbObjetsB() + "," + oui.getNbObjetsC());
//        }

        //List<Arc> listeFichier = new ArrayList<>();
        for (String v : arrayVoisinDistance) {
            String[] tempArray = v.split(",");
            listeSommets.get(Integer.parseInt(tempArray[1])).addVoisin(new Arc(listeSommets.get(Integer.parseInt(tempArray[0])), Integer.parseInt(tempArray[2])));
            listeSommets.get(Integer.parseInt(tempArray[0])).addVoisin(new Arc(listeSommets.get(Integer.parseInt(tempArray[1])), Integer.parseInt(tempArray[2])));
//            listeFichier.add(new Arc(listeSommets.get(Integer.parseInt(tempArray[0])), Integer.parseInt(tempArray[2])));
//            listeFichier.add(new Arc(listeSommets.get(Integer.parseInt(tempArray[1])), Integer.parseInt(tempArray[2])));
        }

//        //TESTS DAFFICHAGE
//        for (Arc oui : listeFichier) {
//            System.out.println(oui.getVoisin().getNoeud() + "," + oui.getDistance());
//        }

//        for (Arc v : listeFichier) {
//            //listeSommets.get(v.getNoeudCourant()).addVoisin(v);
//            //listeSommets.get(v.getNoeudVoisin()).addVoisin(new Voisin(v.getNoeudVoisin(), v.getNoeudCourant(), v.getDistance()));
//            listeSommets.get(v.getVoisin().getNoeud()).addVoisin(v);
//        }

        //TESTS DAFFICHAGE
//        for (Sommet s : listeSommets) {
//            List<Arc> tempVoisin = s.getVoisins();
//            for (Arc v : tempVoisin) {
//                System.out.println(s.getNoeud() + "," + v.getVoisin().getNoeud() + "," + v.getDistance());
//            }
//        }
    }

    /** TODO
     * Affiche la représentation du graphe sauvegardé en mémoire
     */
    public void afficherGraphe(){
        for (Sommet s : listeSommets) {
            s.print();
        }
        System.out.println("---------------------------------------");
    }


    /** TODO
     * Permet à l’utilisateur de rentrer un nombre d'objets de chaque
     * type que le robot devra aller chercher.
     */
    public void prendreCommande(){
        for (Sommet s : listeSommets) {
            s.reinitialiserTotal();
        }

        calculerMaxObjets();
        System.out.println(maxA);

        int inputUserA, inputUserB, inputUserC;

        System.out.println("Entrez le nombre d'objets de type A: ");
        Scanner scan = new Scanner(System.in);

        while(!scan.hasNextInt()) {
            String input = scan.next();
            System.out.println(input + " n'est pas une option valide!");
            System.out.println("Veuillez saisir le nombre d'objets de type A: ");
            System.out.println("---------------------------------------");
        }
        inputUserA = scan.nextInt();
//        if (inputUserA > maxA) {
//            System.out.println(inputUserA + " n'est pas une option valide!");
//        }

        System.out.println("Entrez le nombre d'objets de type B: ");
        while(!scan.hasNextInt()) {
            String input = scan.next();
            System.out.println(input + " n'est pas une option valide!");
            System.out.println("Veuillez saisir le nombre d'objets de type B: ");
            System.out.println("---------------------------------------");
        }
        inputUserB = scan.nextInt();

        System.out.println("Entrez le nombre d'objets de type C: ");
        while(!scan.hasNextInt()) {
            String input = scan.next();
            System.out.println(input + " n'est pas une option valide!");
            System.out.println("Veuillez saisir le nombre d'objets de type C: ");
            System.out.println("---------------------------------------");
        }
        inputUserC = scan.nextInt();

        commande = new Commande(inputUserA, inputUserB, inputUserC);
    }


    /** TODO
     * Permet de voir la commande en mémoire.
     */
    public void afficherCommande(){
        System.out.println("La commande en mémoire est de : ");
        System.out.println("Nombre d'objets de type A : " + commande.getNbObjetsA_());
        System.out.println("Nombre d'objets de type B : " + commande.getNbObjetsB_());
        System.out.println("Nombre d'objets de type C : " + commande.getNbObjetsC_() + "\n");
    }


    /** TODO
     * Affiche le type de robot utilisé, la liste des noeuds traversés,
     * spécifie lorsqu'un objet est pris à un noeud et le temps
     * total que le robot a pris pour aller chercher la commande.
     *
     * Si le chemin est impossible, la fonction doit en informer l'utilisateur.
     */
    public void plusCourtChemin(Sommet noeudDepart, Sommet noeudArrivee){
        //LinkedList<Sommet> cheminCourt = new LinkedList<>();
        List<Sommet> listeTemp = new ArrayList<>();
        Dijkstra dijskstra = new Dijkstra();
        dijskstra.dijkstra(noeudDepart);
        for (Sommet s : listeSommets) {
            if (s.contientAssezObjets(commande)) {
                listeTemp.add(s);
            }
        }
//        if (listeTemp.isEmpty()) {
//            Sommet max = trouverSommetMaxObjets(listeSommets);
//            dijskstra.dijkstra(max);
//            for (Sommet s : listeSommets) {
//                if (s.contientAssezObjets(new Commande(commande.getNbObjetsA_() - max.getTotalA(), commande.getNbObjetsB_() - max.getTotalB(), commande.getNbObjetsC_() - max.getTotalC()))) {
//                    listeTemp.add(s);
//                }
//            }
//        }
        Sommet distanceMin = trouverSommetMin(listeTemp);
        afficherParcours(distanceMin.getListeSommetsTraverses());
        //System.out.println("noeud" + distanceMin.getNoeud() + " avec distance de " + distanceMin.getSommetDistance());
    }

    public Sommet trouverSommetMaxObjets(List<Sommet> liste) {
        Sommet objetsMax = liste.get(0);
        for (Sommet s : liste) {
            if (s.getTotalA() > objetsMax.getTotalA()) {
                objetsMax = s;
            }
        }
        return objetsMax;
    }

    public Sommet trouverSommetMin(List<Sommet> liste) {
        Sommet distanceMin = liste.get(0);
        for (Sommet sommet : liste) {
            if (sommet.getSommetDistance() < distanceMin.getSommetDistance()) {
                distanceMin = sommet;
            }
        }
        return distanceMin;
    }

    public void afficherParcours(LinkedList<Sommet> liste) {
        for (Sommet s : liste) {
            System.out.print("Noeud" + s.getNoeud() + " --> ");
        }
    }


    public void quitter(){ }

    public void interfaceUsager() {
        int userInput;

        while (true) {
            do{
                System.out.println("Veuillez sélectionner une option");
                System.out.println("--------------------------------");
                System.out.println("1- Créer le graphe");
                System.out.println("2- Afficher le graphe");
                System.out.println("3- Prendre une commande");
                System.out.println("4- Afficher la commande");
                System.out.println("5- Trouver le plus court chemin");
                System.out.println("6- Quitter");

                // Pour lire l'option que l'usager a choisi
                Scanner scan = new Scanner(System.in);

                while(!scan.hasNextInt()) {
                    String input = scan.next();
                    System.out.println(input + " n'est pas une option valide!");
                    System.out.println("Veuillez saisir un chiffre entre 1 à 6.");
                    System.out.println("---------------------------------------");
                }
                userInput = scan.nextInt();

                if (userInput > 6) {
                    System.out.println("Veuillez saisir un chiffre entre 1 à 6!");
                    System.out.println("---------------------------------------");
                }
                // System.out.println(userInput);
            } while (userInput > 6);

            System.out.print("Vous avez sélectionné l'option " + userInput + ": ");

            switch(userInput) {
                case 1:
                    System.out.println("Créer le graphe. \n");
                    creerGraphe("allo");
                    break;
                case 2:
                    System.out.println("Afficher le graphe. \n");
                    afficherGraphe();
                    break;
                case 3:
                    System.out.println("Prendre une commande. \n");
                    prendreCommande();
                    break;
                case 4:
                    System.out.println("Afficher la commande. \n");
                    afficherCommande();
                    break;
                case 5:
                    System.out.println("Trouver le plus court chemin. \n");
                    plusCourtChemin(listeSommets.get(0), listeSommets.get(0));
                    break;
                // pour quitter
                case 6:
                    System.out.println("Quitter.");
                    System.out.println("Au revoir.");
                    System.exit(0);
                    break;
            }
        }
    }

}
