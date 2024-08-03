import javax.management.InstanceNotFoundException;
import javax.swing.*;
import java.awt.*;
public class ButtonFuncs extends Initialization{
    public static void addAct() {
        SetUp.addPass.addActionListener(e -> {
            SingletonFrame addPassWindow = SingletonFrame.getInstance();
            addPassWindow.resetContent();
            buttonsConstraints.insets = new Insets(2, 0, 2, 0);
            buttonsConstraints.fill = GridBagConstraints.HORIZONTAL;
            String text = "Add password";
            addPassWindow.setTitle(text);
            Initialization.addText(text);
            mainConstraints.gridy=1;

            JTextField sitePassField = new JTextField(10);
            JTextField emailPassField = new JTextField(10);
            JPasswordField passwordField = new JPasswordField(10);

            Initialization.addLabelTextSiteField("Site: ", sitePassField, buttonsConstraints);
            Initialization.addLabelTextEmailField("Email: ", emailPassField, buttonsConstraints);
            Initialization.addLabelPasswordField("Pass: ", passwordField, buttonsConstraints);
            Initialization.saveButtonPlace();
            save.addActionListener(e1 -> {
                try {
                    FileFuncs.checkFile();
                    FileFuncs.writeFile(passwordField.getText(), emailPassField.getText(), sitePassField.getText());
                } catch (Exception ex) {System.err.println("Error during encryption/decryption: " + ex.getMessage());}
            });
            mainLayout.add(buttonsLayout, mainConstraints);
            addPassWindow.add(mainLayout);
        });
    }
    public static void editAct() {
        SetUp.editPass.addActionListener(e -> {
            SingletonFrame editPassWindow = SingletonFrame.getInstance();
            editPassWindow.resetContent();
            String text = "Edit password";
            editPassWindow.setTitle(text);
            Initialization.addText(text);
            editPassWindow.add(mainLayout);
        });
    }
    public static void deleteAct() {
        SetUp.delPass.addActionListener(e -> {
            SingletonFrame delPassWindow = SingletonFrame.getInstance();
            delPassWindow.resetContent();
            String text = "Delete password";
            delPassWindow.setTitle(text);
            Initialization.addText(text);
            delPassWindow.add(mainLayout);
        });
    }
    public static void genAct() {
        SetUp.genPass.addActionListener(e -> {
            SingletonFrame genPassWindow = SingletonFrame.getInstance();
            genPassWindow.resetContent();
            String text = "Generate password";
            genPassWindow.setTitle(text);
            Initialization.addText(text);
            genPassWindow.add(mainLayout);
        });
    }
    public static void testAct() {
        SetUp.testPass.addActionListener(e -> {
            SingletonFrame testPassWindow = SingletonFrame.getInstance();
            testPassWindow.resetContent();
            String text = "Test password";
            testPassWindow.setTitle(text);
            Initialization.addText(text);
            testPassWindow.add(mainLayout);
        });
    }
}

