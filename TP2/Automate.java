/**
 * Représente l'automate créé suite à la lecture d'un fichier texte.
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Automate {
    // listes contentant tous les états, les noms, les codes, et les types
    // de chaque objet dans un fichier texte
    private List<Objet> listeObjets = new ArrayList<>();
    private List<Etat> listeEtats = new ArrayList<>();
    private List<Etat> listeEtatsCodes = new ArrayList<>();
    private List<Etat> listeEtatsTypes = new ArrayList<>();
    private List<Etat> listeEtatsTerminaux = new ArrayList<>();
    private List<Etat> listeEtatsTerminauxCodes = new ArrayList<>();
    private List<Etat> listeEtatsTerminauxTypes = new ArrayList<>();

    // collections contenant les suggestions pour les noms, codes ou types
    private List<Objet> suggestion = new ArrayList<>();
    private List<Objet> suggestionCodes = new ArrayList<>();
    private List<Objet> suggestionTypes = new ArrayList<>();
    private Map<String, List<Objet>> mapSuggestionsNoms = new HashMap<>();
    private Map<String, List<Objet>> mapSuggestionsCodes = new HashMap<>();
    private Map<String, List<Objet>> mapSuggestionsTypes = new HashMap<>();

    /**
     *  Constructeur par défaut
     */
    public Automate() {
        listeObjets = null;
        listeEtats = null;
        listeEtatsCodes = null;
        listeEtatsTypes = null;
        listeEtatsTerminaux = null;
        listeEtatsTerminauxCodes = null;
        listeEtatsTerminauxTypes = null;

        suggestion = null;
        suggestionCodes = null;
        suggestionTypes = null;
        mapSuggestionsNoms = null;
        mapSuggestionsCodes = null;
        mapSuggestionsTypes = null;
    }

    /**
     *  Lit un fichier passé en paramètres
     *
     * @param fichier « Path » du fichier qu'on veut lire
     */
    public void lireFichier(String fichier) throws FileNotFoundException {
        File file = new File(fichier);

        String texteFichier = "";

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                texteFichier += scan.nextLine() + "\n";
            }
        }
        catch (FileNotFoundException e) {
            throw e;
        }

        // stocke les lignes separément dans un tableau de string
        String[] lignesObjets = texteFichier.split("\n");


        // séparer les strings grâce a l'espace entre chaque élément
        for (String s : lignesObjets) {
            String[] tempArray = s.split(" ");
            listeObjets.add(new Objet(tempArray[0], tempArray[1], tempArray[2]));
        }
    }

    /**
     *  Vérifie si un état quelconque se retrouve dans une
     *  liste passée en paramètres
     *
     * @param etat Etat recherché
     * @param liste Liste fournie
     */
    public boolean contientEtat(String etat, List<Etat> liste) {
        for (Etat e : liste) {
            if (e.getNom().equals(etat)) {
                return true;
            }
        }
        return false;
    }

    /**
     *  Propose des suggestions de mots à l'aide des
     *  caractères mis en paramètres, en vérifiant
     *  le noms des états
     *
     * @param input Liste de caractères rentrés en paramètres
     */
    public List<Objet> suggestion(String input) {
        resetListeEtats(listeEtatsTerminaux);
        resetListeObjets();
        List<Objet> listeSuggestions = new ArrayList<>();

        for (Etat e : listeEtats) {
            if (e.getNom().equals(input)) {
                parcourirEtats(e, listeEtatsTerminaux, listeSuggestions);
            }
        }

        sortSuggestions(listeSuggestions);
        return listeSuggestions;
    }

    /**
     *  Propose des suggestions de mots à l'aide des
     *  caractères mis en paramètres, en vérifiant
     *  le codes des états
     *
     * @param input Liste de caractères rentrés en paramètres
     */
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
    }

    /**
     *  Propose des suggestions de mots à l'aide des
     *  caractères mis en paramètres, en vérifiant
     *  le types des états
     *
     * @param input Liste de caractères rentrés en paramètres
     */
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

    /**
     *  Parcourt une liste d'états terminaux et ajoute des états
     *  au fur et à mesure dans une liste de suggestions, en vérifiant
     *  le nom des états
     *
     * @param e État de départ
     * @param listeTerminaux Liste d'états terminaux
     * @param listeSuggestions Liste de suggestions à remplir
     */
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

    /**
     *  Parcourt une liste d'états terminaux et ajoute des états
     *  au fur et à mesure dans une liste de suggestions, en vérifiant
     *  le code des états
     *
     * @param e État de départ
     * @param listeTerminaux Liste d'états terminaux
     * @param listeSuggestions Liste de suggestions à remplir
     */
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

    /**
     *  Parcourt une liste d'états terminaux et ajoute des états
     *  au fur et à mesure dans une liste de suggestions, en vérifiant
     *  le type des états
     *
     * @param e État de départ
     * @param listeTerminaux Liste d'états terminaux
     * @param listeSuggestions Liste de suggestions à remplir
     */
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

    /**
     *  Ajoute des objets à une liste de suggestions
     *  lorsque le nom des objets de la liste
     *  d'objets de l'automate correspond à l'état
     *  passé en paramètres
     *
     * @param etat État de départ
     * @param listeSuggestions Liste de suggestions à remplir
     */
    public void ajouterObjetsSuggestion(Etat etat, List<Objet> listeSuggestions) {
        for (Objet o : getListeObjets()) {
            if (etat.getNom().equals(o.getNom()) && !o.isEstTraite() && listeSuggestions.size() < 10) {
                listeSuggestions.add(o);
                o.setEstTraite(true);
            }
        }
    }

    /**
     *  Ajoute des objets à une liste de suggestions
     *  lorsque le code des objets de la liste
     *  d'objets de l'automate correspond à l'état
     *  passé en paramètres
     *
     * @param etat État de départ
     * @param listeSuggestions Liste de suggestions à remplir
     */
    public void ajouterObjetsSuggestionCodes(Etat etat, List<Objet> listeSuggestions) {
        for (Objet o : getListeObjets()) {
            if (etat.getNom().equals(o.getCode()) && !o.isEstTraite() && listeSuggestions.size() < 10) {
                listeSuggestions.add(o);
                o.setEstTraite(true);
            }
        }
    }

    /**
     *  Ajoute des objets à une liste de suggestions
     *  lorsque le type des objets de la liste
     *  d'objets de l'automate correspond à l'état
     *  passé en paramètres
     *
     * @param etat État de départ
     * @param listeSuggestions Liste de suggestions à remplir
     */
    public void ajouterObjetsSuggestionTypes(Etat etat, List<Objet> listeSuggestions) {
        for (Objet o : getListeObjets()) {
            if (etat.getNom().equals(o.getType()) && !o.isEstTraite() && listeSuggestions.size() < 10) {
                listeSuggestions.add(o);
                o.setEstTraite(true);
            }
        }
    }

    /**
     *  Trie une liste en ordre alphabétique
     *
     * @param liste Liste à trier
     */
    public void sortSuggestions(List<Objet> liste) {
        List<Objet> temp = new ArrayList<>(liste);
        liste.clear();
        List<String> listeNoms = getNomsSuggestions(temp);

        // trie la liste des noms en ordre alphabétique
        java.util.Collections.sort(listeNoms);
        for (String s : listeNoms) {
            for (Objet o : temp) {
                if (o.getNom().equals(s) && !o.isSortedNom()) {
                    liste.add(o);
                    o.setSortedNom(true);
                }
            }
        }
    }

    /**
     *  Réinitialise une liste d'états
     *
     * @param liste Liste à réinitialiser
     */
    public void resetListeEtats(List<Etat> liste) {
        for (Etat e : liste) {
            e.setEstTraite(false);
            e.setSortedNom(false);
        }
    }

    /**
     *  Réinitialise la liste d'objets
     *  de l'automate
     */
    public void resetListeObjets() {
        for (Objet o : listeObjets) {
            o.setEstTraite(false);
            o.setSortedNom(false);
        }
    }

    /**
     *  Réinitialise une liste quelconque
     *
     * @param liste Liste à réinitialiser
     */
    public void resetSuggestions(List<Objet> liste) {
        liste.clear();
    }

    // getters et setters

    // on cherche un etat à l'aide de son nom
    public Etat getEtatContenu(String etat, List<Etat> liste) {
        for (Etat e : liste) {
            if (e.getNom().equals(etat))
                return e;
        }
        return null;
    }

    public List<String> getNomsSuggestions(List<Objet> liste) {
        List<String> listeTemp = new ArrayList<>();
        for (Objet o : liste) {
            listeTemp.add(o.getNom());
        }
        return listeTemp;
    }

    public List<Objet> getListeObjets() {
        return listeObjets;
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

    public void setMapSuggestions() {
        setMapSuggestionsNom();
        setMapSuggestionsCode();
        setMapSuggestionsType();
    }

    public void setMapSuggestionsNom() {
        for (Etat e : listeEtats) {
            mapSuggestionsNoms.put(e.getNom(), suggestion(e.getNom()));
        }
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
}
