package com.peter.leetcode.array.sort;

import com.peter.leetcode.ArrayUtil;

import java.util.Arrays;

/**
 * • 将数组中每个元素与第一个元素比较，如果这个元素小于第一个元素， 则交换这两个元素
 * • 循环第 1 条规则，找出最小元素，放于第 1 个位置
 * • 经过 n-1 轮比较完成排序
 *
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，
 * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 */
public class SelectSort {
    public static void main(String[] args) {

        int maxValue= (int) Math.pow(10,3);
        int testCount=maxValue/2;
        int maxSize=maxValue/2;
        boolean fail=false;
        for(int i=1;i<=testCount;i++) {
            int[] array = ArrayUtil.createArray(maxSize, maxValue);
            int[] array2=ArrayUtil.copyArray(array);
            selectSort(array);
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
     * 选择排序的交换操作介于 0 和 (n - 1)次之间。选择排序的比较操作为 n (n - 1） / 2 次之间。
     * 选择排序的赋值操作介于 0 和 3 (n - 1） 次之间。比较次数O(n^2），
     * 比较次数与关键字的初始状态无关，
     * 总的比较次数N=(n-1）+(n-2）+...+1=n*(n-1）/2
     *
     * 时间复杂度 O(n^2)
     * @param array
     */
    public static void selectSort( int[] array){
        for(int i=0;i<array.length;i++){
            int minIndex=i;
            //遍历I+1 至N-1 找出最小索引
            for(int j=i+1;j<array.length;j++){
                if(array[minIndex]>array[j]){
                    minIndex=j;
                }
            }
            if(minIndex!=i){
               swap(array,i,minIndex);
            }
        }
    }
    public static void swap(int[] array,int i,int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }
}
