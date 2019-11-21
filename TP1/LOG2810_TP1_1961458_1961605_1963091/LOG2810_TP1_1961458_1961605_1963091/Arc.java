/**
 * Représente les arcs du graphe illustrant l'emplacement des objets dans l'entrepôt.
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
public class Arc {
    // Noeud se retrouvant de l'autre extremité de l'arc, c'est-à-dire le noeud voisin
    private Sommet noeudVoisin;

    // Distance entre les deux extrémités de l'arc
    private int distance;

    /**
     *  Constructeur par paramètres
     *
     * @param noeudVoisin Noeud voisin au noeud présent dans l'arc
     * @param distance Distance entre les deux noeuds de l'arc
     */
    public Arc(Sommet noeudVoisin, int distance) {
        this.noeudVoisin = noeudVoisin;
        this.distance = distance;
    }

    /**
     *  Getter pour chercher l'attribut noeudVoisin
     *
     * @return attribut noeudVoisin
     */
    public Sommet getVoisin() {
        return noeudVoisin;
    }

    /**
     *  Getter pour chercher l'attribut distance
     *
     * @return attribut distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     *  Setter pour mettre à jour l'attribut distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }
}
