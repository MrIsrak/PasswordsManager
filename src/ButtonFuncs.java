public class ButtonFuncs {
    public static void addAct() {
        SetUp.addPass.addActionListener(e -> {
            SingletonFrame addPassWindow = SingletonFrame.getInstance();
            addPassWindow.setTitle("Add password");
        });
    }
    public static void editAct() {
        SetUp.editPass.addActionListener(e -> {
            SingletonFrame editPassWindow = SingletonFrame.getInstance();
            editPassWindow.setTitle("Edit password");
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

