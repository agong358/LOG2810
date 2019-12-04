/**
 * Représente l'interface créé pour ce travail.
 *
 * @auteure: Alice Gong
 * @auteure: Nu Chan Nhien Ton
 * @auteure: Kai Sen Trieu
 */
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.util.Timer;

import java.io.File;
import java.io.FileNotFoundException;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.event.FocusEvent;

import java.util.*;
import java.util.List;

public class Interface {

    private JFrame frame = new JFrame("Interface de commandes");
    private JFrame frame_loading = new JFrame("En cours d'exécution...");

    private JLabel label_search = new JLabel("Recherche ");
    private JLabel label_name = new JLabel("Nom");
    private JLabel label_code = new JLabel("Code");
    private JLabel label_type = new JLabel("Type");
    private JLabel label_panier = new JLabel("Panier");
    private JLabel label_nbElements = new JLabel("(poids maximal de 25 kg)");
    private JLabel lblPoidsDeLobject = new JLabel("<html>Poids de l'objet<br>  sélectionné (kg) : </html>");
    private JLabel label_errorClickNoSelectionAdd = new JLabel("<html><font color='#E73F1A'>Veuillez sélectionner un objet à<br>ajouter dans le panier</font></html>");
    private JLabel label_errorClickNoSelectionRemove = new JLabel("<html><font color='#E73F1A'>Veuillez s\u00E9lectionner un objet \u00E0<br>enlever du panier</font></html>");
    private final JLabel label_poidsSelectionPanier = new JLabel("<html>Poids de l'objet<br>  s\u00E9lectionn\u00E9 <br> dans le panier (kg) : </html>");
    private JLabel label_loading1 = new JLabel("Veuillez attendre patiemment. ");
    private  JLabel label_loading2 = new JLabel("Cela pourrait prendre entre 2 et 3 minutes.");

    private JTextField textField_name = new JTextField(){
        public void addNotify(){
            super.addNotify();
            requestFocus();
        }
    };

    private JTextField textField_code = new JTextField();
    private JTextField textField_type = new JTextField();
    private JTextField textField_poids = new JTextField();
    private JTextField textField_poidsSelection = new JTextField();
    private HintTextField pathFichier = new HintTextField("Sélectionner un fichier en écrivant son path ou sinon à l'aide du bouton Naviguer");
    private final JTextField textField_poidsPanier = new JTextField();

    private JButton button_add = new JButton("Ajouter");
    private JButton button_remove = new JButton("Retirer");
    private JButton button_order = new JButton("Commander");
    private JButton button_clear = new JButton("Vider");
    private JButton browseButton = new JButton("Naviguer");

    private Timer timer = new Timer();

    private JPanel panel_loading = new JPanel();

    private JProgressBar loadingBar = new JProgressBar(JProgressBar.HORIZONTAL);

    private List<Objet> listeObjets = new ArrayList<>();
    private List<Objet> listeObjetsPanier = new ArrayList<>();

    private DefaultListModel listModel = new DefaultListModel();
    private DefaultListModel listPanierModel = new DefaultListModel();

    private JList liste = new JList(listModel);
    private JList liste_panier = new JList(listPanierModel);

    private Automate automate = new Automate();

    private int poids_panier = 0;
    private final JPanel panel = new JPanel();

