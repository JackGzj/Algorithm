import jianzhioffer.Array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ListNode l15 = new ListNode(5);
        ListNode l14 = new ListNode(4, l15);
        ListNode l13 = new ListNode(3, l14);
        ListNode l12 = new ListNode(2, l13);
        ListNode l11 = new ListNode(1, l12);
        ListNode l23 = new ListNode(9);
        ListNode l22 = new ListNode(8, l23);
        ListNode l21 = new ListNode(7, l22);
        ListNode head = addTwoNumbers(l11, l21);
        traverse(head);
    }

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

    public static void traverse(ListNode head) {
        while(head != null) {
            System.out.print(head + " ");
            head = head.next;
        }
        System.out.println();
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
            return "ListNode{" + "val=" + val + '}';
        }
    }
}
