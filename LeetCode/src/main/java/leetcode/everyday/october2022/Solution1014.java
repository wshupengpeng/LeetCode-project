package leetcode.everyday.october2022;

/**
 * @Description：
 * 769. 最多能完成排序的块
 *
 * 给定一个长度为 n 的整数数组 arr ，它表示在 [0, n - 1] 范围内的整数的排列。
 *
 * 我们将 arr 分割成若干 块 (即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。
 *
 * 返回数组能分成的最多块数量。
 *
 *
 * 示例 1:
 *
 * 输入: arr = [4,3,2,1,0]
 * 输出: 1
 * 解释:
 * 将数组分成2块或者更多块，都无法得到所需的结果。
 * 例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
 * 示例 2:
 *
 * 输入: arr = [1,0,2,3,4]
 * 输出: 4
 * 解释:
 * 我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
 * 然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
 *提示:
 *
 * n == arr.length
 * 1 <= n <= 10
 * 0 <= arr[i] < n
 * arr 中每个元素都 不同
 *
 * owner
 * @Author 01415355
 * @Date 2022/10/13 9:46
 */
public class Solution1014 {
    /**
     * 解法1： 由题可知，arr是[0,n-1]区间值，此时想要判断当前子区间是否是所取区间
     *     只需要判断，当前区间最大值max与当前区间最大数组下标i相等时，代表当前子区间可用
      */
    public int maxChunksToSorted(int[] arr) {
        int max = -1;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
            if (max == i) {
                res++;
            }
        }
        return res;
    }

    /**
     * 解法二：arr是[0,n-1]区间值，此时想要判断当前子区间sub是否是所取区间
     * 仅需要通过求和sub中的所有值sum  与sub中数组下标值 indexSum，如果两个值相同，代表当前子区间有效
     * @param arr
     * @return
     */
    public int maxChunksToSorted1(int[] arr) {
         int sum = 0, indexSum = 0, res =0;
         for(int i = 0; i < arr.length; i++){
             sum+=arr[i];
             indexSum+=i;
             if(sum == indexSum){
                 res++;
                 sum = 0;
                 indexSum =0;
             }
         }
         return res;
    }

}
