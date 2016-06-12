package pl.edu.uj.io.invoice;

import org.armedbear.lisp.*;
import org.armedbear.lisp.Package;

public class InvoiceGenerator {

    public static String generateInvoice (Cons data) {
        Interpreter interpreter = Interpreter.createInstance();
        interpreter.eval("(load \"Server/src/pl/edu/uj/io/invoice/generate-invoice.lisp\")");
        Package defaultPackage = Packages.findPackage("CL-USER");
        Symbol myFunctionSym = defaultPackage.findAccessibleSymbol("PRINT-ALL");
        Function myFunction = (Function) myFunctionSym.getSymbolFunction();

        return (String) myFunction.execute(data).javaInstance();
    }
}
