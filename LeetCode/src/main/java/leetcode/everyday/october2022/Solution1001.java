package leetcode.everyday.october2022;

/**
 * @creater hpp
 * @Date 2022/10/1-17:06
 * @description: 1694. 重新格式化电话号码
 *
 * 给你一个字符串形式的电话号码 number 。number 由数字、空格 ' '、和破折号 '-' 组成。
 *
 * 请你按下述方式重新格式化电话号码。
 *
 * 首先，删除 所有的空格和破折号。
 * 其次，将数组从左到右 每 3 个一组 分块，直到 剩下 4 个或更少数字。剩下的数字将按下述规定再分块：
 * 2 个数字：单个含 2 个数字的块。
 * 3 个数字：单个含 3 个数字的块。
 * 4 个数字：两个分别含 2 个数字的块。
 * 最后用破折号将这些块连接起来。注意，重新格式化过程中 不应该 生成仅含 1 个数字的块，并且 最多 生成两个含 2 个数字的块。
 *
 * 返回格式化后的电话号码。
 * 示例 1：
 *
 * 输入：number = "1-23-45 6"
 * 输出："123-456"
 * 解释：数字是 "123456"
 * 步骤 1：共有超过 4 个数字，所以先取 3 个数字分为一组。第 1 个块是 "123" 。
 * 步骤 2：剩下 3 个数字，将它们放入单个含 3 个数字的块。第 2 个块是 "456" 。
 * 连接这些块后得到 "123-456" 。
 * 示例 2：
 *
 * 输入：number = "123 4-567"
 * 输出："123-45-67"
 * 解释：数字是 "1234567".
 * 步骤 1：共有超过 4 个数字，所以先取 3 个数字分为一组。第 1 个块是 "123" 。
 * 步骤 2：剩下 4 个数字，所以将它们分成两个含 2 个数字的块。这 2 块分别是 "45" 和 "67" 。
 * 连接这些块后得到 "123-45-67" 。
 */
public class Solution1001 {


    /**
     *  解题思路：
     *  1 先过滤所有非数字字符
     *  2 按照三个一组，进行拼接
     *  3 判断是否需要交换位置
     */
    public String reformatNumber(String number) {
        // 先过滤不需要的字符
        String replace = number.replaceAll("[ -]","");
        if(replace.length() < 3){
            return replace;
        }
        StringBuilder sb = new StringBuilder();
        int insertFlag = 0;
        for(int i = 0; i < replace.length(); i++){
            sb.append(replace.charAt(i));
            insertFlag++;
            // 如果是最后一位则不需要添加-字符
            if(insertFlag == 3 && i!= replace.length() -1){
                sb.append("-");
                insertFlag = 0;
            }
        }
        if(replace.length() % 3 == 1){
            return swap(sb);
        }
        return sb.toString();
    }

    /**
     *  交换倒数第二位和倒数第三位位置，以满足此条件： 4 个数字：两个分别含 2 个数字的块。
     * @param sb
     * @return
     */
    private String swap(StringBuilder sb) {
        char[] res = sb.toString().toCharArray();
        char temp = res[res.length-2];
        res[res.length-2] = res[res.length-3];
        res[res.length-3] = temp;
        return new String(res);
    }


    /**
     * 解法二：官方解法，通过string的subString进行，
     * 效率没有第一种高
     */
    public String reformatNumber1(String number) {
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < number.length(); ++i) {
            char ch = number.charAt(i);
            if (Character.isDigit(ch)) {
                digits.append(ch);
            }
        }

        int n = digits.length();
        int pt = 0;
        StringBuilder ans = new StringBuilder();
        while (n > 0) {
            if (n > 4) {
                ans.append(digits.substring(pt, pt + 3) + "-");
                pt += 3;
                n -= 3;
            } else {
                if (n == 4) {
                    ans.append(digits.substring(pt, pt + 2) + "-" + digits.substring(pt + 2, pt + 4));
                } else {
                    ans.append(digits.substring(pt, pt + n));
                }
                break;
            }
        }
        return ans.toString();
    }
}
