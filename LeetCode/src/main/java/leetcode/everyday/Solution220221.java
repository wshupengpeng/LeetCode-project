package leetcode.everyday;

/**
 *  838. 推多米诺
 */
public class Solution220221 {

//    public String pushDominoes(String dominoes) {
//        char[] change = dominoes.toCharArray();
//        for(int i = 0; i < dominoes.length(); i++){
//            if(dominoes.charAt(i) == '.') continue;
//            if(dominoes.charAt(i) == 'L'){
//                int left = i;
//                while(--left >= 0 && dominoes.charAt(left) == '.'){
//                    change[left] = 'L';
//                }
//            }
//
//            if(dominoes.charAt(i) == 'R'){
//                int left = i;
//                while(++i < dominoes.length() && dominoes.charAt(i) == '*'){}
//
//            }
//        }
//    }
public static void main(String[] args) {
    Solution220221 solution220221 = new Solution220221();
    String s = solution220221.pushDominoes("RR.L");
    System.out.println(s);
}
    public String pushDominoes(String dominoes) {
        // 获取当前除.后的字符
        String replace = dominoes.replaceAll("\\.","");
        char[] chars = dominoes.toCharArray();
        if(replace.length() < 1){
            return dominoes;
        }

        int [] arr = new int[replace.length()];
        int count = 0;
        for(int i = 0; i < dominoes.length(); i++){
            if(dominoes.charAt(i)!='.'){

                arr[count++] = i;
            }
        }

        for(int i = 1; i < arr.length; i++){
            if(replace.charAt(i-1) == replace.charAt(i) || replace.charAt(i-1) == 'L'){
                singleChangeChar(arr[i-1],arr[i],chars);
            }else{
                changeChar(arr[i-1],arr[i],chars);
            }
        }

        if(arr[0] !=0 && replace.charAt(0) == 'L'){
            singleChangeChar(0,arr[0],chars);
        }

        if(arr[arr.length-1] != dominoes.length()-1 && replace.charAt(replace.length()-1) == 'R'){
            singleChangeChar(arr[arr.length-1],chars.length,chars);
        }

        return new String(chars);
    }

    public void singleChangeChar(int left,int right,char[] chars){
        char c = chars[right];
        while(left < right){
            chars[left++] = c;
        }
    }

    public void changeChar(int left,int right,char[] chars){
        char leftChar = chars[left];
        char rightChar = chars[right];
        while(++left < --right){
            chars[left] = leftChar;
            chars[right] = rightChar;
        }
    }


}
