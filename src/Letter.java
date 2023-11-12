public class Letter {
    private String val;

    public Letter() {
    }

    public void parse(String val) {
        this.val = val;
    }

    public void print() {
        // NO-OP
    }

    public void execute() {
        // NO-OP
    }

    public String getVal() {
        return this.val;
    }
}
