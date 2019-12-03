
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    private Map<String, List<Objet>> mapSuggestionsNoms = new HashMap<>();
    private Map<String, List<Objet>> mapSuggestionsCodes = new HashMap<>();
    private Map<String, List<Objet>> mapSuggestionsTypes = new HashMap<>();

    // Constructeur par defaut
    public Automate() {
    }

    /* Fonction lire fichier ici*/
    public void lireFichier(String fichier) {

        //TODO FAIRE UN TRY CATCH FICHIER
        File file = new File(fichier);

        String texteFichier = "";

//        //lire le fichier texte et tout store dans un string, ligne par ligne
        try {
            Scanner scan = new Scanner(file).useDelimiter("[\r\n]");
            while (scan.hasNextLine()) {
                texteFichier += scan.nextLine() + "\n";
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //store les lignes separement dans un array de string
        String[] lignesObjets = texteFichier.split("\n");

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

    public void setEtats() {
        setEtatsNoms();
        setEtatsCodes();
        setEtatsTypes();
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
        setEtatsTerminauxNoms();
        setEtatsTerminauxCodes();
        setEtatsTerminauxTypes();
    }

    public void setEtatsTerminauxNoms() {
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


    public List<Objet> suggestion(String input) {
        resetListeEtats(listeEtatsTerminaux);
        resetListeObjets();
        List<Objet> listeSuggestions = new ArrayList<>();
//        for (Etat e : listeEtatsTerminaux) {
//            if (e.getNom().startsWith(input))
//                System.out.println(e.getNom());
//        } //methode non automate

        for (Etat e : listeEtats) {
            if (e.getNom().equals(input)) {
                parcourirEtats(e, listeEtatsTerminaux, listeSuggestions);
            }
        }
        sortSuggestions(listeSuggestions);
        return listeSuggestions;
    }

    public List<Objet> suggestionsCode(String input) {
        resetListeEtats(listeEtatsTerminauxCodes);
        resetListeObjets();
        List<Objet> listeSuggestions = new ArrayList<>();
        for (Etat e : listeEtatsCodes) {
            if (e.getNom().equals(input)) {
                parcourirEtatsCodes(e, listeEtatsTerminauxCodes, listeSuggestions);
            }
        }
        sortSuggestions(listeSuggestions);
        return listeSuggestions;
//        suggestionCodes.retainAll(suggestion);
//        for (Objet o : suggestionCodes) {
//            System.out.println(o.getNom() + " " + o.getCode() + " " + o.getType());
//        }
    }

    public List<Objet> suggestionsType(String input) {
        resetListeEtats(listeEtatsTerminauxTypes);
        resetListeObjets();
        List<Objet> listeSuggestions = new ArrayList<>();
        for (Etat e : listeEtatsTypes) {
            if (e.getNom().equals(input)) {
                parcourirEtatsTypes(e, listeEtatsTerminauxTypes, listeSuggestions);
            }
        }
        sortSuggestions(listeSuggestions);
        return listeSuggestions;
    }



    public void parcourirEtats(Etat e, List<Etat> listeTerminaux, List<Objet> listeSuggestions) {
        Etat courant = e;
        while (courant.hasNext()) {
            for (Etat etat : listeTerminaux) {
                if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                    etat.setEstTraite(true);
                    ajouterObjetsSuggestion(courant, listeSuggestions);
                }
            }

            if (courant.aPlusieursVoisins()) {
                for (Arc a : courant.getListeArcs()) {
                    parcourirEtats(a.getVoisin(), listeTerminaux, listeSuggestions);
                }
            }
            courant = courant.getListeArcs().get(0).getVoisin();
        }
        for (Etat etat : listeTerminaux) {
            if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                etat.setEstTraite(true);
                ajouterObjetsSuggestion(courant, listeSuggestions);
            }
        }
    }

    public void parcourirEtatsCodes(Etat e, List<Etat> listeTerminaux, List<Objet> listeSuggestions) {
        Etat courant = e;
        while (courant.hasNext()) {
            for (Etat etat : listeTerminaux) {
                if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                    etat.setEstTraite(true);
                    ajouterObjetsSuggestion(courant, listeSuggestions);
                }
            }

            if (courant.aPlusieursVoisins()) {
                for (Arc a : courant.getListeArcs()) {
                    parcourirEtatsCodes(a.getVoisin(), listeTerminaux, listeSuggestions);
                }
            }
            courant = courant.getListeArcs().get(0).getVoisin();
        }
        for (Etat etat : listeTerminaux) {
            if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                etat.setEstTraite(true);
                ajouterObjetsSuggestionCodes(courant, listeSuggestions);
            }
        }
    }

    public void parcourirEtatsTypes(Etat e, List<Etat> listeTerminaux, List<Objet> listeSuggestions) {
        Etat courant = e;
        while (courant.hasNext()) {
            for (Etat etat : listeTerminaux) {
                if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                    etat.setEstTraite(true);
                    ajouterObjetsSuggestion(courant, listeSuggestions);
                }
            }
            if (courant.aPlusieursVoisins()) {
                for (Arc a : courant.getListeArcs()) {
                    parcourirEtatsTypes(a.getVoisin(), listeTerminaux, listeSuggestions);
                }
            }
            courant = courant.getListeArcs().get(0).getVoisin();
        }
        for (Etat etat : listeTerminaux) {
            if (etat.getNom().equals(courant.getNom()) && !etat.isEstTraite()) {
                etat.setEstTraite(true);
                ajouterObjetsSuggestionTypes(courant, listeSuggestions);
            }
        }
    }

    public void ajouterObjetsSuggestion(Etat etat, List<Objet> listeSuggestions) {
        for (Objet o : getListeObjets()) {
            if (etat.getNom().equals(o.getNom()) && !o.isEstTraite() && listeSuggestions.size() < 10) {
                listeSuggestions.add(o);
                o.setEstTraite(true);
            }
        }
    }

    public void ajouterObjetsSuggestionCodes(Etat etat, List<Objet> listeSuggestions) {
        for (Objet o : getListeObjets()) {
            if (etat.getNom().equals(o.getCode()) && !o.isEstTraite() && listeSuggestions.size() < 10) {
                listeSuggestions.add(o);
                o.setEstTraite(true);
            }
        }
    }

    public void ajouterObjetsSuggestionTypes(Etat etat, List<Objet> listeSuggestions) {
        for (Objet o : getListeObjets()) {
            if (etat.getNom().equals(o.getType()) && !o.isEstTraite() && listeSuggestions.size() < 10) {
                listeSuggestions.add(o);
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

    public void resetSuggestions(List<Objet> liste) {
        liste.clear();
    }

    public List<Objet> getListeObjets() {
        return listeObjets;
    }

    public void setMapSuggestions() {
        setMapSuggestionsNom();
        setMapSuggestionsCode();
        setMapSuggestionsType();
    }

    public void setMapSuggestionsNom() {
        for (Etat e : listeEtats) {
            mapSuggestionsNoms.put(e.getNom(), suggestion(e.getNom()));
        }
//        System.out.println("TEST MAP   AAPAPA");
//        for (String s : mapSuggestions.keySet()) {
//            System.out.print(s + ": ");
//            for (Objet o : mapSuggestions.get(s))
//                System.out.println(o.getNom());
//        }
    }

    public void setMapSuggestionsCode() {
        for (Etat e : listeEtatsCodes) {
            mapSuggestionsCodes.put(e.getNom(), suggestionsCode(e.getNom()));
        }
    }

    public void setMapSuggestionsType() {
        for (Etat e : listeEtatsTypes) {
            mapSuggestionsTypes.put(e.getNom(), suggestionsType(e.getNom()));
        }
    }

    public List<Objet> getSuggestionsNom(String input) {
        for (String s : mapSuggestionsNoms.keySet()) {
            if (s.equals(input)) {
                return new ArrayList<>(mapSuggestionsNoms.get(s));
            }
        }
        return null;
    }

    public List<Objet> getSuggestionsCode(String input) {
        for (String s : mapSuggestionsCodes.keySet()) {
            if (s.equals(input)) {
                return new ArrayList<>(mapSuggestionsCodes.get(s));
            }
        }
        return null;
    }

    public List<Objet> getSuggestionsType(String input) {
        for (String s : mapSuggestionsTypes.keySet()) {
            if (s.equals(input)) {
                return new ArrayList<>(mapSuggestionsTypes.get(s));
            }
        }
        return null;
    }

}
