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
    public JPanel PanelLauncher;
    private Backend backend;

    public Launcher(){

    }

    public Launcher(Backend b) {
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
                            //// TODO: 13/06/2016 Make UserUi
                        } else {
                            AdminUi.launch(backend,l);
                        }
                    } else {
                        //// TODO: 13/06/2016 Hasło nieprawidłowe
                    }
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


}
