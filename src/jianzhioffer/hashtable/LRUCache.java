package jianzhioffer.hashtable;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    int capacity;
    Node head, tail;
    private Map<Integer, Node> cache;

    private void deleteNode(Node n) {
//        System.out.println("before delete: ");
//        traverse();
        n.next.pre = n.pre;
        n.pre.next = n.next;
    }

    private void addToHead(Node n){
        head.next.pre = n;
        n.next = head.next;
        head.next = n;
        n.pre = head;
    }

    private void moveToHead(Node n) {
        deleteNode(n);
        addToHead(n);
    }

    private void traverse() {
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.k + " ");
            tmp = tmp.next;
        }
        System.out.println("----------");
    }

    private void traverseFromTail() {
        Node tmp = tail;
        while (tmp != null) {
            System.out.print(tmp.k + " ");
            tmp = tmp.pre;
        }
        System.out.println();
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        cache = new HashMap<>((int) (capacity / 0.75));
    }

    public int get(int key) {
        Node n = cache.get(key);
        if (n != null) {
            moveToHead(n);
            return n.v;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node n = cache.get(key);
        if (n != null) {
            n.v = value;
            cache.put(key, n);
            moveToHead(n);
        } else {
            if (cache.size() == capacity) {
                n = tail.pre;
//                tail = tail.pre;
                deleteNode(n);
                cache.remove(n.k);
                System.out.println("remove: " + n.k);
            }
            n = new Node(key, value);
            cache.put(key, n);
            addToHead(n);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(3, 2);
        System.out.println("===============");
        cache.traverse();

        System.out.println(cache.get(3));
//        System.out.println("===============");
//        cache.traverse();
        System.out.println(cache.get(2));

//        System.out.println("===============");
//        cache.traverse();
        cache.put(4, 3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    static class Node {
        int k, v;
        Node pre, next;

        public Node() {

        }

        public Node(int k, int v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public String toString() {
            return "Node{" + "k=" + k + ", v=" + v + '}';
        }
    }
}
