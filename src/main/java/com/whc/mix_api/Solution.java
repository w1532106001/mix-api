package com.whc.mix_api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author whc
 * @date 2020/8/31
 * @description
 */

public class Solution {
    public static void main(String[] args) {

    }


    public int strToInt(String str) {
        int num;
        str = str.trim();
        char[] strArr = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            boolean b = strArr[i] >= 48 && strArr[i] <= 57;
            if (i == 0) {
                if ((strArr[i] == 45 && strArr.length > 1 && strArr[i + 1] >= 48 && strArr[i + 1] <= 57) || (strArr[i] == 43 && strArr.length > 1 && strArr[i + 1] >= 48 && strArr[i + 1] <= 57) || b) {
                    stringBuilder.append(strArr[i]);
                } else {
                    break;
                }
            } else {
                if (b) {
                    stringBuilder.append(strArr[i]);
                } else {
                    break;
                }
            }
        }
        if (stringBuilder.toString().isEmpty()) {
            num = 0;
            return num;
        }
        int len = stringBuilder.length();
        int zeroLen = 0;
        char[] chars = stringBuilder.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0) {
                if (chars[i] == 48) {
                    zeroLen++;
                } else {
                    if (chars[i] == 45 || chars[i] == 43) {

                    } else {
                        break;
                    }
                }
            } else {
                if (chars[i] == 48) {
                    zeroLen++;
                } else {
                    break;
                }
            }
        }
        if (len - zeroLen > 11) {
            if (stringBuilder.toString().contains("-")) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        long longValue = Long.parseLong(stringBuilder.toString());
        if (longValue > Integer.MAX_VALUE) {
            num = Integer.MAX_VALUE;
        } else if (longValue < Integer.MIN_VALUE) {
            num = Integer.MIN_VALUE;
        } else {
            num = Integer.parseInt(stringBuilder.toString());
        }

        return num;
    }


    public String thousandSeparator(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add(n % 10);
            n = n / 10;
        }
        int d = 0;
        for (Integer integer : list) {
            stringBuilder.insert(0, integer.toString());
            d++;
            if (d == 4) {
                stringBuilder.insert(1, ".");
                d = 1;
            }
        }
        return stringBuilder.toString();
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                current++;
            } else {
                max = Math.max(max, current);
                current = 0;
            }
        }
        return Math.max(max, current);
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i : arr1) {
            list.add(i);
        }
        Collections.sort(list);
        for (int i = arr2.length - 1; i >= 0; i--) {
            for (int i1 = 0; i1 < list.size(); i1++) {
                if (arr2[i] == list.get(i1)) {
                    list.remove(i1);
                    list.addFirst(arr2[i]);
                }
            }
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
