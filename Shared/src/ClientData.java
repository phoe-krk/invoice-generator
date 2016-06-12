/**
 * Created by koden on 12/06/2016.
 */
public class ClientData implements Data {
    String pesel;
    String name;

    public ClientData(String pesel, String name) {
        this.pesel = pesel;
        this.name = name;
    }

    @Override
    public String toString() {
        return pesel + " " +name;
    }
}
