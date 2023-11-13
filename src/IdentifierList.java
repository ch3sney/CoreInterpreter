public class IdentifierList {
    private Identifier id;
    private IdentifierList idList;

    public IdentifierList() {

    }

    public void parse() {
        id = new Identifier();
        System.out.print("me");
        id.parse(Interpreter.tok.idName());
        Interpreter.tok.skipToken();
        System.out.println("ow");
        if (Interpreter.tok.getToken() == 13) {
            System.out.println("comma");
            Interpreter.tok.skipToken();
            idList = new IdentifierList();
            idList.parse();
        }
    }

    public void print() {
        id.print();
        if (idList != null) {
            System.out.print(", ");
            idList.print();
        }
    }

    public void execute() {
        // No-op
    }

    public void setValues(boolean initialize) {
        if (initialize) {
            System.out.println("init");
            if (Interpreter.tok.hasId(id.getName())) {
                System.err.println("Error: ID in list was already initialized.");
                System.exit(1);
            } else {
                Interpreter.tok.setIdValue(id.getName(), null);
            }
            if (idList != null) {
                idList.setValues(true);
            }
        } else {
            id.setValue(Interpreter.tok.getData());
            Interpreter.tok.skipData();
            if (idList != null) {
                idList.setValues(false);
            }
        }
    }
}
