public class Out {
    IdentifierList idList;

    public Out() {

    }

    public void parse() {
        if (Interpreter.tok.getToken() == 11) {
            System.out.print("write");
            Interpreter.tok.skipToken();
        }
        idList = new IdentifierList();
        idList.parse();
    }

    public void print() {
        System.out.print("write ");
        idList.print();
        System.out.print(";\n");
    }

    public void execute() {
        idList.print();
    }
}
