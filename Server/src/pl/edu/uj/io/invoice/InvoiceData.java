package pl.edu.uj.io.invoice;

import org.armedbear.lisp.Cons;
import org.armedbear.lisp.JavaObject;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.Nil;

//Ta nazwa już do czegoś służy
public class InvoiceData {
    public Cons data;
    public Cons seller;
    public Cons buyer;
    public LispObject wares;

    public static Cons makeData(String dataS, String dataW, String dueDate, String paymentForm, String number) {
        LispObject lispDataS = JavaObject.getInstance(dataS, true);
        LispObject lispDataW = JavaObject.getInstance(dataW, true);
        LispObject lispDueDate = JavaObject.getInstance(dueDate, true);
        LispObject lispPaymentForm = JavaObject.getInstance(paymentForm, true);
        LispObject lispNumber = JavaObject.getInstance(number, true);

        Cons result = new Cons(lispDataS,
                new Cons(lispDataW, new Cons(
                        lispDueDate, new Cons(
                        lispPaymentForm, new Cons(
                        lispNumber, Nil.NIL)))));

        return result;
    }

    public static Cons makePerson(String n1, String n2, String n3, String n4) {
        LispObject lispN1 = JavaObject.getInstance(n1, true);
        LispObject lispN2 = JavaObject.getInstance(n2, true);
        LispObject lispN3 = JavaObject.getInstance(n3, true);
        LispObject lispN4 = JavaObject.getInstance(n4, true);

        Cons result = new Cons(lispN1,
                new Cons(lispN2, new Cons(
                        lispN3, new Cons(
                        lispN4, Nil.NIL))));

        return result;
    }

    public static Cons makeWare(String name, String unit, int count, double net, int vat) {
        LispObject lispName = JavaObject.getInstance(name, true);
        LispObject lispUnit = JavaObject.getInstance(unit, true);
        LispObject lispCount = JavaObject.getInstance(count, true);
        LispObject lispNet = JavaObject.getInstance(net, true);
        LispObject lispVat = JavaObject.getInstance(vat, true);

        Cons result = new Cons(lispName,
                new Cons(lispUnit, new Cons(
                        lispCount, new Cons(
                        lispNet, new Cons(
                        lispVat, Nil.NIL)))));

        return result;
    }

    public static LispObject makeWares(LispObject... wares) {
        LispObject result = Nil.NIL;
        for (LispObject ware : wares) {
            result = new Cons(ware, result);
        }

        return result;
    }

    public InvoiceData (Cons data, Cons seller, Cons buyer, LispObject wares) {
        this.data = data;
        this.seller = seller;
        this.buyer = buyer;
        this.wares = wares;
    }

    public Cons toCons() {
        Cons result = new Cons(data,
                new Cons(seller, new Cons(
                        buyer, new Cons(
                        wares, Nil.NIL))));

        return result;
    }

}
