import com.sun.deploy.util.SessionState;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by koden on 11/06/2016.
 */
public class AdminUi {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton buttonNewInvoice;
    private JButton editInvoiceButton;
    private JButton exportInvoiceButton;
    private JButton deleteInvoiceButton;
    private JButton newClientButton;
    private JButton editClientButton;
    private JButton deleteClientButton;
    private JButton newProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;
    private JList list1;
    private JList list2;
    private JList list3;
    LoginData loginData;
    ClientBackend backend;
    InvoiceData[] invoices;
    ClientData[] clients;
    ProductData[] products;

    public AdminUi() {
    }

    public AdminUi(ClientBackend b, LoginData l) {
        backend=b;
        loginData=l;
        fillInvoices();

        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //Invoice: Client, Data, Products
                if (tabbedPane1.getSelectedIndex()==0){
                    fillInvoices();
                }else if(tabbedPane1.getSelectedIndex()==1){
                    fillClients();
                }else if(tabbedPane1.getSelectedIndex()==2){
                    fillProducts();
                }
            }
        });
    }

    private void fillInvoices(){
        Request r = new Request(loginData,DataType.INVOICE,null);
        invoices = (InvoiceData[])backend.sendRequestFromUser(r);
        list1.setListData(invoices);
    }

    private void fillClients(){
        Request r = new Request(loginData,DataType.CLIENT,null);
        clients = (ClientData[])backend.sendRequestFromUser(r);
        list2.setListData(clients);
    }

    private void fillProducts(){
        Request r = new Request(loginData,DataType.PRODUCT,null);
        products = (ProductData[])backend.sendRequestFromUser(r);
        list3.setListData(products);
    }

    public static void launch(ClientBackend b, LoginData l) {
        JFrame frame = new JFrame("AdminUi");
        frame.setContentPane(new AdminUi(b,l).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
