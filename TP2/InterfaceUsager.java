import java.util.Scanner;

public class InterfaceUsager {
    public void initialiserProgramme(){}
    public void fonctionnaliteSuggestion(){}
    public void fonctionnaliteAjout(){}

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
                System.out.println("5- Focntionnalite de passage de commande");
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
                    //initialiserProgramme();
                    break;
                case 2:
                    System.out.println("Fonctionnalite suggestion. \n");
                    //fonctionnaliteSuggestion();
                    break;
                case 3:
                    System.out.println("Fonctionnalite d'ajout d'objet au panier.\n");
                    //fonctionnaliteAjout();
                    break;
                case 4:
                    System.out.println("Fonctionnalite de retrait d'objet du panier ou vidage de panier. \n");
                    //fonctionnaliteRetrait();
                    break;
                case 5:
                    System.out.println("Fonctionnalite de passage de commande. \n");
                    //fonctionnaliteCommade();
                    break;

                case 6:
                    System.out.println("Retour. \n");
                    //retour();

                    // pour quitter
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


