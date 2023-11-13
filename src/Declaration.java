public class Declaration {
    private IdentifierList idList;

    public Declaration() {

    }

    public void parse() {
        // Parses decl. Accounts for two alternatives.
        if (Interpreter.tok.getToken() == 4) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected 'int' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }
        idList = new IdentifierList();
        idList.parse('d');
        if (Interpreter.tok.getToken() == 12) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected ';' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
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
