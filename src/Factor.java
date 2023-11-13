public class Factor {
    private Op op;
    private Factor factor;

    public Factor() {
    }

    public void parse() {
        // Factor parse. Simple!
        this.op = new Op();
        op.parse();
        if (Interpreter.tok.getToken() == 24) {
            Interpreter.tok.skipToken();
            this.factor = new Factor();
            factor.parse();
        }
    }

    public void print() {
        op.print();
        if (this.factor != null) {
            System.out.print(" * ");
            factor.print();
        }
    }

    public int execute() {
        if (this.factor == null) {
            return this.op.execute();
        } else {
            return this.op.execute() * this.factor.execute();
        }
    }


}
