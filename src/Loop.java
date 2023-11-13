public class Loop {
    private Condition cond;
    private StatementSeq ss;

    public Loop() {

    }

    public void parse() {
        // Loop parsing. Looks complicated, but just checking syntax!
        if (Interpreter.tok.getToken() == 8) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected 'while' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }
        cond = new Condition();
        cond.parse();

        if (Interpreter.tok.getToken() == 9) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected 'loop' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }

        ss = new StatementSeq();
        ss.parse();

        if (Interpreter.tok.getToken() == 3) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected 'end' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
        }

        if (Interpreter.tok.getToken() == 12) {
            Interpreter.tok.skipToken();
        } else {
            System.err.println("Error: Expected ';' token. id = " + Interpreter.tok.getToken());
            System.exit(1);
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
