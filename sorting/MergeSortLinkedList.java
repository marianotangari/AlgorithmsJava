/** Leetcode challenge : https://leetcode.com/problems/sort-list
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class MergeSortLinkedList {

    public ListNode sortList(ListNode head) {

        if (head == null)
            return null;

        return mergesort(head);
    }

    private ListNode mergesort(ListNode node) {

        if (node.next != null) {

            ListNode mid = getMid(node);

            node = mergesort(node);
            mid = mergesort(mid);

            return merge(node, mid);
        }
        return node;
    }

    private ListNode getMid(ListNode head) {

        ListNode slow = head, fast = head;

        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        fast = slow.next;
        slow.next = null;

        return fast;
    }

    public ListNode merge(ListNode list1, ListNode list2) {

        ListNode head, tail;

        if (list1.val < list2.val) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }

        tail = head;

        while (list1 != null && list2 != null) {

            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        tail.next = (list1 != null) ? list1 : list2;

        return head;
    }
}