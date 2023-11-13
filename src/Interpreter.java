import java.io.IOException;

public class Interpreter {
    public static Tokenizer tok;

    public static void main(String[] args) throws IOException {
        // Check that correct number of arguments were provided.
        if (args.length != 2) {
            System.err.println("Error: Invalid number of arguments provided.");
            System.exit(1);
        }

        // Initialize tokenizer
        tok = new Tokenizer(args[0], args[1]);

        // Declare and initialize new program
        Program p = new Program();

        // Parse program. Print statements for clarity.
        System.out.println("******* begin parse ****************************");
        p.parse();
        System.out.println("******* end parse ******************************\n");

        // Print program.
        System.out.println("******* begin print ****************************");
        p.print();
        System.out.println("******* end print ******************************\n");

        // Execute program.
        System.out.println("******* begin execute **************************");
        p.execute();
        System.out.println("******* end execute ****************************");
    }
}
