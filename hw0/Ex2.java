public class Ex2 {
    public static int max(int[] m) {
        int rv = -2147483647;
        for (int i = 0; i < m.length; i = i + 1) {
            if (m[i] > rv) {
                rv = m[i];
            }
        }
        return rv;
    }
    public static void main (String[] Args){
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println("the max value of the array is " + max(numbers));
    }
}
