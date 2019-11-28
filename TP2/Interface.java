import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Interface {
    JFrame frame;

    Interface() {
        frame = new JFrame("Interface de commande");

        frame.setSize(500, 500);                    // pour etablir les tailles de la fenetre de l'interface
        frame.setLocationRelativeTo(null);                       // pour que la fenetre s'ouvre au milieu de l'ecran
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // quitte l'application en utilisant le methode de sortie de System

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


