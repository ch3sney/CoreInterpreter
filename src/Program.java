public class Program {
    public Program() {
    }

    public void parse() {
        System.out.println("Token from program... " + Interpreter.tok.getToken());
        Interpreter.tok.skipToken();
        System.out.println("Second token from program... " + Interpreter.tok.getToken());

    }

    public void print() {

    }

    public void execute() {

    }
}
