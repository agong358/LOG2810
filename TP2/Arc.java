public class Arc {
    private Etat voisin;
    private int distance;
    private String nom;

//    public Arc(Etat voisin, int distance) {
//        this.voisin = voisin;
//        this.distance = distance;
//    }

    public Arc(Etat voisin, String nom) {
        this.voisin = voisin;
        this.nom = nom;
    }

    //getters setters
//    public void setDistance(int distance) {
//        this.distance = distance;
//    }

    public void setVoisin(Etat voisin) {
        this.voisin = voisin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    //    public int getDistance() {
//        return distance;
//    }

    public String getNom() {
        return nom;
    }

    public Etat getVoisin() {
        return voisin;
    }
}
