import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Interface {
    JFrame frame = new JFrame("Interface de commandes");

    JLabel label_search = new JLabel("Recherche ");
    JLabel label_name = new JLabel("Nom");
    JLabel label_code = new JLabel("Code");
    JLabel label_type = new JLabel("Type");
    JLabel label_panier = new JLabel("Panier");
    JLabel label_nbElements = new JLabel("(poids maximal de 25 kg)");

    JTextField textField_name = new JTextField();
    JTextField textField_code = new JTextField();
    JTextField textField_type = new JTextField();
    JTextField textField_panier = new JTextField();
    JTextField textField_poids = new JTextField();
    JTextField pathFichier = new JTextField("Sélectionner un fichier en écrivant son path ou sinon à l'aide du bouton Browse");

    JButton button_add = new JButton("Ajouter");
    JButton button_remove = new JButton("Retirer");
    JButton button_order = new JButton("Commander");
    JButton button_clear = new JButton("Vider");

    List<Objet> listeObjets = new ArrayList<>();

    Automate automate = new Automate();
    private JTextField textField;

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
        liste_panier.setBounds(582, 171, 254, 300);

        pathFichier.setForeground(new Color(0, 0, 0));
        pathFichier.setBackground(Color.WHITE);
//        pathFichier.setEditable(false);


        // autosuggestion lorsque l'utilisateur ecrit dans le textField sous "Nom"
        textField_name.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                DefaultListModel listModel = new DefaultListModel();
                List<Objet> listeSuggestions = trouverSuggestions();
                if (listeSuggestions != null) {
                    for (Objet o : listeSuggestions) {
                        listModel.addElement(o.getNom() + " " + o.getCode() + " " + o.getType());
                    }
                }
                liste.setModel(listModel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                DefaultListModel listModel = new DefaultListModel();
                List<Objet> listeSuggestions = trouverSuggestions();
                if (listeSuggestions != null) {
                    for (Objet o : listeSuggestions) {
                        listModel.addElement(o.getNom() + " " + o.getCode() + " " + o.getType());
                    }
                }
                liste.setModel(listModel);
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
                DefaultListModel listModel = new DefaultListModel();
                List<Objet> listeSuggestions = trouverSuggestions();
                if (listeSuggestions != null) {
                    for (Objet o : listeSuggestions) {
                        listModel.addElement(o.getNom() + " " + o.getCode() + " " + o.getType());
                    }
                }
                liste.setModel(listModel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                DefaultListModel listModel = new DefaultListModel();
                List<Objet> listeSuggestions = trouverSuggestions();
                if (listeSuggestions != null) {
                    for (Objet o : listeSuggestions) {
                        listModel.addElement(o.getNom() + " " + o.getCode() + " " + o.getType());
                    }
                }

                liste.setModel(listModel);
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
                DefaultListModel listModel = new DefaultListModel();
                List<Objet> listeSuggestions = trouverSuggestions();
                if (listeSuggestions != null) {
                    for (Objet o : listeSuggestions) {
                        listModel.addElement(o.getNom() + " " + o.getCode() + " " + o.getType());
                    }
                }
                liste.setModel(listModel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                DefaultListModel listModel = new DefaultListModel();
                List<Objet> listeSuggestions = trouverSuggestions();
                if (listeSuggestions != null) {
                    for (Objet o : listeSuggestions) {
                        listModel.addElement(o.getNom() + " " + o.getCode() + " " + o.getType());
                    }
                }
                liste.setModel(listModel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.print("changedUpdate");
            }
        });

        frame.getContentPane().add(button_add);
        button_add.setBounds(185, 510, 100, 40);
        button_add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Objet> listeSuggestions = trouverSuggestions();

                listPanierModel.addElement(liste.getSelectedValue());
                liste_panier.setModel(listPanierModel);


//                textField_poids.setText(t);

//                listPanierModel.removeElement(liste.getSelectedIndex());
//                liste_panier.setModel(listPanierModel);


                //TODO ajouter les éléments de la liste dans listModel--Impossible de remove puisque size = 0
//                listModel.removeElementAt(liste.getSelectedIndex());
//                liste.setModel(listModel);
            }
        });

        frame.getContentPane().add(button_remove);
        button_remove.setBounds(582, 487, 120, 40);
        button_remove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO ajouter update pour liste -- mettre un élément dans la liste efface tout
                listModel.addElement(liste_panier.getSelectedValue());
                liste.setModel(listModel);

                listPanierModel.removeElementAt(liste_panier.getSelectedIndex());
                liste_panier.setModel(listPanierModel);
            }
        });

        frame.getContentPane().add(button_order);
        button_order.setBounds(793, 543, 120, 40);
        button_order.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(liste_panier.getModel().getSize() <= 25){
                    JOptionPane.showMessageDialog(frame,
                            "La commande a été passée.",
                            "Commande passée",
                            JOptionPane.PLAIN_MESSAGE);
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
                //TODO Oof...
                listModel.addElement(liste_panier);
                liste.setModel(listModel);

                listPanierModel.removeAllElements();
                liste_panier.setModel(listPanierModel);
            }
        });

        pathFichier.setBounds(185, 30, 500, 30);
        frame.getContentPane().add(pathFichier);
        // offre le choix à l'utilisateur d'écrire le path de son fichier
        pathFichier.addMouseListener(new MouseAdapter() {
            //            @Override
            public void mouseClicked(MouseEvent e) {
                pathFichier.setText("");
            }
        });

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

        JLabel lblPoids = new JLabel("Poids actuel :");
        lblPoids.setBounds(582, 548, 100, 30);
        frame.getContentPane().add(lblPoids);

        textField_poids.setText("     ---");
        textField_poids.setEditable(false);
        textField_poids.setBounds(686, 553, 70, 30);
        frame.getContentPane().add(textField_poids);
        textField_poids.setColumns(10);

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
        else
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

    public List<Objet> getListeSuggestions() {
        return listeObjets;
    }
}


