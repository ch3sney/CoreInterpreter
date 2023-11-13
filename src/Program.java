public class Program {
    private DeclarationSeq decSeq;
    private StatementSeq stmtSeq;

    public Program() {
    }

    public void parse() {
        // Program parse. Expects 'program' <decl s> 'begin' <stmt s> 'end'
        if (Interpreter.tok.getToken() == 1) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected 'program' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }

        decSeq = new DeclarationSeq();
        decSeq.parse();

        if (Interpreter.tok.getToken() == 2) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected 'begin' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }
        stmtSeq = new StatementSeq();
        stmtSeq.parse();

        if (Interpreter.tok.getToken() == 3) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected 'end' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }
    }

    public void print() {

        System.out.println("program");
        Interpreter.tok.increaseTab();
        decSeq.print();
        Interpreter.tok.decreaseTab();
        System.out.println("begin");
        Interpreter.tok.increaseTab();
        stmtSeq.print();
        Interpreter.tok.decreaseTab();
        System.out.println("end");
    }

    public void execute() {
        decSeq.execute();
        stmtSeq.execute();
    }
}
