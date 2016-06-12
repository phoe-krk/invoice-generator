import org.armedbear.lisp.Cons;
import org.armedbear.lisp.LispObject;
import pl.edu.uj.io.invoice.InvoiceData;
import pl.edu.uj.io.invoice.InvoiceGenerator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Start {
    public static void main(String[] args) {


        Cons data = InvoiceData.makeData("03.03.2016", "03.03.2016", "17.03.2016", "PRZELEW", "03/2016");
        Cons seller = InvoiceData.makePerson("Michał Wiśniewski", "ul. Miodowa 9", "30-009 Kraków", "NIP: 999-999-99-99");
        Cons buyer = InvoiceData.makePerson("Paweł Janowiec", "ul. Krakowska 8", "30-013 Kraków", "NIP: 123-321-32-23");

        LispObject ware1 = InvoiceData.makeWare("Deski", "szt", 300, 20.0, 22);
        LispObject ware2 = InvoiceData.makeWare("Gwoździe", "szt", 20, 2.0, 8);
        LispObject ware3 = InvoiceData.makeWare("Blacharko-kamieniarka", "szt", 1, 1500.0, 22);

        LispObject wares = InvoiceData.makeWares(ware1, ware2, ware3);

        InvoiceData invoiceData = new InvoiceData(data, seller, buyer, wares);
        System.out.println(InvoiceGenerator.generateInvoice(invoiceData.toCons()));
        try{
            ServerSocket listner = new ServerSocket(9090);
            while (true){
                Socket socket = listner.accept();
                watek w = new watek(socket,invoiceData);
                w.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
