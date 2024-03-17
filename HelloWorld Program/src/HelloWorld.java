import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lines = sc.nextInt();
        String[] strings = new String[lines];
        int k = 0;
        sc.nextLine();
        while (lines > 0) {
            strings[k] = sc.nextLine();
            k++;
            lines--;
        }
        for (int i = 0; i < strings.length; i++) {
            System.out.println("Hello, " + strings[i] + "!");
        }
    }
}
