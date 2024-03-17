/**
 * This class represents a Binary Search Tree data structure.
 */
public class BinarySearchTree {
    Node root; // The root node of the binary search tree

    /**
     * Constructor to initialize the root of the binary search tree.
     */
    BinarySearchTree() {
        root = null;
    }

    /**
     * Method to insert data into the binary search tree.
     *
     * @param data the data to be inserted
     */
    public void insert(String data) {
        root = insertRec(root, data);
    }

    /**
     * Recursive method to insert data into the binary search tree.
     *
     * @param root the root of the subtree
     * @param data the data to be inserted
     * @return the root of the subtree after insertion
     */
    Node insertRec(Node root, String data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data.compareTo(root.data) < 0)
            root.left = insertRec(root.left, data);
        else if (data.compareTo(root.data) > 0)
            root.right = insertRec(root.right, data);

        return root;
    }

    /**
     * Method to update data in the binary search tree.
     *
     * @param root the root of the binary search tree
     * @param oldData the old data to be updated
     * @param newData the new data to replace the old data
     */
    public void updateData(Node root, String oldData, String newData) {
        if (root != null) {
            updateData(root.left, oldData, newData);
            if (root.data.equals(oldData))
                root.data = newData;
            updateData(root.right, oldData, newData);
        }
    }

    /**
     * Method to search for a node in the binary search tree.
     *
     * @param root the root of the binary search tree
     * @param data the data to search for
     * @return the node containing the data if found, null otherwise
     */
    Node search(Node root, String data) {
        if (root == null || root.data.equals(data))
            return root;

        if (data.compareTo(root.data) < 0)
            return search(root.left, data);

        return search(root.right, data);
    }

    /**
     * Method to perform inorder traversal of the binary search tree.
     *
     * @param root the root of the binary search tree
     */
    public void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            String Word = root.data;
            System.out.println(Word);
            inorderTraversal(root.right);
        }
    }

    /**
     * Method to perform inorder traversal and list statements with a specific term.
     *
     * @param root the root of the binary search tree
     * @param Term the term to search for
     */
    public void inorderTraversalList(Node root, String Term) {
        if (root != null) {
            inorderTraversalList(root.left, Term);
            
            // Split the data into components
            String[] components = root.data.split("\t");
            
            // Extract the first string
            String firstString = components[0];
            
            // Check if the first string matches the specified term
            if (firstString.equals(Term)) {
                // Print the statement
                System.out.print("Statement found: "+components[1]);
                System.out.printf(" (Confidence score: %.2f)%n", Double.parseDouble(components[2]));
                System.out.println("\n");
            }
            
            inorderTraversalList(root.right, Term);
        }
    }

    /**
     * Method to perform inorder traversal and search for a specific term to update.
     *
     * @param root the root of the binary search tree
     * @param Term the term to search for
     * @return the old statement if found, null otherwise
     */
    public String inorderTraversalSearch(Node root, String Term) {
        String oldStatement = null;
        if (root != null) {
            inorderTraversalSearch(root.left, Term);
            
            // Split the data into components
            String[] components = root.data.split("\t");
            
            // Extract the first string
            String firstString = components[0];
            
            // Check if the first string matches the specified term
            if (firstString.equals(Term)) {
                oldStatement = components[0]+"\t"+components[1]+"\t"+components[2];
                
            }
            
            inorderTraversalSearch(root.right, Term);
        }
        return oldStatement;
    }

    /**
     * Method to perform inorder traversal and search for a specific term and statement.
     *
     * @param root the root of the binary search tree
     * @param Term the term to search for
     * @param statement the statement to search for
     */
    public void inorderTraversalStatement(Node root, String Term,String statement) {
        if (root != null) {
            inorderTraversalList(root.left, Term);
            
            // Split the data into components
            String[] components = root.data.split("\t");
            
            // Extract the first string
            String firstString = components[0];
            String SecondString = components[1];
            
            // Check if the first string matches the specified term
            if (firstString.equals(Term) && SecondString.equals(statement)) {
                // Print the statement
                System.out.println("");
                System.out.printf("\nThe statement was found and has a confidence score of (%.2f)%n", Double.parseDouble(components[2]));
                System.out.println("");
            }
            
            inorderTraversalStatement(root.right, Term, statement);
        }
    }

    /**
     * Static nested class representing a node in the binary search tree.
     */
    static class Node {
        String data;
        Node left, right;

        /**
         * Constructor to initialize a node with the given data.
         *
         * @param item the data to be stored in the node
         */
        public Node(String item) {
            data = item;
            left = right = null;
        }
    }

    /**
     * Functional interface for performing an operation on a node.
     */
    interface Operation {
        void perform(Node node);
    }
}
