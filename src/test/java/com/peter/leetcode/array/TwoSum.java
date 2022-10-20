package com.peter.leetcode.array;

import com.peter.leetcode.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 * 提示：
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 *
 * 为了测试数据 则显示数组的值
 */
public class TwoSum {

    public static void main(String[] args) {

        int maxValue= (int) Math.pow(10,4);
        int testCount=maxValue/2;
        int maxSize=maxValue/2;
        boolean fail=false;
        for(int i=1;i<=testCount;i++) {
            int target=ArrayUtil.getNum(maxValue);
            int[] array = createArray(maxSize,maxValue,target);

            int[] result1=twoSumByIter(array,target);
            int[] result2=twoSumHash(array,target);
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

    /**
     *
     * 方法一：暴力枚举
     * 思路及算法
      最容易想到的方法是枚举数组中的每一个数 x，寻找数组中是否存在 target - x。
      当我们使用遍历整个数组的方式寻找 target - x 时，需要注意到每一个位于 x 之前的元素都已经和 x 匹配过，因此不需要再进行匹配。而每一个元素不能被使用两次，所以我们只需要在 x 后面的元素中寻找 target - x。
     复杂度
     时间复杂度：O(N^2)其中 NN 是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     空间复杂度：O(1)
     * @param nums
     * @param target
     * @return
     */
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
     * 注意到方法一的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。因此，我们需要一种更优秀的方法，能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。
     *
     * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N)O降低到 O(1)
     *
     * 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配。
     * 复杂度分析
     *
     * 时间复杂度：O(N)，其中 N是数组中的元素数量。对于每一个元素 x，我们可以 O(1) 地寻找 target - x。
     *
     * 空间复杂度：O(N)，其中 N 是数组中的元素数量。主要为哈希表的开销。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumHash(int[] nums, int target) {
        if(nums==null||nums.length==0){
            return new int[0];
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                return new int[]{target -nums[i],nums[i]};
            }
            map.put(target - nums[i], i);
        }
        return new int[0];
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
}
