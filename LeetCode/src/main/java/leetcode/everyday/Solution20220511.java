package leetcode.everyday;

import java.util.Stack;

/**
 * 449. 序列化和反序列化二叉搜索树
 * <p>
 * 学习到了二叉树还原的方式：
 * 1 通过前序结果 + 中序结果
 * 2 通过后序结果 + 中序结果
 * <p>
 * 如何还原：
 * 第一种方式：前序结果 + 中序结果
 * 1 前序结果遍历，首位为根节点
 * 2 通过根节点查找中序结果值，搜索中序对应结果，找到后结果左边均为左子树，右边为右子树
 * 3 根据第二步的左子树长度和右子树长度截取前序结果（前序先遍历左子树后右子树，从左到右截取左子树长度，后面就是右子树），并重复1-2操作。
 * <p>
 * <p>
 * 第二种方式：后序结果 + 中序结果
 * 1 前序结果遍历，最后一位为根节点
 * 2 通过根节点查找中序结果值，搜索中序对应结果，找到后结果左边均为左子树，右边为右子树
 * 3 根据第二步的左子树长度和右子树长度截取前序结果（后序序先遍历左子树后右子树，从左到右截取左子树长度，后面就是右子树），并重复1-2操作。
 */
public class Solution20220511 {
    private Stack<TreeNode> stack = new Stack<>();


//    public static void main(String[] args) {
//        Solution20220511 solution = new Solution20220511();
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.left.left = new TreeNode(3);
//        root.right = new TreeNode(4);
//        root.left.right = new TreeNode(5);
////      第一种实现方式
////        String serialize = solution.preSerialize(root);
////        System.out.println(serialize);
////        PreMidSerialize preMidSerialize = new PreMidSerialize();
////        root = preMidSerialize.deserialize(serialize);
////        serialize = solution.preSerialize(root);
////        System.out.println(serialize);
////      第二种实现方式
//        String postSerialize = solution.postSerialize(root);
//        System.out.println(postSerialize);
//        PostMidSerialize postMidSerialize = new PostMidSerialize();
//        TreeNode postNode = postMidSerialize.deserialize(postSerialize);
//        postSerialize = solution.postSerialize(postNode);
//        System.out.println(postSerialize);
//    }
    // Encodes a tree to a single string.
    public String preSerialize(TreeNode root) {
        PreMidSerialize preMidSerialize = new PreMidSerialize();
        String preString = preMidSerialize.preString(root);
        String midString = midString(root,new StringBuilder());
        return preString + "#" + midString.substring(0, midString.length() - 1);
    }


    public String postSerialize(TreeNode root){
        PostMidSerialize postMidSerialize = new PostMidSerialize();
        String postString = postMidSerialize.postString(root,new StringBuilder());
        String midString = midString(root,new StringBuilder());
        return postString.substring(0, midString.length() - 1)+ "#" + midString.substring(0, midString.length() - 1);
    }

    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//
//    }

//    public String midString(TreeNode root,StringBuilder preStr) {
//        if (root == null) return "";
//        preStr.append(midString(root.left,new StringBuilder()));
//        preStr.append(root.val).append(",");
//        preStr.append(midString(root.right,new StringBuilder()));
//        return preStr.toString();
//    }
    public String preString(TreeNode root,StringBuilder strBuilder){
        if(root == null) return "";
        strBuilder.append(root.val).append(",");
        preString(root.left,strBuilder);
        preString(root.right,strBuilder);
        return strBuilder.toString();
    }

    public String midString(TreeNode root,StringBuilder strBuilder){
        if(root == null) return "";
        midString(root.left,strBuilder);
        strBuilder.append(root.val).append(",");
        midString(root.right,strBuilder);
        return strBuilder.toString();
    }

    public static void main(String[] args) {
        Solution20220511 solution20220511 = new Solution20220511();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        String preString = solution20220511.preString(root,new StringBuilder());
        String midString = solution20220511.midString(root,new StringBuilder());
        String str=  preString.substring(0,preString.length()-1) + "#" + midString.substring(0,midString.length()-1);
        System.out.println(str);
        TreeNode deserialize = solution20220511.deserialize(str);
        System.out.println(deserialize);
    }


    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        String[] split =  data.split("#");
        String preString = split[0];
        String midString = split[1];
        split = preString.split(",");
//        return createNode(0,midString.length()-1,0,split[].length-1,,midString.split(","));
        return null;
    }

    public TreeNode createNode(int mLeft,int mRight,int pLeft,int pRight,String[] preString,String[] midString){
        if(mLeft > mRight || pLeft > pRight) return null;
        int i;
        String val = preString[pLeft];
        TreeNode root = new TreeNode(Integer.valueOf(val));
        for(i = mLeft; i<= mRight;i++){
            if(val.equals(midString[i])) break;
        }
        root.left = createNode(mLeft,i-1,pLeft+1,pRight - (mRight-i),preString,midString);
        root.right = createNode(i+1,mRight,pRight - (mRight-i)+1,pRight,preString,midString);
        return root;
    }

}

/**
 * 第一种方式代码实现
 */
class PreMidSerialize {
    private Stack<TreeNode> stack = new Stack<>();

    public String preString(TreeNode root) {
        stack.push(root);
        StringBuilder preStr = new StringBuilder();
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (temp == null) continue;
            preStr.append(temp.val).append(",");
            stack.push(temp.right);
            stack.push(temp.left);
        }
        return preStr.substring(0, preStr.length() - 1);
    }


    public TreeNode deserialize(String serialize){
        String[] split = serialize.split("#");
        String preStr = split[0];
        String midStr = split[1];
        split = preStr.split(",");
        TreeNode root = build(0, midStr.length() - 1, 0, preStr.length() - 1, midStr.split(","), split);
        return root;
    }


    public TreeNode build(int midLeft,int midRight,int preLeft,int preRight,String[] midArr,String[] preArr){
        if(midLeft > midRight || preLeft > preRight) return null;
        int i;
        TreeNode root = new TreeNode(Integer.valueOf(preArr[preLeft]));
        for(i = midLeft; i<= midRight; i++){
            if(midArr[i].equals(preArr[preLeft])){
                break;
            }
        }
        root.left = build(midLeft, i - 1, preLeft + 1, preRight - i,midArr,preArr);
        root.right = build(i+1,midRight,preLeft + i + 1,preRight,midArr,preArr);
        return root;
    }

}


/**
 * 第二种方式代码实现
 */
class PostMidSerialize {

    public String postString(TreeNode root,StringBuilder stringBuilder) {
        if(root == null) return "";
        stringBuilder.append(postString(root.left,new StringBuilder()));
        stringBuilder.append(postString(root.right,new StringBuilder()));
        stringBuilder.append(root.val).append(",");
        return stringBuilder.toString();
    }

    public TreeNode deserialize(String serialize){
        String[] split = serialize.split("#");
        String postStr = split[0];
        String midStr = split[1];
        split = postStr.split(",");
        return createNode(0,split.length-1,0,midStr.split(",").length-1,midStr.split(","),split);
    }


    public TreeNode createNode(int pleft,int pright,int mleft,int mright,String[] midArr,String[] postArr){
        if(pleft > pright || mleft > mright) return null;
        TreeNode root = new TreeNode(Integer.valueOf(postArr[pright]));
        int i;
        for(i = mleft; i <= mright; i++){
            if(midArr[i].equals(postArr[pright])) break;
        }
        root.left = createNode(pleft,pleft+(i-mleft)-1,mleft,i-1,midArr,postArr);
        root.right = createNode(pright-(mright-i),pright-1,i+1,mright,midArr,postArr);
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
