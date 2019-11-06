import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe pour contenant des méthodes utiles pour appliquer l'algorithme Dijkstra
 * dans notre laboratoire.
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
public class Dijkstra {

    // Liste contenant les sommets non traités
    private List<Sommet> listeSommetsATraiter= new ArrayList<>();

    /**
     * Méthode principale de cette classe, utilisant les deux prochaines méthodes.
     * Représente l'algorithme de Dijkstra
     */
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

    /**
     * Retourne le sommet ayant la plus distance parmi une liste de noeuds non traités
     *
     * @return Sommet se retrouvant à la plus courte distance parmi une liste de d'autres sommets
     */
    public Sommet getSommetDistanceMin(List<Sommet> liste) {
        Sommet distanceMin = liste.get(0);
        
        // Pour parcourir une liste de sommets pour évaluer leur distance
        for (Sommet s : liste) {
            if (s.getSommetDistance() < distanceMin.getSommetDistance()) {
                distanceMin = s;
            }
        }
        return distanceMin;
    }

    /**
     * Compare la distance actuelle avec une distance initiale, permettant de calculer
     * la distance minimale
     */
    public void calculerDistanceMin(Sommet sommetTraite, int distanceArc, Sommet sommetInitial) {
        int distanceInitiale = sommetInitial.getSommetDistance();

        // Comparaison de la distance initiale avec la distance du sommet traité
        if (sommetTraite.getSommetDistance() > distanceInitiale + distanceArc) {
            sommetTraite.setSommetDistance(distanceInitiale + distanceArc);
            LinkedList<Sommet> chemin = new LinkedList<>(sommetInitial.getListeSommetsTraverses());
            chemin.add(sommetTraite);
            sommetTraite.setListeSommetsTraverses(chemin);
        }
    }

}
