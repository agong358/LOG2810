import java.util.ArrayList;
import java.util.List;

public class Sommet {
    private int noeud;
    private int nbObjetsA = 0;
    private int nbObjetsB = 0;
    private int nbObjetsC = 0;
    private List<Voisin> voisins = new ArrayList<>();

    public Sommet(int noeud, int nbObjetsA, int nbObjetsB, int nbObjetsC) {
        this.noeud = noeud;
        this.nbObjetsA = nbObjetsA;
        this.nbObjetsB = nbObjetsB;
        this.nbObjetsC = nbObjetsC;
    }

    public int getNoeud() {
        return noeud;
    }

    public int getNbObjetsA() {
        return nbObjetsA;
    }

    public int getNbObjetsB() {
        return nbObjetsB;
    }

    public int getNbObjetsC() {
        return nbObjetsC;
    }

    public List<Voisin> getVoisins() {
        return voisins;
    }

    public void setVoisins(List<Voisin> voisins) {
        this.voisins = voisins;
    }

    public void addVoisin(Voisin voisin) {
        this.voisins.add(voisin);
    }

    public void print() {
        System.out.println("Noeud" + this.getNoeud() + ", nbObjetsA : " + this.getNbObjetsA() + ", nbObjetsB : " +
                this.getNbObjetsB() + ", nbObjetsC : " + this.getNbObjetsC());
        for (Voisin v : voisins) {
            System.out.println("(noeud_voisin_" + v.getNoeudVoisin() + ", distance : " + v.getDistance() + ")");
        }
    }
}
