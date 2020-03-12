package com.home.learn.pinterest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FrogJump {
    public boolean canCross(int[] stones) {
        if (stones[1] != 1) return false;
        if (stones.length == 2) return true;
        //index to k
        Map<Integer, Set<Integer>> jumpMap = new HashMap<>();
        for (int stone : stones) {
            jumpMap.put(stone, new HashSet<>());
        }
        jumpMap.get(1).add(1);
        for(int i = 1; i < stones.length; i++) {
            for(int k : jumpMap.get(stones[i])) {
                for(int j = -1; j <= 1; j++) {
                    int jump = k + j;
                    if(jump <= 0) {
                        continue;
                    }
                    int newPos = jump + stones[i];
                    Set<Integer> index = jumpMap.getOrDefault(newPos, null);
                    if(newPos == stones[stones.length - 1]) {
                        return true;
                    } else if(index != null) {
                        jumpMap.get(newPos).add(jump);
                    }
                }
            }
        }
        return false;
    }
}
