//用Java模拟栈的工作及栈的应用,处理后缀表达式求值
package com.Note;

import java.util.*;

public class StackPractice2 {
    public static void main(String[] args){
        System.out.println("请输入后缀表达式：");
        Scanner scanner=new Scanner(System.in);
        Processor processor=new Processor(scanner.nextLine());
        processor.run();
        processor.print();
    }
}

class Processor{
    private char[] data;
    private Stack2 stack;
    private double n1,n2;
    private double result;
    private String OriginalString;

    public Processor(String data){
        OriginalString=data;
        this.data=data.toCharArray();
        stack=new Stack2(data.length());
    }

    public void run(){     //处理程序主方法
        for (int i=0; i<data.length; i++){
            if (data[i]>='0' && data[i]<='9'){
                int[] indexWrapper={i};  //使用数组进行i值的中转
                stack.push(readNumber(data,indexWrapper));
                i=indexWrapper[0];   //使用数组把i值同步
                i--;   //抵消掉外层for循环的多余自增
            }
            else{
                if (data[i]==' '){
                    continue;
                }
                else if (data[i]=='+'){
                    n2=stack.read();
                    n1=stack.read();
                    stack.push(n1+n2);
                }
                else if (data[i]=='-'){
                    n2=stack.read();
                    n1=stack.read();
                    stack.push(n1-n2);
                }
                else if (data[i]=='*'){
                    n2=stack.read();
                    n1=stack.read();
                    stack.push(n1*n2);
                }
                else if (data[i]=='/'){
                    n2=stack.read();
                    n1=stack.read();
                    stack.push(n1/n2);
                }
            }
        }
        result= stack.read();
    }

    private double readNumber(char[] data, int[] indexWrapper){    //辅助方法，将字符串转换为数字
        double number=0;
        int indicator=0;
        int i=indexWrapper[0];

        while (data[i]>='0' && data[i]<='9'){
            number=number*10+(data[i]-'0');
            i++;
        }
        if (data[i]=='.'){
            i++;
        }
        while (data[i]>='0' && data[i]<='9'){
            number=number*10+(data[i]-'0');
            i++;
            indicator++;
        }
        while (indicator>0){
            number=number/10;
            indicator--;
        }

        indexWrapper[0]=i;  //把i值更新到中转站数组中
        return number;
    }

    public double getResult(){
        return result;
    }

    public void print(){
        System.out.println(OriginalString+" ---> "+result);
    }
}

class Stack2{
    private final int maxsize;
    private final double[] data;
    private int top=0;
    private int i=0;

    public Stack2(int maxsize){
        this.maxsize=maxsize;
        data=new double[maxsize];
    }

    public void push(double data){
        this.data[top]=data;
        top++;
    }

    public double read(){
        if (isEmpty()){
            throw new IllegalArgumentException("this stack is empty");
        }
        else{
            double temp=data[top-1];
            data[top-1]=0; //将栈中已读取的位置的数据清零，便于调试观察
            top--;
            return temp;
        }
    }

    public boolean isEmpty(){
        return (top==0?true:false);
    }

    public int getTop(){
        return top;
    }

    public void setTop(int top){
        this.top=top;
    }

    public double[] getData(){
        return data;
    }
}