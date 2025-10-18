/*
给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
示例 1:
输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。

示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
package com.LeetCode;

import java.util.*;

public class String1 {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.println(lengthOfLongestSubstring(scanner.nextLine()));
    }

    private static int lengthOfLongestSubstring(String s) {
        StringBuilder substring=new StringBuilder();
        int lengthOfLongest=0;
        for (int i=0;i<s.length();i++){
            substring.setLength(0);
            substring.append(s.charAt(i));
            for (int j=i+1;j<s.length();j++){
                if ((substring.indexOf(String.valueOf(s.charAt(j))))==-1){
                    substring.append(s.charAt(j));
                }
                else{
                    break;
                }
            }
            if (substring.length()>lengthOfLongest){
                lengthOfLongest=substring.length();
            }
        }
        return lengthOfLongest;
    }
}
