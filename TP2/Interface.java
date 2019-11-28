import javax.swing.*;
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label_title.setBounds(5, 50, 1000, 40);   //TODO
        frame.add(label_title);

        label_search.setBounds(5,100,100, 40);    //TODO
        frame.add(label_search);

        textField_name.addFocusListener();

        textField_name.setBounds(160, 50, 200, 30);   //TODO
        frame.add(textField_name);

        textField_code.setBounds(160, 85, 200, 30);   //TODO
        frame.add(textField_code);

        textField_type.setBounds(160, 120, 200, 30);   //TODO
        frame.add(textField_type);




    }

}
