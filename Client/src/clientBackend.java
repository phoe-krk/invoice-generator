/**
 * Created by koden on 11/06/2016.
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class clientBackend {
    Socket s;
    public clientBackend( ){
        try {
            s = new Socket(InetAddress.getByName("localhost"),9090);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    boolean sendLoginData(LoginData loginData) throws UnknownHostException {
        try {

            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(loginData);
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return ois.readBoolean();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    String sendRequestFromUser(Request request){
        ObjectOutputStream oos= null;
        String str="";
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(request);
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            try {
                str=(String)ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }
}
