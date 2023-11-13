public class Identifier {
    private Identifier id;
    private String name;

    public Identifier() {
    }

    public void parse(String idString, char type) {
        name = idString;
        //System.out.println("access type = " + type + ". id = " + idString + ". idMap = " + Interpreter.tok.idMap);
        switch (type) {
            case 'd':
                if (!Interpreter.tok.hasIdParse(name)) {
                    Interpreter.tok.setIdParseValue(name, 'd');
                } else {
                    System.err.println("Error: Identifier was declared twice.");
                    System.exit(1);
                }
                break;
            case 'i':
                if (Interpreter.tok.hasIdParse(name)) {
                    Interpreter.tok.setIdParseValue(name, 'i');
                } else {
                    System.err.println("Error: Identifier was initialized before being declared.");
                    System.exit(1);
                }
            case 'a':
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
        Interpreter.tok.setIdValue(this.name, val);
    }

    public Integer getValue() {
        return Interpreter.tok.getIdValue(name);
    }
}
