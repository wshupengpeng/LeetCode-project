package leetcode.everyday.october2022;

/**
 * @Description:
 * 779. 第K个语法符号
 * @Author 01415355
 * @Date 2022/10/20 11:30
 */
public class Solution1020 {

    public int kthGrammar(int n, int k) {
        if(n == 1) return 0;
        if (k > 1 << (n - 2)) return 1 ^ kthGrammar(n - 1, k - (1 << (n - 2)));
        return kthGrammar(n - 1, k);
    }

    public static void main(String[] args) {
    }
}
