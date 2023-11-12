
public class DigitSeq {
    private Digit dig;
    private DigitSeq seq;
    private String val;

    public String getVal() {
        return this.val;
    }

    public void parse(String constString) {
        if (constString.length() <= 1) {
            this.dig = new Digit();
            dig.parse(constString);
            this.val = this.dig.getVal();
        } else {
            this.seq = new DigitSeq();
            this.seq.parse(constString.substring(0, constString.length() - 1));
            this.dig = new Digit();
            this.dig.parse(constString.substring(constString.length() - 1));
            this.val = seq.getVal() + dig.getVal();
        }
    }

    public void print() {
        // Try/catch?
        System.out.print(Integer.parseInt(getVal()));
    }

    public int execute() {
        // Try/catch?
        return Integer.parseInt(getVal());
    }
}
