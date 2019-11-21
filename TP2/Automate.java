import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Automate {
    private List<String> etats;
    private int id;
    private List<Objet> listeObjets = new ArrayList<>();
    private String fichier = "inventaire.txt";

    // Constructeur par defaut
    public Automate() {
        etats = new ArrayList<String>();
    }

    // Constructeur par parametres
    public Automate(List<String> etats, int id) {
        this.etats = etats;
        this.id = id;
    }

    /* Fonction lire fichier ici*/

    public void lireFichier() {
        File file = new File(fichier);

        String texteFichier = "";

        //lire le fichier texte et tout store dans un string, ligne par ligne
        try {
            Scanner scan = new Scanner(file).useDelimiter("[\n]");
            while (scan.hasNext()) {
                texteFichier += scan.next() + ',';
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //store les lignes separement dans un array de string
        String[] lignesObjets = texteFichier.split(",");

        //separer les strings grace a l'espace entre chaque element
        for (String s : lignesObjets) {
            String[] tempArray = s.split(" ");
            listeObjets.add(new Objet(tempArray[0], tempArray[1], tempArray[2]));
        }

        //test pour print
        for (Objet o : listeObjets) {
            System.out.println(o.getNom() + " " + o.getCode() + " " + o.getType());
        }
    }


//    // Fonction pour creer les etats
//    if
//
//
////    // Fonction pour suggestions
////    public List<String> suggestionNom() {
////        List<String> suggestions = new ArrayList<String>();
////
////        return suggestions;
////    }

    // Getters et setters
    public List<String> getEtats() {
        return etats;
    }

    public void setEtats(List<String> etats) {
        this.etats = etats;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }
}
