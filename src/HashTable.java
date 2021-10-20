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

    public HashTable(int probeType){
        numProbes = 0;
        numKeys = 0;
        totalDuplicates = 0;
        map = new HashObject[TwinPrimeGenerator.findTwins(95500, 96000)[1]];

        this.probeType = probeType;
    }

    public int add(HashObject object){
        return 0;
    }

    public int linearHashFunction(int key){
        return 0;
    }

    public int doubleHashFunction(int key){
        return 0;
    }

    public HashObject get(int i){
        return map[i];
    }

    public double getCurrentLoad(){
        return 0.0;
    }

    //All methods from here down are complete.
    public int getTotalDuplicates(){
        return totalDuplicates;
    }

    public int getNumProbes(){
        return numProbes;
    }

    public int getSize(){
        return map.length;
    }

    public int getTotalKeys(){
        return numKeys + totalDuplicates;
    }

    public int getUniqueKeys(){
        return numKeys;
    }


}
