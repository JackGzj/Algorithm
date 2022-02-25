public class Solution {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(3);
//        Node node4 = new Node(4);
//        Node node5 = new Node(6);
        node1.next = node2;
        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
        node3.next = node1;
        traverse(insert(node1, 4));
//        System.out.println("=========");
//        traverse(insert(null, 1));
//        System.out.println("=========");
//        Node n = new Node(1);
//        n.next = n;
//        traverse(insert(n, 1));
    }

    public static Node insert(Node head, int insertVal) {
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }
        // 找到 min 和 max 对应的节点，保证 minH 和 maxH 是相连的
        Node minH = head, maxH = head, first = head;
        head = head.next;
        while (!head.equals(first)) {
            if (head.val < minH.val) {
                minH = head;
            } else if (head.val >= maxH.val) {
                maxH = head;
            }
            head = head.next;
        }
        System.out.println(minH + ", " + maxH + "， " + maxH.next);
        if (insertVal <= minH.val || insertVal >= maxH.val) {
            System.out.println("head insert point: " + minH + ", " + minH.next);
            // 在min前插入，即 maxH 后 minH 前
            Node n = new Node(insertVal, maxH.next);
            maxH.next = n;
        } else {
            while (minH.val <= insertVal) {
                if (minH.next.val > insertVal) {
                    System.out.println("insert point: " + minH + ", " + minH.next);
                    Node n = new Node(insertVal, minH.next);
                    minH.next = n;
                    break;
                }
                minH = minH.next;
            }
        }
        return head;
    }

    static void traverse(Node h) {
        if (h == null) {
            return;
        }
        Node first = h;
        System.out.print(h + " ");
        h = h.next;
        while (!first.equals(h)) {
            System.out.print(h + " ");
            h = h.next;
        }
        System.out.println();
    }

    static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }

        @Override
        public String toString() {
            return "Node{" + "val=" + val + '}';
        }
    }
}
