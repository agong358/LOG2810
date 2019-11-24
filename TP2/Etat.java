import java.util.HashMap;
public class Etat {
    private String nom;
    private HashMap<Character, Etat> prochains;
    private int nbUtilisation;

    // Constructeur par defaut
    public Etat() {
        nom = "";
        prochains = new HashMap<Character, Etat>();
        nbUtilisation = 0;
    }

}
