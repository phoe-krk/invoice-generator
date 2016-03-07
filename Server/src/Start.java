import Kontroler.Kontroler;
import Model.Model;
public class Start {
    public static void main(String[] args) {
        Model model = new Model();
        Kontroler kontroler = new Kontroler(model);
    }
}
