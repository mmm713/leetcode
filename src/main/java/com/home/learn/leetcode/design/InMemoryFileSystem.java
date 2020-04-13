package com.home.learn.leetcode.design;

import java.util.*;

public class InMemoryFileSystem {
    static class InMemFile {
        boolean isfile = false;
        Map<String, InMemFile> files = new HashMap<>();
        String content = "";
    }

    InMemFile root;

    public InMemoryFileSystem() {
        root = new InMemFile();
    }

    public List<String> ls(String path) {
        InMemFile t = root;
        List<String> files = new ArrayList<>();
        if (!path.equals("/")) {
            String[] d = path.split("/");
            for (int i = 1; i < d.length; i ++) {
                t = t.files.get(d[i]);
            }
            if (t.isfile) {
                files.add(d[d.length - 1]);
                return files;
            }
        }
        List<String> res_files = new ArrayList<>(t.files.keySet());
        Collections.sort(res_files);
        return res_files;
    }

    public void mkdir(String path) {
        InMemFile t = root;
        String[] d = path.split("/");
        for (int i = 1; i < d.length; i ++) {
            if (!t.files.containsKey(d[i])) {
                t.files.put(d[i], new InMemFile());
            }
            t = t.files.get(d[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        InMemFile t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.files.get(d[i]);
        }
        if (!t.files.containsKey(d[d.length - 1]))
            t.files.put(d[d.length - 1], new InMemFile());
        t = t.files.get(d[d.length - 1]);
        t.isfile = true;
        t.content = t.content + content;
    }

    public String readContentFromFile(String filePath) {
        InMemFile t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.files.get(d[i]);
        }
        return t.files.get(d[d.length - 1]).content;
    }
}
