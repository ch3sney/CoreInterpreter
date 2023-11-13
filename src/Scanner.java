import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *  Opted to use scanner provided by professor to limit number of variables :)
 */

//Define a class named CoreScanner to tokenize a file into a list of tokens based on CORE language
class Scanner {
    //List to hold the tokens extracted from the file
    public List<Token> tokens;
    private BufferedReader reader;
    //String to hold the current line being processed
    private String currentLine = "";
    //Cursor index to move from one token to another
    private int cursorIndex;

    //List of reserved words for tokenizing
    private static final List<String> reservedWords = Arrays.asList("program", "begin", "end", "int", "if", "then", "else", "while", "loop", "read", "write");

    //Used in tokenizeLine for checking whether the character is an Upper case letter
    private static boolean isUpperCase(char c) {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(String.valueOf(c));
    }

    /*
    Constructor to initialize the CoreScanner with a file name and tokenize the first line
    */
    Scanner(String fileName) throws IOException {
        try {
            reader = new BufferedReader(new FileReader(fileName));
            //Initialize the list to contain tokens
            tokens = new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not Found");
            System.exit(1);
        }
        //First call to tokenizeLine to start tokenizing the file
        tokenizeLine();
    }

    //Define a class named Token to represent a token in CORE.
    class Token {
        //Integer to represent the type of the token
        int type;
        //String to represent the content of the token
        String content;

        //Constructor to initialize the Token object with a type and a content.
        Token(int type, String content) {
            this.type = type;
            this.content = content;
        }
    }

    /*
    Method that reads a line from the input file and converts the sequence of characters in that line into a
    sequence of Core tokens and save them in the tokens array
    */
    public void tokenizeLine() throws IOException {
        //Cursor to keep track of remaing tokens in a line
        int cursor = 0;
        //Read in first line
        currentLine = reader.readLine();

        //Skip blank lines
        while (currentLine != null && currentLine.trim().isEmpty()) {
            currentLine = reader.readLine();
        }
        //EOF has been reached
        if (currentLine == null) {
            //Set last token as "EOF" with appropriate type
            tokens.add(new Token(33, "EOF"));
        } else {
            //Integer to keep track of the length of current line
            int lineLength = currentLine.length();
            //Go through each line adding each token till there are none left
            while (cursor < lineLength) {
                //Character in line based on cursor value
                char currentChar = currentLine.charAt(cursor);
                //Integer to determine last "character" position in token
                int endIndex = cursor + 1;
                //Skip all white space
                if (Character.isWhitespace(currentChar)) {
                    cursor++;
                    //Case for Identifiers
                } else if (isUpperCase(currentChar)) {
                    //Go through adding all legal "characters" that make up an identifier
                    while (endIndex < lineLength && (Character.isUpperCase(currentLine.charAt(endIndex)) || Character.isDigit(currentLine.charAt(endIndex)))) {
                        endIndex++;
                    }
                    //String to hold identifier
                    String identifier = currentLine.substring(cursor, endIndex);
                    //Add identifier to the token array
                    tokens.add(new Token(32, identifier));
                    //Set cursor to point to next "character" position to be tokenized
                    cursor = endIndex;
                    //Case for Special symbols
                } else if (isSpecialSymbol(currentChar)) {
                    //Keep track of next character for greedy tokenizing
                    char nextChar = (cursor != lineLength - 1) ? currentLine.charAt(endIndex) : 0;
                    //String to hold symbol
                    String symbol;
                    //Go through each "special" cases for symbols
                    switch (currentChar) {
                        case '=':
                            //Case for when toke is "=="
                            if (nextChar == '=') {
                                symbol = currentLine.substring(cursor, endIndex + 1);
                                //Increase endIndex to next character
                                endIndex++;
                                //Case for when token is "="
                            } else {
                                symbol = currentLine.substring(cursor, endIndex);
                            }
                            //Add symbol to the token array
                            tokens.add(new Token(getSpecialSymbolType(symbol), symbol));
                            break;

                        case '!':
                        case '<':
                        case '>':
                            //Case for when token is "!=" or "<=" or ">="
                            if (nextChar == '=') {
                                symbol = currentLine.substring(cursor, endIndex + 1);
                                endIndex++;
                                //Case for when token is "!" or "<" or ">"
                            } else {
                                symbol = currentLine.substring(cursor, endIndex);
                            }
                            //Add symbol to the token array
                            tokens.add(new Token(getSpecialSymbolType(symbol), symbol));
                            break;

                        default:
                            //Case for all other symbols
                            symbol = currentLine.substring(cursor, endIndex);
                            //Add symbol to the token array
                            tokens.add(new Token(getSpecialSymbolType(symbol), symbol));
                            break;
                    }
                    //Set cursor to point to next "character" position to be tokenized
                    cursor = endIndex;
                    //Case for integers
                } else if (Character.isDigit(currentChar)) {
                    //Go through adding all legal "characters" that make up an identifier
                    while (endIndex < lineLength && Character.isDigit(currentLine.charAt(endIndex))) {
                        endIndex++;
                    }
                    //String to hold integer
                    String integer = currentLine.substring(cursor, endIndex);
                    //Add integer to token array
                    tokens.add(new Token(31, integer));
                    //Set cursor to point to next "character" position to be tokenized
                    cursor = endIndex;
                    //Case for reserved words or illegal token
                } else {
                    //Go through adding all legal "characters" that make up an identifier
                    while (endIndex < lineLength && Character.isLetterOrDigit(currentLine.charAt(endIndex))) {
                        endIndex++;
                    }
                    //String to hold reserved word
                    String reservedWord = currentLine.substring(cursor, endIndex);
                    //Checks to make sure string is a reserved word
                    if (reservedWords.contains(reservedWord)) {
                        //Add reserved word to token array
                        tokens.add(new Token(reservedWords.indexOf(reservedWord) + 1, reservedWord));
                        //Case for illegal token
                    } else {
                        //Add illegal token to token array
                        tokens.add(new Token(34, reservedWord));
                    }
                    //Set cursor to point to next "character" position to be tokenized
                    cursor = endIndex;
                }
            }
        }
    }

