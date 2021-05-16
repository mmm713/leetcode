package com.home.learn.concurrency;

import com.home.learn.library.HtmlParser;

import java.util.*;

public class WebCrawlerMultithreaded {

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
        List<Thread> threads = new ArrayList<>();
        for(String link : links) {
            if(link.startsWith(domain) && !visited.contains(link)) {
                Thread t = new Thread(()-> crawl(link, htmlParser));
                threads.add(t);
                t.start();
            }
        }
        for(Thread t : threads) {
            try {
                t.join();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(visited);
    }
}
