public class Ex1 {	
    /**
     * draw a triangle like
     * *
     * **
     * ***
     * ****
     * *****
     * @N is the total height of the tower 
     */
    private static void DrawTriangle(int N) {
        for (int i = 0; i < N; i = i + 1){
            for (int j = 0; j < i + 1; j = j + 1){
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }
    public static void main(String[] args) {
        DrawTriangle(10);
    }
}
