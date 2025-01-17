import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Graphe {
    //liste de tous les Sommets contenus dans ce Graphe
    private List<Sommet> listeSommets = new ArrayList<>();
    //Commande entrée par l'usager
    private Commande commande = new Commande();
    //String du fichier d'ou on va extraire les données pour créer le Graphe
    private String fichier;

    public List<Sommet> getListeSommets() {
        return listeSommets;
    }

    // constructeur par paramètres
    public Graphe(String fichier){
        this.fichier = fichier;
    }
    /** TODO
     * Créer un graphe représentant les différentes section de l'entrepôt, ainsi
     * que les chemins reliants ces sections entre elles à partir d'un fichier texte
     * contenu au même path que ce fichier
     */
    public void creerGraphe(){
        File file = new File(fichier);
        String sommetNbObjets = new String("");
        String voisinDistance = new String("");
        String pattern = ".*,.*,.*,.*";

        //séparer le fichier texte en deux parties, la première
        //avec les nombres d'objets et le deuxième avec les
        //distances
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

        //split les array de String pour séparer les différents noeuds par ligne
        String[] arraySommetNbObjets = sommetNbObjets.split("\n");
        String[] arrayVoisinDistance = voisinDistance.split("\n");

        //séparer les string en valeur unitaire et les convertir en Integer pour les manipuler plus tard
        for (String s : arraySommetNbObjets) {
            String[] tempArray = s.split(",");
            listeSommets.add(new Sommet(Integer.parseInt(tempArray[0]), Integer.parseInt(tempArray[1]),
                    Integer.parseInt(tempArray[2]), Integer.parseInt(tempArray[3])));
        }

        for (String v : arrayVoisinDistance) {
            String[] tempArray = v.split(",");
            listeSommets.get(Integer.parseInt(tempArray[1])).addVoisin(new Arc(listeSommets.get(Integer.parseInt(tempArray[0])), Integer.parseInt(tempArray[2])));
            listeSommets.get(Integer.parseInt(tempArray[0])).addVoisin(new Arc(listeSommets.get(Integer.parseInt(tempArray[1])), Integer.parseInt(tempArray[2])));
        }
    }

    /** TODO
     * Affiche la représentation du graphe sauvegardé en mémoire
     */
    public void afficherGraphe(){
        for (Sommet s : listeSommets) {
            s.print();
        }
    }


    /** TODO
     * Permet à l’utilisateur de rentrer un nombre d'objets de chaque
     * type que le robot devra aller chercher.
     */
    public void prendreCommande(){
        for (Sommet s : listeSommets) {
            s.reinitialiserTotal();
        }

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
        System.out.println("Nombre d'objets de type A : " + commande.getNbObjetsA());
        System.out.println("Nombre d'objets de type B : " + commande.getNbObjetsB());
        System.out.println("Nombre d'objets de type C : " + commande.getNbObjetsC() + "\n");
    }


    /** TODO
     * Affiche le type de robot utilisé, la liste des noeuds traversés,
     * spécifie lorsqu'un objet est pris à un noeud et le temps
     * total que le robot a pris pour aller chercher la commande.
     *
     * Si le chemin est impossible, la fonction doit en informer l'utilisateur.
     */
    public void plusCourtChemin(Sommet noeudDepart){
        List<Sommet> listeTemp = new ArrayList<>();
        Dijkstra dijskstra = new Dijkstra();
        dijskstra.dijkstra(noeudDepart);

        for (Sommet s : listeSommets) {
            if (s.contientAssezObjets(commande)) {
                listeTemp.add(s);
            }
        }

        Sommet distanceMin = trouverSommetMin(listeTemp);

        //création des différents robots avec la commande
        Commande commandeOriginale = new Commande(commande.getNbObjetsA(), commande.getNbObjetsB(), commande.getNbObjetsC());
        RobotX robotX = new RobotX(commande);
        robotX.calculerTempsTotal(distanceMin.getListeSommetsTraverses());
        commande = new Commande(commandeOriginale.getNbObjetsA(), commandeOriginale.getNbObjetsB(), commandeOriginale.getNbObjetsC());
        RobotY robotY = new RobotY(commande);
        robotY.calculerTempsTotal(distanceMin.getListeSommetsTraverses());
        commande = new Commande(commandeOriginale.getNbObjetsA(), commandeOriginale.getNbObjetsB(), commandeOriginale.getNbObjetsC());
        RobotZ robotZ = new RobotZ(commande);
        robotZ.calculerTempsTotal(distanceMin.getListeSommetsTraverses());

        //trouve quel robot a le temps minimum
        trouverRobotMin(robotX, robotY, robotZ);

        //va afficher le robot qui a la distance minimum
        if (robotX.isEstMin())
            afficherParcoursX(distanceMin.getListeSommetsTraverses(), robotX);

        else if (robotY.isEstMin())
            afficherParcoursY(distanceMin.getListeSommetsTraverses(), robotY);

        else if (robotZ.isEstMin())
            afficherParcoursZ(distanceMin.getListeSommetsTraverses(), robotZ);

        else
            System.out.println("Commande trop grande");
    }

    /** TODO
     * Trouver le Robot ayant la distance minimum
     */
    public void trouverRobotMin(RobotX robotX, RobotY robotY, RobotZ robotZ) {
        double tempsMin = robotX.getTempsTotal();
        if (robotY.getTempsTotal() < tempsMin){
            tempsMin = robotY.getTempsTotal();
            if (robotZ.getTempsTotal() < tempsMin) {
                robotZ.setEstMin(true);
            }
            else
                robotY.setEstMin(true);
        }
        else if (robotZ.getTempsTotal() < tempsMin) {
            robotZ.setEstMin(true);
        }
        else if (tempsMin < Double.MAX_VALUE)
            robotX.setEstMin(true);
    }

    /** TODO
     * Trouver le Sommet ayant la distance minimum parmi une liste de Sommets
     */
    public Sommet trouverSommetMin(List<Sommet> liste) {
        Sommet distanceMin = liste.get(0);
        for (Sommet sommet : liste) {
            if (sommet.getSommetDistance() < distanceMin.getSommetDistance()) {
                distanceMin = sommet;
            }
        }
        return distanceMin;
    }

    /** TODO
     * Afficher le parcours d'un RobotX
     */

    public void afficherParcoursX(LinkedList<Sommet> liste, RobotX robot) {
        for (Sommet s : liste) {
            System.out.print("Noeud" + s.getNoeud() + " --> ");
        }
        Sommet premierSommet = robot.getCheminInverse().get(0);
        for (int i = 0; i < premierSommet.getNbPrendreA(); i++)
            System.out.print("Prendre A --> ");
        for (int i = 0; i < premierSommet.getNbPrendreB(); i++)
            System.out.print("Prendre B --> ");
        for (int i = 0; i < premierSommet.getNbPrendreC(); i++)
            System.out.print("Prendre C --> ");
        robot.getCheminInverse().pollFirst();
        for (Sommet s : robot.getCheminInverse()) {
            System.out.print("Noeud" + s.getNoeud() + " --> ");
            for (int i = 0; i < s.getNbPrendreA(); i++)
                System.out.print("Prendre A --> ");
            for (int i = 0; i < s.getNbPrendreB(); i++)
                System.out.print("Prendre B --> ");
            for (int i = 0; i < s.getNbPrendreC(); i++)
                System.out.print("Prendre C --> ");
        }
        System.out.println("end");
        System.out.println("Robot : RobotX");
        System.out.println("Temps : " + robot.getTempsTotal() + "\n");
    }

    /** TODO
     * Afficher le parcours d'un RobotY
     */
    public void afficherParcoursY(LinkedList<Sommet> liste, RobotY robot) {
        for (Sommet s : liste) {
            System.out.print("Noeud" + s.getNoeud() + " --> ");
        }
        Sommet premierSommet = robot.getCheminInverse().get(0);
        for (int i = 0; i < premierSommet.getNbPrendreA(); i++)
            System.out.print("Prendre A --> ");
        for (int i = 0; i < premierSommet.getNbPrendreB(); i++)
            System.out.print("Prendre B --> ");
        for (int i = 0; i < premierSommet.getNbPrendreC(); i++)
            System.out.print("Prendre C --> ");
        robot.getCheminInverse().pollFirst();
        for (Sommet s : robot.getCheminInverse()) {
            System.out.print("Noeud" + s.getNoeud() + " --> ");
            for (int i = 0; i < s.getNbPrendreA(); i++)
                System.out.print("Prendre A --> ");
            for (int i = 0; i < s.getNbPrendreB(); i++)
                System.out.print("Prendre B --> ");
            for (int i = 0; i < s.getNbPrendreC(); i++)
                System.out.print("Prendre C --> ");
        }
        System.out.println("end");
        System.out.println("Robot : RobotY");
        System.out.println("Temps : " + robot.getTempsTotal() + "\n");
    }

    /** TODO
     * Afficher le parcours d'un RobotZ
     */
    public void afficherParcoursZ(LinkedList<Sommet> liste, RobotZ robot) {
        for (Sommet s : liste) {
            System.out.print("Noeud" + s.getNoeud() + " --> ");
        }
        Sommet premierSommet = robot.getCheminInverse().get(0);
        for (int i = 0; i < premierSommet.getNbPrendreA(); i++)
            System.out.print("Prendre A --> ");
        for (int i = 0; i < premierSommet.getNbPrendreB(); i++)
            System.out.print("Prendre B --> ");
        for (int i = 0; i < premierSommet.getNbPrendreC(); i++)
            System.out.print("Prendre C --> ");
        robot.getCheminInverse().pollFirst();
        for (Sommet s : robot.getCheminInverse()) {
            System.out.print("Noeud" + s.getNoeud() + " --> ");
            for (int i = 0; i < s.getNbPrendreA(); i++)
                System.out.print("Prendre A --> ");
            for (int i = 0; i < s.getNbPrendreB(); i++)
                System.out.print("Prendre B --> ");
            for (int i = 0; i < s.getNbPrendreC(); i++)
                System.out.print("Prendre C --> ");
        }
        System.out.println("end");
        System.out.println("Robot : RobotZ");
        System.out.println("Temps : " + robot.getTempsTotal() + "\n");
    }

    /** TODO
     * Afficher une interface demandant à l'usager de choisir une option parmi les 6 disponibles
     */
    public void interfaceUsager() {
        int userInput;

        while (true) {
            do{
                System.out.println("---------------------------------------");
                System.out.println("|   Veuillez sélectionner une option   |");
                System.out.println("---------------------------------------");
                System.out.println("1- Créer le graphe");
                System.out.println("2- Afficher le graphe");
                System.out.println("3- Prendre une commande");
                System.out.println("4- Afficher la commande");
                System.out.println("5- Trouver le plus court chemin");
                System.out.println("6- Quitter");

                // Pour lire l'option que l'usager a choisie
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
            } while (userInput > 6);

            System.out.print("Vous avez sélectionné l'option " + userInput + ": ");

            //switchcase utilisant les différentes méthodes implémentées dans Graphe
            switch(userInput) {
                case 1:
                    System.out.println("Créer le graphe. \n");
                    creerGraphe();
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
                    plusCourtChemin(listeSommets.get(0));
                    break;
                // pour quitter
                case 6:
                    System.out.println("Quitter.");
                    System.out.println("");
                    System.out.println("Au revoir.");
                    System.exit(0);
                    break;
            }
        }
    }
}
