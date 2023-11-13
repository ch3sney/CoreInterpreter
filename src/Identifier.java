public class Identifier {
    private Identifier id;
    private String name;

    public Identifier() {
    }

    public void parse(String idString, char type) {
        name = idString;
        // Switch statement for different 'types' of id parsing.
        switch (type) {
            case 'd':
                // Declaration. ID should not exist yet.
                if (!Interpreter.tok.hasIdParse(name)) {
                    Interpreter.tok.setIdParseValue(name, 'd');
                } else {
                    System.err.println("Error: Identifier was declared twice.");
                    System.exit(1);
                }
                break;
            case 'i':
                // Initialization. ID should exist.
                if (Interpreter.tok.hasIdParse(name)) {
                    Interpreter.tok.setIdParseValue(name, 'i');
                } else {
                    System.err.println("Error: Identifier was initialized before being declared.");
                    System.exit(1);
                }
            case 'a':
                // Access. ID should exist and be initialized.
                if (!Interpreter.tok.hasIdParse(name) || Interpreter.tok.getIdParseValue(name) == 'd') {
                    System.err.println("Error: Identifier was accessed before being declared and/or initialized.");
                    System.exit(1);
                }
        }
    }

    public void print() {
        System.out.print(this.name);
    }

    public int execute() {
        return getValue();
    }

    public String getName() {
        return this.name;
    }

    private void declareId() {
        Interpreter.tok.setIdValue(this.name, null);
    }

    public void setValue(Integer val) {
        // set and get methods do not require error checking as this was done during parse
        Interpreter.tok.setIdValue(this.name, val);
    }

    public Integer getValue() {
        return Interpreter.tok.getIdValue(name);
    }
}
