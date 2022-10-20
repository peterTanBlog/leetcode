package com.peter.leetcode.array.sort;

import com.peter.leetcode.ArrayUtil;

import java.util.Arrays;

/**
 * 冒泡排序就是比较相邻的元素，将小的放到前面
 */
public class InsertSort {
    public static void main(String[] args) {

        int maxValue= (int) Math.pow(10,5);
        int testCount=maxValue/2;
        int maxSize=maxValue/2;
        boolean fail=false;
        for(int i=1;i<=testCount;i++) {
            int[] array = ArrayUtil.createArray(maxSize, maxValue);
            int[] array2=ArrayUtil.copyArray(array);
            insertSort(array);
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
     *  实现思路：
     *    插入排序需要考虑3种情况：
     *     1.当前节点<前面的A节点时，A节点后移一位，接着继续比较
     *     2.当前节点>前面的A节点时，当前节点插入到A节点的后一个位置，此时不需要再继续比较了，因为A节点及之前的元素已经是有序的了
     *     3.当前节点<前面的A节点时，并且A节点的位置是0时，需要连续两步操作：A节点往后移一位，当前节点插入到数组下标为0的位置
     *
     */
    public static void insertSort( int[] array){
        for(int i=1;i<array.length;i++){
            //temp是保存着每轮当前节点的值，保存的目的是用于与前面的值做比较
            int tmp=array[i];
            //j的值记录的是当前节点的前面元素的下标位置;
           for(int j=i-1;j>=0;j--){
               //只要满足j>=0，元素的比较才能进行，若j<0就会超出数组下标的范围
               //j--才能比较前面的元素
               if(tmp<=array[j]){
                   //当前节点<前面的A元素，那么A元素往后移
                   array[j+1]=array[j];
                   //这里属于特殊情况，当temp<=arr[j]以及j==0时，需要连续两步操作：1.A元素往后移 2.当前节点插入到arr[0]的位置
                   if(j==0){
                       array[j]=tmp;
                   }
               }else {
                   //当前节点>前面的A元素,那当前节点插入到A元素的后面，即j+1的位置
                   //break是到这一步就不需要再往前比较了，因为A元素及之前的元素已经是有序了
                   array[j+1]=tmp;
                   break;
               }
           }
        }
    }
}
