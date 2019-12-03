import javax.swing.*;
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
    JTextField textField_name = new JTextField("Entrer le nom");
    JTextField textField_code = new JTextField("Entrer le code");
    JTextField textField_type = new JTextField("Entrer le type");
    JTextField textField_panier = new JTextField();
    JTextField pathFichier = new JTextField("No file selected");

    List<Objet> listeObjets = new ArrayList<>();

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
        textField_name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField_name.setText("");
            }
        });

        textField_code.setBounds(280, 150, 110, 30);   //TODO
        frame.add(textField_code);
        textField_code.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField_code.setText("");
            }
        });

        textField_type.setBounds(400, 150, 110, 30);   //TODO
        frame.add(textField_type);
        textField_type.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField_type.setText("");
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
        DefaultListModel listModel = new DefaultListModel();
        for (Objet o : listeObjets) {
            listModel.addElement(o.getNom() + " " + o.getCode() + " " + o.getType());
            Automate automate = new Automate();
            automate.lireFichier(selectFile());
            List<Objet> liste = automate.getListeObjets();
            for (Objet objet : liste) {
                commandePanier += objet.getNom() + " " + objet.getCode() + " " + objet.getType() + "\n";
            }

//        JList test = new JList();
//        test.setBounds(550, 190, 200, 300);
//        frame.add(test);

            //DefaultListModel listModel = new DefaultListModel();6t cxddxc
//        listModel.addElement("Jasmine Mehra");
//        listModel.addElement("Ankit Mishra");
//        listModel.addElement("Madhuri Sanghvi");
//        listModel.addElement("Alok Kumar");
//        listModel.addElement("Rohit Bothra");
//        listModel.addElement("Rahul Aggarwal");

            //Create the list and put it in a scroll pane.
            JList list = new JList(listModel);
            frame.add(list);
            list.setBounds(550, 190, 200, 300);

//        test.setModel(listePanier);
//        test.setVisible(true);
//        frame.add(test);
        }
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
        Automate automate = new Automate();
        automate.lireFichier(fichier);
        listeObjets = automate.getListeObjets();
    }

    public List<Objet> getListeSuggestions() {
        return listeObjets;
    }

}


