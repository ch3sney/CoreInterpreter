public class Identifier {
    private Identifier id;
    private String name;

    public Identifier() {
    }

    public void parse(String idString) {
        name = idString;
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

    public void setValue(Integer val) {
        if (Interpreter.tok.hasId(this.name)) {
            Interpreter.tok.setIdValue(this.name, val);
        } else {
            System.err.println("Error: Identifier is not declared.");
            System.exit(1);
        }
    }

    public Integer getValue() {
        if (Interpreter.tok.hasId(this.name) && Interpreter.tok.getIdValue(this.name) != null) {
            return Interpreter.tok.getIdValue(name);
        } else {
            System.err.println("Error: Identifier value not initialized or does not exist.");
            System.exit(1);
            return 0;
        }
    }
}
