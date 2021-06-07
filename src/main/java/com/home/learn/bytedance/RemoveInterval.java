package com.home.learn.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveInterval {
    //输入已经排序，且只删除一个，直接线段扫描
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int[] in : intervals) {
            if(in[0] < toBeRemoved[0]) {
                ans.add(Arrays.asList(in[0], Math.min(toBeRemoved[0], in[1])));
            }
            if(toBeRemoved[1] < in[1]) {
                ans.add(Arrays.asList(Math.max(toBeRemoved[1], in[0]), in[1]));
            }
        }
        return ans;
    }
}
