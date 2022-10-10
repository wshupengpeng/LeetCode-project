package leetcode.everyday.october2022;

/**
 * @creater hpp
 * @Date 2022/10/10-22:51
 * @description:
 * 我们有两个长度相等且不为空的整型数组nums1和nums2。在一次操作中，我们可以交换nums1[i]和nums2[i]的元素。
 *
 * 例如，如果 nums1 = [1,2,3,8] ， nums2 =[5,6,7,4] ，你可以交换 i = 3 处的元素，得到 nums1 =[1,2,3,4] 和 nums2 =[5,6,7,8] 。
 * 返回 使 nums1 和 nums2 严格递增所需操作的最小次数 。
 *
 * 数组arr严格递增 且arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1]。
 *
 * 注意：
 *
 * 用例保证可以实现操作。
 *
 * 示例 1:
 * 输入: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
 * 输出: 1
 * 解释:
 * 交换 A[3] 和 B[3] 后，两个数组如下:
 * A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
 * 两个数组均为严格递增的。
 * 示例 2:
 * 输入: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
 * 输出: 1

 */
public class Solution1010 {
    public int minSwap(int[] nums1, int[] nums2) {
        // 动态规划算法：
        // 假设在i下标下dp[i][0] 代表当前下标不叫唤,dp[i][1]代表当前下标交换，dp[0][0]= 0 代表第一个数不交换次数,dp[0][1] = 1代表第一个数交换的次数，
        // 初始化当前dp数组
        int[][] dp = new int[nums1.length][2];
        dp[0][0] = 0;
        dp[0][1] = 1;

        for(int i = 1; i < nums1.length; i++){
            // 当满足 nums1[i] > nums1[i-1] 且 nums1[i] > nums2[i-1] 且 nums2[i] > nums2[i-1] 且 nums2[i] > num1[i-1]时
            // 当前i下标交换与不交换都满足条件
            if(nums1[i] > nums1[i-1] && nums2[i] > nums2[i-1] && nums1[i] > nums2[i-1] && nums2[i] > nums1[i-1]){
                // 此时当前下标i不交换次数取上一个下标交换和不交换中最小的那个
                dp[i][0] = Math.min(dp[i-1][0],dp[i-1][1]);
                // 此时当前交换则取不交换次数+1
                dp[i][1] = dp[i][0] +1;
            }else if(nums1[i] > nums1[i-1] && nums2[i] > nums2[i-1]){  // 此时当nums1[i] > nums1[i-1] 且 nums2[i] > nums2[i-1] 时
                // 如果当前i不交换，则i-1也不需要交换
                dp[i][0] = dp[i-1][0];
                // 如果当前i交换，则i-1也要交换，若
                dp[i][1] = dp[i-1][1] + 1;
            }else{
                // 如果i不交换，则i-1交换
                dp[i][0] = dp[i-1][1];
                // 如果i交换，则i-1不交换
                dp[i][1] = dp[i-1][0]+1;
            }
        }
        return Math.min(dp[nums1.length-1][0],dp[nums1.length-1][1]);
    }
}
