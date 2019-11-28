import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Automate {
    private List<String> etats;
    private int id;
    private List<Objet> listeObjets = new ArrayList<>();
    private String fichier = "inventaire.txt";
    private List<Etat> listeEtats = new ArrayList<>();
    private List<Etat> listeEtatsTerminaux = new ArrayList<>();
    private Etat rootNom = new Etat();
    private Etat rootCode = new Etat();
    private Etat rootType = new Etat();
    private List<Etat> suggestion = new ArrayList<>();

    // Constructeur par defaut
    public Automate() {
        etats = new ArrayList<String>();
    }

    // Constructeur par parametres
    public Automate(List<String> etats, int id) {
        this.etats = etats;
        this.id = id;
    }

    /* Fonction lire fichier ici*/
    public void lireFichier() {
        File file = new File(fichier);

        String texteFichier = "";

        //lire le fichier texte et tout store dans un string, ligne par ligne
        try {
            Scanner scan = new Scanner(file).useDelimiter("[\r\n]");
            while (scan.hasNext()) {
                texteFichier += scan.next() + ",";
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //store les lignes separement dans un array de string
        String[] lignesObjets = texteFichier.split(",,");

        //separer les strings grace a l'espace entre chaque element
        for (String s : lignesObjets) {
            String[] tempArray = s.split(" ");
            listeObjets.add(new Objet(tempArray[0], tempArray[1], tempArray[2]));
        }

        //test pour print
        for (Objet o : listeObjets) {
            System.out.println(o.getNom() + " " + o.getCode() + " " + o.getType());
        }
    }

    public void setEtatsNoms() {
        for (Objet o : listeObjets) {
            int i = 0;
            String chara = "";
            Etat courant = rootNom;
            while (i < o.getNom().length()) {
                chara += String.valueOf(o.getNom().charAt(i++));
                if (!contientEtat(chara, listeEtats)) {
                    Etat prochain = new Etat(chara);
                    listeEtats.add(prochain);
                    courant.addArc(new Arc(prochain, chara));
                    courant = prochain;
                }
                else {
                    courant = getEtatContenu(chara);
                }
            }
        }

//        for (Arc a : rootNom.getListeArcs()) {
//            System.out.println(a.getNom());
//        }
        for (Etat e : listeEtats) {
            System.out.println(e.getNom());
        }
    }

    public boolean contientEtat(String etat, List<Etat> liste) {
        for (Etat e : liste) {
            if (e.getNom().equals(etat)) {
                if (!e.isEstTraite()) {
                    e.setEstTraite(true);
                    return true;
                }
            }
        }
        return false;
    }

    public Etat getEtatContenu(String etat) {
        for (Etat e : listeEtats) {
            if (e.getNom().equals(etat))
                return e;
        }
        return null;
    }

    public void setEtatsTerminaux() {
        for (Objet o : listeObjets) {
            Etat terminal = new Etat(o.getNom());
            listeEtatsTerminaux.add(terminal);
        }
    }

    public void suggestion(String input) {
//        for (Etat e : listeEtatsTerminaux) {
//            if (e.getNom().startsWith(input))
//                System.out.println(e.getNom());
//        } //methode non automate

        Etat courant = new Etat();
        for (Etat e : listeEtats) {
            if (e.getNom().equals(input)) {
                parcourirEtats(e);
//                courant = e;
//                while (courant.hasNext()) {
////                    for (Arc a : e.getListeArcs()) {
////                        courant = a.getVoisin();
////                    }
//                    courant = courant.getListeArcs().get(0).getVoisin();
//                    //courant = e.getListeArcs().get(0).getVoisin();
//                }
                //System.out.println(courant.getNom());
            }
        }
        printSuggestions();
    }

    public void parcourirEtats(Etat e) {
        Etat courant = e;
        //e.setEstTraite(true);
        while (courant.hasNext()) {
//            if (contientEtat(courant.getNom(), listeEtatsTerminaux)) {
//                System.out.println(courant.getNom());
//                courant.setEstTraite(true);
//            }
            for (Etat etat : listeEtatsTerminaux) {
                if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                    etat.setEstTraite(true);
                    suggestion.add(courant);
                    //System.out.println(courant.getNom());
                }
            }

            if (courant.aPlusieursVoisins()) {
                for (Arc a : courant.getListeArcs()) {
                    //if (!a.getVoisin().isEstTraite())
                        parcourirEtats(a.getVoisin());
                }
            }
            //courant.setEstTraite(true);
            courant = courant.getListeArcs().get(0).getVoisin();
        }
//        if (contientEtat(courant.getNom(), listeEtatsTerminaux)) {
//            System.out.println(courant.getNom());
//            courant.setEstTraite(true);
//        }
        for (Etat etat : listeEtatsTerminaux) {
            if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                etat.setEstTraite(true);
                suggestion.add(courant);
                //System.out.println(courant.getNom());
            }
        }


//        if (!courant.isEstTraite()) {
//            System.out.println(courant.getNom());
//            courant.setEstTraite(true);
//        }

    }

    public void printSuggestions() {
        System.out.println("SUGGE");
        for (Etat suggestions : suggestion) {
            System.out.println(suggestions.getNom());
        }
    }



//    // Fonction pour creer les etats
//    if
//
//
////    // Fonction pour suggestions
////    public List<String> suggestionNom() {
////        List<String> suggestions = new ArrayList<String>();
////
////        return suggestions;
////    }

    // Getters et setters
    public List<String> getEtats() {
        return etats;
    }

    public void setEtats(List<String> etats) {
        this.etats = etats;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public List<Objet> getListeObjets() {
        return listeObjets;
    }
}
