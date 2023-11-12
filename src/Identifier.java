public class Identifier {
    private Identifier id;
    private Letter letter;
    private Digit digit;
    private String name;

    public Identifier() {
    }

    public void parse(String idString) {
        int len = idString.length();
        if (len == 1) {
            this.letter = new Letter();
            letter.parse(idString);
            this.name = letter.getVal();
        } else if (Character.isAlphabetic(idString.charAt(len - 1))) {
            this.letter = new Letter();
            letter.parse(idString.substring(len - 1));
            this.id = new Identifier();
            id.parse(idString.substring(0, len - 1));
            this.name = id.getName() + letter.getVal();
        } else if (Character.isDigit(idString.charAt(len - 1))) {
            this.digit = new Digit();
            digit.parse(idString.substring(len - 1));
            this.id = new Identifier();
            id.parse(idString.substring(0, len - 1));
            this.name = id.getName() + digit.getVal();
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

    public void setValue(int val) {
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
