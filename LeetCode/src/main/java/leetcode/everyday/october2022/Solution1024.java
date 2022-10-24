package leetcode.everyday.october2022;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 *
 * @Author 01415355
 * @Date 2022/10/24 9:28
 */
public class Solution1024 {
    public static void main(String[] args) {
        Solution1024 solution1024 = new Solution1024();
        int i = solution1024.partitionDisjoint(new int[]{1,1});
        System.out.println(i);
    }
    public int partitionDisjoint(int[] nums) {
        int[] minArr = new int[nums.length];
        minArr[nums.length-1] = nums[nums.length-1];
        for(int i = nums.length-2; i >= 0; i--){
            minArr[i] = Math.min(minArr[i+1],nums[i]);
        }
        int leftMax = nums[0];
        for(int i = 0; i < nums.length-1; i++){
            leftMax = Math.max(leftMax,nums[i]);
            if(leftMax <= minArr[i+1]) return i+1;
        }
        return -1;
    }
}
