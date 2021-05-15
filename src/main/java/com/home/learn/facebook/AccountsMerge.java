package com.home.learn.facebook;

import java.util.*;
import java.util.stream.Collectors;

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        Map<String, Integer> emailToId = new HashMap<>();
        Map<Integer, Set<String>> idToEmail = new HashMap<>();
        int[] parents = new int[n];
        for(int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (!emailToId.containsKey(email)) {
                    emailToId.put(email, parents[i]);
                } else {
                    int parent0 = find(parents, emailToId.get(email));
                    int parent1 = find(parents, i);
                    if (parent0 != parent1) {
                        parents[parent0] = parent1;
                    }
                }
            }
        }
        for(int i = 0; i < n; i++) {
            int root = find(parents, i);
            if(!idToEmail.containsKey(root)) {
                idToEmail.put(root, new TreeSet<>(accounts.get(i).subList(1, accounts.get(i).size())));
            } else {
                idToEmail.get(root).addAll(accounts.get(i).subList(1, accounts.get(i).size()));
            }
        }

        return idToEmail.entrySet().stream()
                .map(o -> {
                    List<String> tmp = new ArrayList<>(o.getValue());
                    tmp.add(0, accounts.get(o.getKey()).get(0));
                    return tmp;
                }).collect(Collectors.toList());
    }

    private int find(int[] parent, int i) {
        while(i != parent[i]) {
            i = parent[i];
        }
        return parent[i];
    }

    public List<List<String>> accountsMergeUnion(List<List<String>> accounts) {
        int n = accounts.size();
        Map<String, Integer> emailToId = new HashMap<>();
        Map<Integer, Set<String>> idToEmail = new HashMap<>();
        int[] parents = new int[n];
        for(int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            idToEmail.put(i, new TreeSet<>());
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (!emailToId.containsKey(email)) {
                    emailToId.put(email, parents[i]);
                    idToEmail.get(parents[i]).add(email);
                } else {
                    int parent0 = emailToId.get(email);
                    int parent1 = parents[i];
                    if (parent0 != parent1) {
                        unionIds(emailToId, idToEmail, parents, parent1, parent0);
                    }
                }
            }
        }

        return idToEmail.entrySet().stream()
                .map(o -> {
                    List<String> tmp = new ArrayList<>(o.getValue());
                    tmp.add(0, accounts.get(o.getKey()).get(0));
                    return tmp;
                }).collect(Collectors.toList());
    }

    private void unionIds(Map<String, Integer> emailToId,
                          Map<Integer, Set<String>> idToEmail,
                          int[] parents, int parent0, int parent1) {
        parents[parent1] = parent0;
        Set<String> emails = idToEmail.get(parent1);
        emails.forEach(o -> emailToId.put(o, parent0));
        idToEmail.get(parent0).addAll(idToEmail.get(parent1));
        idToEmail.remove(parent1);
    }
}
