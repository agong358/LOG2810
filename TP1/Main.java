package LOG2810.TP1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

            System.out.print("Vous avez sélectionner l'option " + userInput + ": ");

            switch(userInput) {
                case 1:
                    System.out.println("Créer le graphe.");
                    break;
                case 2:
                    System.out.println("Afficher le graphe.");
                    break;
                case 3:
                    System.out.println("Prendre une commande.");
                    break;
                case 4:
                    System.out.println("Afficher la commande.");
                    break;
                case 5:
                    System.out.println("Trouver le plus court chemin.");
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