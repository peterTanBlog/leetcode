package com.peter.leetcode.array.sort;

import com.peter.leetcode.ArrayUtil;

import java.util.Arrays;

/**
 * 二分归并排序，也就是我们把一个数组序列分为左右两部分（每一部分会继续划分左右两部分），
 * 然后对这两部分分别进行排序，
 * 左右排序完成后，再将这两部分合并起来，简化排序的过程。
 */
public class MergeSort {
    public static void main(String[] args) {

        int maxValue= (int) Math.pow(10,2);
        int testCount=maxValue/2;
        int maxSize=maxValue/2;
        boolean fail=false;
        for(int i=1;i<=testCount;i++) {
            int[] array = ArrayUtil.createArray(maxSize, maxValue);
            if(array.length==0){
                continue;
            }
            int[] array2=ArrayUtil.copyArray(array);
            mergeSort(array);
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

    public static void mergeSort( int[] array){
        processSort(array,0,array.length-1);
    }
    public static void processSort( int[] array,int leftIndex,int rightIndex){
        if(leftIndex>=rightIndex){
            return;
        }
        int mid=leftIndex+(rightIndex-leftIndex)/2;
        processSort(array,leftIndex,mid);
        processSort(array,mid+1,rightIndex);
        mergeSort(array,leftIndex,mid,rightIndex);
    }
    public static void mergeSort( int[] array,int leftIndex,int mid,int rightIndex){
        int p1=leftIndex;
        int p2=mid+1;
        int[] newArray=new int[rightIndex-leftIndex+1];
        int index=0;
        while(p1<=mid&&p2<=rightIndex){
            newArray[index++]=array[p1]<array[p2]?array[p1++]:array[p2++];
        }
        while(p1<=mid){
            newArray[index++]=array[p1++];
        }
        while(p2<=rightIndex){
            newArray[index++]=array[p2++];
        }
        for(int i=0;i<newArray.length;i++){
            array[leftIndex+i]=newArray[i];
        }
    }
}
