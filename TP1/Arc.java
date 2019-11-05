public class Arc {
    private Sommet noeudVoisin;
    private int distance;

    public Arc(Sommet noeudVoisin, int distance) {
        this.noeudVoisin = noeudVoisin;
        this.distance = distance;
    }

    public Sommet getVoisin() {
        return noeudVoisin;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
