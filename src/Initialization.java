import javax.swing.*;
import java.awt.*;

public class Initialization {
    static JPanel mainLayout = new JPanel(new GridBagLayout());
    static GridBagConstraints mainConstraints = new GridBagConstraints();

    static JPanel headlineLayout = new JPanel(new GridBagLayout());
    static GridBagConstraints headerConstraints = new GridBagConstraints();
    static JLabel headlineLabel = new JLabel();

    static JPanel buttonsLayout = new JPanel(new GridBagLayout());
    static GridBagConstraints buttonsConstraints = new GridBagConstraints();

    static JButton save = new JButton("Save");


    public static void addText(String text){
        headlineLabel.setText(text);
        headlineLabel.setFont(new Font("CALIBRE", Font.BOLD, 16));
        headlineLabel.setForeground(Color.black);
        headerConstraints.gridx=0;
        headerConstraints.gridy=0;
        headerConstraints.anchor=GridBagConstraints.NORTH;
        headlineLayout.add(headlineLabel, headerConstraints);
        mainConstraints.gridx=0;
        mainConstraints.gridy=0;
        mainConstraints.anchor=GridBagConstraints.NORTH;
        mainLayout.add(headlineLabel, mainConstraints);
    }

    public static void addLabelTextField(String labelText, JTextField field, GridBagConstraints constraints) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("CALIBRE", Font.ITALIC, 15));
        label.setForeground(Color.black);
        constraints.gridx = 0;
        constraints.gridy = constraints.gridy++; // Increment gridy for each label-field pair
        constraints.anchor = GridBagConstraints.CENTER;
        buttonsLayout.add(label, constraints);

        constraints.gridx = 1;
        buttonsLayout.add(field, constraints);
    }

    public static void addLabelPasswordField(String labelText, JPasswordField passwordField, GridBagConstraints constraints) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("CALIBRE", Font.ITALIC, 15));
        label.setForeground(Color.black);
        constraints.gridx = 0;
        constraints.gridy = constraints.gridy++; // Increment gridy for each label-field pair
        constraints.anchor = GridBagConstraints.CENTER;
        buttonsLayout.add(label, constraints);

        constraints.gridx = 1;
        buttonsLayout.add(passwordField, constraints);
    }

    static public void saveButtonPlace() {
        buttonsConstraints.gridx=0;
        buttonsConstraints.gridy=4;
        buttonsConstraints.anchor=GridBagConstraints.CENTER;
        mainLayout.add(save, buttonsConstraints);
    }

}


