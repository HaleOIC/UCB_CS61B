public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0, tot = 0;
        while (x < 46) {
            System.out.print(x + " ");
            x = x + tot;
            tot += 1;
        }
        System.out.print("\n");
    }
}