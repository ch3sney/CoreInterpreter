public class Program {
    private DeclarationSeq decSeq;
    private StatementSeq stmtSeq;

    public Program() {
    }

    public void parse() {
        if (Interpreter.tok.getToken() == 1) {
            Interpreter.tok.skipToken();
        }
        decSeq = new DeclarationSeq();
        decSeq.parse();

        if (Interpreter.tok.getToken() == 2) {
            Interpreter.tok.skipToken();
        }
        stmtSeq = new StatementSeq();
        stmtSeq.parse();

        if (Interpreter.tok.getToken() == 3) {
            Interpreter.tok.skipToken();
        }
    }

    public void print() {
        System.out.println("program");
        decSeq.print();
        System.out.println("begin");
        stmtSeq.print();
        System.out.println("end");
    }

    public void execute() {
        decSeq.execute();
        stmtSeq.execute();
    }
}
