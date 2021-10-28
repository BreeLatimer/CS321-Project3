/**
 * @author Bree Latimer
 * This is the HashTable class. This is the data structure that stores HashObjects.
 */
public class HashTable {
    private HashObject[] map;
    private int numProbes;
    private int numKeys;
    private int totalDuplicates;
    private int probeType;

    /**
     * Basic constructor for HashTable
     * @param probeType Either 0 or 1. 0 for linear probing, 1 for double probing.
     */
    public HashTable(int probeType){
        numProbes = 0;
        numKeys = 0;
        totalDuplicates = 0;
        map = new HashObject[TwinPrimeGenerator.findTwins(95500, 96000)[1]];

        this.probeType = probeType;
    }

    /**
     * Adds an object to the hashTable
     * @param object object to be added to the table
     * @return index of the added object.
     */
    public int add(HashObject object){
        int tempProbes = 0;

        int i = linearHashFunction(object.getKey());
        while(true){
            tempProbes++;
            if(map[i] == null){
                map[i] = object;
                numProbes += tempProbes;
                numKeys++;
                return i;
            } else if(map[i].getKey() == object.getKey()){
                totalDuplicates++;
                return i;
            }
            if(probeType == 0){
                i = linearHashFunction(i + 1);
            } else {
                i = linearHashFunction(i + doubleHashFunction(object.getKey()));
            }
        }
    }

    /**
     * Linear hash operation
     * @param key the key of the object used to find the proper index
     * @return the hash value of the object
     */
    public int linearHashFunction(int key){
        int hash = key % map.length;
        while(hash < 0){
            hash += map.length;
        }
        return hash;
    }

    /**
     * Double hash operation
     * @param key the key of the object used to find the proper index
     * @return the value to be used in the linear hash function
     */
    public int doubleHashFunction(int key){
        int output = (key % (map.length - 2));
        while (output < 0){
            output += map.length - 2;
        }
        return ++output;
    }

    /**
     * Object that is being stored in the hash table
     * @param i index of the object
     * @return the object at the provided index
     */
    public HashObject get(int i){
        return map[i];
    }

    /**
     * Returns the current load of the program
     * @return the current load of the program
     */
    public double getCurrentLoad(){
        return (double)numKeys / (double)map.length;
    }

    /**
     * Returns the total number of duplicates in the table
     * @return the total number of duplicates in the table
     */
    public int getTotalDuplicates(){
        return totalDuplicates;
    }

    /**
     * Returns the number of probes
     * @return the number of probes
     */
    public int getNumProbes(){
        return numProbes;
    }

    /**
     * Returns the size of the table
     * @return the size of the table
     */
    public int getSize(){
        return map.length;
    }

    /**
     * Returns the total number of keys in the table
     * @return the total number of keys in the table
     */
    public int getTotalKeys(){
        return numKeys + totalDuplicates;
    }

    /**
     * Returns the total number of unique keys in the table
     * @return the total number of unique keys in the table
     */
    public int getUniqueKeys(){
        return numKeys;
    }


}
