/**
 * Représente les arcs du graphe illustrant l'emplacement des objets dans l'entrepôt.
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
public class Arc {
    // état voisin
    private Etat voisin;

    // nom de l'arc
    private String nom;

    /**
     *  Constructeur par paramètres
     *
     * @param voisin Etat voisin
     * @param nom Nom de l'arc
     */
    public Arc(Etat voisin, String nom) {
        this.voisin = voisin;
        this.nom = nom;
    }

    // getters et setters
    public Etat getVoisin() {
        return voisin;
    }
    public void setVoisin(Etat voisin) {
        this.voisin = voisin;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
