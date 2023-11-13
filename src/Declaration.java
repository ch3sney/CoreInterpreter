public class Declaration {
    private IdentifierList idList;

    public Declaration() {

    }

    public void parse() {
        if (Interpreter.tok.getToken() == 4) {
            Interpreter.tok.skipToken();
        }
        idList = new IdentifierList();
        idList.parse(true);
        if (Interpreter.tok.getToken() == 12) {
            Interpreter.tok.skipToken();
        }

    }

    public void print() {
        System.out.print("int ");
        idList.print();
        System.out.print(";\n");
    }

    public void execute() {
        idList.setValues(true);
    }
}
