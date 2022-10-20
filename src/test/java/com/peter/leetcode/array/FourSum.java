package com.peter.leetcode.array;

import com.peter.leetcode.ArrayUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。
 请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]
 （若两个四元组元素一一对应，则认为两个四元组重复）：

 0 <= a, b, c, d < n
 a、b、c 和 d 互不相同
 nums[a] + nums[b] + nums[c] + nums[d] == target
 你可以按 任意顺序 返回答案 。

 * 为了测试数据 则显示数组的值
 */
public class FourSum {

    public static void main(String[] args) {

        int maxValue= (int) Math.pow(10,2);
        int testCount=maxValue/2;
        int maxSize=maxValue/2;
        boolean fail=false;
        for(int i=1;i<=testCount;i++) {
            int[] array = ArrayUtil.createArray(maxSize,maxValue);
            int target=ArrayUtil.getNum(maxValue);
            List<List<Integer>> result1=fourSumByIter(array,target);
            List<List<Integer>> result2=fourSumByOptimize(array,target);
            if(!isEqual(result1,result2)) {
                System.out.println("test fail");
                ArrayUtil.sort(array);
                ArrayUtil.print(array);
                System.out.println("result1:" + result1+":"+result1.size());
                System.out.println("result2:" + result2+":"+result2.size());
                fail = true;
                break;
            }else {
               // System.out.println("result1:" + result1+":"+result1.size());
               // System.out.println("result2:" + result2+":"+result2.size());
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
     *
     * 方法一：暴力枚举
     * 思路及算法
      最容易想到的方法是枚举数组中的每一个数 x，寻找数组中是否存在 target - x。
      当我们使用遍历整个数组的方式寻找 target - x 时，需要注意到每一个位于 x 之前的元素都已经和 x 匹配过，因此不需要再进行匹配。而每一个元素不能被使用两次，所以我们只需要在 x 后面的元素中寻找 target - x。
     复杂度
     时间复杂度：O(N^4)其中 N 是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static List<List<Integer>>  fourSumByIter(int[] nums,int target){
        ArrayUtil.sort(nums);
        List<List<Integer>> result=new ArrayList();
        if(nums==null||nums.length==0){
            return result;
        }

        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++) {
                    for(int z=k+1;z<nums.length;z++) {
                        if (nums[i] + nums[j] + nums[k] + nums[z] == target
                                && (i != j) && (j != i) && (i != k)&&(j!=z)&&(i!=z)&&(k!=z)) {
                            List<Integer> oneRow = new ArrayList();
                            oneRow.add(nums[i]);
                            oneRow.add(nums[j]);
                            oneRow.add(nums[k]);
                            oneRow.add(nums[z]);
                            if (!isRepeat(oneRow, result)) {
                                result.add(oneRow);
                            }
                        }
                    }
                }
            }
        }
        return  result;
    }

    public static boolean isRepeat(List<Integer> oneRow, List<List<Integer>> result){
        for(int i=0;i<result.size();i++) {
            List<Integer> row1=result.get(i);
            Collections.sort(row1);
            Collections.sort(oneRow);
            for(int j=0;j<row1.size();j++){
                if(row1.get(0).equals(oneRow.get(0))
                &&row1.get(1).equals(oneRow.get(1))
                        &&row1.get(2).equals(oneRow.get(2))
                        &&row1.get(3).equals(oneRow.get(3))){
                    return true;
                }
            }
        }
        return false;
    }

    /**

     * @param nums
     * @return
     */
    public static List<List<Integer>>  fourSumByOptimize(int[] nums,int target){
        ArrayUtil.sort(nums);
        List<List<Integer>> result=new ArrayList();
        if(nums==null||nums.length==0){
            return result;
        }
        for(int first=0;first<nums.length;first++){
            if(first>0&&nums[first]==nums[first-1]){
                continue;
            }
            int target1=target-nums[first];

            for(int second=first+1;second< nums.length;second++){
                if(second>first+1&&nums[second]==nums[second-1]){
                    continue;
                }
                int target2=target1-nums[second];
                int fourth=nums.length-1;
                for(int third=second+1;third< nums.length;third++) {
                    if(third>second+1&&nums[third]==nums[third-1]){
                        continue;
                    }
                    while (third < fourth && nums[third] + nums[fourth] > target2) {
                        --fourth;
                    }
                    if (third == fourth) {
                        break;
                    }
                    if (nums[fourth] + nums[third] == target2) {
                        List<Integer> oneRow = new ArrayList();
                        oneRow.add(nums[first]);
                        oneRow.add(nums[third]);
                        oneRow.add(nums[second]);
                        oneRow.add(nums[fourth]);
                        result.add(oneRow);
                    }
                }
            }
        }
        return  result;
    }


    public static boolean isEqual(List<List<Integer>> result1,List<List<Integer>> result2){
        if((result1==null&&result2!=null)||(result1!=null&&result2==null)){
            return false;
        }
        if(result1==null&&result2==null){
            return true;
        }
        if(result1.size()==result2.size() &&result2.size()==0){
            System.out.println(" don't find array data");
            return true;
        }
        for(int i=0;i<result1.size();i++) {
            List<Integer> row1=result1.get(i);
            Collections.sort(row1);
            List<Integer> row2=result2.get(i);
            Collections.sort(row2);
            for(int j=0;j<row1.size();j++){
                if(!row1.get(j).equals(row2.get(j))){
                    System.out.println("row1:" + row1.get(j));
                    System.out.println("row2:" + row2.get(j));
                    return false;
                }
            }
        }
        return true;
    }
}
