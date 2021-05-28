package com.home.learn.leetcode;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class AnalyzeUserWebsiteVisitPattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int N = username.length;
        Map<String,List<Pair<String,Integer>>> vis = new HashMap<>();
        Map<List<String>,Integer> map = new HashMap<>();
        for (int i=0; i<N; i++) {
            vis.putIfAbsent(username[i], new ArrayList<>());
            vis.get(username[i]).add(Pair.of(website[i], timestamp[i]));
        }
        int max = 0;
        for (List<Pair<String,Integer>> e: vis.values()) {
            for (List<String> seq: geneSeqs(e)) {
                int tmp = 1 + map.getOrDefault(seq, 0);
                map.put(seq, tmp);
                max = Math.max(max, tmp);
            }
        }
        // result
        List<String> res = null;
        for (Map.Entry<List<String>,Integer> e: map.entrySet()) {
            if (e.getValue() == max) {
                if (res == null)
                    res = e.getKey();
                else
                    res = minSeq(res, e.getKey());
            }
        }
        return res;
    }

    public List<String> minSeq(List<String> a, List<String> b) {
        for (int i=0; i<3; i++) {
            if (!a.get(i).equals(b.get(i)))
                return a.get(i).compareTo(b.get(i)) < 0 ? a : b;
        }
        return a;
    }

    public Set<List<String>> geneSeqs(List<Pair<String,Integer>> list) {
        int len = list.size();
        Set<List<String>> seqs = new HashSet<>();
        list.sort(Comparator.comparingInt(Pair::getValue));
        for (int i=0; i<len; i++) {
            for (int j=i+1; j<len; j++) {
                for (int k=j+1; k<len; k++) {
                    List<String> seq = new ArrayList<>();
                    seq.add(list.get(i).getKey());
                    seq.add(list.get(j).getKey());
                    seq.add(list.get(k).getKey());
                    seqs.add(seq);
                }
            }
        }
        return seqs;
    }
}
