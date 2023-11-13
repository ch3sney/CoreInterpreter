public class DeclarationSeq {
    private Declaration dec;
    private DeclarationSeq decSeq;


    public DeclarationSeq() {

    }

    public void parse() {
        dec = new Declaration();
        dec.parse();

        if (Interpreter.tok.getToken() == 2) {
            return;
        }
        Interpreter.tok.skipToken();

        decSeq = new DeclarationSeq();
        decSeq.parse();
    }

    public void print() {
        dec.print();
        if (decSeq != null) {
            decSeq.print();
        }
    }

    public void execute() {
        dec.execute();
        if (decSeq != null) {
            decSeq.execute();
        }
    }
}
