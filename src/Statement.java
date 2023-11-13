public class Statement {
    private Assign assign;
    private If ifStmt;
    private Loop loop;
    private In in;
    private Out out;


    public Statement() {

    }

    public void parse() {
        switch (Interpreter.tok.getToken()) {
            case (5):
                ifStmt = new If();
                ifStmt.parse();
                break;
            case (8):
                loop = new Loop();
                loop.parse();
                break;
            case (9):
                in = new In();
                in.parse();
                break;
            case (10):
                out = new Out();
                out.parse();
                break;
            default:
                assign = new Assign();
                assign.parse();
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
