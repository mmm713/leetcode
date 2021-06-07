package com.home.learn.leetcode.design;

import java.util.*;

public class FileSharing {

    int id = 0;
    int m;
    List<Integer>[] c;
    Queue<Integer> ids;
    Map<Integer, Set<Integer>> oc;
    public FileSharing(int m) {
        this.m = m;
        c = new List[m];
        for (int i = 0; i < c.length; i++) {
            c[i] = new ArrayList<>();
        }
        ids = new PriorityQueue<>();
        oc = new HashMap<>();
    }

    public int join(List<Integer> ownedChunks) {
        int newId;
        if(!ids.isEmpty()) {
            newId = ids.poll();
        } else {
            newId = ++id;
        }
        for(int o : ownedChunks) {
            addId(newId, o);
        }
        oc.put(newId, new HashSet<>(ownedChunks));
        return newId;
    }

    public void leave(int userID) {
        ids.offer(userID);
        oc.get(userID).forEach(o -> remove(userID, o));
        oc.remove(userID);
    }

    private void addId(int userId, int chunkId) {
        c[chunkId - 1].add(userId);
        Collections.sort(c[chunkId - 1]);
    }

    private void remove(int userId, int chunkId) {
        c[chunkId - 1].remove(Integer.valueOf(userId));
        Collections.sort(c[chunkId - 1]);
    }

    public List<Integer> request(int userID, int chunkID) {
        List<Integer> res = new ArrayList<>(c[chunkID - 1]);
        if(!c[chunkID - 1].isEmpty() && !c[chunkID - 1].contains(userID)) {
            addId(userID, chunkID);
            oc.get(userID).add(chunkID);
        }
        return res;
    }
}