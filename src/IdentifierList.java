public class IdentifierList {
    private Identifier id;
    private IdentifierList idList;

    public IdentifierList() {

    }

    public void parse(boolean decl) {
        id = new Identifier();
        id.parse(Interpreter.tok.idName(), decl);
        Interpreter.tok.skipToken();
        if (Interpreter.tok.getToken() == 13) {
            Interpreter.tok.skipToken();
            idList = new IdentifierList();
            idList.parse(decl);
        }
    }

    public void print() {
        id.print();
        if (idList != null) {
            System.out.print(", ");
            idList.print();
        }
    }

    public String execute() {
        if (idList == null) {
            return id.getName();
        } else {
            return id.getName() + '~' + idList.execute();
        }
    }

    public void setValues(boolean initialize) {
        if (initialize) {
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
