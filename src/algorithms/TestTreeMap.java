package algorithms;

import java.util.TreeMap;

public class TestTreeMap {
    public static void main(String[] args) {
        TreeMap<A, Integer> treeMap = new TreeMap<>();
        treeMap.put(new A(1), 1);
        treeMap.put(new A(2), 2);
        treeMap.put(new A(1), 3);
        System.out.println(treeMap);
    }


    static class A implements Comparable {
        int k;

        public A(int k) {
            this.k = k;
        }

        @Override
        public boolean equals(Object obj) {
            return this.k == ((A)obj).k;
        }

        @Override
        public int hashCode() {
            return 5;
        }

        @Override
        public String toString() {
            return String.valueOf(k);
        }

        @Override
        public int compareTo(Object o) {
            return this.k - ((A)o).k;
        }
    }
}