    @SuppressWarnings({ "unchecked" })
    Interface() {
        // layout général
        frame.setSize(950, 650);// taille de la fenêtre 950 x 650
        frame.getContentPane().setLayout(null);// aucun layout managers n'est utilisé
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label_search.setBounds(44, 118, 100, 40);
        frame.getContentPane().add(label_search);

        label_panier.setBounds(544, 108, 100, 40);
        frame.getContentPane().add(label_panier);

        label_nbElements.setBounds(544, 131, 200, 40);
        frame.getContentPane().add(label_nbElements);

        label_name.setBounds(159, 98, 110, 30);
        frame.getContentPane().add(label_name);
        textField_name.setBounds(159, 123, 140, 30);
        frame.getContentPane().add(textField_name);

        // initialisation de la liste contenant les suggestions
        frame.getContentPane().add(liste);
        liste.setBounds(160, 171, 350, 329);

        // initialisation de la liste contenant les objets du panier
        frame.getContentPane().add(liste_panier);
        liste_panier.setBounds(544, 171, 235, 272);

        // lorsqu'une suggestion est selectionnee, afficher le poids associe a cet objet
        liste.addListSelectionListener(e -> {
            try {
                Objet objetSelectionne = trouverObjet(liste.getSelectedValue().toString());
                textField_poidsSelection.setText(String.valueOf(objetSelectionne.getPoids()));;
            } catch(NullPointerException e1) {
                textField_poidsSelection.setText("         ---");
            }
        });

        // lorsqu'un objet du panier est selectionné, afficher le poids associé à cet objet
        liste_panier.addListSelectionListener(e3 -> {
            try {
                Objet objetSelectionne = trouverObjetPanier(liste_panier.getSelectedValue().toString());
                textField_poidsPanier.setText(String.valueOf(objetSelectionne.getPoids()));;
            } catch(NullPointerException e4) {
                textField_poidsPanier.setText("         ---");
            }
        });

        // autosuggestion lorsque l'utilisateur écrit dans le textField sous "Nom"
        textField_name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                liste.setModel(getListModel());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                liste.setModel(getListModel());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        // initialisation du text_field ou entrer le code
        label_code.setBounds(314, 98, 110, 30);
        frame.getContentPane().add(label_code);
        textField_code.setBounds(314, 123, 110, 30);
        frame.getContentPane().add(textField_code);

        // autosuggestion lorsque l'utilisateur écrit dans le textField sous "Code"
        textField_code.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                liste.setModel(getListModel());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                liste.setModel(getListModel());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.print("changedUpdate");
            }
        });

        // initialisation du text_field ou entrer le type
        label_type.setBounds(439, 98, 110, 30);
        frame.getContentPane().add(label_type);
        textField_type.setBounds(439, 123, 70, 30);
        frame.getContentPane().add(textField_type);

        // autosuggestion lorsque l'utilisateur écrit dans le textField sous "Type"
        textField_type.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                liste.setModel(getListModel());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                liste.setModel(getListModel());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.print("changedUpdate");
            }
        });

        // initialisation du bouton ajouter
        frame.getContentPane().add(button_add);
        button_add.setBounds(160, 510, 100, 40);
        button_add.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(liste.isSelectionEmpty()) {
                    label_errorClickNoSelectionAdd.setVisible(true);
                    label_errorClickNoSelectionAdd.revalidate();
                    label_errorClickNoSelectionAdd.repaint();
                }
                else {
                    label_errorClickNoSelectionAdd.setVisible(false);

                    // enlève l'objet de la listeObjets et la rajoute dans la listeObjetsPanier
                    listPanierModel.addElement(liste.getSelectedValue());
                    liste_panier.setModel(listPanierModel);

                    Objet addedObject = trouverObjet(liste.getSelectedValue().toString());

                    // réaffiche les nouvelles suggestions maintenant que la listeObjets est modifiée
                    listeObjets.remove(addedObject);
                    listeObjetsPanier.add(addedObject);
                    liste.setModel(getListModel());

                    // met a jour la valeur du poids du panier
                    poids_panier += addedObject.getPoids();
                    textField_poids.setText(String.valueOf(poids_panier));
                }
            }
        });

        // initialisation du bouton retirer
        frame.getContentPane().add(button_remove);
        button_remove.setBounds(557, 487, 120, 40);
        button_remove.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(liste_panier.getModel().getSize() == 0) {
                    JOptionPane.showMessageDialog(null,"Le panier est vide!");

                }
                else {
                    if(liste_panier.isSelectionEmpty()) {
                        label_errorClickNoSelectionRemove.setVisible(true);
                        label_errorClickNoSelectionRemove.revalidate();
                        label_errorClickNoSelectionRemove.repaint();
                    }
                    else {
                        label_errorClickNoSelectionRemove.setVisible(false);

                        // enlève l'objet de la listeObjetPaniers et la rajoute dans listeObjets
                        Objet removedObject = creerObjet(liste_panier.getSelectedValue().toString());

                        listeObjets.add(removedObject);
                        listeObjetsPanier.remove(removedObject);
                        liste.setModel(getListModel());

                        // réaffiche les nouvelles suggestions maintenant que la listeObjets est modifiée
                        listPanierModel.removeElementAt(liste_panier.getSelectedIndex());
                        liste_panier.setModel(listPanierModel);

                        // met a jour la valeur du poids du panier
                        poids_panier -= removedObject.getPoids();
                        textField_poids.setText(String.valueOf(poids_panier));
                    }
                }
            }
        });

        // initialiser bouton commander
        frame.getContentPane().add(button_order);
        button_order.setBounds(793, 543, 120, 40);
        button_order.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // message d'erreur lorsque le panier est vide et que l'usager tente de passer une commande
                if(liste_panier.getModel().getSize() == 0) {
                    JOptionPane.showMessageDialog(null,"Le panier est vide!");

                }

                // s'assure que le poids du panier respecte le poids maximal
                else if(poids_panier <= 25){
                    JOptionPane.showMessageDialog(frame,
                            "La commande a été passée.",
                            "Commande passée",
                            JOptionPane.PLAIN_MESSAGE);
                    listPanierModel.removeAllElements();
                    liste_panier.setModel(listPanierModel);
                    listeObjetsPanier.clear();
                    poids_panier = 0;
                    textField_poids.setText("         ---");
                }

                // message d'erreur si le poids du panier dépasse le poids maximal
                else {
                    JOptionPane.showMessageDialog(frame,
                            "Vous avez trop d'éléments dans votre panier",
                            "Erreur",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });


        // initialisation bouton vider
        frame.getContentPane().add(button_clear);
        button_clear.setBounds(692, 487, 120, 40);
        button_clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // message d'erreur si le panier est vide et que l'usager tente de vider le panier
                if (liste_panier.getModel().getSize() == 0) {
                    JOptionPane.showMessageDialog(null, "Le panier est vide!");

                } else {

                    // rajoute chaque objet du panier dans listeObjets
                    for (int i = 0; i < liste_panier.getModel().getSize(); i++) {
                        listeObjets.add(creerObjet(liste_panier.getModel().getElementAt(i).toString()));
                    }
                    listeObjetsPanier.clear();

                    textField_poids.setText("         ---");

                    // réaffiche les nouvelles suggestions maintenant que listeObjets est modifiée
                    liste.setModel(getListModel());

                    // retire tous les elements du panier
                    listPanierModel.removeAllElements();
                    liste_panier.setModel(listPanierModel);

                    // met a jour le poids du panier
                    poids_panier = 0;
                }
            }
        });

        // initialisation du text_field ou entrer le path du fichier.txt contenant le lexique
        pathFichier.setBounds(185, 30, 500, 30);
        pathFichier.setForeground(new Color(0, 0, 0));
        pathFichier.setBackground(Color.WHITE);
        frame.getContentPane().add(pathFichier);

        // initialisation du bouton naviguer permettant de selectionner un fichier dans nos dossiers
        frame.getContentPane().add(browseButton);
        browseButton.setBounds(700, 25, 100, 40);
        browseButton.addActionListener(e -> {
            pathFichier.setText(selectFile());
        });

        // initialisation du bouton initialiser permettant d'initialiser et de créer l'automate
        JButton boutonInitialiserProgramme = new JButton("Initialiser");
        frame.getContentPane().add(boutonInitialiserProgramme);
        boutonInitialiserProgramme.setBounds(815, 25, 100, 40);

        // initialisation de la fenêtre d'attente
        loadingBar.setIndeterminate(true);
        frame_loading.setSize(300, 110);
        loadingBar.setForeground(Color.green);
        panel_loading.add(loadingBar);
        panel_loading.add(label_loading1);
        panel_loading.add(label_loading2);
        frame_loading.getContentPane().add(panel_loading);

        // initialisation de la fenêtre affichant le fichier à sélectionner
        JLabel lblSlectionnerUnFichier = new JLabel("S\u00E9lectionner un fichier");
        lblSlectionnerUnFichier.setBounds(15, 35, 183, 20);
        frame.getContentPane().add(lblSlectionnerUnFichier);
        boutonInitialiserProgramme.addActionListener(e -> {
            frame_loading.setVisible(true);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    initialiser(pathFichier.getText());
                }
            },500);
        });

        // initialisation du Jlabel permettant d'afficher le poids du panier
        JLabel lblPoids = new JLabel("Poids actuel (kg) :");
        lblPoids.setBounds(582, 548, 129, 30);
        frame.getContentPane().add(lblPoids);
        textField_poids.setBackground(Color.WHITE);

        textField_poids.setText("         ---");
        textField_poids.setEditable(false);
        textField_poids.setBounds(700, 548, 70, 30);
        frame.getContentPane().add(textField_poids);
        textField_poids.setColumns(10);

        lblPoidsDeLobject.setBounds(15, 192, 155, 40);

        frame.getContentPane().add(lblPoidsDeLobject);

        textField_poidsPanier.setBackground(Color.WHITE);
        textField_poidsPanier.setEditable(false);
        textField_poidsPanier.setText("         ---");
        textField_poidsPanier.setBounds(794, 236, 100, 26);
        textField_poidsPanier.setColumns(10);

        textField_poidsSelection = new JTextField();
        textField_poidsSelection.setText("         ---");
        textField_poidsSelection.setBounds(25, 236, 70, 26);
        frame.getContentPane().add(textField_poidsSelection);
        textField_poidsSelection.setBackground(Color.WHITE);
        textField_poidsSelection.setColumns(10);
        textField_poidsSelection.setEditable(false);

        label_errorClickNoSelectionAdd.setBounds(275, 510, 235, 34);
        frame.getContentPane().add(label_errorClickNoSelectionAdd);
        label_errorClickNoSelectionAdd.setVisible(false);


        label_errorClickNoSelectionRemove.setBounds(557, 447, 254, 40);
        frame.getContentPane().add(label_errorClickNoSelectionRemove);
        label_poidsSelectionPanier.setBounds(794, 182, 155, 52);

        frame.getContentPane().add(label_poidsSelectionPanier);

        frame.getContentPane().add(textField_poidsPanier);

        frame.getContentPane().add(panel);
        label_errorClickNoSelectionRemove.setVisible(false);

        frame.setVisible(true);

    }

    /**
     *  Permet de trouver la liste de suggestions à afficher,
     *  en prenant en compte quels critères comportent un input
     *  et si des suggestions existent ou non pour ces inputs
     */
    public List<Objet> trouverSuggestions() {
        List<Objet> suggestionsNom = automate.getSuggestionsNom(textField_name.getText());
        if (!textField_name.getText().isEmpty() && suggestionsNom == null)
            return null;

        List<Objet> suggestionsCode = automate.getSuggestionsCode(textField_code.getText());
        if (!textField_code.getText().isEmpty() && suggestionsCode == null)
            return null;

        List<Objet> suggestionsType = automate.getSuggestionsType(textField_type.getText());
        if (!textField_type.getText().isEmpty() && suggestionsType == null)
            return null;

        if (suggestionsNom != null) {
            if (suggestionsCode != null) {
                if (suggestionsType != null) {
                    suggestionsNom.retainAll(suggestionsType);
                    suggestionsNom.retainAll(suggestionsCode);
                    if (suggestionsNom.size() > 10)
                        suggestionsNom = suggestionsNom.subList(0, 9);
                } else {
                    suggestionsNom.retainAll(suggestionsCode);
                    if (suggestionsNom.size() > 10)
                        suggestionsNom = suggestionsNom.subList(0, 9);
                }
            } else if (suggestionsType != null) {
                suggestionsNom.retainAll(suggestionsType);
                if (suggestionsNom.size() > 10)
                    suggestionsNom = suggestionsNom.subList(0, 9);
            }
            if (suggestionsNom.size() > 10)
                suggestionsNom = suggestionsNom.subList(0, 9);
            return suggestionsNom;
        }

        else if (suggestionsCode != null) {
            if (suggestionsNom != null) {
                if (suggestionsType != null) {
                    suggestionsCode.retainAll(suggestionsType);
                    suggestionsCode.retainAll(suggestionsNom);
                    if (suggestionsCode.size() > 10)
                        suggestionsCode = suggestionsCode.subList(0, 9);
                } else {
                    suggestionsCode.retainAll(suggestionsNom);
                    if (suggestionsCode.size() > 10)
                        suggestionsCode = suggestionsCode.subList(0, 9);
                }
            } else if (suggestionsType != null) {
                suggestionsCode.retainAll(suggestionsType);
                if (suggestionsCode.size() > 10)
                    suggestionsCode = suggestionsCode.subList(0, 9);
            }
            if (suggestionsCode.size() > 10)
                suggestionsCode = suggestionsCode.subList(0, 9);
            return suggestionsCode;
        }
        else if (suggestionsType != null) {
            if (suggestionsCode != null) {
                if (suggestionsNom != null) {
                    suggestionsType.retainAll(suggestionsNom);
                    suggestionsType.retainAll(suggestionsCode);
                    if (suggestionsType.size() > 10)
                        suggestionsType = suggestionsType.subList(0,9);
                } else {
                    suggestionsType.retainAll(suggestionsCode);
                    if (suggestionsType.size() > 10)
                        suggestionsType = suggestionsType.subList(0,9);
                }
            } else if (suggestionsNom != null) {
                suggestionsType.retainAll(suggestionsNom);
                if (suggestionsType.size() > 10)
                    suggestionsType = suggestionsType.subList(0,9);
            }
            if (suggestionsType.size() > 10)
                suggestionsType = suggestionsType.subList(0,9);
            return suggestionsType;
        }
        return null;
    }

    /**
     *  Permet de choisir le fichier.txt pour récupérer son path
     */
    public String selectFile(){
        JFileChooser chooser = new JFileChooser();

        // filtre le fichier que l'usager peut sélectionner
        FileNameExtensionFilter fileAllowed = new FileNameExtensionFilter("Text files", "txt", "text");
        chooser.setFileFilter(fileAllowed);

        // pour rendre l'option All Files non disponible
        chooser.setAcceptAllFileFilterUsed(false);

        // la fenêtre pour saisir le fichier s'ouvre au directoire ou l'usager se retrouve a l'instant
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = chooser.showOpenDialog(new JFrame());

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        else {
            return "Aucun fichier n'a été sélectionné";
        }

    }

    /**
     *  Initialise le tout, crée l'automate
     */
    public void initialiser(String fichier) {
        try {
            listeObjets.clear();
            automate.lireFichier(fichier);

            listeObjets = automate.getListeObjets();
            automate.setEtatsTerminaux();
            automate.setEtats();
            automate.setMapSuggestions();

            frame_loading.setVisible(false);
            JOptionPane.showMessageDialog(null,"Initialisation terminée" + "\n" + "Vous pouvez procéder à la commande" );


        } catch(FileNotFoundException e) {
            frame_loading.setVisible(false);
            JOptionPane.showMessageDialog(null,"Veuillez sélectionner un fichier");
        }
    }

    // permet de trouver un Objet appartenant à la listeObjets à l'aide d'un String
    public Objet trouverObjet(String input) {
        String[] array = input.split(" ");
        for (Objet o : listeObjets) {
            if (o.getNom().equals(array[0]) && o.getCode().equals(array[1]) && o.getType().equals(array[2]))
                return o;
        }
        return null;
    }

    /**
     *  Permet de trouver un Objet appartenant à la
     *  listeObjetsPanier à l'aide d'un String
     */
    public Objet trouverObjetPanier(String input) {
        String[] array = input.split(" ");
        for (Objet o : listeObjetsPanier) {
            if (o.getNom().equals(array[0]) && o.getCode().equals(array[1]) && o.getType().equals(array[2]))
                return o;
        }
        return null;
    }
    
    /**
     *  Créer un objet à partir du input
     *
     *  @param input String à partir duquel un objet va être créé
     */
    public Objet creerObjet(String input) {
        String[] array = input.split(" ");
        return new Objet(array[0], array[1], array[2]);
    }

    public DefaultListModel getListModel() {
        DefaultListModel listModel = new DefaultListModel();
        List<Objet> listeSuggestions = trouverSuggestions();
        if (listeSuggestions != null) {
            for (Objet o : listeSuggestions) {
                for (Objet objet : listeObjets) {
                    if (objet.getNom().equals(o.getNom()) && objet.getCode().equals(o.getCode()) && objet.getType().equals(o.getType()))
                        listModel.addElement(o.getNom() + " " + o.getCode() + " " + o.getType());
                }
            }
        }
        return listModel;
    }

    // pour afficher le message dans le text_field du path
    public class HintTextField extends JTextField {

        Font gainFont = new Font("Tahoma", Font.PLAIN, 11);
        Font lostFont = new Font("Tahoma", Font.ITALIC, 11);

        public HintTextField(final String hint) {

            setText(hint);
            setFont(lostFont);
            setForeground(Color.GRAY);

            this.addFocusListener(new FocusAdapter() {

                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().equals(hint)) {
                        setText("");
                        setFont(gainFont);
                    } else {
                        setText(getText());
                        setFont(gainFont);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().equals(hint)|| getText().length()==0) {
                        setText(hint);
                        setFont(lostFont);
                        setForeground(Color.GRAY);
                    } else {
                        setText(getText());
                        setFont(gainFont);
                        setForeground(Color.BLACK);
                    }
                }
            });
        }
    }
}


