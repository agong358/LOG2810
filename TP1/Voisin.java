public class Voisin {
    private int noeudCourant;
    private int noeudVoisin;
    private int distance;

    public Voisin(int noeudCourant, int noeudVoisin, int distance) {
        this.noeudCourant = noeudCourant;
        this.noeudVoisin = noeudVoisin;
        this.distance = distance;
    }

    public int getNoeudCourant() {
        return noeudCourant;
    }

    public int getNoeudVoisin() {
        return noeudVoisin;
    }

    public int getDistance() {
        return distance;
    }
}
