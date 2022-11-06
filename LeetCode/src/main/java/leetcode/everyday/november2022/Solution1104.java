package leetcode.everyday.november2022;

/**
 * @Description：
 * 754. 到达终点数字
 *
 * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
 *
 * 你可以做一些数量的移动 numMoves :
 *
 * 每次你可以选择向左或向右移动。
 * 第 i 次移动（从  i == 1 开始，到 i == numMoves ），在选择的方向上走 i 步。
 * 给定整数 target ，返回 到达目标所需的 最小 移动次数(即最小 numMoves ) 。
 *
 * 示例 1:
 *
 * 输入: target = 2
 * 输出: 3
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 -1 。
 * 第三次移动，从 -1 到 2 。
 * 示例 2:
 *
 * 输入: target = 3
 * 输出: 2
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 3 。
 * @Author 01415355
 * @Date 2022/11/4 17:38
 */
public class Solution1104 {

    public static void main(String[] args) {
        Solution1104 solution1104 = new Solution1104();
        int i = solution1104.reachNumber(4);
        System.out.println(i);
    }

    public int reachNumber(int target) {
        int step = 0;
        int index = 0;
        target = Math.abs(target);
        while(index < target || (index - target) % 2 != 0){
            index+=++step;
        }
        return step;
    }
}
