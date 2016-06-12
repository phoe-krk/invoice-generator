/**
 * Created by koden on 11/06/2016.
 */


import java.io.Serializable;
public class LoginData implements Data,Serializable {
    String login;
    int passwordHash;
    UserType type;

    public LoginData(String login, int passwordHash, UserType type) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.type = type;
    }
}
