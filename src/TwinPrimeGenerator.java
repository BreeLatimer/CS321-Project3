import java.util.Random;

/**
 * @author Bree Latimer
 * This is the TwinPrimeGenerator class. This generates two prime numbers that are 2
 * numbers away from each other between the values of 95500 and 96000. This gives us a range
 * of 500 numbers to search. This class acts as its own program because it has its own main method.
 */
public class TwinPrimeGenerator {
    public static void main(String[] args){
        int[] list = findTwins(95500, 96000);
        System.out.println(list[0] + " and " + list[1]);
    }

    /**
     * Constructor for this class. Is blank because not much is performed here.
     */
    public TwinPrimeGenerator(){
    }

    /**
     * Finds twin prime values.
     * @param minVal Minimum value
     * @param maxVal Maximum value
     * @return numbers between minimum and maximum value that are twin primes
     */
    public static int[] findTwins(int minVal, int maxVal) {
        for(int i = minVal; i < maxVal; i++){
            if(isPrime(i) && isPrime(i + 2)){
                int[] output = {i, i+2};
                return output;
            }
        }
        return null;
    }

    public static boolean isPrime(int input){
        return squareMultiply(input) == 1 && squareMultiply(input) == 1;
    }

    /**
     * Performs the operation to determine if the inputed number is prime.
     * @param input number to determine if it is prime
     * @return 1 if the number is prime.
     */
    public static int squareMultiply(int input){
        int base = (int) new Random().nextInt(input);
        int output = base;
        String binary = Integer.toBinaryString(input -1);
        for(int i = 1; i < binary.length(); i++){
            if(binary.charAt(i) == '1'){
                output = (int) Math.abs(((Math.pow(output, 2) % input) * base) % input);
            } else {
                output = (int) (Math.abs(Math.pow(output, 2) % input));
            }
        }
        return (int)output;
    }
}
