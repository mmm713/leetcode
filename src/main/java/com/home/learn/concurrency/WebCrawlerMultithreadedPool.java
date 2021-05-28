package com.home.learn.concurrency;

import com.home.learn.library.HtmlParser;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebCrawlerMultithreadedPool {
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    Set<String> visited = Collections.synchronizedSet(new HashSet<>());

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        visited.add(startUrl);
        List<String> links = htmlParser.getUrls(startUrl);
        int index1 = startUrl.indexOf("//");
        int index2 = startUrl.indexOf("/",index1 + 2);
        String domain = startUrl;
        if(index2 != -1) {
            domain = startUrl.substring(0, index2);
        }
        List<Future<List<String>>> futures =new ArrayList<>();
        for(String link:links) {
            if(link.startsWith(domain) && !visited.contains(link)) {
                futures.add(pool.submit(()-> crawl(link, htmlParser)));
            }
        }

        return new ArrayList<>(visited);
    }
}
