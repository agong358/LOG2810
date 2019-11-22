public class Objet {
    private String nom;
    private String code;
    private String type;
    private int poids;
    private int poidsA = 1;
    private int poidsB = 3;
    private int poidsC = 5;


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
        if (type.equals("A") || type.equals("A,"))
            poids = poidsA;
        else if (type.equals("B"))
            poids = poidsB;
        else
            poids = poidsC;
        return poids;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Objet(String nom, String code, String type) {
        this.nom = nom;
        this.code = code;
        this.type = type;
    }
}
