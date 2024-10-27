package com.learn.multithreading;

import java.util.HashMap;
import java.util.Map;

public class Duplicate {
	public static void main(String args[]) {
		int[]nums = {1,1,1,3,3,4,3,2,4,2};
		System.out.println(containsDuplicate(nums));
	}

	public static boolean containsDuplicate(int[] nums) {
		
		Map<Integer, Boolean> map = new HashMap<>();
		for(int i : nums) {
			if(map.size() > 0 && map.get(i) != null) {
				return true;
			}
			map.put(i, true);
		}
		return false;
	}
}
