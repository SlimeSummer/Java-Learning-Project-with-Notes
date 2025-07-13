//用Java模拟栈的工作及栈的应用
package com.Note;

public class StackPractice {
    public static void main (String[] args) {
        Stack a=new Stack(5);
        a.initial();
        System.out.println(a.isEmpty());
        for (int i=0;i<5;i++){
            a.push((i+1)*2-1);
        }
        for (int i=0;i<5;i++){
            System.out.println(a.read());
            a.pop();
        }
        String s="{[()]}";
        System.out.println(matchBracket(s));
    }
    public static boolean matchBracket(String s){   //检查括号是否匹配的方法
        char[] char1=s.toCharArray();
        Stack stack=new Stack(20);
        int i=0;
        while (i<char1.length){
            switch (char1[i]){
                case '{':
                case '[':
                case '(':
                    stack.push(char1[i]);
                    break;
                case '}':
                    if(!stack.isEmpty()&& (char)stack.read()=='{'){
                        stack.pop();
                    }
                    else{
                        return false;
                    }
                    break;
                case ']':
                    if(!stack.isEmpty()&& (char)stack.read()=='['){
                        stack.pop();
                    }
                    else{
                        return false;
                    }
                    break;
                case ')':
                    if(!stack.isEmpty()&& (char)stack.read()=='('){
                        stack.pop();
                    }
                    else{
                        return false;
                    }
                    break;
                default:
                    System.out.println("not the brackets");
                    return false;
            }
            i++;
        }
        return stack.isEmpty();
    }
}

class Stack {   //模拟栈类
    private final int maxsize;
    private final Object[] data;
    private int top;

    public Stack(int maxsize){
        this.maxsize=maxsize;
        this.data=new Object[maxsize];
    }

    void initial(){
        top=0;
    }

    boolean isEmpty(){
        return (top==0?true:false);
    }

    Object read(){
        if (this.isEmpty()){
            throw new IllegalArgumentException("this stack is empty");
        }
        else{
            return data[top-1];
        }
    }

    void push(Object a){
        if (top==maxsize){
            throw new IllegalArgumentException("this stack is full");
        }
        else {
            data[top]=a;
            top++;
        }
    }

    void pop(){
        if (this.isEmpty()){
            throw new IllegalArgumentException("this stack is empty");
        }
        else {
            top=top-1;
        }
    }
}

/*
总结：
在使用if条件语句判断时，涉及到逻辑与&&运算一定要小心，要时刻意识到&&运算是从左到右依次运算的，有短路评估的特性。在
if(!stack.isEmpty()&& (char)stack.read()=='[')中，就要有意识把!stack.isEmpty()放在左边，无论如何都要
保证优先判断栈是否为空，因为如果把(char)stack.read()=='['放左边，那么程序无论如何都会先读栈，如果栈一开始就是空的，
这个小细节就会导致抛出异常导致程序终止。编程的时候要注意每个细节，不能想着“功能实现一样”就笼统行事。由此可见，以后遇到
“一题多解”的情况时，千万不能抱着“随便用一种方法就行了，反正最终效果都一样”的想法，要考虑每个方法是否有潜在的风险以及评估
方法的优化程度等
*/