package leetcode.everyday.september2022;

/**
 * @creater hpp
 * @Date 2022/9/30-21:15
 * @description: 面试题 01.08. 零矩阵
 *
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 * 示例 1：
 *
 * 输入：
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出：
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2：
 *
 * 输入：
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出：
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 */
public class Solution0930 {
    /**
     * 解法思路：
     * 定义两个数组记录那些行和列需要清除
     * 后面直接清除即可
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        boolean[] rowExists = new boolean[matrix.length];
        boolean[] colExists = new boolean[matrix[0].length];
        // 先标记
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length;j++){
                if(matrix[i][j] == 0){
                    if(!rowExists[i]){
                        rowExists[i] = true;
                    }
                    if(!colExists[j]){
                        colExists[j] = true;
                    }
                }
            }
        }
        // 后清除
        for(int i = 0; i < rowExists.length; i++){
            if(rowExists[i]) clearRow(matrix,i);
        }

        for(int j = 0; j < colExists.length; j++){
            if(colExists[j]) clearCol(matrix,j);
        }
    }

    public void clearRow(int[][] matrix,int row){
        for(int j = 0;j < matrix[row].length; j++){
            matrix[row][j] = 0;
        }
    }

    public void clearCol(int[][] matrix,int col){
        for(int i = 0; i < matrix.length; i++){
            matrix[i][col] = 0;
        }
    }
}
