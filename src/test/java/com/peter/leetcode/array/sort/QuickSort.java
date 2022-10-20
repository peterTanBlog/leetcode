package com.peter.leetcode.array.sort;

import com.peter.leetcode.ArrayUtil;

import java.util.Arrays;

/**
 * 冒泡排序就是比较相邻的元素，将小的放到前面
 */
public class QuickSort {
    public static void main(String[] args) {

        int maxValue= (int) Math.pow(10,5);
        int testCount=maxValue/2;
        int maxSize=maxValue/2;
        boolean fail=false;
        for(int i=1;i<=testCount;i++) {
            int[] array = ArrayUtil.createArray(maxSize, maxValue);
            int[] array2=ArrayUtil.copyArray(array);
            bubbleSort(array);
            ArrayUtil.sort(array2);
            if(!ArrayUtil.isEqual(array,array2)) {
                System.out.println("test fail");
                System.out.println("result1:" + Arrays.toString(array));
                System.out.println("result2:" + Arrays.toString(array2));
                fail = true;
                break;
            }
        }
        if(!fail){
            System.out.println("test success");
        }else{
            System.out.println("test fail");
        }
        System.out.println("test finish"+":");
    }

    public static void bubbleSort( int[] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-1;j++){
                if(array[j]>array[j+1]){
                    int temp=array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                }
            }
        }
    }
}
