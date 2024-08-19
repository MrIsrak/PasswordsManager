import javax.swing.*;
import java.io.IOException;

public class ButtonFuncs extends Initialization{
    //TODO fix save button removing
    public static void addAct() {
        MainWindowSetUp.addPass.addActionListener(e -> {
            SingletonFrame addPassWindow = SingletonFrame.getInstance();
            Initialization.setUp(addPassWindow);
            String text = "Add password";
            addPassWindow.setTitle(text);
            Initialization.addText(text);
            mainConstraints.gridy=1;

            JTextField sitePassField = new JTextField(10);
            JTextField emailPassField = new JTextField(10);
            JPasswordField passwordField = new JPasswordField(10);

            Initialization.addLabelTextSiteField("Site: ", sitePassField, buttonsConstraints, 0, 1);
            Initialization.addLabelTextSiteField("Email: ", emailPassField, buttonsConstraints, 0, 2);
            Initialization.addLabelPasswordField("Pass: ", passwordField, buttonsConstraints, 0, 3);
            Initialization.placeButton(save);
            Initialization.buttonsArrIndex = 0;

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
    public static void getAct() {
        MainWindowSetUp.getPass.addActionListener(e -> {
            SingletonFrame genPassWindow = SingletonFrame.getInstance();
            Initialization.setUp(genPassWindow);
            
            String text = "Get password";
            genPassWindow.setTitle(text);
            Initialization.addText(text);

            JTextField sitePassField = new JTextField(10);
            JTextField emailPassField = new JTextField(10);

            Initialization.addLabelTextSiteField("Email: ", emailPassField, buttonsConstraints, 0, 1);
            Initialization.addLabelTextSiteField("Site: ", sitePassField, buttonsConstraints, 0, 2);
            Initialization.placeButton(get);
            Initialization.buttonsArrIndex = 1;

            get.addActionListener(e1 -> {
                try{
                    FileFuncs.checkFile();
                    FileFuncs.readFile(emailPassField.getText(), sitePassField.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });

            mainConstraints.gridy=2;
            mainLayout.add(buttonsLayout, mainConstraints);
            genPassWindow.add(mainLayout);
        });
    }
    public static void editAct() {
        MainWindowSetUp.editPass.addActionListener(e -> {
            SingletonFrame editPassWindow = SingletonFrame.getInstance();
            Initialization.setUp(editPassWindow);
            String text = "Edit password";
            editPassWindow.setTitle(text);
            Initialization.addText(text);
            editPassWindow.add(mainLayout);
        });
    }
    public static void deleteAct() {
        MainWindowSetUp.delPass.addActionListener(e -> {
            SingletonFrame delPassWindow = SingletonFrame.getInstance();
            delPassWindow.resetContent();
            String text = "Delete password";
            delPassWindow.setTitle(text);
            Initialization.addText(text);
            delPassWindow.add(mainLayout);
        });
    }
    public static void testAct() {
        MainWindowSetUp.testPass.addActionListener(e -> {
            SingletonFrame testPassWindow = SingletonFrame.getInstance();
            testPassWindow.resetContent();
            String text = "Test password";
            testPassWindow.setTitle(text);
            Initialization.addText(text);
            testPassWindow.add(mainLayout);
        });
    }
}

