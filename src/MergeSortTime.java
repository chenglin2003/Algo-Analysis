public class MergeSortTime {
    static int keyCmp;

    static final int TEST_SIZE = 10000000;
    static final int TEST_VALUE = 1000;
    static final int ITER = 10; // number of each test to be averaged

    // same function as MergeSortOriginal
    public static void sortIt(int[] anyArray, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;

            sortIt(anyArray, start, mid);
            sortIt(anyArray, mid + 1, end);

            merge(anyArray, start, mid, end);
        }
    }

// same function as MergeSortOrginal, but now we add in our keyCmp counter
    public static void merge(int[] anyArray, int start, int mid, int end) {

        int n1 = mid - start + 1;
        int n2 = end - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = anyArray[start + i];
        for (int j = 0; j < n2; ++j)
            R[j] = anyArray[mid + 1 + j];

        int i = 0, j = 0;
        int k = start;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                anyArray[k] = L[i];
                i++;
            } else {
                anyArray[k] = R[j];
                j++;
            }
            // while we have not reached the end for both halves, 
            //an additional key comparison is needed to compare the  2 elements, L[i] and R[j]
            //hence, keyCmp increases by one everytime we run this while loop
            keyCmp++;
            k++;
        }
        while (i < n1) {
            anyArray[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            anyArray[k] = R[j];
            j++;
            k++;
        }

    }

    public static long[] getMergeSortKeyCmpandTime() {
        long[] result = new long[2]; //result[0] is keyCmp, result[1] is CPU time
        long averageKeyCmp = 0;
        long averageTime = 0;
        for (int i = 0; i < ITER; i++) {
            System.out.println("Testing trial (MergeSort) " + (i+1) + " ... ");
            keyCmp = 0;
            int[] testArr = GenerateInput.generateRandom(TEST_SIZE, TEST_VALUE);
            long t0 = System.currentTimeMillis();
            sortIt(testArr, 0, testArr.length - 1);
            averageTime+=System.currentTimeMillis()-t0;
            averageKeyCmp += keyCmp;
        }
        averageKeyCmp /= ITER;
        averageTime /= ITER;
        result[0] = averageKeyCmp;
        result[1] = averageTime;
        return result;

    }

    public static void main(String[] args) {
        getMergeSortKeyCmpandTime();
    }
}

