package jianzhioffer;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LinkedListTiMu {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
//        ListNode node4 = new ListNode(3);
//        ListNode node5 = new ListNode(3);
        node1.next = node2;
//        node3.next = node4;
//        node4.next = node5;
        ListNode n = removeNthFromEnd(node1, 2);
        while (n != null) {
            System.out.print(n.val + " ");
            n = n.next;
        }
        System.out.println();
    }

    /**
     * 21. 合并两个有序链表
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1), ptr = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                ptr.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                ptr.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            ptr = ptr.next;
        }
        while (list1 != null) {
            ptr.next = new ListNode(list1.val);
            ptr = ptr.next;
            list1 = list1.next;
        }
        while (list2 != null) {
            ptr.next = new ListNode(list2.val);
            ptr = ptr.next;
            list2 = list2.next;
        }
        return head.next;
    }

    /**
     * 删除排序链表中的重复元素
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode tmp = head;
        while (tmp.next != null) {
            if (tmp.val == tmp.next.val) {
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }
        return head;
    }

    public static ListNode deleteAllDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode newHead = new ListNode(-128, head), cur = newHead;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return newHead.next;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = head, right = head;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        if (right == null) {
            // 删除头结点
            if (head.next != null) {
                return head.next;
            } else {
                return null;
            }
        }
        System.out.println(right);
        while(right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
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
