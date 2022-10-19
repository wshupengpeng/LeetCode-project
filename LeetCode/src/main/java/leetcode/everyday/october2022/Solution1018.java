package leetcode.everyday.october2022;

import java.util.Stack;

/**
 * @Description
 * @Author 01415355
 * @Date 2022/10/18 11:50
 *
 * 902. 最大为 N 的数字组合
 *
 * 给定一个按非递减顺序排列的数字数组digits。你可以用任意次数digits[i]来写的数字。例如，如果digits = ['1','3','5']，
 * 我们可以写数字，如'13','551', 和'1351315'。
 *
 * 返回 可以生成的小于或等于给定整数 n 的正整数的个数。
 *
 * 示例 1：
 *
 * 输入：digits = ["1","3","5","7"], n = 100
 * 输出：20
 * 解释：
 * 可写出的 20 个数字是：
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 * 示例 2：
 *
 * 输入：digits = ["1","4","9"], n = 1000000000
 * 输出：29523
 * 解释：
 * 我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
 * 81 个四位数字，243 个五位数字，729 个六位数字，
 * 2187 个七位数字，6561 个八位数字和 19683 个九位数字。
 * 总共，可以使用D中的数字写出 29523 个整数。
 * 示例 3:
 *
 * 输入：digits = ["7"], n = 8
 * 输出：1
 *
 */
public class Solution1018 {
    public static void main(String[] args) {
        Solution1018 solution1018 = new Solution1018();
        solution1018.atMostNGivenDigitSet(new String[]{"1","3","5","7"},100);
    }

    public int atMostNGivenDigitSet(String[] digits, int n) {
        String limit = Integer.toString(n);
        int res = 0;
        int k = digits.length;
        for (int i = 1; i < k; i++) res += Math.pow(k, i);
        boolean isCompareNext;
        for(int i = 0; i < limit.length(); i++){
            isCompareNext = false;
            for(String digit : digits){
                char c = digit.charAt(0);
                if(c < limit.charAt(i)){
                    res+= Math.pow(k,k-1-i);
                }else if(c == limit.charAt(i)){
                    isCompareNext = true;
                    break;
                }
            }
            if(!isCompareNext) return res;
        }
        return ++res;
    }


//    public int atMostNGivenDigitSet(String[] digits, int n) {
//        int[] baseNum = new int[10];
//        for(int i = 1; i <= digits.length; i++) baseNum[Integer.valueOf(digits[i-1])] = i;
//        Stack<Integer> stack = new Stack<>();
//        while (n > 0) {
//            stack.push(n % 10);
//            n /= 10;
//        }
//        int sum = 0;
//        int size = stack.size();
//        boolean isCompareNext = false;
//        while(!stack.isEmpty()){
//            int j = stack.pop();
//            while(j >= 0 && baseNum[j] == 0){
//                j--;
//            }
//            if(j < 0){
//                sum = 0;
//                break;
//            }
//            sum = Math.pow(digits.length,);
//        }
//
//        for(int i = 1; i < size; i++){
//            sum += Math.pow(digits.length,i);
//        }
//        return sum;
//    }
}
