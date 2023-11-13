import java.util.Arrays;

public class Out {
    IdentifierList idList;

    public Out() {

    }

    public void parse() {
        // Out parsing.
        if (Interpreter.tok.getToken() == 11) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected 'write' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }
        idList = new IdentifierList();
        idList.parse('a');

        if (Interpreter.tok.getToken() == 12) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected ';' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }
    }

    public void print() {
        Interpreter.tok.printTabs();
        System.out.print("write ");
        idList.print();
        System.out.print(";\n");
    }

    public void execute() {
        String[] arr = idList.execute().split("~");
        for (String s : arr) {
            System.out.println(s + " = " + Interpreter.tok.getIdValue(s));
        }
    }
}
