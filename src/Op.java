public class Op {
    private Integer dig;
    private Identifier id;
    private Expression exp;

    public void parse() {
        if (Interpreter.tok.getToken() == 31) {
            System.out.println("int");
            this.dig = Interpreter.tok.intVal();
            Interpreter.tok.skipToken();
        } else if (Interpreter.tok.getToken() == 32) {
            System.out.println("id");
            this.id = new Identifier();
            id.parse(Interpreter.tok.idName());
            Interpreter.tok.skipToken();
        } else if (Interpreter.tok.getToken() == 20) {
            System.out.println("exp");
            Interpreter.tok.skipToken();
            this.exp = new Expression();
            exp.parse();
            if (Interpreter.tok.getToken() == 21) {
                Interpreter.tok.skipToken();
            } else {
                System.err.println("Error: Missing closing parenthesis. id=" + Interpreter.tok.getToken());
            }
        }
    }

    public void print() {
        if (dig != null) {
            System.out.print(dig);
        } else if (id != null) {
            id.print();
        } else if (exp != null) {
            System.out.print("(");
            exp.print();
            System.out.print(")");
        }
    }

    public int execute() {
        if (dig != null) {
            return dig;
        } else if (id != null) {
            return id.execute();
        } else {
            return exp.execute();
        }
    }
}
