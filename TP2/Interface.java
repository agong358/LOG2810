import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Interface {
    JFrame frame = new JFrame("Interface de commandes");
    JLabel label_search = new JLabel("Recherche ");
    JLabel label_title = new JLabel("Interface de commandes");
    JLabel label_name = new JLabel("Nom");
    JLabel label_code = new JLabel("Code");
    JLabel label_type = new JLabel("Type");
    JLabel label_panier = new JLabel("Panier");
    JTextField textField_name = new JTextField();
    JTextField textField_code = new JTextField();
    JTextField textField_type = new JTextField();
    JTextField textField_panier = new JTextField();
    JTextField pathFichier = new JTextField("No file selected");

    List<Objet> listeObjets = new ArrayList<>();

    Automate automate = new Automate();

    Interface() {
        //Layout general
        frame.setSize(800, 600);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label_title.setBounds(30, 75, 200, 40);   //TODO
        frame.add(label_title);

        label_search.setBounds(50, 150, 100, 40);    //TODO
        frame.add(label_search);

        label_panier.setBounds(650, 150, 100, 40);
        frame.add(label_panier);

//        textField_panier.setBounds(550, 190, 200, 300);
//        frame.add(textField_panier);

        //textField_name.addFocusListener();

        label_name.setBounds(160, 120, 110, 30);
        frame.add(label_name);
        textField_name.setBounds(160, 150, 110, 30);   //TODO
        frame.add(textField_name);

        DefaultListModel listModel = new DefaultListModel();
        JList liste = new JList(listModel);
        frame.add(liste);
        liste.setBounds(150, 200, 250, 300);



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

        textField_code.setBounds(280, 150, 110, 30);   //TODO
        frame.add(textField_code);
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

        textField_type.setBounds(400, 150, 110, 30);   //TODO
        frame.add(textField_type);
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

        pathFichier.setBounds(140, 30, 500, 30);
        frame.add(pathFichier);
        pathFichier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pathFichier.setText("");
            }
        });

        JButton browseButton = new JButton("Browse");
        frame.add(browseButton);
        browseButton.setVisible(true);
        browseButton.setBounds(20, 25, 100, 40);
        frame.setVisible(true);  // pour rendre la bordure de l'interface visible

        browseButton.addActionListener(e -> {
            pathFichier.setText(selectFile());
        });

        JButton boutonInitialiserProgramme = new JButton("Initialiser");
        frame.add(boutonInitialiserProgramme);
        boutonInitialiserProgramme.setVisible(true);
        boutonInitialiserProgramme.setBounds(650, 25, 100, 40);
        boutonInitialiserProgramme.addActionListener(e -> {
            initialiser(pathFichier.getText());
        });

        //test panier

        String commandePanier = "";

//        Automate automate = new Automate();
//        automate.lireFichier("inventaire.txt");
//        List<Objet> liste = automate.getListeObjets();
//        DefaultListModel<String> listePanier = new DefaultListModel<>();
//        for (Objet o : liste) {
//            //commandePanier += o.getNom() + " " + o.getCode() + " " + o.getType() + "\n";
//            listePanier.addElement(o.getNom() + " " + o.getCode() + " " + o.getType());
//        }

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
            return "No file selected";
        }

    }

    public void initialiser(String fichier) {
        automate.lireFichier(fichier);
        listeObjets = automate.getListeObjets();
        automate.setEtatsTerminaux();
        automate.setEtatsNoms();
        automate.setEtatsCodes();
        automate.setEtatsTypes();
        automate.setMapSuggestions();
    }

    public List<Objet> getListeSuggestions() {
        return listeObjets;
    }

}


