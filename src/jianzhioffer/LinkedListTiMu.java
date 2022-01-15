package jianzhioffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LinkedListTiMu {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node3;
        node3.next = node4;
        node4.next = node1;
        insert(node3, 0);
    }

    public static void testNode() {
        Node node12 = new Node(12, null);
        Node node11 = new Node(11, node12);
        node12.prev = node11;

        Node node10 = new Node(10, null);
        Node node9 = new Node(9, node10);
        Node node8 = new Node(8, node9);
        node8.child = node11;
        Node node7 = new Node(7, node8);
        node10.prev = node9;
        node9.prev = node8;
        node8.prev = node7;

        Node node6 = new Node(6, null);
        Node node5 = new Node(5, node6);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        node3.child = node7;
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        node6.prev = node5;
        node5.prev = node4;
        node4.prev = node3;
        node3.prev = node2;
        node2.prev = node1;
        traverse(node1);
        System.out.println("=====================");
        traverse(flatten(node1));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode i = head, j = head, pre = head;
        for (int k = 0; k < n; k++) {
            i = i.next;
        }
        System.out.println(i);
        if (i == null) {
            // move head
            pre = j.next;
//            System.out.println(pre + ", " + pre.next);
            return pre;
        }
        while (i != null) {
            i = i.next;
            pre = j;
            j = j.next;
        }
//        System.out.println("remove: " + j);
        pre.next = j.next;
        return head;
    }

    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ArrayList<Integer> n1 = new ArrayList<>(), n2 = new ArrayList<>(), res = new ArrayList<>();
        while (l1 != null) {
            n1.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            n2.add(l2.val);
            l2 = l2.next;
        }
        // ensure n1 longer then n2
        if (n1.size() < n2.size()) {
            ArrayList tmp = n1;
            n1 = n2;
            n2 = tmp;
        }
        // sum same part
        int jinWei = 0, tmp, diff = n1.size() - n2.size();
        for (int i = n2.size() - 1; i >= 0; i--) {
            tmp = jinWei + n1.get(i + diff) + n2.get(i);
//            System.out.println(n1.get(i + diff) + ", " + n2.get(i) + ", " + tmp + ", " + jinWei);
            if (tmp > 9) {
                jinWei = tmp / 10;
                tmp %= 10;
            } else {
                jinWei = 0;
            }
            res.add(tmp);
        }
        for (int i = diff - 1; i >= 0; i--) {
            tmp = jinWei + n1.get(i);
            if (tmp > 9) {
                jinWei = tmp / 10;
                tmp %= 10;
            } else {
                jinWei = 0;
            }
            res.add(tmp);
        }
        if (jinWei > 0) {
            res.add(jinWei);
        }
//        System.out.println(res);
        if (res.size() == 1) {
            return new ListNode(res.get(0));
        }
        ListNode pre = new ListNode(res.get(res.size() - 1)), temp = null, head = pre;
        for (int i = res.size() - 2; i >= 0; i--) {
            temp = new ListNode(res.get(i));
            pre.next = temp;
            pre = temp;
        }
        return head;
    }
    
    /**
     * 剑指 Offer II 026. 重排链表
     * https://leetcode-cn.com/problems/LGjMqU/
     * @param head
     */
    public static void reorderList(ListNode head) {
        if (head.next == null) {
            return;
        }
        ListNode n = head;
        List<ListNode> list = new ArrayList<>();
        while(n != null) {
            list.add(n);
            n = n.next;
        }
        System.out.println(list);
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i++).next = list.get(j);
            if (i == j) {
                break;
            }
            list.get(j--).next = list.get(i);
        }
        list.get(i).next = null;
        traverse(head);
    }

    /**
     * 剑指 Offer II 024. 反转链表
     * https://leetcode-cn.com/problems/UHnkqh/
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null, tmp = head, next;
        while (tmp != null) {
            next = tmp.next;
            tmp.next = pre;
            pre = tmp;
            tmp = next;
        }
        return pre;
    }

    /**
     * 剑指 Offer II 027. 回文链表
     * https://leetcode-cn.com/problems/aMhZSa/
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            if (!list.get(i).equals(list.get(list.size() - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 剑指 Offer II 028. 展平多级双向链表
     * https://leetcode-cn.com/problems/Qv1Da2/
     * @param head
     * @return
     */
    public static Node flatten(Node head) {
        Node n;
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.empty() && (n = stack.pop()) != null) {
            if (n.next != null) {
                stack.push(n.next);
            }
            if (n.child != null) {
                stack.push(n.child);
                n.next = n.child;
                n.child.prev = n;
                n.child = null;
            }
            if (n.next == null && n.child == null && stack.size() > 0) {
                // 与栈顶节点相连
                Node tmp = stack.get(stack.size() - 1);
                n.next = tmp;
                tmp.prev = n;
            }
        }
        return head;
    }

    /**
     *
     * @param head
     * @param insertVal
     * @return
     */
    public static ListNode insert(ListNode head, int insertVal) {
        if (head == null) {
            return new ListNode(insertVal);
        }
        // find min
        int min = head.val, max = head.val;
        ListNode minH = head, maxH = head, first = head;
        head = head.next;
        while (!head.equals(first)) {
            if (head.val < min) {
                min = head.val;
                minH = head;
            } else if (head.val > max) {
                max = head.val;
                maxH = head;
            }
            head = head.next;
        }
//        System.out.println(min + ", " + max);
//        System.out.println(minH + ", " + maxH);
        if (insertVal < min || insertVal > max) {
//            System.out.println("head insert point: " + minH + ", " + minH.next);
            // 在min前插入，即 maxH 后 minH 前
            ListNode n = new ListNode(insertVal, maxH.next);
            maxH.next = n;
        } else {
            while (minH.val < insertVal && minH.next.val > insertVal) {
//                System.out.println("insert point: " + minH + ", " + minH.next);
                ListNode n = new ListNode(insertVal, minH.next);
                minH.next = n;
                minH = minH.next;
            }
        }
        return head;
    }

    public static void traverse(ListNode node) {
        for (; node != null; node = node.next) {
            System.out.print(node + " ");
        }
        System.out.println();
    }

    public static void traverse(Node node) {
        Node childHead = null;
        for (; node != null; node = node.next) {
            System.out.print(node + " ");
            if (node.child != null) {
                childHead = node.child;
            }
        }
        System.out.println();
        while (childHead != null) {
            node = childHead;
            childHead = null;
            for (; node != null; node = node.next) {
                System.out.print(node + " ");
                if (node.child != null) {
                    childHead = node.child;
                }
            }
            System.out.println();
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + "val=" + val + '}';
        }
    }

    static class Node {
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
}
