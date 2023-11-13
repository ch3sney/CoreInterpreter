public class Loop {
    private Condition cond;
    private StatementSeq ss;

    public Loop() {

    }

    public void parse() {
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

        if (Interpreter.tok.getToken() == 3) {
            Interpreter.tok.skipToken();
        }

        if (Interpreter.tok.getToken() == 12) {
            Interpreter.tok.skipToken();
        }
    }

    public void print() {
        Interpreter.tok.printTabs();
        System.out.print("while (");
        cond.print();
        System.out.print(") loop\n");
        Interpreter.tok.increaseTab();
        ss.print();
        Interpreter.tok.decreaseTab();
        Interpreter.tok.printTabs();
        System.out.print("end;\n");
    }

    public void execute() {
        while (cond.execute()) {
            ss.execute();
        }
    }
}
