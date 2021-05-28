package com.home.learn.facebook;

import com.home.learn.library.HtmlParser;

import java.util.*;

public class WebCrawler {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        HashSet<String> visited = new HashSet<>();
        List<String> result = new ArrayList<>();
        Queue<String> pending = new ArrayDeque<>();

        int count = 0;
        int i = 0;
        while (i < startUrl.length()) {
            if (startUrl.charAt(i) == '/') ++count;
            if (count == 3) break;
            ++i;
        }
        String prefix = startUrl.substring(0, i);

        pending.add(startUrl);
        visited.add(startUrl);
        while (pending.size() > 0) {
            String current = pending.poll();
            result.add(current);
            for (String newUrl : htmlParser.getUrls(current))
                if (!visited.contains(newUrl)){
                    if (newUrl.startsWith(prefix)) pending.add(newUrl);
                    visited.add(newUrl);
                }
        }

        return result;
    }
}
