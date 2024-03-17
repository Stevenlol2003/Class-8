import java.util.*;

public class FF {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numInstances = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numInstances; i++) {
            int cacheSize = Integer.parseInt(sc.nextLine());
            int numRequests = Integer.parseInt(sc.nextLine());
            String stringPages = sc.nextLine();
            String[] requestsString = stringPages.split(" ");
            int[] requests = new int[numRequests]; // page requests
            for (int j = 0; j < requestsString.length; j++) {
                requests[j] = Integer.parseInt(requestsString[j]); // changes String to Int
            }
            System.out.println(countPageFaults(cacheSize, requests));
        }

        sc.close();
    }

    private static int countPageFaults(int cacheSize, int[] requests) {
        int pageFaults = 0;
        Set<Integer> cache = new HashSet<>();
        Map<Integer, Integer> futureIndexes = new HashMap<>();

        for (int i = 0; i < requests.length; i++) {
            int page = requests[i];
            // If the page is not in the cache, it's a page fault
            if (!cache.contains(page)) {
                pageFaults++;
                // If the cache is not full, add the page to the cache
                if (cache.size() < cacheSize) {
                    cache.add(page);
                } else {
                    // Find the page in the cache that has the furthest future request index
                    int furthestFutureIndex = Integer.MIN_VALUE;
                    int pageToRemove = -1;
                    for (int cachedPage : cache) {
                        int futureIndex = futureIndexes.getOrDefault(cachedPage, Integer.MAX_VALUE);
                        if (futureIndex > furthestFutureIndex) {
                            furthestFutureIndex = futureIndex;
                            pageToRemove = cachedPage;
                        }
                    }
                    // Remove the page with the furthest future request index from the cache
                    cache.remove(pageToRemove);
                    futureIndexes.remove(pageToRemove);
                    // Add the new page to the cache
                    cache.add(page);
                }
            }
            // Update the future request index of the page in the cache
            futureIndexes.put(page, findNextIndex(page, i, requests));
        }
        return pageFaults;
    }

    private static int findNextIndex(int page, int startIndex, int[] requests) {
        for (int i = startIndex + 1; i < requests.length; i++) {
            if (requests[i] == page) {
                return i;
            }
        }

        return Integer.MAX_VALUE;
    }
}
