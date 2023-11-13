import java.io.IOException;

public class Interpreter {
    public static Tokenizer tok;

    public static void main(String[] args) throws IOException {
        tok = new Tokenizer("./data/test.txt", "./data/testData.txt");
        Program p = new Program();
        p.parse();
        System.out.println("end parse ******************************");
        System.out.println("begin print ****************************\n");
        p.print();
        System.out.println("\nend print ******************************");
        System.out.println("begin execute **************************\n");
        p.execute();
        System.out.println("\nend execute ****************************");
    }
}
