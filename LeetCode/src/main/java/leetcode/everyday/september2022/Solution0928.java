package leetcode.everyday.september2022;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @creater hpp
 * @Date 2022/9/29-21:21
 * @description: 面试题 17.09. 第 k 个数
 *
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 * 示例 1:
 *
 * 输入: k = 5
 *
 * 输出: 9
 */
public class Solution0928 {
    /**
     * 解题思路1：
     * 分析题目可知，所谓素因子为3,5,7说明当前数的k可以分解为3,5,7的倍数，而不可以被其他数而分解。
     * 并且按照题意可知，当前数需要被排序。
     *
     * 通过最小堆方式进行排序，即当前排序数为x,下一个排序数为 3x 5x 7x 中的最小值，每次一轮，直到结束当前k轮，即获取第一个数为要求的数
     * @param k
     * @return
     */
    public int getKthMagicNumber(int k) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        minHeap.add(1L);
        Set<Long> distinct = new HashSet<>();
        int[] baseNum = new int[]{3, 5, 7};
        for (int i = 2; i <= k; i++) {
            long poll = minHeap.poll();
            for (int num : baseNum) {
                long res = poll * num;
                if (distinct.add(res)) {
                    minHeap.offer(res);
                }
            }
        }
        return minHeap.poll().intValue();
    }

    /**
     * 算法二： 改进方案，因为算法1采用最小堆，而每次遍历都会加3*x，5*x，7*x，会添加多余的数到队列中，因此
     * 可以通过动态规划进行优化，即每次取当前最小的值为下一次需要计算的开始，因此有当前数x，其下一个数据为 Min(3*x,5*x,7*x)，
     * 当选中下一个数时，其对应的素数坐标+1，即参与下一次计算。
     */

    public int getKthMagicNumber1(int k) {
        int s1 = 0, s2 = 0, s3 = 0;
        int res[] = new int[k];
        res[0] = 1;
        for(int i = 1; i < k; i++){
            res[i] = Math.min(3 * res[s1], Math.min(5 * res[s2], 7 * res[s3]));
            if (res[i] == 3 * res[s1]) {
               s1++;
            }
            if (res[i] == 5 * res[s2]) {
                s2++;
            }
            if (res[i] == 7 * res[s3]) {
                s3++;
            }
        }
        return res[k-1];
    }

    public static void main(String[] args) {
        Solution0928 solution0928 = new Solution0928();
        int kthMagicNumber1 = solution0928.getKthMagicNumber1(10);
        System.out.println(kthMagicNumber1);
    }
}
