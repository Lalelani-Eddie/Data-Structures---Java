import java.io.File;
import java.util.Scanner;

/**
 * The main class called GenericsKbBSTApp has many operations that go beyond of what was expected in
 * terms of validating users inputs this was done to ensure that only correct inputs or users data is
 * stores.
 * It interacts with BinarySearchTree to manage a knowledge base.
 *
 * @author Lalelani Nene
 */
public class GenericsKbBSTApp {

    /**
     * The main method for the application.
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree tree = new BinarySearchTree();

        // Keep running until the user chooses to quit (Option 5)
        while (true) {
            // User Interface
            System.out.println("\n/////////////////////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("IF THE PROGRAM DOES NOT RETURN ANYTHING AFTER SEARCHING IT'S BECAUSE THE ITEM WAS NOT FOUND");
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////\n");
            System.out.println("\nChoose an action from the menu:");
            System.out.println("1. Load a knowledge base from a file");
            System.out.println("2. Add a new statement to the knowledge base");
            System.out.println("3. Search for an item in the knowledge base by term");
            System.out.println("4. Search for an item in the knowledge base by term and sentence");
            System.out.println("5. Quit\n");
            System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("IF THE PROGRAM DOES NOT RETURN ANYTHING AFTER SEARCHING IT'S BECAUSE THE ITEM WAS NOT FOUND");
            System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////\n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); // Variable name changed to lowercase as per Java conventions

            switch (choice) {
                case 1:
                    System.out.println("1. Load a knowledge base from a file");
                    System.out.print("Enter file name: ");
                    scanner.nextLine(); // Consume the newline character left by nextInt()
                    String fileName = scanner.nextLine(); // Variable name changed to lowercase

                    try {
                        Scanner scanner1 = new Scanner(new File(fileName)); // Reset scanner to read from the beginning
                        while(scanner1.hasNextLine()) {
                            String lines = scanner1.nextLine();
                            tree.insert(lines);
                        }
                        System.out.println("\nKnowledge base loaded successfully.\n");
                        //System.out.println("Binary Search Tree:");
                        //tree.inorderTraversal(tree.root);
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

                    String finalStatement = term + "\t" + statement + "\t" + score; // Variable name changed to lowercase

                    // Update
                    // Check if the item already exists
                    String oldStatement = tree.inorderTraversalSearch(tree.root, term);

                    if (tree.search(tree.root, finalStatement) != null) {
                        System.out.println("The statement already exists in the knowledge base.");
                        // Update the existing node's data
                        tree.updateData(tree.root, oldStatement, finalStatement);
                    } else {
                        // Update the tree only if the item doesn't exist
                        tree.insert(finalStatement);
                        System.out.println("\nStatement Was added to the knowledge base.\n");
                    }
                    break;

                case 3:
                    System.out.println("3. Search for an item in the knowledge base by term");
                    System.out.print("Enter the term: ");
                    scanner.nextLine(); // Consume newline
                    String termToSearch = scanner.nextLine();
                    tree.inorderTraversalList(tree.root, termToSearch);
                    // Read data from the file
                    break;

                case 4:
                    System.out.println("4. Search for an item in the knowledge base by term and sentence");
                    System.out.print("Enter the term: ");
                    scanner.nextLine(); // Consume newline
                    String termForSearch = scanner.nextLine();
                    System.out.print("Enter the statement to search for: ");
                    String statementToSearch = scanner.nextLine();
                    tree.inorderTraversalStatement(tree.root, termForSearch, statementToSearch);
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
     * Represents a node in the Binary Search Tree.
     */
    public static class Node {
        String data;
        Node left, right;

        /**
         * Constructs a node with the given data.
         *
         * @param item the data to be stored in the node
         */
        public Node(String item) {
            data = item;
            left = right = null;
        }
    }
    
}