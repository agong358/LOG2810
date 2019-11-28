import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Etat {
    private String nom;
    private HashMap<Character, Etat> prochains;
    private int nbUtilisation;
    private List<Arc> listeArcs = new ArrayList<>();
    private boolean estTraite = false;

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

    public boolean isEstTraite() {
        return estTraite;
    }

    public void setEstTraite(boolean estTraite) {
        this.estTraite = estTraite;
    }

    public boolean hasNext() {
        if (!listeArcs.isEmpty())
            return true;
        return false;
    }

    public boolean aPlusieursVoisins() {
        if (listeArcs.size() > 1)
            return true;
        return false;
    }

    //recursivite BABY
//    public void parcourirEtats() {
//        Etat courant = new Etat();
//        while (hasNext()) {
//            if (aPlusieursVoisins()) {
//                courant.parcourirEtats();
//            }
//            courant = courant.getListeArcs().get(0).getVoisin();
//        }
//    }
}
