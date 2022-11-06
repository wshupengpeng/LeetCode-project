package leetcode.everyday.november2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description：1106. 解析布尔表达式
 *给你一个以字符串形式表述的 布尔表达式（boolean） expression，返回该式的运算结果。
 *
 * 有效的表达式需遵循以下约定：
 *
 * "t"，运算结果为 True
 * "f"，运算结果为 False
 * "!(expr)"，运算过程为对内部表达式 expr 进行逻辑 非的运算（NOT）
 * "&(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 与的运算（AND）
 * "|(expr1,expr2,...)"，运算过程为对 2 个或以上内部表达式 expr1, expr2, ... 进行逻辑 或的运算（OR）
 *
 *
 * 示例 1：
 *
 * 输入：expression = "!(f)"
 * 输出：true
 * 示例 2：
 *
 * 输入：expression = "|(f,t)"
 * 输出：true
 * 示例 3：
 *
 * 输入：expression = "&(t,f)"
 * 输出：false
 * 示例 4：
 *
 * 输入：expression = "|(&(t,f,t),!(t))"
 * 输出：false
 * @Author 01415355
 * @Date 2022/11/6 15:13
 */
public class Solution1105 {

    public boolean parseBoolExpr(String expression) {
        Stack<Character> expreStack = new Stack();
        Stack<Character> symbolStack = new Stack();
        List<Character> temp = new ArrayList<>();
        for(int i = 0; i < expression.length();i++){
            char c = expression.charAt(i);
            if(c == ',') continue;
            if(c == ')'){
                char symbol = symbolStack.pop();
                while(expreStack.peek()!='('){
                    temp.add(expreStack.pop());
                }
                c = computer(symbol,temp);
                temp.clear();
                expreStack.pop();
            }else if (isComputerSymbol(c)){
                symbolStack.push(c);
                continue;
            }
            expreStack.push(c);
        }
        return expreStack.pop() == 't';
    }

    public char computer(char symbol,List<Character> cs){
        if(symbol == '!') return cs.get(0) == 't' ? 'f' : 't';
        if (symbol == '|') return cs.stream().filter(c->c == 't').count() > 0 ? 't' : 'f';
        else return cs.stream().filter(c->c == 'f').count() > 0 ? 'f' : 't';
    }


    private boolean isComputerSymbol(char c){
        return c == '!' || c == '|' || c == '&';
    }
}
