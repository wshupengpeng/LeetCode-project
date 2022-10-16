package leetcode.everyday.october2022;

import java.util.ArrayList;
import java.util.List;

/**
 * @creater hpp
 * @Date 2022/10/16-19:23
 * @description:
 * 1441. 用栈操作构建数组
 */
public class Solution1015 {
    public List<String> buildArray(int[] target, int n) {
        List<String> res = new ArrayList();
        int last = 1;
        for(int t : target){
            for(int i =last; i < t; i++){
                res.add("Push");
                res.add("Pop");
            }
            res.add("Push");
            last = t+1;
        }


        return res;
    }
}
