package leetcode.everyday.october2022;

import java.util.Arrays;

/**
 * @creater hpp
 * @Date 2022/10/8-21:59
 * @description:
 * 870. 优势洗牌
 * 给定两个大小相等的数组nums1和nums2，nums1相对于 nums2 的优势可以用满足nums1[i] > nums2[i]的索引 i的数目来描述。
 * 返回 nums1的任意排列，使其相对于 nums2的优势最大化。
 *示例 1：
 *
 * 输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * 输出：[2,11,7,15]
 * 示例 2：
 *
 * 输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
 * 输出：[24,32,8,12]
 *
 */
public class Solution1008 {
    /**
     * 解法1 ：
     * 通过排序当前数组，获取两个数组的索引下标排序数据，
     * 通过遍历当前index下标数组，如果当前下标i对应的值num1[index1[i]] > num2[index2[left]]
     * 代表在当前结果坐标 res[index2[left]]下，有num1[index1[i]]的值，大于num2，因此
     * res[index2[left]] = num1[index1[i]]
     * 如果小于，则将当前num1对应的数，放到num2中最后一位（田忌赛马) 即 num2最大值匹配num1最小值
     * res[index[right]] = num1[index[i]]
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        Integer[] index1 = new Integer[nums1.length];
        Integer[] index2 = new Integer[nums2.length];
        int[] res = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            index2[i] = index1[i] = i;
        }
        Arrays.sort(index1,(o1,o2)->nums1[o1] - nums1[o2]);
        Arrays.sort(index2,(o1,o2)->nums2[o1] - nums2[o2]);
        int left = 0, right = nums1.length - 1;
        for(int i = 0; i < nums1.length; i++){
            if(nums1[index1[i]] > nums2[index2[left]]){
                res[index2[left++]] = nums1[index1[i]];
            }else{
                res[index2[right--]] = nums1[index1[i]];
            }
        }
        return res;
    }

    /**
     *  优化算法2：
     *  本身需要遍历对比的只有num2，因此，只排序num2的index和num1的数，
     *  遍历num1，重复上述算法1情况即可。
     *
     */
    public int[] advantageCount1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Integer[] index2 = new Integer[n];
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            index2[i] = i;
        }
        Arrays.sort(nums1);
        Arrays.sort(index2,(o1,o2)->nums2[o1] - nums2[o2]);
        int left = 0, right = n - 1;
        for(int num : nums1){
          if(num > nums2[index2[left]]){
              res[index2[left++]] = num;
          }else{
              res[index2[right--]] = num;
          }
        }
        return res;
    }
    /**
     *  算法3：
     *  1 先排序nums1
     *  2 再定义使用的boolean类型数组，用于记录哪些数被使用了
     *  3 当num1中所有数都比当前num2数小时，分配到最大那位数上
     */
    public int[] advantageCount3(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] res = new int[n];
        boolean[] isUsed = new boolean[nums1.length];
        Arrays.sort(nums1);
        for(int i = 0; i < nums2.length; i++){
            int left = 0, right = n;
            while(left != right){
                int mid = left + ((right - left) >> 1);
                if(nums1[mid] > nums2[i]){
                    right = mid;
                }else{
                    left = mid + 1;
                }
            }

            while(left < n && (isUsed[left] || nums1[left] == nums2[i])){
                left++;
            }
            if(left == nums1.length){
                int j = 0;
                while(j < nums1.length && isUsed[j]){
                    j++;
                }
                res[i] = nums1[j];
                isUsed[j] = true;
            }else{
                res[i] = nums1[left];
                isUsed[left] = true;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution1008 solution1008 = new Solution1008();
        int[] ints = solution1008.advantageCount3(new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11});
        System.out.println(Arrays.toString(ints));
    }

}
