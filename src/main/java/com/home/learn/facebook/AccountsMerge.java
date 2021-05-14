package com.home.learn.facebook;

import java.util.*;
import java.util.stream.Collectors;

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Set<String>> names = new HashMap<>();
        Map<String, Integer> ids = new HashMap<>();
        for (List<String> account: accounts) {
            String name = account.get(0);
            int id = ids.getOrDefault(name, 0);
            ids.put(name, ++id);
            String idName = name + "_" + id;
            HashSet<String> temp = new HashSet<>();
            Set<String> toMerge = new HashSet<>();
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                temp.add(email);
                if(emailToName.containsKey(email) && !emailToName.get(email).equals(idName)) {
                    toMerge.add(emailToName.get(email));
                }
                emailToName.put(email, idName);
            }
            if(!toMerge.isEmpty()) {
                String primary = "";
                for(String m : toMerge) {
                    if(primary.equals("")) {
                        primary = m;
                    } else {
                        names.get(primary).addAll(names.get(m));
                        names.remove(m);
                    }
                }
                names.get(primary).addAll(temp);
                names.forEach((key, value) -> value.forEach(v -> emailToName.put(v, key)));
            } else {
                names.put(idName, temp);
            }
        }

        return names.entrySet().stream()
                .map(o -> {
                    List<String> tmp = new ArrayList<>(o.getValue());
                    Collections.sort(tmp);
                    tmp.add(0, o.getKey().split("_")[0]);
                    return tmp;
                })
                .collect(Collectors.toList());
    }
}
