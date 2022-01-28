import java.util.Random;

public class Test {

    public static void main(String[] args) {
        int num = 25;
        for (int k = 30; k >= 0; --k) {
            int bit = (num >> k) & 1;
            System.out.println(bit);
        }
    }
}
