package leetcode.everyday.october2022;

/**
 * @creater hpp
 * @Date 2022/10/2-11:34
 * @description:
 */
public class Solution1002 {
    public static void main(String[] args) {
        boolean b = canTransform("RXXLRXRXL",
                "XRLXXRRLX");
        System.out.println(b);
    }
    public static boolean canTransform(String start, String end) {
        if(start.length() != end.length()){
            return false;
        }
        // 判断特殊逻辑：
        // 当前XL可以移动为LX 因此 L只能前移，且L不能在第一位，如果在的话，则无法移动
        // 同理可得，RX可以移动R 因此R只能后移，且R不能在最后一位，否则无法移动
        // 记录start 和end的L和R的位置
        int i = 0, j =0;
        int length = start.length();
        while(i < length && j < length){
            while(i < length && start.charAt(i) == 'X'){
                i++;
            }
            while(j < length && end.charAt(j) == 'X'){
                j++;
            }
            if(i < length && j < length){
                if(start.charAt(i) != end.charAt(j)) return false;
                if(start.charAt(i) == 'L' && i < j) return false;
                if(start.charAt(i) == 'R' && i > j) return false;
            }
            i++;
            j++;
        }
        while(i < length){
            if(start.charAt(i) != 'X') return false;
            i++;
        }
        while(j < length){
            if(end.charAt(j) != 'X') return false;
            j++;
        }
        return true;
    }
}
