import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SingletonFrame extends JFrame{
    private static SingletonFrame instance;
//    private final JFrame frame; // The actual JFrame object
    private SingletonFrame() {
        super();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                instance = null;
            }
        });
    }
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
    public void resetContent() {
//        getContentPane().removeAll();
//        revalidate();
//        repaint();
        Initialization.headlineLayout.removeAll();
        Initialization.buttonsLayout.removeAll();
//        Initialization.mainLayout.remove(Initialization.save);
    }

}
