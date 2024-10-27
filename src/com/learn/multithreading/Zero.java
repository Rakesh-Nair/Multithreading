package com.learn.multithreading;

import java.util.Arrays;

public class Zero {
	
	public static void main(String args[]) {
		//int [] nums = {0,1,0,3,12};
		//int [] nums = {0,1,0,3,12};
		int [] nums = {0,1,0,3,12,0,15};
		//int [] nums = {0};
		
		moveZeroes(nums);
		
	}
	public static void moveZeroes(int[] nums) {
		for(int i=0; i<nums.length; i++) {
			if(nums[i] == 0) {
				int j = i+1;

				while(j<nums.length) {
					if(nums[j] != 0) {
						int temp = nums[j];
						nums[j] = nums[i];
						nums[i]=temp;
						break;
					}
					j++;
				}
			}
		}
		Arrays.stream(nums).forEach(System.out::println);
		
	}

}
