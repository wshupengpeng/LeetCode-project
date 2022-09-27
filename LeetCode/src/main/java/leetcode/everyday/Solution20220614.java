package leetcode.everyday;

import java.util.Arrays;

public class Solution20220614 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] diagonalOrder = solution.findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println(Arrays.toString(diagonalOrder));
    }
}


class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int rowLimit = mat.length;
        int colLimit = mat[0].length;
        int[] res = new int[rowLimit * colLimit];
        // 标记方向 1 从下往上  -1 从上往下
        int direIndex = 1;
        int i = 0, ni = 0,j=0,nj=0;
        int k = 0;
        while(k < res.length){
            res[k++] = mat[i][j];
            if(direIndex == 1){
                // 如果是从下往上
                ni = i-1;
                nj = j+1;
            }else{
                ni = i+1;
                nj = j-1;
            }
            // 判断当前坐标是否超出范围
            if(k < res.length && (ni < 0 || ni >= rowLimit || nj < 0 || nj >= colLimit)){
                if(direIndex == 1){
                    // 如果从下往上，则判断当前指针j是否超出范围
                    ni = j + 1 < colLimit ? i : i+1;
                    nj = j + 1 < colLimit ? j+1 : j;
                }else{
                    ni = i + 1 < rowLimit ? i+1: i;
                    nj = i + 1 < rowLimit ? j :j+1;

                }
                direIndex *=-1;
            }
            i = ni;
            j = nj;
        }
        return res;
    }
}
