package leetcode.everyday.September2022;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @creater hpp
 * @Date 2022/9/27-21:40
 * @description:
 * 面试题 01.02. 判定是否互为字符重排
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 */
public class Solution0927 {
    private static Solution0927 solution0927 = new Solution0927();
    public static void main(String[] args) {
        System.out.println("开始两个数比较：");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("请输入s1:");
            String s1 = scanner.next();
            System.out.println("请输入s2:");
            String s2 = scanner.next();
            boolean b = solution0927.CheckPermutation(s1, s2);
            System.out.println("结果为：" + b);
        }
    }

    public boolean CheckPermutation(String s1, String s2) {
        // 解法1：时间复杂度O（n^2）
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        boolean[] exists = new boolean[s1.length()];
        int j = 0;
        for (int i = 0; i < chars1.length; i++) {
            for (j = 0; j < chars2.length; j++) {
                if (chars1[i] == chars2[j] && !exists[j]) {
                    exists[j] = true;
                    break;
                }
            }
            if (j == chars2.length) return false;
        }
        return true;

    }

    public boolean CheckPermutation1(String s1, String s2) {
        // 解法2：hash表进行对比
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Map<Character, Integer> existsMap = new HashMap();


        for (int i = 0; i < chars1.length; i++) {
            existsMap.put(chars1[i], existsMap.getOrDefault(chars1[i], 0) + 1);
        }

        for (int i = 0; i < chars2.length; i++) {
            if (!existsMap.containsKey(chars2[i])) {
                return false;
            }
            existsMap.put(chars2[i], existsMap.get(chars2[i]) - 1);
            if (existsMap.get(chars2[i]) < 0) return false;
        }
        return true;
    }

}
