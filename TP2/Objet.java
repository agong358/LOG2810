/**
 * Représente les objets d'un fichier texte.
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
public class Objet {
    private String nom;
    private String code;
    private String type;
    private int poids;
    private int poidsA = 1;
    private int poidsB = 3;
    private int poidsC = 5;

    private boolean estTraite = false;
    private boolean sortedNom = false;

    /**
     *  Constructeur par parametres
     *
     * @param nom Nom de l'objet
     * @param code Code de l'objet
     * @param type Type de l'objet
     */
    public Objet(String nom, String code, String type) {
        this.nom = nom;
        this.code = code;
        this.type = type;
    }

    /**
     *  Vérifie si un objet à été
     *  trié
     *
     *  Utile lorsqu'une méthode de triage
     *  est appelée
     */
    public boolean isSortedNom() {
        return sortedNom;
    }

    /**
     *  Vérifie si un objet à été
     *  traité
     */
    public boolean isEstTraite() {
        return estTraite;
    }

    //getters et setters
    public String getNom() {
        return nom;
    }
    public String getCode() {
        return code;
    }
    public String getType() {
        return type;
    }

    public int getPoids() {
        if (type.equals("A"))
            poids = poidsA;
        else if (type.equals("B"))
            poids = poidsB;
        else
            poids = poidsC;
        return poids;
    }

    public void setSortedNom(boolean sortedNom) { this.sortedNom = sortedNom; }
    public void setEstTraite(boolean estTraite) { this.estTraite = estTraite; }
    public void setNom(String nom) { this.nom = nom; }
    public void setCode(String code) { this.code = code; }
    public void setType(String type) { this.type = type; }
}
