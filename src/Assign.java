public class Assign {
    private Identifier id;
    private Expression exp;

    public Assign() {

    }

    public void parse() {
        id = new Identifier();
        id.parse(Interpreter.tok.idName());
        Interpreter.tok.skipToken();
        if (Interpreter.tok.getToken() == 14) {
            Interpreter.tok.skipToken();
        }
        exp = new Expression();
        exp.parse();
        if (Interpreter.tok.getToken() == 12) {
            Interpreter.tok.skipToken();
        }
    }

    public void print() {
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
