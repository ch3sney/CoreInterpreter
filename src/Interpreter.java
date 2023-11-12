import java.io.IOException;

public class Interpreter {
    public static Tokenizer tok;

    public static void main(String[] args) throws IOException {
        tok = new Tokenizer("./data/test.txt", "./data/testData.txt");
        Program p = new Program();
        p.parse();
    }
}
