package com.home.learn.wish;

import java.util.*;

public class PeopleRelationship {

    public List<String> getRelationSequence(String[][] relations, String name1, String name2) {
        Map<String, Map<String, String>> map = new HashMap<>();
        for (String[] pair : relations) {
            map.putIfAbsent(pair[0], new HashMap<>());
            map.get(pair[0]).put(pair[2], pair[1]);
        }
        List<String> ans = new ArrayList<>();
        dfs(map, name1, name2, new StringBuilder(name1), ans, new HashSet<>());
        return ans;
    }

    private void dfs(Map<String, Map<String, String>> map, String start, String end,
                     StringBuilder path, List<String> ans, Set<String> visited) {
        if (start.equals(end)) {
            ans.add(path.toString());
            return;
        }
        if (!map.containsKey(start)) return;
        visited.add(start);
        for (String next : map.get(start).keySet()) {
            if (visited.contains(next)) continue;
            String temp = " " + map.get(start).get(next) + " " + next;
            path.append(temp);
            dfs(map, next, end, path, ans, visited);
            path.delete(path.length()-temp.length(), path.length());  // backtrack
        }
        visited.remove(start);  // backtrack
    }


    public static void main(String[] args) {
        PeopleRelationship obj = new PeopleRelationship();
        String[][] relations = {
                {"Bart", "brother", "Lisa"},
                {"Bart", "son", "Homer"},
                {"Marge", "wife", "Homer"},
                {"Lisa", "daughter", "Homer"},
                {"Homer", "father", "Bart"}   // form a cycle  -> no problem. Has a visited set.
        };
        List<String> paths = obj.getRelationSequence(relations, "Bart", "Homer");
        for (String path : paths)
            System.out.println(path);
    }
}


/*
Follow-up: Allow duplicated relations between two people -> add a nested for-loop
 */
class PeopleRelationshipDuplicated {
    public List<String> getRelationSequence(String[][] relations, String name1, String name2) {
        Map<String, Map<String, List<String>>> map = new HashMap<>();
        for (String[] pair : relations) {
            map.putIfAbsent(pair[0], new HashMap<>());
            map.get(pair[0]).putIfAbsent(pair[2], new ArrayList<>());
            map.get(pair[0]).get(pair[2]).add(pair[1]);
        }
        List<String> ans = new ArrayList<>();
        dfs(map, name1, name2, new StringBuilder(name1), ans, new HashSet<>());
        return ans;
    }

    private void dfs(Map<String, Map<String, List<String>>> map, String start, String end,
                     StringBuilder path, List<String> ans, Set<String> visited) {
        if (start.equals(end)) {
            ans.add(path.toString());
            return;
        }
        if (!map.containsKey(start)) return;
        visited.add(start);
        for (String next : map.get(start).keySet()) {
            if (visited.contains(next)) continue;
            for (String relation : map.get(start).get(next)) {
                String temp = " " + relation + " " + next;
                path.append(temp);
                dfs(map, next, end, path, ans, visited);
                path.delete(path.length()-temp.length(), path.length());  // backtrack
            }
        }
        visited.remove(start);  // backtrack
    }


    public static void main(String[] args) {
        PeopleRelationshipDuplicated obj = new PeopleRelationshipDuplicated();
        String[][] relations = {
                {"Bart", "sister", "Lisa"},
                {"Bart", "brother", "Lisa"},
                {"Bart", "son", "Homer"},
                {"Marge", "wife", "Homer"},
                {"Lisa", "daughter", "Homer"},
                {"Lisa", "son", "Homer"}
        };

        List<String> paths = obj.getRelationSequence(relations, "Bart", "Homer");
        for (String path : paths)
            System.out.println(path);
    }

}
