public class Assign {
    private Identifier id;
    private Expression exp;

    public Assign() {

    }

    public void parse() {
        // Assign parsing. Checks for id, '=', and expression.
        id = new Identifier();
        id.parse(Interpreter.tok.idName(), 'i');
        Interpreter.tok.skipToken();
        if (Interpreter.tok.getToken() == 14) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected '=' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }
        exp = new Expression();
        exp.parse();
        if (Interpreter.tok.getToken() == 12) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected ';' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }
    }

    public void print() {
        Interpreter.tok.printTabs();
        id.print();
        System.out.print(" = ");
        exp.print();
        System.out.print(";\n");
    }

    public void execute() {
        int val = exp.execute();
        id.setValue(val);
    }
}
