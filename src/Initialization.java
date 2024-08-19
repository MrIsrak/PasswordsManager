import org.json.JSONArray;
import org.json.JSONObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Initialization {
    static JPanel mainLayout = new JPanel(new GridBagLayout());
    static GridBagConstraints mainConstraints = new GridBagConstraints();
    static JPanel headlineLayout = new JPanel(new GridBagLayout());
    static GridBagConstraints headerConstraints = new GridBagConstraints();
    static JLabel headlineLabel = new JLabel();
    static JPanel buttonsLayout = new JPanel(new GridBagLayout());
    static GridBagConstraints buttonsConstraints = new GridBagConstraints();
    static JButton save = new JButton("Save");
    static JButton get = new JButton("Get");

    static ArrayList<JButton> buttonsArr = new ArrayList<>();
    static int buttonsArrIndex;

    static final String filePath = "passwords.json";
    /////////////////////////
    static JSONObject inventoryData;
    static JSONObject userData;
    static JSONArray sites;
    /////////////////////////


    static final SecretKey secretKey;
    static {
        try {
            secretKey = EncryptDecrypt.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    static final IvParameterSpec ivParameterSpec = EncryptDecrypt.generateIv();

    public static void setUp(SingletonFrame window){
        window.resetContent();
        removeButton(buttonsArr.get(buttonsArrIndex));
        buttonsConstraints.insets = new Insets(2, 0, 2, 0);
        buttonsConstraints.fill = GridBagConstraints.HORIZONTAL;

    }
    //main label
    public static void addText(String text) {
        headlineLabel.setText(text);
        headlineLabel.setFont(new Font("CALIBRE", Font.BOLD, 16));
        headlineLabel.setForeground(Color.black);
        headerConstraints.gridx = 0;
        headerConstraints.gridy = 0;
        headerConstraints.anchor = GridBagConstraints.NORTH;
        headlineLayout.add(headlineLabel, headerConstraints);
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainConstraints.anchor = GridBagConstraints.NORTH;
        mainLayout.add(headlineLabel, mainConstraints);
    }
    //text field
    public static void addLabelTextSiteField(String labelText, JTextField field, GridBagConstraints constraints, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("CALIBRE", Font.ITALIC, 15));
        label.setForeground(Color.black);
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.anchor = GridBagConstraints.CENTER;
        buttonsLayout.add(label, constraints);

        constraints.gridx = 1;
        buttonsLayout.add(field, constraints);
    }
    //password field
    public static void addLabelPasswordField(String labelText, JPasswordField passwordField, GridBagConstraints constraints, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("CALIBRE", Font.ITALIC, 15));
        label.setForeground(Color.black);
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.anchor = GridBagConstraints.CENTER;
        buttonsLayout.add(label, constraints);

        constraints.gridx = 1;
        buttonsLayout.add(passwordField, constraints);
    }
    //placement and remover of buttons
    static public void buttonsArraySetUp(){
        buttonsArr.add(save);
        buttonsArr.add(get);
    }
    static public void placeButton(JButton button) {
        buttonsConstraints.gridx = 0;
        buttonsConstraints.gridy = 4;
        buttonsConstraints.anchor = GridBagConstraints.CENTER;
        mainLayout.add(button, buttonsConstraints);
    }
    public static void removeButton(JButton button) {
        if (button != null) {
            mainLayout.remove(button);
            mainLayout.revalidate(); // Inform the layout manager to recalculate layout
            mainLayout = new JPanel(new GridBagLayout());
        }
    }
}


