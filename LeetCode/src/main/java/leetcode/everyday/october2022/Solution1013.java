package leetcode.everyday.october2022;

import leetcode.component.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * 817. 链表组件
 * 给定链表头结点head，该链表上的每个结点都有一个 唯一的整型值 。同时给定列表nums，该列表是上述链表中整型值的一个子集。
 *
 * 返回列表 nums 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表nums中）构成的集合。
 * 示例1：
 * 输入: head = [0,1,2,3], nums = [0,1,3]
 * 输出: 2
 * 解释: 链表中,0 和 1 是相连接的，且 nums 中不包含 2，所以 [0, 1] 是 nums 的一个组件，同理 [3] 也是一个组件，故返回 2。
 * 示例 2：
 * 输入: head = [0,1,2,3,4], nums = [0,3,1,4]
 * 输出: 2
 * 解释: 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 *
 *
 *
 *
 *  答案
 * @Author 01415355
 * @Date 2022/10/13 9:40
 */
public class Solution1013 {
    /**
     * 解题方法1：
     * 通过读题可知，nums中含有的元素为有效元素，当出现无效元素时，即当前位置为一个分割点，当无效元素连续出现时，也为一个分割点。
     * 只有无效元素不连续出现，才会有多个分割点。
     * 因此通过set获取所有有效元素的值，通过遍历链表，找到分割点，通过标记为isSub判断当前是否为需要被统计，如果当前元素在set列表中
     * 且isSub处于未统计状态，则统计一次。如果当前元素不在set中，则将isSub标记为未统计，等待下一次统计。
     * @param head
     * @param nums
     * @return
     */
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet();
        for(int num : nums){
            set.add(num);
        }

        int res = 0;
        boolean isSub = true;
        while(head != null){
            if(set.contains(head.val)){
                if(isSub){
                    isSub = false;
                    res++;
                }
            }else{
                isSub = true;
            }
            head = head.next;
        }
        return res;
    }
}
