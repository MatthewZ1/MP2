/** This class fills out most of the requirements of the assignment
* @author Jason Chen and Matthew Zhang
* @version 1.0*/
package main;

public class Requirements {
    private String str;
    private static int requirements = 0;

    /**
     * The constructor initializes the intance variables
     * 
     * @param str represents a message to be displayed
     */
    public Requirements() {
        str = "HI MR.HOLMER!! -JASON AND MATTHEW";
    }

    // private method
    /** A method that prints a message */
    private void privateMethod() {
        System.out.println("HI MR.HOLMER!! -JASON AND MATTHEW");
    }

    // return statement in loop
    /**
     * A method that prints a message
     * 
     * @param n is a number to be checked if equal to 99
     * @return message for Mr. Holmer
     *         Precondition: n has to be positive
     *         Postcondition: A string is returned that is not null
     */
    public String returnInLoop(int n) {
        while (n == 99) {
            return "HI MR.HOLMER!! -JASON AND MATTHEW";
        }
        return "HI MR.HOLMER!! -JASON AND MATTHEW";
    }

    // execution counts
    /** A method that iterates 10 times and then returns a message 
    * @return message*/
    public static String returnExecutionCount() {
        int i = 1;
        while (i <= 10) {
            System.out.println(i);
            i++;
        }
        return "HI MR.HOLMER!! -JASON AND MATTHEW";
    }

    // static method
    /** A method that increments requirements variable and prints message */
    public static void staticMethod() {
        requirements++;
        System.out.println("HI MR.HOLMER!! -JASON AND MATTHEW");
    }

    // toString method
    /** A method that returns the string of this class
    * @return str */
    public String toString() {
        return str;
    }

    /** A method that returns true if the the array inside has an odd number
    * @return true*/
    /*
     * array of primitive types
     * uses initializer list to create array
     * uses enhanced for loop
     * Determines if all elements of an array have a particular property.
     */
    public static boolean isOdd() {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        for (int i : arr) {
            if (i % 2 == 1) {
                return true;
            }
        }
        return false;
    }
    //includes at least one method that returns an object other than String
    /** A method that returns an object from Requirements2
    * @return Requirements2 object */
    public static Requirements2 returnOther(Requirements2 a){
        return a;
    }

	private String getStr() {
		return str;
	}

	private void setStr(String str) {
		this.str = str;
	}

	private static int getRequirements() {
		return requirements;
	}

	private static void setRequirements(int requirements) {
		Requirements.requirements = requirements;
	}

}
