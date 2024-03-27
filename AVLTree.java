import java.util.Scanner;

/**
 * This class represents an AVL Tree data structure.
 * @author Lalelani Eddie Nene
 */
public class AVLTree {
    private int searchOpCount; // Counter for search operations
    private int insertOpCount; // Counter for insert operations
    AVLNode root;

    // Constructor
    /**
     * Constructs an AVL Tree with null root.
     */
    public AVLTree() {
        // Initialize counters
        searchOpCount = 0;
        insertOpCount = 0;
        root = null;
    }

    // Method to insert data into the AVL tree
    /**
     * Inserts a new key into the AVL tree.
     *
     * @param data the key to be inserted
     */
    public void insert(String data) {
        // Increment insert operation count
        insertOpCount++;
        root = insertRec(root, data);
    }

    // Recursive method to insert data into the AVL tree
    /**
     * Recursive method to insert data into the AVL tree.
     *
     * @param node the root of the current subtree
     * @param data the key to be inserted
     * @return the root of the subtree after insertion
     */
    private AVLNode insertRec(AVLNode node, String data) {
        // Perform regular BST insertion
        if (node == null)
            return new AVLNode(data);

        if (data.compareTo(node.key) < 0){
            insertOpCount++; // Increment comparison count
            node.left = insertRec(node.left, data);
        }else if (data.compareTo(node.key) > 0){
            insertOpCount++; // Increment comparison count
            node.right = insertRec(node.right, data);
        }else // Duplicate keys are not allowed
            return node;

        // Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor of this ancestor node to check whether this node became unbalanced
        int balance = getBalance(node);

        // If the node becomes unbalanced, there are four cases

        // Left Left Case
        if (balance > 1 && data.compareTo(node.left.key) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && data.compareTo(node.right.key) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && data.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && data.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Right rotate subtree rooted with y
    /**
     * Right rotates the subtree rooted with the given node.
     *
     * @param y the root of the subtree to be rotated
     * @return the new root of the rotated subtree
     */
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotate subtree rooted with x
    /**
     * Left rotates the subtree rooted with the given node.
     *
     * @param x the root of the subtree to be rotated
     * @return the new root of the rotated subtree
     */
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get height of the node
    /**
     * Gets the height of the given node.
     *
     * @param node the node to get the height from
     * @return the height of the node
     */
    private int height(AVLNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // Get balance factor of the node
    /**
     * Gets the balance factor of the given node.
     *
     * @param node the node to get the balance factor from
     * @return the balance factor of the node
     */
    private int getBalance(AVLNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // Node class
    /**
     * This class represents a node in the AVL Tree.
     */
    private static class AVLNode {
        String key;
        AVLNode left, right;
        int height;

        /**
         * Constructs an AVLNode with the given key and height initialized to 1.
         *
         * @param key the key of the node
         */
        AVLNode(String key) {
            this.key = key;
            this.height = 1;
        }
    }

    // Method to perform inorder traversal of the AVL tree and print the keys
    /**
     * Performs an inorder traversal of the AVL tree and prints the keys.
     *
     * @param Word the word to search for during traversal
     */
    public void inorderTraversal(String Word) {
        inorderTraversalRec(root, Word);
    }

    // Recursive method to perform inorder traversal of the AVL tree
    /**
     * Recursive method to perform an inorder traversal of the AVL tree.
     *
     * @param node the root of the current subtree
     * @param Word the word to search for during traversal
     */
    private void inorderTraversalRec(AVLNode node, String Word) {
        if (node != null) {
            inorderTraversalRec(node.left, Word);
            String[] Statement = node.key.split("\t");
            String KeyWord = Statement[0];
            Boolean Stat = null;
            if (Word.equals(KeyWord)) {
                System.out.println("The Term " + Word + " Was Found");
                Stat = true;
            }
            inorderTraversalRec(node.right, Word);
        }
    }

    // Method to search for a key in the AVL tree
    /**
     * Searches for a key in the AVL tree.
     *
     * @param key the key to search for
     * @return a message indicating whether the term was found or not
     */
    public String search(String key) {
        // Increment search operation count
        searchOpCount++;
        return searchRec(root, key);
    }

    // Recursive method to search for a key in the AVL tree
    /**
     * Recursive method to search for a key in the AVL tree.
     *
     * @param node the root of the current subtree
     * @param key  the key to search for
     * @return a message indicating
*/
private String searchRec(AVLNode node, String key) {
    // Base case: If the node is null, the key is not found
    if (node == null)
        return "The Term " + key + " Was Not Found!";

    String Line = (node.key);
    Scanner scan = new Scanner(Line).useDelimiter("\t");
    // Compare the key with the current node's key
    int cmp = key.compareTo(scan.next());

    // If the key matches the current node's key, return true
    if (cmp == 0)
        return "The term " + key + " Was Found";

    // If the key is less than the current node's key, search in the left subtree
    else if (cmp < 0){
        searchOpCount++; // Increment comparison count
        return searchRec(node.left, key);
    }
    // If the key is greater than the current node's key, search in the right subtree
    else{
        searchOpCount++;
        return searchRec(node.right, key);}
    }

    // Method to report the value of counters
    public void reportCounters() {
        System.out.println("Search operations performed: " + searchOpCount);
        System.out.println("Insert operations performed: " + insertOpCount);
    }
}