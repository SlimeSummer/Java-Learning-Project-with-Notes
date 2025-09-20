/*
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
你可以按任意顺序返回答案
*/
package com.LeetCode;

import java.util.HashMap;
import java.util.*;

public class Sum1 {
    public static void main(String args[]){
        int target=14;
        int[] nums={2,6,4,8,3};
        int[] index;
        index=Solution2.twoSum(nums,target);
        for (int i=0;i< index.length;i++){
            System.out.println("value="+nums[index[i]]+" index="+index[i]);
        }
    }
}

// 解法1：暴力枚举
// 时间:O(n²)
class Solution {
    public static int[] twoSum(int[] nums, int target) {
        for (int i=0;i< nums.length;i++){
            for (int j=i+1;j< nums.length;j++){
                if (target==nums[i]+nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("数组中没有满足要求的两个数");
    }
}

// 解法2：哈希集合
// 时间:O(n)
class Solution2{
    public static int[] twoSum(int[] nums,int target){  //利用hashmap查找，时间复杂度仅为O(n)
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i=0;i< nums.length;i++){
            int complement=target-nums[i];
            if (map.containsKey(complement)){
                return new int[]{map.get(complement),i};
            }
            map.put(nums[i],i);
        }
        System.out.println("数组中没有满足要求的两个数");
        return new int[]{};
    }
}

/*
1. 第29行除了可以抛出异常外，还可以return null; 或return new int[]{};
2. HashMap<Integer,Integer> map=new HashMap<>();这里的<Integer,Integer>为泛型类型参数声明，它指定了该HashMap中键(key)和值(value)
的数据类型。在 new HashMap<>() 中的 <> 是Java 7引入的"钻石操作符"，它允许编译器从变量声明中推断泛型类型。它等价于完整写法
new HashMap<Integer,Integer>()，但更简洁。

总结：
1. 抛出异常语句是有效的控制路径终结方式，它是一种合法的方法终止方式，能够补全程序的控制流。可以在可能出错的地方用 throw 抛出异常，
以确保 Java 编译器认为控制流是完整的，这样一来程序就能通过编译了。
2. 之所以把数组的值nums[i]作为hashmap的key值，把索引i作为value，是因为hashmap的key->value的映射关系是单向映射的，使用get(key)方法
时是通过映射关系直接从key找到value的，使用containsKey方法也是hashmap通过先获取key的hash值，然后在用这个hash值去找对应的内存地址的“桶”
时间复杂度为O(1)。如果反向从value找key值，hashmap内部则需要从头到尾遍历查找，时间复杂度为O(n)。
3. 时间复杂度相关知识：
定义：输入规模扩大时，代码执行步骤数量增长的速度。表示的是一个算法在数据量变大时，操作量增长的“离谱程度”，它是一个动态的概念，而不是用来带入具
体数值计算具体步骤的工具。
（1）O(1)：常数时间
    特征：无论输入规模多大，执行步骤数不变；不涉及循环；只做固定次数的操作
    判断技巧：没有循环、没有递归、没有随着输入增长而做更多的事情。
（2）O(n)：线性时间
    特征：输入数据有 n 个，就做 n 次操作；单层循环遍历数组或列表
    判断技巧：一个 for / while 循环，次数和输入规模成正比；或递归调用 n 次（如线性递归）
（3） O(n²)：平方时间
    特征：两层嵌套循环（遍历每一对元素）；每增加一个输入，操作数量呈平方增长
    判断技巧：嵌套循环，每一层都与 n 成比例；处理“成对元素”、“所有组合”等问题；最常见于暴力解法、比较排序等算法
 */
