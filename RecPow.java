import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
* Program that takes an input file,
* and does a power operation on every line with 2 numbers.
* The first number is the base.
* The second number is the exponent.
* Results will be calculated through a recursive function.
* Output will be written to an output file.
*
* @author  Atri Sarker
* @version 1.0
* @since   2025-11-25
*/
public final class RecPow {
  /**
   * Private constructor to satisfy style checker.
   * @exception IllegalStateException for the utility class.
   * @see IllegalStateException
   */
  private RecPow() {
    // Prevents illegal states.
    throw new IllegalStateException("Utility class.");
  }

  /**
   * Recursive function that raises a base to an exponent's power.
   *
   * @param base The base.
   * @param exp The exponent.
   * @return The result of base ^ exponent.
   */
  public static int recPow(final int base, final int exp) {
    // BASE CASE [exponent of 0 -> 1]
    if (exp <= 0) {
      return 1;
    } else {
      // RECURSIVE CALL
      return base * recPow(base, exp - 1);
    }
  }

  /**
   * Entrypoint of the program.
   *
   * @param args For command line arguments.
   */
  public static void main(final String[] args) {
    // First argument is the path to the input file.
    final String inputFilePath = args[0];
    // Second argument is the path to the output file.
    final String outputFilePath = args[1];
    // Print arguments
    System.out.println("Input file: " + inputFilePath);
    System.out.println("Output file: " + outputFilePath);
    try {
      // Access the input file and create a File object.
      File inputFile = new File(inputFilePath);
      // Access the output file and create a File object.
      File outputFile = new File(outputFilePath);
      // Scanner that will read the File Object.
      Scanner fileReader = new Scanner(inputFile);
      // Create list to store all the pairs
      ArrayList<int[]> listOfPairs = new ArrayList<>();
      // Loop through all available lines
      while (fileReader.hasNextLine()) {
        // Add the line to the list
        // [As an integer]
        try {
          // Get the line
          final String line = fileReader.nextLine();
          // Split the line
          final String[] pairStrings = line.split(" ");
          // Convert the strings to ints
          if (pairStrings.length == 2) {
            // First number
            final int base = Integer.parseInt(pairStrings[0]);
            // Second number
            final int exp = Integer.parseInt(pairStrings[1]);
            // Create the pair
            final int[] pair = {base, exp};
            // Add it to the list
            listOfPairs.add(pair);
          }
        } catch (NumberFormatException error) {
          // If the line can't be converted to a pair,
          // the program just ignores the line and continues.
          continue;
        }
      }
      // Close the file reader
      fileReader.close();
      // Convert the list to an array
      int[][] arrOfPairs = new int[listOfPairs.size()][2];
      for (int index = 0; index < listOfPairs.size(); index++) {
        arrOfPairs[index] = listOfPairs.get(index);
      }
      // String to hold all output
      String output = "";
      // Go through every number and add the result to the output string
      for (int[] pair : arrOfPairs) {
        // Get base and exponent
        final int base = pair[0];
        final int exp = pair[1];
        // Check if exponent is negative
        if (exp < 0) {
          // If so, just print an error
          // Add it to the output
          output += "ERROR: Negative exponents aren't allowed";
          // Print transformation
          System.out.print(exp);
          System.out.println(" is negative, and can't be used as an exponent.");
        } else {
          // Get the power
          final int result = recPow(base, exp);
          // Add it to the output
          output += Integer.toString(result);
          // Print transformation
          System.out.print(base);
          System.out.print("^");
          System.out.print(exp);
          System.out.print(" -> ");
          System.out.println(result);
        }
        // Add a newline
        output += "\n";
      }
      // Write output to output file
      try {
        // Create a FileWriter object to write to the file
        FileWriter writer = new FileWriter(outputFile);
        // Write the output to the file
        writer.write(output);
        // Close the writer
        writer.close();
      } catch (IOException error) {
        System.out.println(error);
      }
    } catch (IOException error) {
      System.out.println(error);
    }
    // Completion message
    System.out.println("DONE!");
  }
}
