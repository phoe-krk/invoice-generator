import java.net.UnknownHostException;

/**
 * Created by koden on 13/06/2016.
 */
public interface Backend {
    boolean sendLoginData(LoginData loginData) throws UnknownHostException;
    Data[] sendRequestFromUser(Request request);
}
