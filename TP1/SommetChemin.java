import java.util.ArrayList;
import java.util.List;

public class SommetChemin {
    private int sommetCourant;
    private int distanceSommetInitial;
    private List<Integer> listeSommetsTraverses = new ArrayList<>();

    public SommetChemin(int sommetCourant, int distanceSommetInitial) {
        this.sommetCourant = sommetCourant;
        this.distanceSommetInitial = distanceSommetInitial;
    }

    public void addSommet(Sommet sommet) {
        listeSommetsTraverses.add(sommet.getNoeud());
    }
}
