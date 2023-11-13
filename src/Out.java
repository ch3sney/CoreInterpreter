import java.util.Arrays;

public class Out {
    IdentifierList idList;

    public Out() {

    }

    public void parse() {
        if (Interpreter.tok.getToken() == 11) {
            Interpreter.tok.skipToken();
        }
        idList = new IdentifierList();
        idList.parse();

        if (Interpreter.tok.getToken() == 12) {
            Interpreter.tok.skipToken();
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
