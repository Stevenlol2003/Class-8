import java.util.*;

public class InversionCount {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numInstances = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numInstances; i++) {
            int numElements = Integer.parseInt(sc.nextLine());
            String stringIntegers = sc.nextLine();
            String[] requestsString = stringIntegers.split(" ");
            int[] requests = new int[numElements];
            for (int j = 0; j < requestsString.length; j++) {
                requests[j] = Integer.parseInt(requestsString[j]); // changes String to Int
            }
            System.out.println(mergeSortAndCount(requests, 0, requests.length - 1));
        }

        sc.close();
    }

    private static int mergeAndCount(int[] array, int left, int mid, int right) {
        // left subarray
        int[] leftArray = Arrays.copyOfRange(array, left, mid + 1);

        // right subarray
        int[] rightArray = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0;
        int j = 0;
        int k = left;
        int count = 0;

        // merging the left and right
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                k++;
                i++;
            }
            else {
                array[k++] = rightArray[j++];
                count += (mid + 1) - (left + i);
            }
        }
        while (i < leftArray.length) {
            array[k] = leftArray[i];
            k++;
            i++;
        }
        while (j < rightArray.length) {
            array[k] = rightArray[j];
            k++;
            j++;
        }
        return count;
    }

    // Merge sort
    private static int mergeSortAndCount(int[] array, int left, int right) {

        // Keeps track of the inversion count at a particular node of the recursion tree
        int count = 0;

        if (left < right) {
            int mid = (left + right) / 2;

            // Total inversion count = left subarray count + right subarray count + merge count

            // Left subarray count
            count += mergeSortAndCount(array, left, mid);

            // Right subarray count
            count += mergeSortAndCount(array, mid + 1, right);

            // Merge count
            count += mergeAndCount(array, left, mid, right);
        }

        return count;
    }
}
