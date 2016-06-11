/**
 * Created by koden on 11/06/2016.
 */
public class LoginData implements Data {
    String login;
    int passwordHash;
    UserType type;

    public LoginData(String login, int passwordHash, UserType type) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.type = type;
    }
}
