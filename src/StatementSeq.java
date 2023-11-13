import javax.swing.plaf.nimbus.State;

public class StatementSeq {
    private Statement stmt;
    private StatementSeq stmtSeq;

    public StatementSeq() {

    }

    public void parse() {
        stmt = new Statement();
        stmt.parse();
        if (Interpreter.tok.getToken() == 3 || Interpreter.tok.getToken() == 7) {
            return;
        }
        stmtSeq = new StatementSeq();
        stmtSeq.parse();

    }

    public void print() {
        stmt.print();
        if (stmtSeq != null) {
            stmtSeq.print();
        }
    }

    public void execute() {
        stmt.execute();
        if (stmtSeq != null) {
            stmtSeq.execute();
        }
    }
}
