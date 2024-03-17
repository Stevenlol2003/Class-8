import java.util.*;
import java.util.Random;

public class Randomization {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<int[]> clauses = new ArrayList<int[]>();
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            clauses.add(new int[] {x, y, z});
        }
        int[] bestAssignment = null;
        int bestScore = -1;
        int numRepetitions = 20 * m;
        for (int i = 0; i < numRepetitions; i++) {
            int[] assignment = randomAssignment(n);
            int score = scoreAssignment(assignment, clauses);
            if (score > bestScore) {
                bestAssignment = assignment;
                bestScore = score;
            }
            if (bestScore >= (7 * m / 8)) {
                break;
            }
        }
        if (bestScore < (7 * m / 8)) {
            System.out.println(arrayToString(bestAssignment));
        } else {
            System.out.println(arrayToString(bestAssignment));
        }
    }

    public static int[] randomAssignment(int n) {
        int[] assignment = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            assignment[i] = rand.nextInt(2) == 0 ? -1 : 1;
        }
        return assignment;
    }

    public static int scoreAssignment(int[] assignment, List<int[]> clauses) {
        int score = 0;
        for (int[] clause : clauses) {
            if (isClauseSatisfied(assignment, clause)) {
                score++;
            }
        }
        return score;
    }

    public static boolean isClauseSatisfied(int[] assignment, int[] clause) {
        boolean isSatisfied = false;
        for (int i = 0; i < 3; i++) {
            int literal = clause[i];
            if ((literal > 0 && assignment[literal - 1] > 0)
                    || (literal < 0 && assignment[-literal - 1] < 0)) {
                isSatisfied = true;
                break;
            }
        }
        return isSatisfied;
    }

    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int x : arr) {
            sb.append(x + " ");
        }
        return sb.toString().trim();
    }
}