    /*
    Method to return the type of token as number from 1-34
    */
    public int getToken() {
        //Get the token at the current cursor index from the list of tokens
        Token token = tokens.get(cursorIndex);
        return token.type;
    }

    /*
    Method to return the content of token as a string
    */
    public String getContent() {
        //Get the token at the current cursor index from the list of tokens
        Token token = tokens.get(cursorIndex);
        return token.content;
    }

    /*
    Method to move the token cursor to the next token
    */
    public void skipToken() throws IOException {
        //Move cursor to next token if there are still tokens in the token array
        if (cursorIndex < tokens.size() - 1) {
            cursorIndex++;
            //If cursor token is at last token in array and token type is not EOF or Illegal, tokenize next line.
        } else if (getToken() != 33 && getToken() != 34) {
            tokenizeLine();
            cursorIndex++;
        }
    }

    /*
    Method to get the value of the current integer token
    */
    public int intVal() {
        //Check if token is an integer
        if (getToken() == 31) {
            //String to hold the content of that integer
            String integer = tokens.get(cursorIndex).content;
            //Return the integer value of the string
            return Integer.parseInt(integer);
            //If token is not an integer print error message
        } else {
            System.err.println("Error: Current token is not an integer.");
            System.exit(1);
            return -1; //This line will never be reached, added for compilation
        }
    }

    /*
    Method to get the content of the current identifier token
    */
    public String idName() {
        //Check if token is a string
        if (getToken() == 32) {
            //Return the name of identifier as string
            return tokens.get(cursorIndex).content;
            //If token is not an identifier print error message
        } else {
            System.err.println("Error: Current token is not an identifier. id=" + Interpreter.tok.getToken());
            System.exit(1);
            return null; //This line will never be reached, added for compilation
        }
    }

    /*
    Method used in tokenizeLine for checking if character is a valid special symbol
    */
    private boolean isSpecialSymbol(char character) {
        return ";,=![]&&||()+-*!===<><=>=".indexOf(character) != -1;
    }

    /*
    Method used in tokenizeLine to obtain the token type value of special symbol
    */
    private int getSpecialSymbolType(String symbol) {
        switch (symbol) {
            case ";":
                return 12;
            case ",":
                return 13;
            case "=":
                return 14;
            case "!":
                return 15;
            case "[":
                return 16;
            case "]":
                return 17;
            case "&&":
                return 18;
            case "||":
                return 19;
            case "(":
                return 20;
            case ")":
                return 21;
            case "+":
                return 22;
            case "-":
                return 23;
            case "*":
                return 24;
            case "!=":
                return 25;
            case "==":
                return 26;
            case "<":
                return 27;
            case ">":
                return 28;
            case "<=":
                return 29;
            case ">=":
                return 30;
            default:
                return -1; //Invalid special symbol
        }
    }
}
