import java.util.ArrayList;
import java.util.List;

public class Etat {
    private String nom;
    private List<Arc> listeArcs = new ArrayList<>();
    private boolean estTraite = false;
    private boolean sortedNom = false;

    // Constructeur par defaut
    public Etat() {
        nom = "";
    }

    public boolean isSortedNom() {
        return sortedNom;
    }

    public void setSortedNom(boolean sortedNom) {
        this.sortedNom = sortedNom;
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

}
