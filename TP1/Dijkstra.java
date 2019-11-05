import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dijkstra {

    private List<Sommet> listeSommetsVerifies = new ArrayList<>();
    private List<Sommet> listeSommetsATraiter= new ArrayList<>();
    private int nbA;
    private int nbB;
    private int nbC;

    public void dijkstra(Sommet noeudDepart) {
        noeudDepart.setSommetDistance(0);
        noeudDepart.addSommetTraverse(noeudDepart);
        listeSommetsATraiter.add(noeudDepart);

        while (!listeSommetsATraiter.isEmpty()) {
            Sommet sommetTemp = getSommetDistanceMin(listeSommetsATraiter);
            listeSommetsATraiter.remove(sommetTemp);
            for (Arc a : sommetTemp.getVoisins()) {
                if (!a.getVoisin().estTraite()) {
                    calculerDistanceMin(a.getVoisin(), a.getDistance(), sommetTemp);
                    listeSommetsATraiter.add(a.getVoisin());
                }
            }
            sommetTemp.setEstTraite(true);
        }
    }

    public Sommet getSommetDistanceMin(List<Sommet> liste) {
        //List<Voisin> listeVoisins = sommet.getVoisins();
        //SommetChemin sommetTemp = listeVoisins.get(0);
        Sommet distanceMin = liste.get(0);
        for (Sommet s : liste) {
            if (s.getSommetDistance() < distanceMin.getSommetDistance()) {
                distanceMin = s;
            }
        }
        return distanceMin;
    }

    public void calculerDistanceMin(Sommet sommetTraite, int distanceArc, Sommet sommetInitial) {
        int distanceInitiale = sommetInitial.getSommetDistance();
        if (sommetTraite.getSommetDistance() > distanceInitiale + distanceArc) {
            sommetTraite.setSommetDistance(distanceInitiale + distanceArc);
            LinkedList<Sommet> chemin = new LinkedList<>(sommetInitial.getListeSommetsTraverses());
            //chemin.add(sommetInitial);
            chemin.add(sommetTraite);
            sommetTraite.setListeSommetsTraverses(chemin);
        }
    }

}
