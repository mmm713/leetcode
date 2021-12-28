package com.home.learn.smartnews;

import java.util.Arrays;

public class NextClosestTime {
    public String nextClosestTime(String time) {
        char[] res = time.toCharArray();
        char[] sc = new char[4];
        sc[0] = time.charAt(0);
        sc[1] = time.charAt(1);
        sc[2] = time.charAt(3);
        sc[3] = time.charAt(4);
        Arrays.sort(sc);
        for(int i = 4; i >= 0; i--) {
            if(res[i] == ':') {
                continue;
            }
            int pos = find(sc, res[i]);
            if (pos != sc.length - 1) {
                char next = sc[pos + 1];
                if (i == 4) {
                    res[i] = next;
                    break;
                } else if (i == 3 && next <= '5') {
                    res[i] = next;
                    break;
                } else if (i == 1 && (res[0] != '2' || next <= '3')) {
                    res[i] = next;
                    break;
                } else if (i == 0 && next <= '2') {
                    res[i] = next;
                    break;
                }
            }
            res[i] = sc[0];
        }
        return String.valueOf(res);
    }

    private int find(char[] sc, char a) {
        for(int i = 0; i < sc.length; i++) {
            if(i == sc.length - 1 || (sc[i] == a && sc[i + 1] != a)) return i;
        }
        return -1;
    }
}
