package leetcode.component;

/**
 * @Description
 * @Author 01415355
 * @Date 2022/10/13 9:44
 */
public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
