public class In {
    IdentifierList idList;

    public In() {

    }

    public void parse() {
        // Parse in call.
        if (Interpreter.tok.getToken() == 10) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected 'read' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }
        idList = new IdentifierList();
        idList.parse('i');
        if (Interpreter.tok.getToken() == 12) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected ';' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }
    }

    public void print() {
        Interpreter.tok.printTabs();
        System.out.print("read ");
        idList.print();
        System.out.print(";\n");
    }

    public void execute() {
        idList.setValues(false);
    }
}
