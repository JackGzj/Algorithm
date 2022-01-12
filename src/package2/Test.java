package package2;

import java.util.HashMap;
import java.util.Scanner;

public class Test
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String[] input = in.nextLine().split(";");
            String a = input[0], b = input[1];
            if (a.length() != b.length()) {
                // length not equals
                System.out.println("False");
                continue;
            }
            HashMap<Character, Character> map = new HashMap<Character, Character>();
            boolean isSame = true;
            for (int i = 0; i < a.length(); i ++) {
                char ca = a.charAt(i), cb = b.charAt(i);
                if (map.containsKey(ca)) {
                    // contain, compare
                    if (cb != map.get(ca)) {
                        System.out.println("False");
                        isSame = false;
                        break;
                    }
                    // else: continue
                }
                else {
                    // not contain, put
                    map.put(ca, cb);
                }
            }
            if (isSame) {
                System.out.println("True");
            }
        }
        in.close();
    }
}