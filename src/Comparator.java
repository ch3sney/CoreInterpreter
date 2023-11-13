public class Comparator {
    private Op right;
    private Op left;
    private String compOp;

    public Comparator() {
        // No-op
    }

    public void parse() {
        if (Interpreter.tok.getToken() == 20) {
            Interpreter.tok.skipToken();
        }
        this.left = new Op();
        left.parse();

        switch (Interpreter.tok.getToken()) {
            case 25:
                compOp = "!=";
                break;
            case 26:
                compOp = "==";
                break;
            case 27:
                compOp = "<";
                break;
            case 28:
                compOp = ">";
                break;
            case 29:
                compOp = "<=";
                break;
            case 30:
                compOp = ">=";
                break;
            default:
                System.err.println("Error: Invalid comparator syntax.");
                System.exit(1);
        }
        Interpreter.tok.skipToken();


        this.right = new Op();
        right.parse();

        if (Interpreter.tok.getToken() == 21) {
            Interpreter.tok.skipToken();
        }

    }

    public void print() {
        left.print();
        System.out.print(" " + compOp + " ");
        right.print();
    }

    public boolean execute() {
        switch (compOp) {
            case "!=":
                return left.execute() != right.execute();
            case "==":
                return left.execute() == right.execute();
            case "<":
                return left.execute() < right.execute();
            case ">":
                return left.execute() > right.execute();
            case "<=":
                return left.execute() <= right.execute();
            case ">=":
                return left.execute() >= right.execute();
            default:
                System.err.println("Error: Comparator operator not initialized.");
                System.exit(1);
                return false;
        }
    }
}
