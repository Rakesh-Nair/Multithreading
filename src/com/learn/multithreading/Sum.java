package com.learn.multithreading;

import java.util.HashMap;

public class Sum {
	public static void main(String args[]) {
		Solution solution = new Solution();
		//int[] x = {2,7,11,15};
		//int y =9;
		//solution.twoSum(x, y  );
		int[] nums = {0,1,0,13,12};
		
		solution.moveZeroes(nums);
		System.out.println(nums);
	}

}
//Two Sum Solution
class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int []indexes = new int[2];
        for(int i=0; i<nums.length;i++){
            //3, 2, 4 --> 6
            Integer diff = target - nums[i];
            Integer index = map.get(nums[i]);
            System.out.println("Diff "+diff +" index "+index);
            if(index == null){
                map.put(diff, i);
                System.out.println(map);
            }
            else{
                indexes[0] = index;
                indexes[1] = i;
                System.out.println(indexes[0]+ " , "+ indexes[1]);
                return indexes;
            }
        }
        System.out.println(indexes[0]+ " , "+ indexes[1]);
      return indexes;
       
    }
}

class Solution {
    public void moveZeroes(int[] nums) {
        for(int i=0; i<nums.length;i++){
            if(nums[i] == 0){
                int j = i + 1;
                while(j<nums.length && nums[j] == 0){
                	j++;
                }
                if(nums[j] !=0 ) {
                    int temp = nums[j];
                    nums[i] = temp;
                    nums[j] = 0;
                }
            }

        }
    }
}
