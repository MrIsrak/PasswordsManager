import javax.swing.*;
import java.awt.*;

public class ButtonFuncs extends Initialization{
    public static void addAct() {
        SetUp.addPass.addActionListener(e -> {
            SingletonFrame addPassWindow = SingletonFrame.getInstance();
            buttonsConstraints.insets = new Insets(2, 0, 2, 0);
            buttonsConstraints.fill = GridBagConstraints.HORIZONTAL;
            addPassWindow.setTitle("Add password");
            Initialization.addText("Add password");

            JTextField sitePassField = new JTextField(10);
            mainConstraints.gridy=1;
            Initialization.addLabelTextField("Site: ", sitePassField, buttonsConstraints);

            JLabel userNameHintLabel = new JLabel();
            JTextField emailPassField = new JTextField(10);
            Initialization.addLabelTextField("Email: ", emailPassField, buttonsConstraints);

            JLabel passwordHintLabel = new JLabel();
            JPasswordField passwordField = new JPasswordField(10);
            Initialization.addLabelPasswordField("Pass: ", passwordField, buttonsConstraints);

            Initialization.saveButtonPlace();
            mainLayout.add(buttonsLayout, mainConstraints);
            addPassWindow.add(mainLayout);
        });
    }
    public static void editAct() {
        SetUp.editPass.addActionListener(e -> {
            SingletonFrame editPassWindow = SingletonFrame.getInstance();
            editPassWindow.setTitle("Edit password");
            Initialization.addText("Edit password");
            editPassWindow.add(mainLayout);
        });
    }
    public static void deleteAct() {
        SetUp.delPass.addActionListener(e -> {
            SingletonFrame delPassWindow = SingletonFrame.getInstance();
            delPassWindow.setTitle("Delete password");
        });
    }
    public static void genAct() {
        SetUp.genPass.addActionListener(e -> {
            SingletonFrame genPassWindow = SingletonFrame.getInstance();
            genPassWindow.setTitle("Generate password");
        });
    }
    public static void testAct() {
        SetUp.testPass.addActionListener(e -> {
            SingletonFrame testPassWindow = SingletonFrame.getInstance();
            testPassWindow.setTitle("Test password");
        });
    }
}

