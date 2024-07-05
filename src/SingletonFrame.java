import javax.swing.*;

public class SingletonFrame extends JFrame{
    private static SingletonFrame instance;
    private final JFrame frame; // The actual JFrame object
    private SingletonFrame() {frame = new JFrame();}
    public static synchronized SingletonFrame getInstance() {
        if (instance == null) synchronized (SingletonFrame.class) {
            if (instance == null) {
                instance = new SingletonFrame();
                instance.setSize(300, 400);
                instance.setLocationRelativeTo(null);
                instance.setVisible(true);
            }
        }
        return instance;
    }
    public JFrame getFrame() {return frame;}
}
