import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Automate {

    private List<Objet> listeObjets = new ArrayList<>();
//    private String fichier = "inventaire.txt";
    private List<Etat> listeEtats = new ArrayList<>();
    private List<Etat> listeEtatsCodes = new ArrayList<>();
    private List<Etat> listeEtatsTypes = new ArrayList<>();
    private List<Etat> listeEtatsTerminaux = new ArrayList<>();
    private List<Etat> listeEtatsTerminauxCodes = new ArrayList<>();
    private List<Etat> listeEtatsTerminauxTypes = new ArrayList<>();
    private List<Objet> suggestion = new ArrayList<>();
    private List<Objet> suggestionCodes = new ArrayList<>();
    private List<Objet> suggestionTypes = new ArrayList<>();

    // Constructeur par defaut
    public Automate() {
    }

    /* Fonction lire fichier ici*/
    public void lireFichier(String nom) {
        File file = new File(nom);

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
            Etat courant = new Etat();
            while (i < o.getNom().length()) {
                chara += String.valueOf(o.getNom().charAt(i++));
                if (!contientEtat(chara, listeEtats)) {
                    Etat prochain = new Etat(chara);
                    listeEtats.add(prochain);
                    courant.addArc(new Arc(prochain, chara));
                    courant = prochain;
                }
                else {
                    courant = getEtatContenu(chara, listeEtats);
                }
            }
        }
        for (Etat e : listeEtats) {
            System.out.println(e.getNom());
        }
    }

    public void setEtatsCodes() {
        for (Objet o : listeObjets) {
            int i = 0;
            String chara = "";
            Etat courant = new Etat();
            while (i < o.getCode().length()) {
                chara += String.valueOf(o.getCode().charAt(i++));
                if (!contientEtat(chara, listeEtatsCodes)) {
                    Etat prochain = new Etat(chara);
                    listeEtatsCodes.add(prochain);
                    courant.addArc(new Arc(prochain, chara));
                    courant = prochain;
                }
                else {
                    courant = getEtatContenu(chara, listeEtatsCodes);
                }
            }
        }
        System.out.println("COde");
        for (Etat e : listeEtatsCodes) {
            System.out.println(e.getNom());
        }
    }

    public void setEtatsTypes() {
        for (Objet o : listeObjets) {
            int i = 0;
            String chara = "";
            Etat courant = new Etat();
            while (i < o.getType().length()) {
                chara += String.valueOf(o.getType().charAt(i++));
                if (!contientEtat(chara, listeEtatsTypes)) {
                    Etat prochain = new Etat(chara);
                    listeEtatsTypes.add(prochain);
                    courant.addArc(new Arc(prochain, chara));
                    courant = prochain;
                }
                else {
                    courant = getEtatContenu(chara, listeEtatsTypes);
                }
            }
        }
        System.out.println("typ e");
        for (Etat e : listeEtatsTypes) {
            System.out.println(e.getNom());
        }
    }




    public boolean contientEtat(String etat, List<Etat> liste) {
        for (Etat e : liste) {
            if (e.getNom().equals(etat)) {
                return true;
            }
        }
        return false;
    }


    //pour nom
    public Etat getEtatContenu(String etat, List<Etat> liste) {
        for (Etat e : liste) {
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

    public void setEtatsTerminauxCodes() {
        for (Objet o : listeObjets) {
            Etat terminal = new Etat(o.getCode());
            listeEtatsTerminauxCodes.add(terminal);
        }
    }

    public void setEtatsTerminauxTypes() {
        for (Objet o : listeObjets) {
            Etat terminal = new Etat(o.getType());
            listeEtatsTerminauxTypes.add(terminal);
        }
    }


    public void suggestion(String input) {
        resetListeEtats(listeEtatsTerminaux);
        resetListeObjets();
//        for (Etat e : listeEtatsTerminaux) {
//            if (e.getNom().startsWith(input))
//                System.out.println(e.getNom());
//        } //methode non automate

        for (Etat e : listeEtats) {
            if (e.getNom().equals(input)) {
                parcourirEtats(e, listeEtatsTerminaux);
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
        sortSuggestions(suggestion);
    }

    public void suggestionsCode(String input) {
        resetListeEtats(listeEtatsTerminauxCodes);
        resetListeObjets();
        for (Etat e : listeEtatsCodes) {
            if (e.getNom().equals(input)) {
                parcourirEtatsCodes(e, listeEtatsTerminauxCodes);
            }
        }
        sortSuggestions(suggestionCodes);
        System.out.println("ALLALLALALAO");
        suggestionCodes.retainAll(suggestion);
        for (Objet o : suggestionCodes) {
            System.out.println(o.getNom() + " " + o.getCode() + " " + o.getType());
        }
    }

    public void suggestionsType(String input) {
        resetListeEtats(listeEtatsTerminauxTypes);
        resetListeObjets();
        for (Etat e : listeEtatsTypes) {
            if (e.getNom().equals(input)) {
                parcourirEtatsTypes(e, listeEtatsTerminauxTypes);
            }
        }
        sortSuggestions(suggestionTypes);
    }



    public void parcourirEtats(Etat e, List<Etat> listeTerminaux) {
        Etat courant = e;
        while (courant.hasNext()) {
            for (Etat etat : listeTerminaux) {
                if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                    etat.setEstTraite(true);
                    ajouterObjetsSuggestion(courant);
                }
            }

            if (courant.aPlusieursVoisins()) {
                for (Arc a : courant.getListeArcs()) {
                        parcourirEtats(a.getVoisin(), listeTerminaux);
                }
            }
            courant = courant.getListeArcs().get(0).getVoisin();
        }
        for (Etat etat : listeTerminaux) {
            if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                etat.setEstTraite(true);
                ajouterObjetsSuggestion(courant);
            }
        }
    }

    public void parcourirEtatsCodes(Etat e, List<Etat> listeTerminaux) {
        Etat courant = e;
        while (courant.hasNext()) {
            for (Etat etat : listeTerminaux) {
                if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                    etat.setEstTraite(true);
                    ajouterObjetsSuggestion(courant);
                }
            }

            if (courant.aPlusieursVoisins()) {
                for (Arc a : courant.getListeArcs()) {
                    parcourirEtatsCodes(a.getVoisin(), listeTerminaux);
                }
            }
            courant = courant.getListeArcs().get(0).getVoisin();
        }
        for (Etat etat : listeTerminaux) {
            if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                etat.setEstTraite(true);
                ajouterObjetsSuggestionCodes(courant);
            }
        }
    }

    public void parcourirEtatsTypes(Etat e, List<Etat> listeTerminaux) {
        Etat courant = e;
        while (courant.hasNext()) {
            for (Etat etat : listeTerminaux) {
                if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                    etat.setEstTraite(true);
                    ajouterObjetsSuggestion(courant);
                }
            }
            if (courant.aPlusieursVoisins()) {
                for (Arc a : courant.getListeArcs()) {
                    parcourirEtatsTypes(a.getVoisin(), listeTerminaux);
                }
            }
            courant = courant.getListeArcs().get(0).getVoisin();
        }
        for (Etat etat : listeTerminaux) {
            if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                etat.setEstTraite(true);
                ajouterObjetsSuggestionTypes(courant);
            }
        }
    }

    public void ajouterObjetsSuggestion(Etat etat) {
        for (Objet o : getListeObjets()) {
            if (etat.getNom().equals(o.getNom()) && !o.isEstTraite()) {
                suggestion.add(o);
                o.setEstTraite(true);
            }
        }
    }

    public void ajouterObjetsSuggestionCodes(Etat etat) {
        for (Objet o : getListeObjets()) {
            if (etat.getNom().equals(o.getCode()) && !o.isEstTraite()) {
                suggestionCodes.add(o);
                o.setEstTraite(true);
            }
        }
    }

    public void ajouterObjetsSuggestionTypes(Etat etat) {
        for (Objet o : getListeObjets()) {
            if (etat.getNom().equals(o.getType()) && !o.isEstTraite()) {
                suggestionTypes.add(o);
                o.setEstTraite(true);
            }
        }
    }

    public void sortSuggestions(List<Objet> liste) {
        List<Objet> temp = new ArrayList<>(liste);
        liste.clear();
        System.out.println("SUGGE");
        List<String> listeNoms = getNomsSuggestions(temp);

        java.util.Collections.sort(listeNoms);
        for (String s : listeNoms) {
            for (Objet o : temp) {
                if (o.getNom().equals(s) && !o.isSortedNom()) {
                    liste.add(o);
                    o.setSortedNom(true);
                }
            }
        }
        for (Objet o : liste)
            System.out.println(o.getNom() + " " + o.getCode() + " " + o.getType());
    }

