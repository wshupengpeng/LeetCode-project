package leetcode.everyday.october2022;

import java.util.ArrayList;
import java.util.List;

/**
 * @creater hpp
 * @Date 2022/10/30-20:56
 * @description:
 * 784. 字母大小写全排列
 *
 * 给定一个字符串s，通过将字符串s中的每个字母转变大小写，我们可以获得一个新的字符串。
 *
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * 示例 2:
 *
 * 输入: s = "3z4"
 * 输出: ["3z4","3Z4"]
 *
 */
public class Solution1030 {
    public static void main(String[] args) {
        Solution1030 solution1030 = new Solution1030();
        List<String> a1b2 = solution1030.letterCasePermutation(
                "a1b2");
        System.out.println(a1b2);
    }

    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        res.add(s);
        dfs(0,s,res,false);
        return res;
    }

    /**
     *
     * @param i  代表遍历到哪个下标
     * @param s  原有字符串
     * @param res 结果
     * @param isChange 上一步是否发生变化
     */
    private void dfs(int i,String s,List<String> res, boolean isChange){
        if(isChange) res.add(s);
        if(i == s.length()){
            return;
        }
        char c = s.charAt(i);
        // 小写字母
        if (c >= 97 && c <= 122) {
            dfs(i + 1, s, res, false);
            String s1 = replaceToUpperCase(i, s);
            dfs(i + 1, s1, res, true);
        } else if (c >= 65 && c <= 90) {
            dfs(i + 1, s, res, false);
            String s1 = replaceToLowerCase(i, s);
            dfs(i + 1, s1, res, true);
        } else {
            dfs(i + 1, s, res, false);
        }

    }


    private String replaceToUpperCase(int index, String s){
        return s.substring(0, index) + (char) (s.charAt(index) - 32) + s.substring(index + 1);
    }


    private String replaceToLowerCase(int index, String s){
        return s.substring(0, index) + (char) (s.charAt(index) + 32) + s.substring(index + 1);
    }
}
