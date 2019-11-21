public class Objet {
    private String nom;
    private String code;
    private String type;

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
