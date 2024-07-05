import com.sun.nio.sctp.PeerAddressChangeNotification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.NoRouteToHostException;
import java.util.Locale;

public class SetUp {
    static JButton addPass = new JButton("Add password");
    static JButton editPass = new JButton("Edit password");
    static JButton delPass = new JButton("Delete password");
    static JButton genPass = new JButton("Generate password");
    static JButton testPass = new JButton("Test password");


    public static void creatingWindow() {
        JFrame window = new JFrame("GUI Screen");
        window.setSize(300, 400);
        window.setLocationRelativeTo(null);

        // Main layout
        JPanel mainLayout = new JPanel(new GridBagLayout());
        GridBagConstraints mainConstraints = new GridBagConstraints();

        mainConstraints.ipady = 10;
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainConstraints.anchor = GridBagConstraints.CENTER;

        // Label layout
        JPanel labelLayout = new JPanel(new GridBagLayout());
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.anchor = GridBagConstraints.CENTER;

        // Greeting
        JLabel greeting = new JLabel("Welcome to password manager");
        greeting.setFont(new Font("CALIBRE", Font.BOLD, 16));
        greeting.setForeground(Color.black);
        labelLayout.add(greeting, labelConstraints);

        mainLayout.add(labelLayout, mainConstraints);


        // Buttons layout
        JPanel buttonsLayout = new JPanel(new GridBagLayout());
        GridBagConstraints buttonsConstraints = new GridBagConstraints();

        buttonsConstraints.insets = new Insets(2, 0, 2, 0);
        buttonsConstraints.gridwidth = 2;
        buttonsConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonsConstraints.ipady = 20;
        buttonsConstraints.anchor = GridBagConstraints.CENTER;


        buttonsConstraints.gridy = 0;
        ButtonFuncs.addAct();
        buttonsLayout.add(addPass, buttonsConstraints);
        buttonsConstraints.gridy = 1;
        buttonsLayout.add(editPass, buttonsConstraints);
        buttonsConstraints.gridy = 2;
        buttonsLayout.add(delPass, buttonsConstraints);
        buttonsConstraints.gridy = 3;
        buttonsLayout.add(genPass, buttonsConstraints);
        buttonsConstraints.gridy = 4;
        buttonsLayout.add(testPass, buttonsConstraints);

        mainConstraints.gridy = 1;
        mainLayout.add(buttonsLayout, mainConstraints);



        window.add(mainLayout);
        window.setVisible(true);

    }
}
