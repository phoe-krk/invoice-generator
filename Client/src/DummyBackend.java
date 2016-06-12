import java.util.Date;

/**
 * Created by koden on 12/06/2016.
 * Klasa stworzona w celu testowania podstawowego działania okienek
 */

public class DummyBackend implements ClientBackend {
    @Override
    public boolean sendLoginData(LoginData loginData) {
        return true;
    }

    @Override
    public Data[] sendRequestFromUser(Request request) {
        if (request.dataType == DataType.INVOICE){
            InvoiceData[] temp = new InvoiceData[1];
            temp[0] = new InvoiceData(new ClientData("Jan Kowalski", "32167"),new ProductData("Arbuz", 300) , new Date(1));
            return temp;
        }
        if (request.dataType == DataType.CLIENT){
            ClientData[] temp = new ClientData[2];
            temp[0] = new ClientData("32167", "Jan Kowalski");
            temp[1] = new ClientData("76123", "Kowal Janowski");
            return temp;
        }
        if(request.dataType == DataType.PRODUCT){
            ProductData[] temp = new ProductData[2];
            temp[0] = new ProductData("Arbuz", 300);
            temp[1] = new ProductData("Ananas", 200);
            return temp;
        }
        return null;
    }
}
