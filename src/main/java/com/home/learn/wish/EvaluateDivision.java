package com.home.learn.wish;

import com.home.learn.library.UnionNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {

    static class UnionFind {
        Map<String, UnionNode<String>> map;

        public UnionFind() {
            map = new HashMap<>();
        }

        public UnionNode<String> find(String s) {
            if (!map.containsKey(s)) {
                return null;
            }
            UnionNode<String> n = map.get(s);
            UnionNode<String> pn = map.get(n.parent);
            if(!n.parent.equals(pn.parent)) {
                n.parent = pn.parent;
                n.ratio *= pn.ratio;
            }
            return n;
        }

        public void union(String s, String t, double r) {
            if (!map.containsKey(s) && !map.containsKey(t)) {
                map.put(s, new UnionNode<>(t, r));
                map.put(t, new UnionNode<>(t, 1.0));
            } else if (!map.containsKey(s)) {
                UnionNode<String> tn = find(t);
                map.put(s, new UnionNode<>(tn.parent, r * tn.ratio));
            } else if (!map.containsKey(t)) {
                UnionNode<String> sn = find(s);
                map.put(t, new UnionNode<>(sn.parent, sn.ratio / r));
            } else {
                UnionNode<String> rs = map.get(find(s).parent);
                UnionNode<String> rt = map.get(find(t).parent);
                rs.parent = rt.parent;
                rs.ratio = r / map.get(s).ratio * map.get(t).ratio;
            }
        }
    }

    public double[] calcEquation(List<List<String>> es, double[] vs, List<List<String>> qs) {
        UnionFind uf = new UnionFind();
        for (int i = 0; i < es.size(); i++) {
            uf.union(es.get(i).get(0), es.get(i).get(1), vs[i] );
        }

        double[] res = new double[qs.size()];

        for (int i = 0; i < qs.size(); i++) {
            UnionNode<String> ns = uf.find(qs.get(i).get(0));
            UnionNode<String> nt = uf.find(qs.get(i).get(1));
            if (ns == null || nt == null || !ns.parent.equals(nt.parent)){
                res[i] = -1.0;
            } else {
                res[i] = ns.ratio / nt.ratio;
            }
        }
        return res;
    }
}
