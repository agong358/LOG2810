import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Sommet {
    private int totalA = 0;
    private int totalB = 0;
    private int totalC = 0;
    private boolean estTraite = false;
    private int sommetDistance = Integer.MAX_VALUE;
    private int noeud;
    private int nbObjetsA = 0;
    private int nbObjetsB = 0;
    private int nbObjetsC = 0;
    private List<Arc> voisins = new ArrayList<>();
    private LinkedList<Sommet> listeSommetsTraverses = new LinkedList<>();

    public Sommet(int noeud, int nbObjetsA, int nbObjetsB, int nbObjetsC) {
        this.noeud = noeud;
        this.nbObjetsA = nbObjetsA;
        this.nbObjetsB = nbObjetsB;
        this.nbObjetsC = nbObjetsC;
    }

    public boolean estTraite() {
        return estTraite;
    }

    public void setEstTraite(boolean estTraite) {
        this.estTraite = estTraite;
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

    public List<Arc> getVoisins() {
        return voisins;
    }

    public void setVoisins(List<Arc> voisins) {
        this.voisins = voisins;
    }

    public void addVoisin(Arc voisin) {
        this.voisins.add(voisin);
    }

    public void print() {
        System.out.println("Noeud" + this.getNoeud() + ", nbObjetsA : " + this.getNbObjetsA() + ", nbObjetsB : " +
                this.getNbObjetsB() + ", nbObjetsC : " + this.getNbObjetsC());
        for (Arc v : voisins) {
            System.out.println("(noeud_voisin_" + v.getVoisin().getNoeud() + ", distance : " + v.getDistance() + ")");
        }
    }

    public int getSommetDistance() {
        return sommetDistance;
    }

    public void setSommetDistance(int sommetDistance) {
        this.sommetDistance = sommetDistance;
    }

    public void setListeSommetsTraverses(LinkedList<Sommet> listeSommetsTraverses) {
        this.listeSommetsTraverses = listeSommetsTraverses;
    }

    public LinkedList<Sommet> getListeSommetsTraverses() {
        return listeSommetsTraverses;
    }

    public void addSommetTraverse(Sommet sommet) {
        listeSommetsTraverses.add(sommet);
    }

    public void calculerTotal() {

        for (Sommet s : listeSommetsTraverses) {
            totalA += s.getNbObjetsA();
            totalB += s.getNbObjetsB();
            totalC += s.getNbObjetsC();
        }
    }

    public boolean contientAssezObjets(Commande commande) {
        boolean estVrai = false;
        calculerTotal();
        if (totalA >= commande.getNbObjetsA_() && totalB >= commande.getNbObjetsB_() && totalC >= commande.getNbObjetsC_())
            estVrai = true;
        return estVrai;
    }

//    public Sommet trouverSommetMin(List<Sommet> liste) {
//        Sommet distanceMin = liste.get(0);
//        for (Sommet sommet : liste) {
//            if (sommet.getSommetDistance() <= distanceMin.getSommetDistance()) {
//                distanceMin = sommet;
//            }
//        }
//        return distanceMin;
//    }

    public int getTotalA() {
        return totalA;
    }

    public int getTotalB() {
        return totalB;
    }

    public int getTotalC() {
        return totalC;
    }
}
