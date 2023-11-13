public class Loop {
    private Condition cond;
    private StatementSeq ss;

    public Loop() {

    }

    public void parse() {
        System.out.println("parse while");
        if (Interpreter.tok.getToken() == 8) {
            Interpreter.tok.skipToken();
        }
        cond = new Condition();
        cond.parse();

        if (Interpreter.tok.getToken() == 9) {
            Interpreter.tok.skipToken();
        }

        ss = new StatementSeq();
        ss.parse();
        System.out.print("parse ss");

        if (Interpreter.tok.getToken() == 3) {
            Interpreter.tok.skipToken();
        }

        if (Interpreter.tok.getToken() == 12) {
            Interpreter.tok.skipToken();
        }
    }

    public void print() {
        System.out.print("while ");
        cond.print();
        System.out.print(" loop ");
        ss.print();
        System.out.print(" end;");
    }

    public void execute() {
        while (cond.execute()) {
            ss.execute();
        }
    }
}
