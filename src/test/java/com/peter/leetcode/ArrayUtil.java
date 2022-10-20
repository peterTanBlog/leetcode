package com.peter.leetcode;

import java.util.*;

public class ArrayUtil {
   public static Random random=new Random();

    public static int[] copyArray(int[] array){
        int[] newArray=new int[array.length];
        System.arraycopy(array,0,newArray,0,array.length);
        return  newArray;
    }
    // 生成数组
    public static int[] createArray(int maxSize,int maxValue){
        int size= ArrayUtil.getNum(maxSize+1);
        int[] array=new int[size];
        for(int i=0;i<array.length;i++){
            array[i]=ArrayUtil.getNum(maxValue+1)-ArrayUtil.getNum(maxValue);
        }
        return  array;
    }
    public static  int getNum(int value){
        //Math.random() [0,1)
        //Math.random * N[0,N);
        //(int) (N*Math.random()) [0,N-1)
        return (int) ((value)*Math.random());
    }

    public static boolean isEqual(int[] array1,int array2[]){
        if((array1==null&&array2!=null)||(array1!=null&&array2==null)){
            return false;
        }
        if(array1==null&&array2==null){
            return true;
        }
        if(array1.length==array2.length &&array2.length==0){
            System.out.println(" don't find array data");
            return true;
        }
        for(int j=0;j<array1.length;j++) {
            if (array1[j] != array2[j]) {
                return false;
            }
        }
        System.out.println("result1:" + Arrays.toString(array1));
        System.out.println("result2:" + Arrays.toString(array2));
        return true;
    }

    public static void sort(int[] array){
        Arrays.sort(array);
    }
    public static void print(int[] array){
        System.out.println("array:"+Arrays.toString(array));
    }

}
