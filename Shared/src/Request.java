import java.util.ArrayList;

/**
 * Created by koden on 11/06/2016.
 */
public class Request {
    LoginData loginData;
    DataType dataType;
    ArrayList<Data> data;

    public Request(LoginData loginData, DataType dataType, ArrayList<Data> data) {
        this.loginData = loginData;
        this.dataType = dataType;
        this.data = data;
    }
}
