package com.peter.leetcode.array.sort;

import com.peter.leetcode.ArrayUtil;

import java.util.Arrays;

/**
 * 冒泡排序就是比较相邻的元素，将小的放到前面
 * 冒泡排序算法的原理如下： [1]
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。 [1]
 * 对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。 [1]
 * 针对所有的元素重复以上的步骤，除了最后一个。 [1]
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较
 */
public class BubbleSort {
    public static void main(String[] args) {

        int maxValue= (int) Math.pow(10,2);
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

    /**
     *  时间复杂度 O(n^2)
     * @param array
     */
    public static void bubbleSort( int[] array){
        for(int i=0;i<array.length;i++){
            // 每次遍历标志位都要先置为false，才能判断后面的元素是否发生了交换
            boolean flag = false;
            for(int j=0;j<array.length-1-i;j++){
                if(array[j]>array[j+1]){
                    int temp=array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                    flag=true;
                }
            }
            // 判断标志位是否为false，如果为false，说明后面的元素已经有序，就直接return
            if(!flag){
                break;
            }
        }
    }
}
