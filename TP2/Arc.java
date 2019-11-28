public class Arc {
    private Etat voisin;
    private String nom;

    public Arc(Etat voisin, String nom) {
        this.voisin = voisin;
        this.nom = nom;
    }

    //getters setters

    public void setVoisin(Etat voisin) {
        this.voisin = voisin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Etat getVoisin() {
        return voisin;
    }
}
