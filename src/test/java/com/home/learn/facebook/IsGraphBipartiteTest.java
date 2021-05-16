package com.home.learn.facebook;

import org.junit.jupiter.api.Test;

public class IsGraphBipartiteTest {
    @Test
    void test() {
        IsGraphBipartite bipartite = new IsGraphBipartite();
        int[][] graph = {{3},{2},{1,3},{0,2}};
        System.out.println(bipartite.isBipartiteUF(graph));
    }
}
