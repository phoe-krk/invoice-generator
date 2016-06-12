import javax.swing.*;

/**
 * Created by koden on 13/06/2016.
 */
public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Launcher");
        frame.setContentPane(new Launcher().PanelLauncher);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
