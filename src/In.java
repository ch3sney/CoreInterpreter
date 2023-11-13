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
    }

    public void print() {
        System.out.print("read ");
        idList.print();
        System.out.print(";\n");
    }

    public void execute() {
        idList.setValues(false);
    }
}
