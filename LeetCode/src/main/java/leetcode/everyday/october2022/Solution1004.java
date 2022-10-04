package leetcode.everyday.october2022;

import java.util.Stack;

/**
 * 921 匹配添加最少字符串的数量
 */
public class Solution1004 {

    public static void main(String[] args) {
        Solution1004 solution1004 = new Solution1004();
        solution1004.minAddToMakeValid("())");
    }
    /**
     *  解法1： 有效字符串需要判断当前字符串字符，因为题目要求只要字符串 ( )
     *  如果出现一个左括号，则需要一个右括号才能组成有效字符串。
     *
     *  贪心算法 + 栈
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ')' && !stack.empty() && stack.peek() == '('){
                stack.pop();
            }else{
                stack.push(c);
            }
        }
        return stack.size();
    }

    /**
     *  解法二：通过定义计数变量进行计数统计
     */
    public int minAddToMakeValid1(String s) {
        int leftCount = 0;
        int rightCount = 0;
        for(int i = 0; i <s.length(); i++){
            if(s.charAt(i) == '('){
                leftCount++;
            }else{
                if(leftCount > 0) leftCount--;
                else rightCount++;
            }
        }
        return leftCount + rightCount;
    }


}
