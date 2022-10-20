package com.peter.leetcode.array;

import com.peter.leetcode.ArrayUtil;

import java.util.*;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 *
 * 你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 * 提示：
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 *
 * 为了测试数据 则显示数组的值
 */
public class ThreeSum {

    public static void main(String[] args) {

        int maxValue= (int) Math.pow(10,2);
        int testCount=maxValue/2;
        int maxSize=maxValue/2;
        boolean fail=false;
        for(int i=1;i<=testCount;i++) {
            int[] array = ArrayUtil.createArray(maxSize,maxValue);
            List<List<Integer>> result1=threeSumByIter(array);
            List<List<Integer>> result2=threeSumByOptimize(array);
            if(!isEqual(result1,result2)) {
                System.out.println("test fail");
                ArrayUtil.sort(array);
                ArrayUtil.print(array);
                System.out.println("result1:" + result1+":"+result1.size());
                System.out.println("result2:" + result2+":"+result2.size());
                fail = true;
                break;
            }else {
                System.out.println("result1:" + result1+":"+result1.size());
                System.out.println("result2:" + result2+":"+result2.size());
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
     时间复杂度：O(N^3)其中 N 是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static List<List<Integer>>  threeSumByIter(int[] nums){
        ArrayUtil.sort(nums);
        List<List<Integer>> result=new ArrayList();
        if(nums==null||nums.length==0){
            return result;
        }

        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++) {
                    if (nums[i] + nums[j] + nums[k] == 0 && (i != j) && (j !=i) && (i!= k)) {
                        List<Integer> oneRow = new ArrayList();
                        oneRow.add(nums[i]);
                        oneRow.add(nums[j]);
                        oneRow.add(nums[k]);
                        if(!isRepeat(oneRow,result)) {
                            result.add(oneRow);
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
                        &&row1.get(2).equals(oneRow.get(2))){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 题目中要求找到所有「不重复」且和为 00 的三元组，这个「不重复」的要求使得我们无法简单地使用三重循环枚举所有的三元组。这是因为在最坏的情况下，数组中的元素全部为 00，即
     *
     *
     * [0, 0, 0, 0, 0, ..., 0, 0, 0]
     * 任意一个三元组的和都为 00。如果我们直接使用三重循环枚举三元组，会得到 O(N^3)O(N
     * 3
     *  ) 个满足题目要求的三元组（其中 NN 是数组的长度）时间复杂度至少为 O(N^3)O(N
     * 3
     *  )。在这之后，我们还需要使用哈希表进行去重操作，得到不包含重复三元组的最终答案，又消耗了大量的空间。这个做法的时间复杂度和空间复杂度都很高，因此我们要换一种思路来考虑这个问题。
     *
     * 「不重复」的本质是什么？我们保持三重循环的大框架不变，只需要保证：
     *
     * 第二重循环枚举到的元素不小于当前第一重循环枚举到的元素；
     *
     * 第三重循环枚举到的元素不小于当前第二重循环枚举到的元素。
     *
     * 也就是说，我们枚举的三元组 (a, b, c)(a,b,c) 满足 a \leq b \leq ca≤b≤c，保证了只有 (a, b, c)(a,b,c) 这个顺序会被枚举到，而 (b, a, c)(b,a,c)、(c, b, a)(c,b,a) 等等这些不会，这样就减少了重复。要实现这一点，我们可以将数组中的元素从小到大进行排序，随后使用普通的三重循环就可以满足上面的要求。
     *
     * 同时，对于每一重循环而言，相邻两次枚举的元素不能相同，否则也会造成重复。举个例子，如果排完序的数组为
     *
     *
     * [0, 1, 2, 2, 2, 3]
     *  ^  ^  ^
     * 我们使用三重循环枚举到的第一个三元组为 (0, 1, 2)(0,1,2)，如果第三重循环继续枚举下一个元素，那么仍然是三元组 (0, 1, 2)(0,1,2)，产生了重复。因此我们需要将第三重循环「跳到」下一个不相同的元素，即数组中的最后一个元素 33，枚举三元组 (0, 1, 3)(0,1,3)。
     *
     * 时间复杂度：O(N^2)O(N
     * 2
     *  )，其中 NN 是数组 nums 的长度。
     *
     * 空间复杂度：O(\log N)。我们忽略存储答案的空间，额外的排序的空间复杂度为 O(\log N)
     * 。然而我们修改了输入的数组nums}，在实际情况下不一定允许，因此也可以看成使用了一个额外的数组存储了 \textit{nums}nums 的副本并进行排序，空间复杂度为 O(N)O(N)。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>>  threeSumByOptimize(int[] nums){
        ArrayUtil.sort(nums);
        List<List<Integer>> result=new ArrayList();
        if(nums==null||nums.length==0){
            return result;
        }
        for(int first=0;first<nums.length;first++){
            if(first>0&&nums[first]==nums[first-1]){
                continue;
            }
            int target=0-nums[first];
            int third=nums.length-1;
            for(int second=first+1;second< nums.length;second++){
                if(second>first+1&&nums[second]==nums[second-1]){
                    continue;
                }
                while(second<third&&nums[second]+nums[third]>target){
                    --third;
                }
                if(second==third){
                    break;
                }
                if(nums[second]+nums[third]==target) {
                    List<Integer> oneRow = new ArrayList();
                    oneRow.add(nums[first]);
                    oneRow.add(nums[third]);
                    oneRow.add(nums[second]);
                    result.add(oneRow);
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
