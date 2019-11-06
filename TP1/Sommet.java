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
    private boolean assezA = false;
    private boolean assezB = false;
    private boolean assezC = false;
    private int nbPrendreA = 0;
    private int nbPrendreB = 0;
    private int nbPrendreC = 0;

    //Getters
    public int getNbPrendreA() {
        return nbPrendreA;
    }

    public int getNbPrendreB() {
        return nbPrendreB;
    }

    public int getNbPrendreC() {
        return nbPrendreC;
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

    public int getSommetDistance() {
        return sommetDistance;
    }

    public LinkedList<Sommet> getListeSommetsTraverses() {
        return listeSommetsTraverses;
    }

    public int getDistanceArc(Sommet sommet) {
        int distance = 0;
        for (Arc a : voisins) {
            if (a.getVoisin().getNoeud() == sommet.getNoeud()) {
                distance = a.getDistance();
            }
        }
        return distance;
    }

    //Setters
    public void setNbPrendreA(int nbPrendreA) {
        this.nbPrendreA = nbPrendreA;
    }

    public void setNbPrendreB(int nbPrendreB) {
        this.nbPrendreB = nbPrendreB;
    }

    public void setNbPrendreC(int nbPrendreC) {
        this.nbPrendreC = nbPrendreC;
    }

    public void setEstTraite(boolean estTraite) {
        this.estTraite = estTraite;
    }

    public void setVoisins(List<Arc> voisins) {
        this.voisins = voisins;
    }

    public void setSommetDistance(int sommetDistance) {
        this.sommetDistance = sommetDistance;
    }

    public void setListeSommetsTraverses(LinkedList<Sommet> listeSommetsTraverses) {
        this.listeSommetsTraverses = listeSommetsTraverses;
    }

    //Constructeur par paramètres
    public Sommet(int noeud, int nbObjetsA, int nbObjetsB, int nbObjetsC) {
        this.noeud = noeud;
        this.nbObjetsA = nbObjetsA;
        this.nbObjetsB = nbObjetsB;
        this.nbObjetsC = nbObjetsC;
    }

    //Vérifie si le sommet est traité
    public boolean estTraite() {
        return estTraite;
    }

    //Réinitialiser le sommet
    public void reinitialiserTotal() {
        totalA = 0;
        totalB = 0;
        totalC = 0;
        this.assezA = false;
        this.assezB = false;
        this.assezC = false;
    }

    //Ajouter un voisin
    public void addVoisin(Arc voisin) {
        this.voisins.add(voisin);
    }

    //Affichage
    public void print() {
        System.out.println("Noeud" + this.getNoeud() + ", nbObjetsA : " + this.getNbObjetsA() + ", nbObjetsB : " +
                this.getNbObjetsB() + ", nbObjetsC : " + this.getNbObjetsC());
        for (Arc v : voisins) {
            System.out.println("(noeud_voisin_" + v.getVoisin().getNoeud() + ", distance : " + v.getDistance() + ")");
        }
    }

    //Ajouter un sommet a la liste de sommets traversés
    public void addSommetTraverse(Sommet sommet) {
        listeSommetsTraverses.add(sommet);
    }

    //Calculer le total de la distance des trois objets
    public void calculerTotal() {

        for (Sommet s : listeSommetsTraverses) {
            totalA += s.getNbObjetsA();
            totalB += s.getNbObjetsB();
            totalC += s.getNbObjetsC();
        }
    }

    //Vérifier si le robot a pris toutes les objets des commandes
    public boolean contientAssezObjets(Commande commande) {
        calculerTotal();
        if (totalA >= commande.getNbObjetsA_())
            assezA = true;
        if (totalB >= commande.getNbObjetsB_())
            assezB = true;
        if (totalC >= commande.getNbObjetsC_())
            assezC = true;
        return assezA && assezB && assezC;
    }
}
