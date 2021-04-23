package com.home.learn.microsoft;

import java.util.*;

public class SynonymousSentences {
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Map<String, List<String>> dict = buildGraph(synonyms);

        List<String> ans = new ArrayList<>();
        String[] parts = text.split(" ");
        List<String> acc = new ArrayList<>();
        dfs(parts, 0, dict, ans, acc);

        Collections.sort(ans);
        return ans;
    }

    private Map<String, List<String>> buildGraph(List<List<String>> synonyms) {
        Map<String, List<String>> dict = new HashMap<>();
        for (List<String> pair : synonyms) {
            String s1 = pair.get(0);
            String s2 = pair.get(1);

            if (!dict.containsKey(s1)) {
                dict.put(s1, new ArrayList<>());
            }
            if (!dict.containsKey(s2)) {
                dict.put(s2, new ArrayList<>());
            }

            dict.get(s1).add(s2);
            dict.get(s2).add(s1);
        }

        return dict;
    }

    private Iterable<String> getSynonyms(Map<String, List<String>> dict, String word) {
        Set<String> ret = new HashSet<>();

        Deque<String> st = new ArrayDeque<>();
        st.addFirst(word);

        while(st.size() > 0) {
            String s = st.removeFirst();

            ret.add(s);

            if (dict.containsKey(s)) {
                for (String neighbor : dict.get(s)) {
                    if (!ret.contains(neighbor)) {
                        st.addFirst(neighbor);
                    }
                }
            }
        }

        return ret;
    }

    private void dfs(String[] parts, int i, Map<String, List<String>> dict, List<String> ans, List<String> acc) {
        if (i == parts.length) {
            StringBuilder sb = new StringBuilder();
            for (String part : acc) {
                sb.append(part);
                sb.append(" ");
            }

            sb.deleteCharAt(sb.length() - 1);

            ans.add(sb.toString());
            return;
        }

        String part = parts[i];

        for (String synonym : getSynonyms(dict, part)) {
            acc.add(synonym);
            dfs(parts, i + 1, dict, ans, acc);
            acc.remove(acc.size() - 1);
        }
    }
}
