import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * The ExperimentRunner class conducts experiments to investigate the operational complexities of AVL binary trees.
 * It generates random datasets of various sizes and performs insertion and search operations on an AVL tree,
 * reporting the operation counts for each dataset size.
 * @author Lalelani Eddie Nene
 */
public class ExperimentRunner {

    /**
     * The main method runs the experiment by generating datasets of different sizes,
     * inserting them into an AVL tree, performing search operations, and reporting operation counts.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Random random = new Random();

        // Vary dataset sizes logarithmically
        int[] dataSizes = {1, 10, 100, 1000, 10000, 100000};

        // Run experiments for each dataset size
        for (int dataSize : dataSizes) {
            // Generate a random dataset of size 'dataSize'
            String[] dataset = generateRandomDataset(dataSize);

            // Insert the dataset into the AVL tree
            for (String data : dataset) {
                tree.insert(data);
            }

            // Run fixed queries on the tree to simulate search operations
            for (int i = 0; i < 100; i++) {
                String query = generateRandomQuery(dataset);
                tree.search(query);
            }

            // Report operation counts for the current dataset size
            tree.reportCounters();
        }
    }

    /**
     * Generates a random dataset of the given size.
     *
     * @param size The size of the dataset to generate.
     * @return An array of strings representing the random dataset.
     */
    private static String[] generateRandomDataset(int size) {
        String[] dataset = new String[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            dataset[i] = "Data" + random.nextInt(10000); // Example random data generation
        }
        return dataset;
    }

    /**
     * Generates a random query from the provided dataset.
     *
     * @param dataset The dataset from which to generate the query.
     * @return A randomly selected query from the dataset.
     */
    private static String generateRandomQuery(String[] dataset) {
        Random random = new Random();
        return dataset[random.nextInt(dataset.length)];
    }
    /**
     * Saves operation counts to a text file.
     *
     * @param dataSize     The size of the dataset.
     * @param insertCount  The number of insert operations.
     * @param searchCount  The number of search operations.
     */
    private static void saveOperationCountsToFile(int dataSize, int insertCount, int searchCount) {
        try {
            FileWriter writer = new FileWriter("operation_counts_" + dataSize + ".txt");
            writer.write("Dataset Size: " + dataSize + "\n");
            writer.write("Insert Operations: " + insertCount + "\n");
            writer.write("Search Operations: " + searchCount + "\n");
            System.out.println("File path: " + new File("operation_counts_" + dataSize + ".txt").getAbsolutePath());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
