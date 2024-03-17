import java.io.File;
import java.util.Scanner;

/**
 * The main class called GenericsKbArrayApp contains operations for managing a knowledge base using arrays.
 * It allows loading data from files, adding new statements, and searching the knowledge base by term and statement.
 * This class implements a basic command-line interface for user interaction.
 *
 * @author Lalelani Nene
 */
public class GenericsKbArrayApp {

    /**
     * The main method to start the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        String[] Data = new String[100000]; // Array to hold knowledge base statements

        // Keep running until the user chooses to quit (Option 5)
        while (true) {
            // User Interface
            System.out.println("\nChoose an action from the menu:");
            System.out.println("1. Load a knowledge base from a file");
            System.out.println("2. Add a new statement to the knowledge base [Update an existing statement]");
            System.out.println("3. Search for an item in the knowledge base by term");
            System.out.println("4. Search for an item in the knowledge base by term and sentence");
            System.out.println("5. Quit\n");
            System.out.print("Enter your choice: ");
            int Choice = scanner.nextInt();

            switch (Choice) {
                case 1:
                    System.out.println("1. Load a knowledge base from a file");
                    System.out.print("Enter file name: ");
                    scanner.nextLine(); // Consume the newline character left by nextInt()
                    String FileName = scanner.nextLine();

                    try {
                        Scanner scanner1 = new Scanner(new File(FileName));
                        i = 0; // Reset i before counting
                        while (scanner1.hasNextLine()) {
                            scanner1.nextLine();
                            i = i + 1;
                        }
                        scanner1.close();

                        scanner1 = new Scanner(new File(FileName)); // Reset scanner to read from the beginning
                        i = 0; // Reset i before reading
                        while (scanner1.hasNextLine()) {
                            Data[i] = scanner1.nextLine();
                            i++; // Increment i after reading
                        }
                        System.out.println("Knowledge base loaded successfully.");
                        scanner1.close();
                    } catch (Exception e) {
                        System.out.println("File Not Found");
                    }
                    break;

                case 2:
                    System.out.println("2. Add a new statement to the knowledge base");

                    System.out.println("Enter the term: ");
                    scanner.nextLine(); // Consume newline
                    String term = scanner.nextLine();

                    System.out.println("Enter the statement:");
                    String statement = scanner.nextLine();

                    System.out.print("Enter the confidence score: ");
                    String score = scanner.nextLine();
                    UpdateStatement(Data, term, statement, score, i);
                    break;

                case 3:
                    System.out.println("3. Search for an item in the knowledge base by term");
                    System.out.print("Enter the term: ");
                    scanner.nextLine(); // Consume newline
                    String termToSearch = scanner.nextLine();
                    // Read data from the file
                    SearchByTerm(Data, termToSearch, i);
                    break;

                case 4:
                    System.out.println("4. Search for an item in the knowledge base by term and sentence");
                    System.out.print("Enter the term: ");
                    scanner.nextLine(); // Consume newline
                    String termForSearch = scanner.nextLine();
                    System.out.print("Enter the statement to search for: ");
                    String statementToSearch = scanner.nextLine();
                    SearchGiven(Data, termForSearch, statementToSearch, i);
                    break;

                case 5:
                    System.out.println("Done!");
                    System.exit(0); // Exit the program
                    break;

                default:
                    System.out.println("No options are selected, or the selected option is not on the list,\"Bye!\"");
                    break;
            }
        }
    }

    /**
     * Method to search for a given statement in the knowledge base by term and statement.
     *
     * @param Array the array containing knowledge base statements
     * @param Term the term to search for
     * @param Statement the statement to search for
     * @param Arrayfilled the number of elements filled in the array
     */
    public static void SearchGiven(String[] Array, String Term, String Statement,int Arrayfilled){
        int Size = Arrayfilled;
        boolean found = false; // Flag to indicate if the statement was found
        for(int k=0;k<Size;k++){
            Scanner Scann = new Scanner(Array[k]).useDelimiter("\t");
            String Word1 = Scann.next();
            String Word2 = Scann.next();
            String Word3 = Scann.next();
            if((Word1.equals(Term)) & ((Word2).equals(Statement))){
                System.out.println("The statement was found and has a confidence score of "+Word3);
                System.out.println("");
                found = true; // Set flag to true if statement was found
            }
            Scann.close();
        }
        if (!found) {
            System.out.println("\nThe statement was not found :( \n");
        }
    }

    /**
     * Method to search for a given term in the knowledge base.
     *
     * @param Data the array containing knowledge base statements
     * @param term the term to search for
     * @param i the number of elements filled in the array
     */
    public static void SearchByTerm(String[] Data, String term, int i){
        boolean found = false; // Flag to indicate if term was found
        for(int r = 0; r < i; r++) {
            Scanner scan = new Scanner(Data[r]).useDelimiter("\t");
            String firstWord = scan.next();
            String secondWords = scan.next();
            String num = scan.next();
            if(firstWord.equals(term)){
                System.out.println("Statement found: " + firstWord + " " + secondWords);
                System.out.println("Confidence score: "+num);
                System.out.println("");
                found = true; // Set flag to true if term was found
            }
            scan.close();
        }
        if (!found) {
            System.out.println("\nStatement Was Not found ! \n");
        }
    }

    /**
     * Method to update a statement in the knowledge base.
     *
     * @param Data the array containing knowledge base statements
     * @param term the term to update
     * @param statement the new statement
     * @param Score the confidence score
     * @param i the number of elements filled in the array
     */
    // Method to update a statement in the knowledge base
    public static void UpdateStatement(String[] Data, String term, String statement, String Score, int i){
        boolean found = false; // Flag to indicate if term was found
        for(int k=0; k<i; k++){
            Scanner Scan = new Scanner(Data[k]).useDelimiter("\t");
            String Word1 = Scan.next();
            if(term.equals(Word1)){
                Data[k] = term+"\t"+statement+"\t"+Score+".";
                System.out.println("Statement for term "+term+" has been updated.\n");
                found = true; // Set flag to true if term was found
            }
            Scan.close();
            
        }
        if (!found) {
            System.out.println("\nStatement Was Not found ! \n");
        }
    }
}
