package leetcode.everyday.october2022;

/**
 * 1784. 检查二进制字符串字段
 *
 * 给你一个二进制字符串 s ，该字符串 不含前导零 。
 *
 * 如果 s 包含 零个或一个由连续的 '1' 组成的字段 ，返回 true​​​ 。否则，返回 false 。
 *
 * 如果 s 中 由连续若干个 '1' 组成的字段 数量不超过 1，返回 true​​​ 。否则，返回 false 。
 *
 * 示例 1：
 *
 * 输入：s = "1001"
 * 输出：false
 * 解释：由连续若干个 '1' 组成的字段数量为 2，返回 false
 * 示例 2：
 *
 * 输入：s = "110"
 * 输出：true
 *
 */
public class Solution1003 {

    public boolean checkOnesSegment(String s) {
        boolean existsOne = false;
        int i = 0;
        while(i < s.length()){
            if(s.charAt(i++) == '1'){
                if(existsOne) return false;
                while(i < s.length() && s.charAt(i++) == '1'){
                    i++;
                }
                existsOne = true;
            }
        }
        return true;
    }


    /**
     *  解法二：因为题目所知，字符串前导零不存在，因此字符串前置为1,因此
     *  只需判断后置是否有01,即可知道是否正确
     */

    public boolean checkOnesSegment1(String s) {
        return !s.contains("01");
    }

    public static void main(String[] args) {
        Class clz = Solution1003.class;
        System.out.println(clz.isAssignableFrom(Object.class));
    }
}
