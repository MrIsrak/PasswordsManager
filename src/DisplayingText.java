import javax.swing.*;
import java.awt.*;

public class DisplayingText {
    static JLabel outPutText = new JLabel();
    static public void displayText(boolean pass){
        Initialization.mainConstraints.gridy=6;
        outPutText.setFont(new Font("CALIBRE", Font.BOLD, 14));
        outPutText.setForeground(Color.black);
        if(pass) {outPutText.setText(STR."Your password is: \{Initialization.decryptedPassword}");}
        else{}
        Initialization.mainLayout.add(outPutText, Initialization.mainConstraints);
        Initialization.mainLayout.revalidate();
    }
}
