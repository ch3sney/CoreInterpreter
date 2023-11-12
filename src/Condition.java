public class Condition {
    private boolean not;
    private String andOr;
    private Comparator comp;
    private Condition left;
    private Condition right;

    public Condition() {
        // No-op
    }

    public void parse() {
        if (Interpreter.tok.getToken() == 15) {
            Interpreter.tok.skipToken();
            boolean not = true;
            left = new Condition();
            left.parse();
        } else if (Interpreter.tok.getToken() == 16) {
            Interpreter.tok.skipToken();
            left = new Condition();
            left.parse();
            if (Interpreter.tok.getToken() == 18) {
                Interpreter.tok.skipToken();
                andOr = "&&";
            } else if (Interpreter.tok.getToken() == 19) {
                Interpreter.tok.skipToken();
                andOr = "||";
            } else {
                System.err.println("Error: Condition is missing 'and' or 'or' token.");
                System.exit(1);
            }
            right = new Condition();
            right.parse();
            if (Interpreter.tok.getToken() == 17) {
                Interpreter.tok.skipToken();
            } else {
                System.err.println("Error: Condition is missing closing bracket.");
                System.exit(1);
            }
        } else {
            comp = new Comparator();
            comp.parse();
        }
    }

    public void print() {
        if (not) {
            System.out.print("!");
            left.parse();
        } else if (andOr != null) {
            System.out.print("[");
            left.print();
            if (andOr.equals("&&")) {
                System.out.print(" && ");
            } else {
                System.out.print(" || ");
            }
            right.print();
        } else {
            comp.print();
        }
    }

    public boolean execute() {
        if (not) {
            return !left.execute();
        } else if (andOr != null && andOr.equals("&&")) {
            return left.execute() && right.execute();
        } else if (andOr != null && andOr.equals("||")) {
            return left.execute() || right.execute();
        } else {
            return comp.execute();
        }
    }
}
