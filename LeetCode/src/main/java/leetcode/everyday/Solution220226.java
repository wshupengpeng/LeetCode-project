package leetcode.everyday;


import java.util.Arrays;

/**
 * 2016. 增量元素之间的最大差值
 */
public class Solution220226 {

    public static void main(String[] args) {
        String str = "[7,1,5,4]";
        String[] split = str.replaceAll("[\\[\\]]", "").split(",");
        int [] arr = new int[split.length];
        for(int i = 0; i < split.length; i++){
            arr[i] = Integer.valueOf(split[i]);
        }
        Solution220226 solution220226 = new Solution220226();
        int i = solution220226.maximumDifference(arr);
        System.out.println(i);
    }
    public int maximumDifference(int[] nums) {
        int step = -1;
        int preMain = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > preMain){
                step = Math.max(step,nums[i]-preMain);
            }else{
                preMain = nums[i];
            }
        }
        return step;
    }
}
