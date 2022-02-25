package jianzhioffer.linkedlist;

public class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node(){ }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" + "val=" + val + '}';
    }
}
