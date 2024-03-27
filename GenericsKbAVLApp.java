import java.io.File;
import java.util.Scanner;

/**
 * The main class called GenericsKbAVLApp has two functions: uploading files with dataset to the AVL tree
 * and validating if they exist within the tree from the uploaded queries.
 *
 * This class provides a command-line interface for the user to interact with the AVL tree.
 * The user can load a file into the AVL tree, enter a file name for queries to be processed,
 * or choose to quit the program.
 *
 * @author Lalelani Eddie Nene
 */
public class GenericsKbAVLApp {

    /**
     * The main method of the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLTree tree = new AVLTree();
        while (true) {
            // User Interface
            System.out.println("\nChoose an action from the menu:");
            System.out.println("1. Load a file to the AVL tree");
            System.out.println("2. Enter the name of file for Queries to be processed");
            System.out.println("3. Display Insert and Search Complexity");
            System.out.println("4. Quit\n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the name of the file");
                    scanner.nextLine();
                    String fileName = scanner.nextLine();
                    System.out.println("Knowledge base loaded successfully.\n");
                    try {
                        Scanner scan = new Scanner(new File(fileName));
                        while (scan.hasNextLine()) {
                            String statement = scan.nextLine();
                            tree.insert(statement);
                        }
                    } catch (Exception e) {
                        System.out.println("File Not Found Or There was an Error!");
                    }
                    break;

                case 2:
                    try {
                        System.out.println("Enter the name of file for Queries:");
                        scanner.nextLine();
                        String fileName2 = scanner.nextLine();
                        Scanner sScan = new Scanner(new File(fileName2));
                        while (sScan.hasNextLine()) {
                            String words2 = sScan.nextLine();
                            System.out.println(tree.search(words2));
                        }
                    } catch (Exception e) {
                        System.out.println("File Not Found Or There was an Error!");
                    }
                    break;
                case 3:
                    System.out.println("Order of complexities for the Insert and Search Operations");
                    tree.reportCounters();
                    break;
                case 4:
                    System.out.println("Done!");
                    System.exit(0); // Exit the program
                    break;

                default:
                    System.out.println("No options are selected, or the selected option is not on the list,\"Bye!\"");
                    break;
            }
        }
    }
}
