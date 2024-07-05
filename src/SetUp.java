import javax.swing.*;
import java.awt.*;
import java.net.NoRouteToHostException;

public class SetUp {
    public static void creatingWindow(JFrame window){
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(300, 400);

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
        buttonsConstraints.gridwidth = 100;
        buttonsConstraints.ipadx = 100;
        buttonsConstraints.ipady = 20;
        buttonsConstraints.anchor = GridBagConstraints.CENTER;

        // Buttons
        for(int i=1; i<6; i++){
            JButton button = new JButton("Button " + i);
            buttonsConstraints.gridx = 0;
            buttonsConstraints.gridy = i;

            buttonsLayout.add(button, buttonsConstraints);
        }
        mainConstraints.gridy = 1;
        mainLayout.add(buttonsLayout, mainConstraints);

        window.add(mainLayout);
        window.setVisible(true);

    }
}
