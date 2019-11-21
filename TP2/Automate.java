import java.util.*;

public class Automate {
    private List<String> etats;
    private int id;

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
