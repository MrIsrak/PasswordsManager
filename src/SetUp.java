import javax.swing.*;
import java.awt.*;

public class SetUp {
    public static void creatigWindow(JFrame window){
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500,700);
        // Consider using a layout manager (e.g., FlowLayout) for better positioning
        window.setLayout(new GridLayout(7, 2, 10, 10));

        JFrame frame = new JFrame("Main Frame"); // Create the JFrame


        JLabel greeting = new JLabel("Welcome to password manager");
        greeting.setFont(new Font("CALIBRE", Font.BOLD, 16));
        greeting.setForeground(Color.BLACK);

        window.add(greeting);

        // Create a JButton object
        JButton button = new JButton("Click Me");
        JButton button1 = new JButton("Click Mee");


        // Add the button to the JFrame
//        button.setHorizontalAlignment(JButton.CENTER);
//        window.add(button,BorderLayout.CENTER);
//
//        button1.setHorizontalAlignment(JButton.CENTER);
//        window.add(button1,BorderLayout.SOUTH);

        window.setVisible(true);

    }
}
