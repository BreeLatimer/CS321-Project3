/**
 * @author Bree Latimer
 * This is the TwinPrimeGenerator class. This generates two prime numbers that are 2
 * numbers away from each other between the values of 95500 and 96000. This gives us a range
 * of 500 numbers to search. This class acts as its own program because it has its own main method.
 */
public class TwinPrimeGenerator {
    public static void main(String[] args){

    }

    public static int[] findTwins(int minVal, int maxVal) {
        int[] intArray = new int[]{};
        return intArray;

        //To test if a random # is prime:
        //p = the selected # to test if it is prime
        //a = a random number from 1 < a < p
        //if(Math.pow(a, p) % p != 1) -> not prime
        //if(Math.pow(a, p) % p == 1) -> prime
        //then check if p+2 is prime as well, if so, we have twin primes.
        //NOTE: Start at the nearest odd number and go up by 2's to find the next prime.
        //      this is because 2 is the only even prime number.
    }
}
