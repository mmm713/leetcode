package com.home.learn.uber;

import com.home.learn.library.TreeNode;

public class HouseRobber {
    public int rob(int[] nums) {
        int prev = 0;
        int result = 0;
        for(int n : nums) {
            int temp = result;
            result = Math.max(prev + n, result);
            prev = temp;
        }
        return result;
    }

    public int robI(int[] nums) {
        if(nums.length == 0) {
            return 0;
        } else if(nums.length == 1) {
            return nums[0];
        }
        int[] result = new int[nums.length];
        result[0] = nums[0];
        result[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < result.length; i++)  {
            result[i] = Math.max(result[i-2]+ nums[i], result[i-1]) ;
        }
        return result[nums.length - 1];
    }

    public int robII(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        int prev0 = 0;
        int result0 = nums[0];
        int prev1 = 0;
        int result1 = 0;
        for(int i = 1; i < nums.length; i++) {
            if(i < nums.length - 1) {
                int temp0 = result0;
                result0 = Math.max(prev0 + nums[i], result0);
                prev0 = temp0;
            }
            int temp1 = result1;
            result1 = Math.max(prev1 + nums[i], result1);
            prev1 = temp1;
        }
        return Math.max(result0, result1);
    }


    public int robIII(TreeNode root) {
        if(root == null) return 0;
        dfs_rob(root);
        return root.val;
    }

    public int dfs_rob(TreeNode root){
        if(root == null) return 0;
        int pre = 0;
        root.val += dfs_rob(root.left) + dfs_rob(root.right);
        if(root.left != null)  pre += root.left.val;
        if(root.right != null)  pre += root.right.val;
        root.val = Math.max(pre,root.val);
        return pre;
    }
}
