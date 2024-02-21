import java.util.*;

public class GenerateInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the maximum value (x) for the datasets: ");
        int maxVal = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Range for array sizes
        int minSize = 1000;
        int maxSize = 10000000;
        int step = 1000000;

        // Generate input data
        Map<Integer, int[]> inputMap = generateInputData(minSize, maxSize, step, maxVal);

        // Print generated input data
        for (Map.Entry<Integer, int[]> entry : inputMap.entrySet()) {
            System.out.println("Size: " + entry.getKey() + ", Data: " + Arrays.toString(entry.getValue()));
        }

        scanner.close(); // Close the scanner
    }

    public static Map<Integer, int[]> generateInputData(int minSize, int maxSize, int step, int maxVal) {
        Map<Integer, int[]> inputMap = new LinkedHashMap<>();
        Random random = new Random();

        for (int size = minSize; size <= maxSize; size += step) {
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = random.nextInt(maxVal) + 1; // Random integer in range [1, maxVal]
            }
            inputMap.put(size, data);
        }

        return inputMap;
    }
}
