import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.io.File;
import java.io.FileNotFoundException;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.*;
import java.util.List;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Interface {

    private JFrame frame = new JFrame("Interface de commandes");

    private JLabel label_search = new JLabel("Recherche ");
    private JLabel label_name = new JLabel("Nom");
    private JLabel label_code = new JLabel("Code");
    private JLabel label_type = new JLabel("Type");
    private JLabel label_panier = new JLabel("Panier");
    private JLabel label_nbElements = new JLabel("(poids maximal de 25 kg)");
    private JLabel lblPoidsDeLobject = new JLabel("<html>Poids de l'objet<br>  sélectionné (kg) : </html>");
    private JLabel label_errorClickNoSelectionAdd = new JLabel("<html><font color='#E73F1A'>Veuillez sélectionner un objet à<br>ajouter dans le panier</font></html>");
    private JLabel label_errorClickNoSelectionRemove = new JLabel("<html><font color='#E73F1A'>Veuillez s\u00E9lectionner un objet \u00E0<br>enlever du panier</font></html>");

    private JTextField textField_name = new JTextField(){
        public void addNotify(){
            super.addNotify();
            requestFocus();
        }
    };
    private JTextField textField_code = new JTextField();
    private JTextField textField_type = new JTextField();
    private JTextField textField_panier = new JTextField();
    private JTextField textField_poids = new JTextField();
    private JTextField textField_poidsSelection = new JTextField();
    private HintTextField pathFichier = new HintTextField("Sélectionner un fichier en écrivant son path ou sinon à l'aide du bouton Browse");

    private JButton button_add = new JButton("Ajouter");
    private JButton button_remove = new JButton("Retirer");
    private JButton button_order = new JButton("Commander");
    private JButton button_clear = new JButton("Vider");

    private List<Objet> listeObjets = new ArrayList<>();

    private Automate automate = new Automate();

    private int poids_panier = 0;
    private int poids_selection = 0;

    private int poids = 0;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    Interface() {
        // layout general
        frame.setSize(950, 650);// taille de la fenetre 950 x 650
        frame.getContentPane().setLayout(null);// aucun layout managers n'est utilise
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label_search.setBounds(70, 115, 100, 40);
        frame.getContentPane().add(label_search);

        label_panier.setBounds(582, 110, 100, 40);
        frame.getContentPane().add(label_panier);

        label_nbElements.setBounds(582, 133, 200, 40);
        frame.getContentPane().add(label_nbElements);

        label_name.setBounds(185, 95, 110, 30);
        frame.getContentPane().add(label_name);
        textField_name.setBounds(185, 120, 140, 30);
        frame.getContentPane().add(textField_name);

        DefaultListModel listModel = new DefaultListModel();
        JList liste = new JList(listModel);
        frame.getContentPane().add(liste);
        liste.setBounds(185, 171, 350, 329);

        DefaultListModel listPanierModel = new DefaultListModel();
        JList liste_panier = new JList(listPanierModel);
        frame.getContentPane().add(liste_panier);
        liste_panier.setBounds(582, 171, 254, 272);

        liste.addListSelectionListener(e -> {
            try {
                Objet objetSelectionne = trouverObjet(liste.getSelectedValue().toString());
                textField_poidsSelection.setText(String.valueOf(objetSelectionne.getPoids()));;
            } catch(NullPointerException e1) {
                textField_poidsSelection.setText("         ---");
            }
        });

        liste_panier.addListSelectionListener(e -> {
            try {
                Objet objetSelectionne = trouverObjet(liste.getSelectedValue().toString());
                textField_poidsSelection.setText(String.valueOf(objetSelectionne.getPoids()));;
            } catch(NullPointerException e1) {
                textField_poidsSelection.setText("         ---");
            }
        });

        pathFichier.setForeground(new Color(0, 0, 0));
        pathFichier.setBackground(Color.WHITE);
//        pathFichier.setEditable(false);

        // autosuggestion lorsque l'utilisateur ecrit dans le textField sous "Nom"
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
                System.out.print("changedUpdate");
            }
        });

        label_code.setBounds(340, 95, 110, 30);
        frame.getContentPane().add(label_code);
        textField_code.setBounds(340, 120, 110, 30);
        frame.getContentPane().add(textField_code);

        // autosuggestion lorsque l'utilisateur ecrit dans le textField sous "Code"
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

        label_type.setBounds(465, 95, 110, 30);
        frame.getContentPane().add(label_type);
        textField_type.setBounds(465, 120, 70, 30);
        frame.getContentPane().add(textField_type);

        // autosuggestion lorsque l'utilisateur ecrit dans le textField sous "Type"
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

        frame.getContentPane().add(button_add);
        button_add.setBounds(185, 510, 100, 40);
        button_add.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(liste.isSelectionEmpty()) {
                    label_errorClickNoSelectionAdd.setVisible(true);
                    label_errorClickNoSelectionAdd.revalidate();
                    label_errorClickNoSelectionAdd.repaint();
                }
                else {
                    label_errorClickNoSelectionAdd.setVisible(false);

                    listPanierModel.addElement(liste.getSelectedValue());
                    liste_panier.setModel(listPanierModel);

                    Objet addedObject = trouverObjet(liste.getSelectedValue().toString());

                    listeObjets.remove(addedObject);
                    liste.setModel(getListModel());

                    poids_panier += addedObject.getPoids();
                    textField_poids.setText(String.valueOf(poids_panier));
                }
            }
        });

        frame.getContentPane().add(button_remove);
        button_remove.setBounds(582, 487, 120, 40);
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

                        Objet removedObject = creerObjet(liste_panier.getSelectedValue().toString());

                        listeObjets.add(removedObject);
                        liste.setModel(getListModel());

                        listPanierModel.removeElementAt(liste_panier.getSelectedIndex());
                        liste_panier.setModel(listPanierModel);

                        poids_panier -= removedObject.getPoids();
                        textField_poids.setText(String.valueOf(poids_panier));
                    }
                }
            }
        });

        frame.getContentPane().add(button_order);
        button_order.setBounds(793, 543, 120, 40);
        button_order.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(liste_panier.getModel().getSize() == 0) {
                    JOptionPane.showMessageDialog(null,"Le panier est vide!");

                }

                else if(poids_panier <= 25){
                    JOptionPane.showMessageDialog(frame,
                            "La commande a été passée.",
                            "Commande passée",
                            JOptionPane.PLAIN_MESSAGE);
                    listPanierModel.removeAllElements();
                    liste_panier.setModel(listPanierModel);
                    poids_panier = 0;
                    //textField_poids.setText(String.valueOf(poids_panier));
                    textField_poids.setText("         ---");
                }
                else {
                    JOptionPane.showMessageDialog(frame,
                            "Vous avez trop d'éléments dans votre panier",
                            "Erreur",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        frame.getContentPane().add(button_clear);
        button_clear.setBounds(717, 487, 120, 40);
        button_clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (liste_panier.getModel().getSize() == 0) {
                    JOptionPane.showMessageDialog(null, "Le panier est vide!");

                } else {
                    for (int i = 0; i < liste_panier.getModel().getSize(); i++) {
                        listeObjets.add(creerObjet(liste_panier.getModel().getElementAt(i).toString()));
                    }

                    textField_poids.setText("         ---");

                    liste.setModel(getListModel());

                    listPanierModel.removeAllElements();
                    liste_panier.setModel(listPanierModel);

                    poids_panier = 0;
                    //textField_poids.setText(String.valueOf(poids_panier));
                }
            }
        });

        pathFichier.setBounds(185, 30, 500, 30);
        frame.getContentPane().add(pathFichier);
        // offre le choix à l'utilisateur d'écrire le path de son fichier
