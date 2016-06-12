/**
 * Created by koden on 12/06/2016.
 */
public class ProductData implements Data {
    String name;
    int price;

    public ProductData(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name+": "+((double)price)/100;
    }
}
