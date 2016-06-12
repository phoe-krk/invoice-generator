import javax.swing.*;

/**
 * Created by koden on 13/06/2016.
 * Testowanie okienek.
 */
public class Main {
    public static void main(String[] args) {
        Backend b = new DummyBackend();
        JFrame frame = new JFrame("Launcher");
        frame.setContentPane(new Launcher(b).PanelLauncher);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