//        pathFichier.addMouseListener(new MouseAdapter() {
//            //            @Override
//            public void mouseClicked(MouseEvent e) {
//                pathFichier.setText("");
//            }
//        });

        JButton browseButton = new JButton("Browse");
        frame.getContentPane().add(browseButton);
        browseButton.setBounds(700, 25, 100, 40);
        browseButton.addActionListener(e -> {
            pathFichier.setText(selectFile());
        });

        JButton boutonInitialiserProgramme = new JButton("Initialiser");
        frame.getContentPane().add(boutonInitialiserProgramme);
        boutonInitialiserProgramme.setBounds(815, 25, 100, 40);

        JLabel lblSlectionnerUnFichier = new JLabel("S\u00E9lectionner un fichier");
        lblSlectionnerUnFichier.setBounds(15, 35, 183, 20);
        frame.getContentPane().add(lblSlectionnerUnFichier);
        boutonInitialiserProgramme.addActionListener(e -> {
            initialiser(pathFichier.getText());
        });

        JLabel lblPoids = new JLabel("Poids actuel (kg) :");
        lblPoids.setBounds(582, 548, 129, 30);
        frame.getContentPane().add(lblPoids);
        textField_poids.setBackground(Color.WHITE);


        textField_poids.setText("         ---");
        textField_poids.setEditable(false);
        textField_poids.setBounds(700, 548, 70, 30);
        frame.getContentPane().add(textField_poids);
        textField_poids.setColumns(10);
        lblPoidsDeLobject.setBounds(27, 191, 155, 40);

        frame.getContentPane().add(lblPoidsDeLobject);

        textField_poidsSelection = new JTextField();
        textField_poidsSelection.setText("         ---");
        textField_poidsSelection.setBounds(37, 235, 70, 26);
        frame.getContentPane().add(textField_poidsSelection);
        textField_poidsSelection.setBackground(Color.WHITE);
        textField_poidsSelection.setColumns(10);
        textField_poidsSelection.setEditable(false);

        label_errorClickNoSelectionAdd.setBounds(300, 510, 235, 34);
        frame.getContentPane().add(label_errorClickNoSelectionAdd);
        label_errorClickNoSelectionAdd.setVisible(false);


        label_errorClickNoSelectionRemove.setBounds(582, 447, 254, 40);
        frame.getContentPane().add(label_errorClickNoSelectionRemove);
        label_errorClickNoSelectionRemove.setVisible(false);

        frame.setVisible(true);

        //test panier
        String commandePanier = "";

    }

    public List<Objet> trouverSuggestionsNoms() {
        List<Objet> suggestionsNom = automate.getSuggestionsNom(textField_name.getText());
        List<Objet> suggestionsCode = automate.getSuggestionsCode(textField_code.getText());
        List<Objet> suggestionsType = automate.getSuggestionsType(textField_type.getText());
        if (suggestionsNom != null) {
            if (suggestionsCode != null) {
                if (suggestionsType != null) {
                    suggestionsNom.retainAll(suggestionsType);
                    suggestionsNom.retainAll(suggestionsCode);
                } else
                    suggestionsNom.retainAll(suggestionsCode);
            } else if (suggestionsType != null)
                suggestionsNom.retainAll(suggestionsType);
        }
        return suggestionsNom;
    }

    public List<Objet> trouverSuggestionsCodes() {
        List<Objet> suggestionsCode = automate.getSuggestionsCode(textField_code.getText());
        List<Objet> suggestionsNom = automate.getSuggestionsNom(textField_name.getText());
        List<Objet> suggestionsType = automate.getSuggestionsType(textField_type.getText());
        if (suggestionsCode != null) {
            if (suggestionsNom != null) {
                if (suggestionsType != null) {
                    suggestionsCode.retainAll(suggestionsType);
                    suggestionsCode.retainAll(suggestionsNom);
                } else
                    suggestionsCode.retainAll(suggestionsNom);
            } else if (suggestionsType != null)
                suggestionsCode.retainAll(suggestionsType);
        }
        return suggestionsCode;
    }

    public List<Objet> trouverSuggestionsType() {
        List<Objet> suggestionsType = automate.getSuggestionsType(textField_type.getText());
        List<Objet> suggestionsNom = automate.getSuggestionsNom(textField_name.getText());
        List<Objet> suggestionsCode = automate.getSuggestionsCode(textField_code.getText());
        if (suggestionsType != null) {
            if (suggestionsCode != null) {
                if (suggestionsNom != null) {
                    suggestionsType.retainAll(suggestionsNom);
                    suggestionsType.retainAll(suggestionsCode);
                } else
                    suggestionsType.retainAll(suggestionsCode);
            } else if (suggestionsNom != null)
                suggestionsType.retainAll(suggestionsNom);
        }
        return suggestionsType;
    }

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
                } else
                    suggestionsNom.retainAll(suggestionsCode);
            } else if (suggestionsType != null)
                suggestionsNom.retainAll(suggestionsType);
            return suggestionsNom;
        }

        else if (suggestionsCode != null) {
            if (suggestionsNom != null) {
                if (suggestionsType != null) {
                    suggestionsCode.retainAll(suggestionsType);
                    suggestionsCode.retainAll(suggestionsNom);
                } else
                    suggestionsCode.retainAll(suggestionsNom);
            } else if (suggestionsType != null)
                suggestionsCode.retainAll(suggestionsType);
            return suggestionsCode;
        }
        else if (suggestionsType != null) {
            if (suggestionsCode != null) {
                if (suggestionsNom != null) {
                    suggestionsType.retainAll(suggestionsNom);
                    suggestionsType.retainAll(suggestionsCode);
                } else
                    suggestionsType.retainAll(suggestionsCode);
            } else if (suggestionsNom != null)
                suggestionsType.retainAll(suggestionsNom);
            return suggestionsType;
        }
        return null;
    }


    public String selectFile(){
        /**** Select lexicon's path ****/
        JFileChooser chooser = new JFileChooser();

        // filtre le fichier que l'usager peut selectionner
        FileNameExtensionFilter fileAllowed = new FileNameExtensionFilter("Text files", "txt", "text");
        chooser.setFileFilter(fileAllowed);

        // pour rendre l'option All Files non disponible
        chooser.setAcceptAllFileFilterUsed(false);

        // la fenetre pour saisir le fichier s'ouvre au directoire ou l'usager se retrouve a l'instant
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = chooser.showOpenDialog(new JFrame());

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();
        }
        else {
            //System.out.println("No file selected");
            return "Aucun fichier n'a été sélectionné";
        }

    }

    public void initialiser(String fichier) {
        try {
            listeObjets.clear();
            automate.lireFichier(fichier);
            listeObjets = automate.getListeObjets();
            automate.setEtatsTerminaux();
            automate.setEtatsNoms();
            automate.setEtatsCodes();
            automate.setEtatsTypes();
            automate.setMapSuggestions();

            JOptionPane.showMessageDialog(null,"Initialisation terminée" + "\n" + "Vous pouvez procéder à la commande" );

        } catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Veuillez sélectionner un fichier");
        }
    }

    public Objet trouverObjet(String input) {
        String[] array = input.split(" ");
        for (Objet o : listeObjets) {
            if (o.getNom().equals(array[0]) && o.getCode().equals(array[1]) && o.getType().equals(array[2]))
                return o;
        }
        return null;
    }

    public Objet creerObjet(String input) {
        String[] array = input.split(" ");
        return new Objet(array[0], array[1], array[2]);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
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


    public List<Objet> getListeSuggestions() {
        return listeObjets;
    }





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


