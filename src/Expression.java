public class Expression {
    private Factor factor;
    private Expression exp;
    private String sign;

    public Expression() {
        sign = "";
    }

    public void parse() {
        this.factor = new Factor();
        factor.parse();
        if (Interpreter.tok.getToken() == 22) {
            Interpreter.tok.skipToken();
            this.sign = "+";
            this.exp = new Expression();
            exp.parse();
        } else if (Interpreter.tok.getToken() == 23) {
            Interpreter.tok.skipToken();
            this.sign = "-";
            this.exp = new Expression();
            exp.parse();
        }
    }

    public void print() {
        this.factor.print();
        if (!sign.isEmpty()) {
            System.out.print(" " + sign + " ");
            this.exp.print();
        }
    }

    public int execute() {
        if (sign.equals("+")) {
            return this.factor.execute() + this.exp.execute();
        } else if (sign.equals("-")) {
            return this.factor.execute() - this.exp.execute();
        } else {
            return this.factor.execute();
        }
    }
}
