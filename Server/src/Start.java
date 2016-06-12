import org.armedbear.lisp.Cons;
import org.armedbear.lisp.LispObject;
import pl.edu.uj.io.invoice.InternalInvoiceData;
import pl.edu.uj.io.invoice.InvoiceGenerator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Start {
    public static void main(String[] args) {


        Cons data = InternalInvoiceData.makeData("03.03.2016", "03.03.2016", "17.03.2016", "PRZELEW", "03/2016");
        Cons seller = InternalInvoiceData.makePerson("Michał Wiśniewski", "ul. Miodowa 9", "30-009 Kraków", "NIP: 999-999-99-99");
        Cons buyer = InternalInvoiceData.makePerson("Paweł Janowiec", "ul. Krakowska 8", "30-013 Kraków", "NIP: 123-321-32-23");

        LispObject ware1 = InternalInvoiceData.makeWare("Deski", "szt", 300, 20.0, 22);
        LispObject ware2 = InternalInvoiceData.makeWare("Gwoździe", "szt", 20, 2.0, 8);
        LispObject ware3 = InternalInvoiceData.makeWare("Blacharko-kamieniarka", "szt", 1, 1500.0, 22);

        LispObject wares = InternalInvoiceData.makeWares(ware1, ware2, ware3);

        InternalInvoiceData internalInvoiceData = new InternalInvoiceData(data, seller, buyer, wares);
        System.out.println(InvoiceGenerator.generateInvoice(internalInvoiceData.toCons()));
        try{
            ServerSocket listner = new ServerSocket(9090);
            while (true){
                Socket socket = listner.accept();
                watek w = new watek(socket, internalInvoiceData);
                w.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
