package com.home.learn.microsoft;

import java.util.*;

public class JumpGame {
    //JUMP GAME I，
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    //也可以用卡0的办法。因为只有0能卡住，所以要前位置跳过0
    public boolean jumpI(int[] nums) {
        boolean zero = false;
        int zeroIdx = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] == 0 && !zero) {
                zero = true;
                zeroIdx = i;
            } else if(zero && nums[i] + i > zeroIdx) {
                zero = false;
            }
        }
        return !zero;
    }

    //假设必然有解，最少跳几步
    public int jumpII(int[] nums) {
        int jumps = 0, coverage = 0, lastIndex = 0;
        for(int i = 0; i < nums.length - 1; i++) {
            if(i > lastIndex) {
                lastIndex = coverage;
                jumps++;
            }
            coverage = Math.max(i + nums[i], coverage);
            if(coverage >= nums.length - 1) {
                return ++jumps;
            }
        }
        return jumps;
    }

    //只能跳正负 value步，问是否可到。只能DFS求解
    public boolean canReach(int[] arr, int start) {
        if (start >= 0 && start < arr.length && arr[start] >= 0) {
            if (arr[start] == 0) {
                return true;
            }
            arr[start] = -arr[start];
            return canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]);
        }
        return false;
    }

    //JUMP GAME IV，同value可以瞬移，同步数内的点（走到或者瞬移到的）必须一次性处理
    public int minJumps(int[] arr) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            map.putIfAbsent(arr[i],new ArrayList<>());
            map.get(arr[i]).add(i);
        }
        Queue<Integer> q = new ArrayDeque<>(arr.length);
        q.add(0);
        int step = 0;
        boolean[] visited = new boolean[arr.length];
        visited[0] = true;
        while(!q.isEmpty()) {
            for(int j = q.size(); j > 0 && !q.isEmpty(); j--){
                int idx = q.poll();
                if(idx == arr.length - 1) {
                    return step;
                }
                visited[idx] = true;
                if(map.containsKey(arr[idx])){
                    for(int a : map.get(arr[idx])){
                        if(!visited[a]){
                            q.add(a);
                            visited[a] = true;
                        }
                    }
                    map.remove(arr[idx]);
                }
                if(idx - 1 > 0 && !visited[idx - 1]) {
                    q.add(idx - 1);
                }
                if(idx + 1 < arr.length && !visited[idx + 1]) {
                    q.add(idx + 1);
                }
            }
            step++;
        }
        return step;
    }

    //JUMP GAME V，只能向下跳且中间无障碍
    public int jumpGameVI(int[] arr, int d) {
        int res = 0;
        int[] dp = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            res = Math.max(res, dfs(arr, dp, i, d));
        }
        return res;
    }

    int dfs(int[] arr, int[] dp, int start, int d){
        int res = 1;
        if(dp[start] != 0){
            return dp[start];
        }
        for(int i = start + 1; i < arr.length && i <= start + d; i++){
            if(arr[i] >= arr[start]){
                break;
            }
            res = Math.max(res, 1 + dfs(arr, dp , i, d));
        }
        for(int i = start - 1; i >= 0 && i >= start - d; i--){
            if(arr[i] >= arr[start]){
                break;
            }
            res = Math.max(res, 1 + dfs(arr, dp, i, d));
        }
        dp[start] = res;
        return res;
    }

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int res = 0;
        int top = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] stack = new int[n];
        for(int i = 0; i <= n; i++) {
            while(top > 0 && (i == n || arr[stack[top - 1]] < arr[i])) {
                int r = top - 1, l = r - 1;
                while(l >= 0 && arr[stack[l]] == arr[stack[r]]) {
                    l--;
                }
                for(int j = l + 1; j <= r; j++) {
                    if(l >= 0 && stack[j] - stack[l] <= d) {
                        dp[stack[l]] = Math.max(dp[stack[l]], 1 + dp[stack[j]]);
                    }
                    if(i < n && i - stack[j] <= d) {
                        dp[i] = Math.max(dp[i], 1 + dp[stack[j]]);
                    }
                }
                top -= r - l;
            }
            stack[top++] = i;
        }
        for(int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    //JUMP GAME VI，简单DP解
    public int maxResult(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            for(int j = 1; j <= k && i - j >= 0; j++) {
                dp[i] = j == 1 ? dp[i - j] + nums[i] :
                        Math.min(dp[i - j] + nums[i], dp[i]);
            }
        }
        return dp[nums.length - 1];
    }

    //根据sliding window max思路优化，维持单增queue，nk复杂度优化到n
    public int maxResultV2(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> q = new LinkedList<>();
        q.offer(0);
        for (int i = 1; i < n; i++) {
            // pop the old index
            while (q.peekFirst() != null && q.peekFirst() < i - k) {
                q.pollFirst();
            }
            dp[i] = dp[q.peek()] + nums[i];
            // pop the smaller value
            while (q.peekLast() != null && dp[i] >= dp[q.peekLast()]) {
                q.pollLast();
            }
            q.offerLast(i);
        }
        return dp[n - 1];
    }

    //JUMP GAME VII，counter记录之前有几个能跳，nk优化成n
    public boolean canReach(String s, int minJump, int maxJump) {
        boolean[] dp = new boolean[s.length()];
        int counter = 0;
        dp[0] = true;
        for(int i = 1; i < dp.length; i++) {
            if(i >= minJump && dp[i - minJump]) {
                counter++;
            }
            if(i > maxJump && dp[i - maxJump - 1]) {
                counter--;
            }
            dp[i] = counter > 0 && s.charAt(i) == '0';
        }
        return dp[dp.length - 1];
    }
}
