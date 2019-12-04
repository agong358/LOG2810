/**
 * Représente les états de l'automate.
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
import java.util.ArrayList;
import java.util.List;

public class Etat {
    // nom de l'état
    private String nom;

    // liste des arcs liés à l'état courant
    private List<Arc> listeArcs = new ArrayList<>();

    private boolean estTraite = false;
    private boolean sortedNom = false;

    /**
     *  Constructeur par défaut
     */
    public Etat() {
        nom = "";
    }

    /**
     *  Constructeur par parametres
     *
     * @param nom Nom de l'état
     */
    public Etat(String nom) {
        this.nom = nom;
    }

    /**
     *  Vérifie si un état à été
     *  trié
     *
     *  Utile lorsqu'une méthode de triage
     *  est appelée
     */
    public boolean isSortedNom() {
        return sortedNom;
    }

    /**
     *  Vérifie si un état à été
     *  trier
     */
    public boolean isEstTraite() {
        return estTraite;
    }

    /**
     *  Ajoute un arc dans la liste des arcs
     *
     *  @param arc Arc à ajouter
     */
    public void addArc(Arc arc) {
        listeArcs.add(arc);
    }

    /**
     *  Vérifie s'il y a état qui
     *  suit l'état courant
     */
    public boolean hasNext() {
        if (!listeArcs.isEmpty())
            return true;
        return false;
    }

    /**
     *  Vérifie si un état possède plusieurs voisins
     */
    public boolean aPlusieursVoisins() {
        if (listeArcs.size() > 1)
            return true;
        return false;
    }

    // getters et setters
    public List<Arc> getListeArcs() { return listeArcs; }
    public String getNom() { return nom; }
    public void setSortedNom(boolean sortedNom) { this.sortedNom = sortedNom; }
    public void setEstTraite(boolean estTraite) { this.estTraite = estTraite; }
}
