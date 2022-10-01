package leetcode.everyday.september2022;

/**
 * @creater hpp
 * @Date 2022/9/29-20:58
 * @description:   面试题 01.09. 字符串轮转
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 *
 * 示例1:
 *
 *  输入：s1 = "waterbottle", s2 = "erbottlewat"
 *  输出：True
 * 示例2:
 *
 *  输入：s1 = "aa", s2 = "aba"
 *  输出：False
 */
public class Solution0929 {
    /**
     *  解题1方式：
     *  假设s1重排后的字符串为s2，那么有以j下标字段按照i坐标旋转， 有s1[(i+j) % s1.length] = s2[j]
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.length() == 0) {
            return true;
        }
        int n = s1.length();
        boolean res = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s1.charAt((i + j) % n) != s2.charAt(j)) {
                    res = false;
                    break;
                }
            }
            if (res) {
                return true;
            }
            res = true;
        }
        return false;
    }


    /**
     *  解题2方式：
     *  假设s1以i旋转后为s2，那么将s1以i坐标分为两块，分别为ss1和ss2,
     *  即 ss1 + ss2 = s1， ss2 + ss1 = s2
     *  s1 + s1 = ss1 + ss2 + ss1 + ss2
     *  s1 + s2 = ss1 + s2 + ss2
     *  那么 s1+s1后的字符串必包含s2
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString1(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        if(s1.length() == 0) return true;
        return (s1+s1).contains(s2);
    }
}
