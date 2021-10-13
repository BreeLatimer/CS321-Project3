/**
 * @author Bree Latimer
 * @param <T> Type of object to be stored
 *
 * This is the HashObject class. This stores an object in a hash map.
 */

public class HashObject<T> {
    private int duplicateCount; //Number of duplicate objects
    private int probeCount; //Number of probes to get to this object
    private int key; //key. Get's assigned by hasCode() function.
    private T object; //The object that will be stored in the hashTable.

    //HashObject constructor
    public HashObject(T object){
        this.duplicateCount = 0;
        this.probeCount = 0;
        this.key = hashCode();
        this.object = object;
    }

    /**
     * returns the value of duplicateCount
     * @return number of duplicates
     */
    public int getDuplicateCount(){
        return duplicateCount;
    }

    /**
     * returns the value of probeCount
     * @return the number of probes
     */
    public int getProbeCount(){
        return probeCount;
    }

    /**
     * returns the key of the HashObject
     * @return the key of the HasObject
     */
    public int getKey(){
        return key;
    }

    /**
     * returns the object stored in HashObject
     * @return the object stored in HashObject
     */
    public T getObject(){
        return object;
    }

    /**
     * increments the duplicateCount variable
     */
    public void incrementDuplicateCount(){
        duplicateCount++;
    }

    /**
     * increments the probeCount variable
     */
    public void incrementProbeCount(){
        probeCount++;
    }

    /**
     * Used for comparing two HashObjects. It determines if the key of each object is the same
     * or not and returns the result
     * @param objectComparison The object to compare
     * @return if the compared object is equal or not
     */
    public boolean equals(HashObject<T> objectComparison){
        return key == objectComparison.getKey();
    }

    /**
     * Returns a string. Uses the default toString() method of the object stored in HashObject
     * @return the object as a string
     */
    public String toString(){
        return object.toString();
    }
}
