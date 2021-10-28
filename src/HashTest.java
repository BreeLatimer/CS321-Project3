import java.io.File;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Bree Latimer
 * This is where the main class for this project is. This class tests the hash table
 * and hash object to ensure that they are working as intended.
 */
public class HashTest {
    static int inputType;
    static double loadFactor;
    static int debugType;
    static HashTable linearTable;
    static HashTable doubleTable;
    final static String[] dataTypes = {"", "random ints", "current time", "word-list"};

    /**
     * Main makeup of the program. Heavily methodized for easy code reading.
     * @param args Command line arguments
     */
    public static void main(String[] args){
        parseInput(args);
        initVars();
        System.out.println("A good table size is found: " + linearTable.getSize());
        System.out.println("Data source type: " + dataTypes[inputType]);
        insertValues();

        if(debugType == 1){
            debug();
        }
    }

    /**
     * Outputs to the dump file for debugging.
     */
    public static void debug(){
        try {
            PrintWriter writer = new PrintWriter(new File("linear-dump"));
            for(int i = 0; i < linearTable.getSize(); i++){
                if(linearTable.get(i) != null){
                    writer.println("table[" + i + "]: " + linearTable.get(i).getObject().toString() + " "
                        + linearTable.get(i).getDuplicateCount() + " " + linearTable.get(i).getProbeCount());
                }
            }
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Uses parsed inputs to figure out which branch should be followed.
     */
    public static void insertValues() {
        switch (inputType) {
            case 1:
                addInts();
                break;
            case 2:
                addLongs();
                break;
            case 3:
                addWords();
                break;
            default:
                printUsage();
                System.exit(1);
                break;
        }
    }

    /**
     * Initiates the linear probing hashTable and the double probing hashTable
     */
    public static void initVars(){
        linearTable = new HashTable(0);
        doubleTable = new HashTable(1);
    }

    /**
     * Prints out the data for the linear probing table
     */
    public static void printLinearStats() {
        System.out.println("Input " + linearTable.getTotalKeys() + " elements, of which "
                + linearTable.getTotalDuplicates() + " duplicates\nload factor = " + loadFactor
                + ", Avg. no. of probes " + ((double) linearTable.getNumProbes() / (double) linearTable.getUniqueKeys()));
    }

    /**
     * Prints out the data for the double probing table
     */
    public static void printDoubleStats() {
        System.out.println("Input " + doubleTable.getTotalKeys() + " elements, of which "
                + doubleTable.getTotalDuplicates() + " duplicates\nload factor = " + loadFactor
                + ", Avg. no. of probes " + ((double) doubleTable.getNumProbes() / (double) doubleTable.getUniqueKeys()));
    }

    /**
     * Adds random integers to the hash tables.
     * Note: Adds to both the linear and double hash tables in order
     * to maintain a fair comparison between the probing methods
     */
    public static void addInts() {
        Random rand = new Random();
        Integer temp;
        System.out.println("\nUsing Linear hashing...");
        while (linearTable.getCurrentLoad() < loadFactor) {
            temp = rand.nextInt();
            linearTable.add(new HashObject<Integer>(temp));
            doubleTable.add(new HashObject<Integer>(temp));
        }
        printLinearStats();
        System.out.println("\nUsing Double Hashing...");
        printDoubleStats();
    }

    /**
     * Adds random longs to the hash tables based off of the current system time.
     * Note: Adds to both the linear and double hash tables in order
     * to maintain a fair comparison between the probing methods
     */
    public static void addLongs() {
        Long temp;
        System.out.println("\nUsing Linear Hashing...");
        for (int i = 0; i < linearTable.getSize() * loadFactor; i++) {
            temp = System.currentTimeMillis();
            linearTable.add(new HashObject<Long>(temp));
            doubleTable.add(new HashObject<Long>(temp));
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        printLinearStats();
        System.out.println("\nUsing Double Hashing...");
        for (int i = 0; i < linearTable.getSize() * loadFactor; i++) {
            temp = System.currentTimeMillis();
            doubleTable.add(new HashObject<Long>(temp));
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        printDoubleStats();
    }

    /**
     * Adds to the hashTables from the word list that was provided.
     */
    public static void addWords() {
        try {
            String temp;
            Scanner scan = new Scanner(new File("word-list"));
            System.out.println("\nUsing Linear Hashing...");
            while (linearTable.getCurrentLoad() < loadFactor) {
                temp = scan.next();
                linearTable.add(new HashObject<String>(temp));
                doubleTable.add(new HashObject<String>(temp));
            }
            printLinearStats();
            scan.close();
            System.out.println("\nUsing Double Hashing...");
            printDoubleStats();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Parses the programs input in order to determine what arguments were passed in.
     * @param args Command line arguments
     */
    public static void parseInput(String[] args) {
        if (args.length >= 2) {
            inputType = Integer.parseInt(args[0]);
            loadFactor = Double.parseDouble(args[1]);
        } else {
            printUsage();
            System.exit(1);
        }
        if (args.length == 3) {
            debugType = Integer.parseInt(args[2]);
        } else {
            debugType = 0;
        }
    }

    /**
     * Prints the usage of the program to the user in case there is an error in input.
     */
    public static void printUsage() {
        System.out.println("java HashTest <input type> <load factor> [<debug level>]");
        System.out.println("\ninput type: 1 for random int, 2 for current time as a Long, "
                + "or 3 for a word list.");
        System.out.println("\nload factor: a double between 0 and 1 to decide what % of the hash table is filled.");
        System.out.println("\ndebug level (optional): 0 prints summary of experiment, "
                + "1 to print summary, as well as print hash tables into linear-dump and double-dump");
    }
}
