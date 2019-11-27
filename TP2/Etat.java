import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Etat {
    private String nom;
    private HashMap<Character, Etat> prochains;
    private int nbUtilisation;
    private List<Arc> listeArcs = new ArrayList<>();

    // Constructeur par defaut
    public Etat() {
        nom = "";
        prochains = new HashMap<Character, Etat>();
        nbUtilisation = 0;
    }

    public Etat(String nom) {
        this.nom = nom;
    }

    public void addArc(Arc arc) {
        listeArcs.add(arc);
    }

    public List<Arc> getListeArcs() {
        return listeArcs;
    }

    public String getNom() {
        return nom;
    }
}
