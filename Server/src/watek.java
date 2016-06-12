/**
 * Created by kuba1 on 2016-06-12.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import pl.edu.uj.io.invoice.*;

public class watek implements Runnable {
    Socket s;
    pl.edu.uj.io.invoice.InvoiceData genDate;
    public watek(Socket s,pl.edu.uj.io.invoice.InvoiceData gen)
    {
        this.s=s;
        this.genDate=gen;
    }
    @Override
    public void run() {
        try {
            InputStream inputStream = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            LoginData date =(LoginData)ois.readObject();
            //Nie ma do czego sprawdzić dostepu
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            boolean acces=true;
            oos.writeBoolean(true);
            if(acces){
                while (true){
                    if(!s.isConnected()){
                        break;
                    }
                    Request request = (Request) ois.readObject();
                    //nie wiadomo do czego
                    oos.writeObject(InvoiceGenerator.generateInvoice(genDate.toCons()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
