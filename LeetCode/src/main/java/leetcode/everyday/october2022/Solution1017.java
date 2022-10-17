package leetcode.everyday.october2022;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description：
 * 904. 水果成篮
 *
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
 *
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
 *
 * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
 * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
 * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
 * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
 *
 *示例 1：
 * 输入：fruits = [1,2,1]
 * 输出：3
 * 解释：可以采摘全部 3 棵树。
 * 示例 2：
 *
 * 输入：fruits = [0,1,2,2]
 * 输出：3
 * 解释：可以采摘 [1,2,2] 这三棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
 * 示例 3：
 *
 * 输入：fruits = [1,2,3,2,2]
 * 输出：4
 * 解释：可以采摘 [2,3,2,2] 这四棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
 * 示例 4：
 *
 * 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
 * 输出：5
 * 解释：可以采摘 [1,2,1,1,2] 这五棵树。
 *
 *
 * @Author 01415355
 * @Date 2022/10/17 15:07
 */
public class Solution1017 {

    /**
     * 解法一： 通过map来存储水果和水果个数，通过判断当前是否存在超过两个水果，来判断左指针移动界限，通过移动左指针来制造满足
     * 条件的max
     * @param fruits
     * @return
     */
    public int totalFruit(int[] fruits) {
        Map<Integer,Integer> exists = new HashMap();
        int max = -1;
        for(int i = 0,j=-1; i < fruits.length; i++){
            exists.put(fruits[i],exists.getOrDefault(fruits[i],0) + 1);
            while(exists.size() > 2){
                j++;
                if(exists.get(fruits[j]) == 1) exists.remove(fruits[j]);
                else exists.put(fruits[j],exists.get(fruits[j])-1);
            }
            max = Math.max(i-j,max);
        }
        return max;
    }
    /**
     * 滑动窗口：
     * 优化算法(将map判断改为数组判断,并且加一个count标记类型个数参数)，将map改为fruits数组，
     * 每次通过count记录当前果树类型个数，通过mark标记已有类型个数，当前个数超过2则left
     * 指针移动，直到count等于2时停止，然后继续移动right指针，当count>2停止，依次执行，直到结束，则max为最大长度.
     *
     */
    public int totalFruit1(int[] fruits) {
        int[] mark = new int[fruits.length];
        int left = 0,count = 0, max = 0;
        for(int right = 0; right < fruits.length; right++){
            if(mark[fruits[right]] == 0) count++;
            mark[fruits[right]]++;
            while(count > 2){
                if(mark[fruits[left]] == 1) count--;
                mark[fruits[left]]--;
                left++;
            }
            max = Math.max(max,right -left+1);
        }
        return max;
    }

}
