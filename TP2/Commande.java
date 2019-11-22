import java.util.ArrayList;
import java.util.List;

public class Commande {
    private List<Objet> objetsDisponibles = new ArrayList<>();
    private List<Objet> commande = new ArrayList<>();
    private int poidsTotal = 0;

    //ajoute la liste d'objets recherchables, commande est initialement vide
    public Commande(List<Objet> liste) {
        objetsDisponibles = liste;
    }

    public void afficherPanier() {
        for (Objet o : commande) {
            System.out.println(o.getNom() + " " + o.getCode() + " " + o.getType());
        }
    }

    public void ajouterCommande(Objet ajout) {
        commande.add(ajout);
        poidsTotal += ajout.getPoids();
        objetsDisponibles.remove(ajout);
    }

    public void viderPanier() {
        for (Objet o : commande) {
            commande.remove(o);
            objetsDisponibles.add(o);
        }
        poidsTotal = 0;
    }

    public void commander() {
        if (poidsTotal > 25) {
            System.out.println("Commande trop lourde, les derniers items seront enlevés jusqu'à ce que le poids maximal sera respecté");
            do {
                //enleve la derniere commande jusqu'a temps que cest en dessous de 25
                Objet dernierItem = commande.get(commande.size()-1);
                System.out.println("L'objet " + dernierItem.getNom() + " " + dernierItem.getCode() + " " + dernierItem.getType() + " sera"
                + " retiré de la commande");
                commande.remove(dernierItem);
                poidsTotal -= dernierItem.getPoids();
            } while (poidsTotal > 25);
        }
        System.out.println("poids total = " + poidsTotal);

        List<Objet> tempCommande = new ArrayList<>(commande);
        for (Objet o : tempCommande) {
            commande.remove(o);
        }
        System.out.println("Commande effectuée!");
    }
}
