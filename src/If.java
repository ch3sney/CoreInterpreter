
public class If {
    private Condition cond;
    private StatementSeq ss1;
    private StatementSeq ss2;

    public If() {
    }

    public void parse() {
        if (Interpreter.tok.getToken() == 5) {
            Interpreter.tok.skipToken();
        }
        cond = new Condition();
        cond.parse();
        if (Interpreter.tok.getToken() == 6) {
            Interpreter.tok.skipToken();
        }
        ss1 = new StatementSeq();
        ss1.parse();
        if (Interpreter.tok.getToken() == 7) {
            Interpreter.tok.skipToken();

            ss2 = new StatementSeq();
            ss2.parse();

            if (Interpreter.tok.getToken() == 3) {
                Interpreter.tok.skipToken();
            }
        } else if (Interpreter.tok.getToken() == 3) {
            Interpreter.tok.skipToken();
        }

        if (Interpreter.tok.getToken() == 12) {
            Interpreter.tok.skipToken();
        }
    }

    public void print() {
        Interpreter.tok.printTabs();
        System.out.print("if (");
        cond.print();
        System.out.print(") then\n");
        Interpreter.tok.increaseTab();
        ss1.print();
        if (ss2 != null) {
            Interpreter.tok.decreaseTab();
            Interpreter.tok.printTabs();
            System.out.print(" else\n");
            Interpreter.tok.increaseTab();
            ss2.print();
        }
        Interpreter.tok.decreaseTab();
        Interpreter.tok.printTabs();
        System.out.print(" end;\n");
    }

    public void execute() {
        boolean condRes = cond.execute();
        if (condRes) {
            ss1.execute();
        } else {
            ss2.execute();
        }
    }
}
