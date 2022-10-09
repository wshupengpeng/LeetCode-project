package leetcode.everyday.october2022;

import java.util.Stack;

/**
 * @creater hpp
 * @Date 2022/10/9-21:20
 * @description:
 * 856. 括号的分数
 *
 * 给定一个平衡括号字符串S，按下述规则计算该字符串的分数：
 *
 * () 得 1 分。
 * AB 得A + B分，其中 A 和 B 是平衡括号字符串。
 * (A) 得2 * A分，其中 A 是平衡括号字符串。
 * 示例 1：
 *
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 *
 * 输入： "(())"
 * 输出： 2
 * 示例3：
 *
 * 输入： "()()"
 * 输出： 2
 * 示例4：
 *
 * 输入： "(()(()))"
 * 输出： 6
 */
public class Solution1009 {
    /**
     *  个人思考：
     *  类似于波兰表达式，将符号入栈后，通过优先级来进行计算，
     *  算法优化点：
     *  1 在发现 ） 时，可以不通过while来完成计算。
     *  2 最后while合并是因为前置合并条件有问题，导致有些数字未合并完的问题。
     * @param s
     * @return
     */
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                int sum = 0;
                while (stack.peek() > 0) {
                    sum += stack.pop();
                }
                sum = Math.max(2 * sum, 1);
                stack.pop();
                stack.push(sum);
            } else {
                stack.push(0);
            }
        }
        while (stack.size() > 1) {
            stack.push(stack.pop() + stack.pop());
        }
        return stack.peek();
    }

    /**
     *  优化算法一：
     *  优化1：将数字合并放到for循环中，没有通过while去做
     *  优化2：将最终数字合并通过第一步添加push进行放到for循环中，减少了时间复杂度
     * @param s
     * @return
     */
    public int scoreOfParentheses1(String s) {
        Stack<Integer> stack = new Stack();
        // 提前push一个数字，防止 () 情况导致异常
        stack.push(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                // 获取上一位数字
                int res = stack.pop();
                // 上一位数字有两种情况： 1 是已经计算过了则取2倍 2是未参与计算则取1
                res = Math.max(res * 2, 1);
                // 将结果加到上一个 ( 括号的值中,参与下次计算
                stack.push(stack.pop() + res);
            } else {
                stack.push(0);
            }
        }
        return stack.peek();
    }


    /**
     *  优化算法二：
     *  本身括号合并可以通过乘法分配律进行分配，比如 （（（（ （） （） （） ))))
     *  可以转换为 （（（（ 1 1 1 ）））） ,此时，当前结果可以表达为 (1+1+1)*2*2*2*2
     *  进一步转换： 1*2^4 + 1 * 2^4 + 1 * 2^4 + 1 * 2^4
     *  因此，只需要计算 （ 出现了几次即可知道当前数需要2的几次幂
     * @param s
     * @return
     */
    public int scoreOfParentheses2(String s) {
        int res = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += s.charAt(i) == '(' ? 1 : -1;
            // 计算二进制值，并且当前一位是(才处理，不然不处理，防止重复累加
            if(s.charAt(i) == ')' && s.charAt(i-1) == '('){
                res += (1 << count);
            }
        }
        return res;
    }


}