//    public void sortSuggestionsCodes(List<Objet> liste) {
//        List<Objet> temp = new ArrayList<>(liste);
//        liste.clear();
//        System.out.println("SUGGE COdes");
//        List<String> listeCodes = getCodesSuggestions(temp);
//
//        java.util.Collections.sort(listeCodes);
//        for (String s : listeCodes) {
//            for (Objet o : temp) {
//                if (o.getCode().equals(s) && !o.isSortedNom()) {
//                    liste.add(o);
//                    o.setSortedNom(true);
//                }
//            }
//        }
//        for (Objet o : liste)
//            System.out.println(o.getNom() + " " + o.getCode() + " " + o.getType());
//    }
//
//    public void sortSuggestionsTypes(List<Objet> liste) {
//        List<Objet> temp = new ArrayList<>(liste);
//        liste.clear();
//        System.out.println("SUGGE ytypedes");
//        List<String> listeTypes = getTypesSuggestions(temp);
//
//        java.util.Collections.sort(listeTypes);
//        for (String s : listeTypes) {
//            for (Objet o : temp) {
//                if (o.getType().equals(s) && !o.isSortedNom()) {
//                    liste.add(o);
//                    o.setSortedNom(true);
//                }
//            }
//        }
//        for (Objet o : liste)
//            System.out.println(o.getNom() + " " + o.getCode() + " " + o.getType());
//    }


    public List<String> getNomsSuggestions(List<Objet> liste) {
        List<String> listeTemp = new ArrayList<>();
        for (Objet o : liste) {
            listeTemp.add(o.getNom());
        }
        return listeTemp;
    }

//    public List<String> getCodesSuggestions(List<Objet> liste) {
//        List<String> listeTemp = new ArrayList<>();
//        for (Objet o : liste) {
//            listeTemp.add(o.getCode());
//        }
//        return listeTemp;
//    }
//
//    public List<String> getTypesSuggestions(List<Objet> liste) {
//        List<String> listeTemp = new ArrayList<>();
//        for (Objet o : liste) {
//            listeTemp.add(o.getType());
//        }
//        return listeTemp;
//    }

    // Getters et setters

    public void resetListeEtats(List<Etat> liste) {
        for (Etat e : liste) {
            e.setEstTraite(false);
            e.setSortedNom(false);
        }
    }

    public void resetListeObjets() {
        for (Objet o : listeObjets) {
            o.setEstTraite(false);
            o.setSortedNom(false);
        }
    }

    public List<Objet> getListeObjets() {
        return listeObjets;
    }
}
