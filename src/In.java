public class In {
    IdentifierList idList;

    public In() {

    }

    public void parse() {
        if (Interpreter.tok.getToken() == 10) {
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
        System.out.print("read ");
        idList.print();
        System.out.print(";\n");
    }

    public void execute() {
        idList.setValues(false);
    }
}
