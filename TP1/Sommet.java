import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Représente les sommets du graphe
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
public class Sommet {
    //Nombre d'objet accumuler jusqu'à ce sommet
    private int totalA = 0;
    private int totalB = 0;
    private int totalC = 0;
    //Vérifie si le sommet est traité
    private boolean estTraite = false;
    //donne la distance d'un sommet à son voisin
    private int sommetDistance = Integer.MAX_VALUE;
    //donne le noeud
    private int noeud;
    //nombre d'objet A au sommet
    private int nbObjetsA = 0;
    //nombre d'objet B au sommet
    private int nbObjetsB = 0;
    //nombre d'objet C au sommet
    private int nbObjetsC = 0;
    //liste des voisins du sommet
    private List<Arc> voisins = new ArrayList<>();
    //liste des sommets parcourus
    private LinkedList<Sommet> listeSommetsTraverses = new LinkedList<>();
    //vérifie que le robot a pris assez d'objets A
    private boolean assezA = false;
    //Vérifie que le robot a pris assez d'objets B
    private boolean assezB = false;
    //Vérifie que le robot a pris assez d'objets C
    private boolean assezC = false;
    //Nombre d'objet pris à ce sommet
    private int nbPrendreA = 0;
    private int nbPrendreB = 0;
    private int nbPrendreC = 0;

    /**
     *  Getter pour chercher l'attribut nbPrendreA
     *
     * @return attribut nbPrendreA
     */
    public int getNbPrendreA() {
        return nbPrendreA;
    }

    /**
     *  Getter pour chercher l'attribut nbPrendreB
     *
     * @return attribut nbPrendreB
     */
    public int getNbPrendreB() {
        return nbPrendreB;
    }

    /**
     *  Getter pour chercher l'attribut nbPrendreC
     *
     * @return attribut nbPrendreC
     */
    public int getNbPrendreC() {
        return nbPrendreC;
    }

    /**
     *  Getter pour chercher l'attribut noeud
     *
     * @return attribut noeud
     */
    public int getNoeud() {
        return noeud;
    }

    /**
     *  Getter pour chercher l'attribut nbObjetsA
     *
     * @return attribut nbObjetsA
     */
    public int getNbObjetsA() {
        return nbObjetsA;
    }

    /**
     *  Getter pour chercher l'attribut nbObjetsB
     *
     * @return attribut nbObjetsB
     */
    public int getNbObjetsB() {
        return nbObjetsB;
    }

    /**
     *  Getter pour chercher l'attribut nbObjetsC
     *
     * @return attribut nbObjetsC
     */
    public int getNbObjetsC() {
        return nbObjetsC;
    }

    /**
     *  Getter pour chercher l'attribut voisins
     *
     * @return attribut voisins
     */
    public List<Arc> getVoisins() {
        return voisins;
    }

    /**
     *  Getter pour chercher l'attribut sommets
     *
     * @return attribut sommetDistance
     */
    public int getSommetDistance() {
        return sommetDistance;
    }

    /**
     *  Getter pour chercher l'attribut listeSommetsTraverses
     *
     * @return attribut listesSommetsTraverses
     */
    public LinkedList<Sommet> getListeSommetsTraverses() {
        return listeSommetsTraverses;
    }

    /**
     *  Trouver la distance entre le sommet courant et son voisin
     *
     * @param sommet
     * @return distance
     */
    public int getDistanceArc(Sommet sommet) {
        int distance = 0;
        for (Arc a : voisins) {
            if (a.getVoisin().getNoeud() == sommet.getNoeud()) {
                distance = a.getDistance();
            }
        }
        return distance;
    }

    /**
     *  Setter pour mettre à jour l'attribut nbPrendreA
     */
    public void setNbPrendreA(int nbPrendreA) {
        this.nbPrendreA = nbPrendreA;
    }

