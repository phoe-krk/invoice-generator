
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by koden on 12/06/2016.
 */
public class InvoiceData implements Data {
    ClientData client;
    ArrayList<ProductData> products;
    Date date;

    public InvoiceData(ClientData client, ArrayList<ProductData> products, Date date) {
        this.client = client;
        this.products = products;
        this.date = date;
    }

    public InvoiceData(ClientData client, ProductData product , Date date) {
        this.client = client;
        this.products = new ArrayList<ProductData>();
        products.add(product);
        this.date = date;
    }

    @Override
    public String toString() {
        return client +", products=" + products;
    }
}
