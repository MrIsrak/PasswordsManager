import java.io.IOException;
import java.security.NoSuchAlgorithmException;
public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        FileFuncs.checkFile();
        Initialization.buttonsArraySetUp();
        MainWindowSetUp.creatingWindow();
        }
}