    /**
     *  Setter pour mettre à jour l'attribut nbPrendreB
     */
    public void setNbPrendreB(int nbPrendreB) {
        this.nbPrendreB = nbPrendreB;
    }

    /**
     *  Setter pour mettre à jour l'attribut nbPrendreC
     */
    public void setNbPrendreC(int nbPrendreC) {
        this.nbPrendreC = nbPrendreC;
    }

    /**
     *  Setter pour mettre à jour l'attribut estTraite
     */
    public void setEstTraite(boolean estTraite) {
        this.estTraite = estTraite;
    }

    /**
     *  Setter pour mettre à jour l'attribut voisins
     */
    public void setVoisins(List<Arc> voisins) {
        this.voisins = voisins;
    }

    /**
     *  Setter pour mettre à jour l'attribut sommetDistance
     */
    public void setSommetDistance(int sommetDistance) {
        this.sommetDistance = sommetDistance;
    }

    /**
     *  Setter pour mettre à jour l'attribut listeSommetsTraverses
     */
    public void setListeSommetsTraverses(LinkedList<Sommet> listeSommetsTraverses) {
        this.listeSommetsTraverses = listeSommetsTraverses;
    }

    /**
     *  Constructeur par paramètres
     *
     * @param noeud
     * @param nbObjetsA nombre d'objets A
     * @param nbObjetsB nombre d'objets B
     * @param nbObjetsC nombre d'objets C
     */
    public Sommet(int noeud, int nbObjetsA, int nbObjetsB, int nbObjetsC) {
        this.noeud = noeud;
        this.nbObjetsA = nbObjetsA;
        this.nbObjetsB = nbObjetsB;
        this.nbObjetsC = nbObjetsC;
    }

    /**
     *  Vérifie si le sommet est traité
     *
     * @return estTraite
     */
    public boolean estTraite() {
        return estTraite;
    }

    /**
     *  Réinitialiser le sommet
     */
    public void reinitialiserTotal() {
        totalA = 0;
        totalB = 0;
        totalC = 0;
        this.assezA = false;
        this.assezB = false;
        this.assezC = false;
    }

    /**
     *  Ajoute un voisin
     */

    public void addVoisin(Arc voisin) {
        this.voisins.add(voisin);
    }

    /**
     *  Affiche un sommet
     */
    public void print() {
        System.out.println("Noeud" + this.getNoeud() + ", nbObjetsA : " + this.getNbObjetsA() + ", nbObjetsB : " +
                this.getNbObjetsB() + ", nbObjetsC : " + this.getNbObjetsC());
        for (Arc v : voisins) {
            System.out.println("    (noeud_voisin_" + v.getVoisin().getNoeud() + ", distance : " + v.getDistance() + ")");
        }

        System.out.println("");
    }

    //Ajouter un sommet a la liste de sommets traversés
    /**
     *  Ajouter un sommet à la liste de sommets traversés
     *
     * @param sommet
     */
    public void addSommetTraverse(Sommet sommet) {
        listeSommetsTraverses.add(sommet);
    }

    /**
     *  Calculer le total des nombres d'objets
     */
    public void calculerTotal() {

        for (Sommet s : listeSommetsTraverses) {
            totalA += s.getNbObjetsA();
            totalB += s.getNbObjetsB();
            totalC += s.getNbObjetsC();
        }
    }

    //Vérifier si le sommet contient assez d'objet pour la commande
    /**
     *  Vérifie si le sommet contient assez d'objet pour la commande
     *
     * @param commande nombre d'objets entrés par l'utilisateur
     * @return assezA & assezB && assezC
     */
    public boolean contientAssezObjets(Commande commande) {
        calculerTotal();
        if (totalA >= commande.getNbObjetsA())
            assezA = true;
        if (totalB >= commande.getNbObjetsB())
            assezB = true;
        if (totalC >= commande.getNbObjetsC())
            assezC = true;
        return assezA && assezB && assezC;
    }
}
