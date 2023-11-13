public class IdentifierList {
    private Identifier id;
    private IdentifierList idList;

    public IdentifierList() {

    }

    public void parse(char type) {
        // Parse identifier. Takes a 'type' param to specify if declaring, assigning, or accessing.
        id = new Identifier();
        id.parse(Interpreter.tok.idName(), type);
        Interpreter.tok.skipToken();
        if (Interpreter.tok.getToken() == 13) {
            Interpreter.tok.skipToken();
            idList = new IdentifierList();
            idList.parse(type);
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
        // Essentially a get method. This is used for write calls.
        if (idList == null) {
            return id.getName();
        } else {
            return id.getName() + '~' + idList.execute();
        }
    }

    public void setValues(boolean initialize) {
        // Set does not require error checking as this was done during parse.
        if (initialize) {
            Interpreter.tok.setIdValue(id.getName(), null);
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
