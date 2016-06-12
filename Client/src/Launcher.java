import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

/**
 * Created by koden on 11/06/2016.
 */
public class Launcher {
    private JButton loginButton;
    private JComboBox comboBoxType;
    private JTextField textFieldLogin;
    private JTextField textFieldPassword;
    private clientBackend backend;


    public Launcher(clientBackend b) {
        this.backend = b;
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginData l;
                UserType userType;
                if (comboBoxType.getSelectedItem().toString().equals("Admin"))
                    userType = UserType.ADMIN;
                else
                    userType = UserType.USER;
                l = new LoginData(textFieldLogin.getText(), textFieldPassword.getText().hashCode(), userType);
                try {
                    if (backend.sendLoginData(l)) {
                        if (userType == UserType.USER) {
                            ClientUi gui = new ClientUi();
                        } else {
                            AdminUi gui = new AdminUi();
                        }
                    }
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
