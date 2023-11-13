import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Tokenizer {
    // Public tokenizer accessed by all other classes
    public Tokenizer tok;

    // Scanner variable. I opted to create tokenizer to encapsulate the behavior of scanner.
    private final Scanner scanner;

    // Map of ids accessed at execution
    private final HashMap<String, Integer> idMap;

    // Map of ids accessed during parse
    private final HashMap<String, Character> idParse;

    // Queue of data read from file
    private Queue<Integer> dataQueue;

    // Used to store current tab amount
    private int tab = 0;

    private void readData(String dataPath) {
        // Reads all data and stores in file. I opted to read all at once versus in real-time :)
        dataQueue = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dataPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line.trim());
                    dataQueue.add(number);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid data found: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Tokenizer(String codePath, String dataPath) throws IOException {
        // Constructor
        readData(dataPath);
        scanner = new Scanner(codePath);
        idParse = new HashMap<>();
        idMap = new HashMap<>();

    }


    public void skipData() {
        if (!dataQueue.isEmpty()) {
            dataQueue.remove();
        } else {
            System.err.println("Error: Reached end of data stream.");
            System.exit(1);
        }
    }

    public int getData() {
        if (!dataQueue.isEmpty()) {
            return dataQueue.peek();
        } else {
            System.err.println("Error: Reached end of data stream.");
            System.exit(1);
            return 0;
        }

    }

    /*
     *  The below methods are encapsulating the scanner or id maps for easy use in other classes.
     */

    public int intVal() {
        return scanner.intVal();
    }

    public String idName() {
        return scanner.idName();
    }

    public Integer getIdValue(String name) {
        return idMap.get(name);
    }

    public Character getIdParseValue(String name) {
        return idParse.get(name);
    }

    public void setIdValue(String name, Integer value) {
        idMap.put(name, value);
    }

    public void setIdParseValue(String name, Character value) {
        idParse.put(name, value);
    }

    public boolean hasId(String name) {
        return idMap.containsKey(name);
    }

    public boolean hasIdParse(String name) {
        return idParse.containsKey(name);
    }

    public void skipToken() {
        try {
            scanner.skipToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getToken() {
        return scanner.getToken();
    }

    public boolean compareToken(int token) {
        return token == scanner.getToken();
    }

    /*
     *  Standardized methods for printing tabs.
     */

    public void increaseTab() {
        tab++;
    }

    public void decreaseTab() {
        tab--;
    }

    public void printTabs() {
        for (int i = 0; i < tab; i++) {
            System.out.print("\t");
        }
    }

}
