
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfaceUsager {

    private List<Objet> liste = new ArrayList<>();
    private Commande commande;

    public void initialiserProgramme(){
        Automate automate = new Automate();
        automate.lireFichier("inventaire.txt");
        liste = automate.getListeObjets();

        commande = new Commande(liste);

        automate.setEtatsNoms();
        automate.setEtatsTerminaux();
        automate.suggestion("a");
    }

    public void fonctionnaliteSuggestion(){}

    public void fonctionnaliteAjout(){
        commande.afficherObjetsDisponibles();
        commande.afficherPanier();
        int userInput = 0;
        while (true) {
            do {
                System.out.println("Veuillez choisir un objet à ajouter au panier : ");
                Scanner scanner = new Scanner(System.in);
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println(input + " n'est pas une option valide");
                }
                userInput = scanner.nextInt();

                if (userInput < 1 || userInput > commande.getTailleObjetsDisponibles())
                    System.out.println(userInput + " n'est pas une option valide");
            } while (userInput < 1 || userInput > commande.getTailleObjetsDisponibles());
            commande.ajouterCommande(commande.getObjetDisponible(userInput));
            break;
        }
    }

    public void fonctionnaliteRetrait(){
        commande.afficherPanier();
        int userInput = 0;
        while (true) {
            do {
                System.out.println("Veuillez la fonctionnalité désirée : ");
                System.out.println("[1] Retrait d'objets");
                System.out.println("[2] Vidage de panier");
                Scanner scanner = new Scanner(System.in);
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println(input + " n'est pas une option valide");
                }
                userInput = scanner.nextInt();

                if (userInput < 1 || userInput > 2)
                    System.out.println(userInput + " n'est pas une option valide");
            } while (userInput < 1 || userInput > 2);
            if (userInput == 1) {
                System.out.println("retrait");
            }
            else {
                System.out.println("Vidage de panier en cours...");
                commande.viderPanier();
            }
            break;
        }
    }

    public void fonctionnaliteCommande(){
        commande.afficherPanier();
        commande.commander();
    }
    public void retour(){
    }

    public void interfaceUsager(){
        int userInput;

        while (true) {
            do{
                System.out.println("---------------------------------------");
                System.out.println("|   Veuillez sélectionner une option   |");
                System.out.println("---------------------------------------");
                System.out.println("1- Initialiser le programme");
                System.out.println("2- Fonctionnalite suggestion");
                System.out.println("3- Fonctionnalite d'ajout d'objet au panier");
                System.out.println("4- Fonctionnalite de retrait d'objets du panier ou de vidage de panier");
                System.out.println("5- Fonctionnalite de passage de commande");
                System.out.println("6- Retour");
                System.out.println("7- Quitter");

                // Pour lire l'option que l'usager a choisie
                Scanner scan = new Scanner(System.in);

                while(!scan.hasNextInt()) {
                    String input = scan.next();
                    System.out.println(input + " n'est pas une option valide.");
                    System.out.println("Veuillez saisir un chiffre entre 1 à 7.");
                    System.out.println("---------------------------------------");
                }
                userInput = scan.nextInt();

                if (userInput < 1 ||userInput > 7 ) {
                    System.out.println("Veuillez saisir un chiffre entre 1 à 7.");
                    System.out.println("---------------------------------------");
                }
            } while (userInput < 1 || userInput > 7);

            System.out.print("Vous avez sélectionné l'option " + userInput + ": ");

            //switchcase utilisant les différentes méthodes implémentées dans Graphe
            switch(userInput) {
                case 1:
                    System.out.println("Initialiser le programme.\n");
                    initialiserProgramme();
                    break;
                case 2:
                    System.out.println("Fonctionnalité suggestion. \n");
                    //fonctionnaliteSuggestion();
                    break;
                case 3:
                    System.out.println("Fonctionnalité d'ajout d'objet au panier.\n");
                    fonctionnaliteAjout();
                    break;
                case 4:
                    System.out.println("Fonctionnalité de retrait d'objet du panier ou vidage de panier. \n");
                    fonctionnaliteRetrait();
                    break;
                case 5:
                    System.out.println("Fonctionnalité de passage de commande. \n");
                    fonctionnaliteCommande();
                    break;

                case 6:
                    System.out.println("Retour. \n");
                    //retour();
                    break;

                case 7:
                    System.out.println("Quitter.");
                    System.out.println("");
                    System.out.println("Au revoir.");
                    System.exit(0);
                    break;
            }
        }
    }
}


