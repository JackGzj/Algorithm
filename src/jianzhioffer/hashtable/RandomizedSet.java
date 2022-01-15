package jianzhioffer.hashtable;

import java.util.*;

//["RandomizedSet","insert","insert","getRandom","getRandom","insert","remove","getRandom","getRandom","insert","remove"]
//[[],[3],[3],[],[],[1],[3],[],[],[0],[0]]
public class RandomizedSet {
    Random random;
    List<Integer> list;
    Map<Integer, Integer> indexMap;

    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
//        System.out.println(set.insert(3));
//        System.out.println(set.insert(3));
//        System.out.println(set.getRandom());
//        System.out.println(set.getRandom());
//        System.out.println(set.insert(1));
//        System.out.println(set.remove(3));
//        System.out.println(set.getRandom());
//        System.out.println(set.getRandom());
//        System.out.println(set.insert(0));
//        System.out.println(set.remove(0));

        System.out.println(set.remove(0));
        System.out.println(set.remove(0));
        System.out.println(set.insert(0));
        System.out.println(set.getRandom());
        System.out.println(set.remove(0));
        System.out.println(set.insert(0));
    }

    /** Initialize your data structure here. */
    public RandomizedSet() {
        random = new Random();
        list = new ArrayList<>();
        indexMap = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        System.out.println("insert: " + indexMap);
        if (indexMap.containsKey(val)) {
            return false;
        }
        list.add(val);
        indexMap.put(val, list.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        System.out.println("remove: " + indexMap);
        if (indexMap.containsKey(val)) {
            int index = indexMap.get(val), lastNum = list.get(list.size() - 1);
            list.set(index, lastNum);
            list.remove(list.size() - 1);
            indexMap.put(lastNum, index);
            indexMap.remove(val);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        System.out.println("all: " + indexMap + ", " + list);
        return list.get(random.nextInt(list.size()));
    }
}
