package com.peter.leetcode.array;

import com.peter.leetcode.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 给你一个下标从 1 开始的整数数numbers ，该数组已按 非递减顺序排列，请你从数组中找出满足相加之和等于目标数target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 *
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 *
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 *
 * 你所设计的解决方案必须只使用常量级的额外空间。
 *
 *  为了测试数据 则显示数组的值
 */
public class twoSumSortedArray {
    public static void main(String[] args) {

        int maxValue= (int) Math.pow(10,4);
        int testCount=maxValue/2;
        int maxSize=maxValue/2;
        boolean fail=false;
        for(int i=1;i<=testCount;i++) {
            int target= ArrayUtil.getNum(maxValue);
            int[] array = createArray(maxSize,maxValue,target);
            ArrayUtil.sort(array);
            int[] result1=twoSumByIter(array,target);
            int[] result2=twoSumPoint(array,target);
            if(!ArrayUtil.isEqual(result1,result2)) {
                System.out.println("test fail");
                System.out.println("target:" + target);
                System.out.println("result1:" + Arrays.toString(result1));
                System.out.println("result2:" + Arrays.toString(result2));
                fail = true;
                break;
            }
        }
        if(!fail){
            System.out.println("test success");
        }else{
            System.out.println("test fail");
        }
        System.out.println("test finish");
    }
    // 生成数组
    public static int[] createArray(int maxSize,int maxValue,int target){
        int size= ArrayUtil.getNum(maxSize+1);
        int[] array=new int[size];
        HashMap<Integer,Integer> dataMap=new HashMap();
        Set<Integer> recordData=new HashSet<>(array.length);
        for(int i=0;i<array.length;i++){
            array[i]=getUnique(maxValue,dataMap,recordData,target);
        }
        return  array;
    }
    //生成唯一数组
    public static  int getUnique(int maxValue, HashMap<Integer,Integer> dataMap, Set<Integer> recordData,int target){
        int num=ArrayUtil.getNum(maxValue+1)-ArrayUtil.getNum(maxValue);
        //检查是否重复生成 数字
        if(recordData.contains(num)){
            return  getUnique(maxValue,dataMap,recordData,target);
        }
        //生成唯一 一组数据相加等于target
        if(recordData.contains(target-num)){
            if(dataMap.size()==0) {
                dataMap.put(target - num, num);
            }else{
                return  getUnique(maxValue,dataMap,recordData,target);
            }
        }
        recordData.add(num);
        return num;
    }

    public static int[] twoSumByIter(int[] nums, int target){
        if(nums==null||nums.length==0){
            return new int[0];
        }
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return  new int[]{nums[i],nums[j]};
                }
            }
        }
        return  new int[0];
    }

    /**
     * 初始时两个指针分别指向第一个元素位置和最后一个元素的位置。每次计算两个指针指向的两个元素之和，并和目标值比较。如果两个元素之和等于目标值，则发现了唯一解。如果两个元素之和小于目标值，则将左侧指针右移一位。如果两个元素之和大于目标值，则将右侧指针左移一位。移动指针之后，重复上述操作，直到找到答案。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumPoint(int[] nums, int target) {
        if(nums==null||nums.length==0){
            return new int[0];
        }
        int leftIndex=0;
        int rightIndex=nums.length-1;
        while (leftIndex<=rightIndex){
            int sum=nums[leftIndex]+nums[rightIndex];
            if(sum==target){
                return  new int[]{nums[leftIndex],nums[rightIndex]};
            }else if(sum>target){
                rightIndex--;
            }else {
                leftIndex++;
            }
        }
        return new int[0];
    }
}
