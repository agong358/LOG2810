import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import javax.swing.JLabel;
import java.awt.*;

public class Interface {
    JFrame frame = new JFrame("Interface de commandes");
    JLabel label_search = new JLabel("Recherche ");
    JLabel label_title = new JLabel("Interface de commandes");
    JTextField textField_name = new JTextField("Entrer le nom");
    JTextField textField_code = new JTextField("Entrer le code");
    JTextField textField_type = new JTextField("Entrer le type");

    Interface(){
        //Layout general
        frame=new JFrame();
        frame.setSize(500,500);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label_title.setBounds(5, 50, 1000, 40);   //TODO
        frame.add(label_title);

        label_search.setBounds(5,100,100, 40);    //TODO
        frame.add(label_search);

        //textField_name.addFocusListener();

        textField_name.setBounds(160, 50, 200, 30);   //TODO
        frame.add(textField_name);

        textField_code.setBounds(160, 85, 200, 30);   //TODO
        frame.add(textField_code);

        textField_type.setBounds(160, 120, 200, 30);   //TODO
        frame.add(textField_type);

        JButton browseButton = new JButton("Browse");
        frame.add(browseButton);
        frame.setVisible(true);                                  // pour rendre la bordure de l'interface visible

        browseButton.addActionListener(e -> {
            selectFile();
        });
    }


    public void selectFile(){
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
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
        else {
            System.out.println("No file selected");
        }

    }

}


