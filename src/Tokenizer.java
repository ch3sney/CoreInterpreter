import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Tokenizer {
    public Tokenizer tok;
    private Scanner scanner;
    private HashMap<String, Integer> idMap;
    private Queue<Integer> dataQueue;
    private int tab = 0;

    private void readData(String dataPath) {
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
        readData(dataPath);
        scanner = new Scanner(codePath);
        idMap = new HashMap<>();
    }

    public int intVal() {
        return scanner.intVal();
    }

    public String idName() {
        return scanner.idName();
    }

    public Integer getIdValue(String name) {
        return idMap.get(name);
    }

    public void setIdValue(String name, Integer value) {
        idMap.put(name, value);
    }

    public boolean hasId(String name) {
        return idMap.containsKey(name);
    }

    public void skipData() {
        if (!dataQueue.isEmpty()) {
            dataQueue.remove();
        } else {
            System.err.println("Reached end of data stream.");
            System.exit(1);
        }
    }

    public int getData() {
        if (!dataQueue.isEmpty()) {
            return dataQueue.peek();
        } else {
            System.err.println("Reached end of data stream.");
            System.exit(1);
            return 0;
        }

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
