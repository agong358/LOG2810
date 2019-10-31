public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenue au troisieme labo de INF2010!");
        Graphe graphe = new Graphe();
        System.out.println("Veuillez sélectionner une option");
        System.out.println("[] Créer le graphe");
        System.out.println("[] Afficher le graphe");
        System.out.println("[] Prendre une commande");
        System.out.println("[] Afficher la commande");
        System.out.println("[] Trouver le plus court chemin");
        System.out.println("[] Quitter");
        graphe.creerGraphe("fichier");
    }
}