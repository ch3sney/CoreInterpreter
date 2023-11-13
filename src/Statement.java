public class Statement {
    private Assign assign;
    private If ifStmt;
    private Loop loop;
    private In in;
    private Out out;


    public Statement() {

    }

    public void parse() {
        // Parse statement. Accounts for different alternatives and checks for invalid statement.
        switch (Interpreter.tok.getToken()) {
            case (5):
                ifStmt = new If();
                ifStmt.parse();
                break;
            case (8):
                loop = new Loop();
                loop.parse();
                break;
            case (10):
                in = new In();
                in.parse();
                break;
            case (11):
                out = new Out();
                out.parse();
                break;
            case (32):
                assign = new Assign();
                assign.parse();
                break;
            default:
                System.err.println("Error: Invalid statement. id = " + Interpreter.tok.getToken());
                System.exit(1);
                break;

        }
    }

    public void print() {
        if (ifStmt != null) {
            ifStmt.print();
        } else if (loop != null) {
            loop.print();
        } else if (in != null) {
            in.print();
        } else if (out != null) {
            out.print();
        } else if (assign != null) {
            assign.print();
        }
    }

    public void execute() {
        if (ifStmt != null) {
            ifStmt.execute();
        } else if (loop != null) {
            loop.execute();
        } else if (in != null) {
            in.execute();
        } else if (out != null) {
            out.execute();
        } else if (assign != null) {
            assign.execute();
        }
    }
}
