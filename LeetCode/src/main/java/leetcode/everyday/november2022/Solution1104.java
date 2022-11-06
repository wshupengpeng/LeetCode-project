package leetcode.everyday.november2022;

/**
 * @Description
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
        while(true){
            target = Math.abs(target);
            if(step+1 == (target - index)){
                return ++step;
            }else if(step+1 < (target - index)){
                index += ++step;
            }else{
                index -= ++step;
            }
        }
    }
}
