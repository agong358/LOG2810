//import java.util.ArrayList;
//import java.util.List;
//
//public class Commande {
//    private List<Objet> objetsDisponibles = new ArrayList<>();
//    private List<Objet> commande = new ArrayList<>();
//    private int poidsTotal = 0;
//
//    //ajoute la liste d'objets recherchables, commande est initialement vide
//    public Commande(List<Objet> liste) {
//        objetsDisponibles = liste;
//    }
//
//    public void afficherPanier() {
//        System.out.println("Contenu du panier : ");
//        for (Objet o : commande) {
//            System.out.println(o.getNom() + " " + o.getCode() + " " + o.getType());
//        }
//    }
//
//    public void afficherObjetsDisponibles() {
//        System.out.println("Objets disponibles : ");
//        for (Objet o : objetsDisponibles) {
//            System.out.println(o.getNom() + " " + o.getCode() + " " + o.getType());
//        }
//    }
//
//    public void ajouterCommande(Objet ajout) {
//        if (objetsDisponibles.contains(ajout)) {
//            commande.add(ajout);
//            poidsTotal += ajout.getPoids();
//            objetsDisponibles.remove(ajout);
//            System.out.println("L'objet a été ajouté à la commande.");
//        }
//        else
//            System.out.println("Cet objet n'est pas disponible!");
//    }
//
//    public void retirerCommande (Objet retire) {
//        if (commande.contains(retire)) {
//            commande.remove(retire);
//            poidsTotal -= retire.getPoids();
//            objetsDisponibles.add(retire);
//            System.out.println("L'objet a été retiré de la commande.");
//        }
//        else
//            System.out.println("Cet objet ne se trouvait pas dans la commande!");
//    }
//
//    public void viderPanier() {
//        List<Objet> temp = new ArrayList<>(commande);
//        for (Objet o : temp) {
//            commande.remove(o);
//            objetsDisponibles.add(o);
//        }
//        poidsTotal = 0;
//        System.out.println("Panier vide!");
//    }
//
//    public void commander() {
//        if (poidsTotal > 25) {
//            System.out.println("Commande trop lourde, les derniers items seront enlevés jusqu'à ce que le poids maximal est respecté.");
//            do {
//                //enleve la derniere commande jusqu'a temps que cest en dessous de 25
//                Objet dernierItem = commande.get(commande.size()-1);
//                System.out.println("L'objet " + dernierItem.getNom() + " " + dernierItem.getCode() + " " + dernierItem.getType() + " sera"
//                + " retiré de la commande.");
//                commande.remove(dernierItem);
//                poidsTotal -= dernierItem.getPoids();
//            } while (poidsTotal > 25);
//        }
//        System.out.println("Poids total = " + poidsTotal);
//
//        List<Objet> tempCommande = new ArrayList<>(commande);
//        for (Objet o : tempCommande) {
//            commande.remove(o);
//        }
//        System.out.println("Commande effectuée!");
//    }
//
//    public int getTailleObjetsDisponibles() {
//        return objetsDisponibles.size();
//    }
//
//    public Objet getObjetDisponible(int index) {
//        return objetsDisponibles.get(index - 1); //-1 puisque affichage commence a 1
//    }
//
//}
