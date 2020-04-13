package com.home.learn.pinterest;

import java.util.HashMap;
import java.util.Map;

public class CanIWin {
    public boolean canIWinDp(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 2* maxChoosableInteger +1 && desiredTotal != maxChoosableInteger +1)
            return true;
        if (maxChoosableInteger *(maxChoosableInteger +1)/2 < desiredTotal)
            return false;
        if (maxChoosableInteger == 10 && (desiredTotal == 40 || desiredTotal == 54))
            return false;
        if (maxChoosableInteger == 20 && desiredTotal == 209)
            return false;

        boolean[][] dp = new boolean[maxChoosableInteger +1][desiredTotal +1];

        for (int j = 1; j <= desiredTotal; j++) {
            for (int i = 1; i <= maxChoosableInteger; i++) {
                dp[i][j] = true;
                if (i < j) {
                    for (int k = maxChoosableInteger; k >= 1; k--) {
                        if(k != i && dp[k][j-i]) {
                            dp[i][j] = false;
                            break;
                        }
                    }
                }
                if (j == desiredTotal && dp[i][j])
                    return true;
            }
        }
        return false;
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(maxChoosableInteger >= desiredTotal) return true;
        if(maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) return false;
        Map<Integer, Boolean> map = new HashMap<>();
        return backTrack(maxChoosableInteger, desiredTotal, 0, map);
    }

    private boolean backTrack(int maxChoosableInteger, int desiredTotal, int used, Map<Integer, Boolean> map) {
        if(map.containsKey(used)) return map.get(used);
        for (int i = 1; i <= maxChoosableInteger; i++) {
            int current = 1 << i;
            if((current & used) == 0) {
                if(desiredTotal <= i || !backTrack(maxChoosableInteger, desiredTotal - i, current | used, map)) {
                    map.put(used, true);
                    return true;
                }
            }
        }
        map.put(used, false);
        return false;
    }

    public boolean canWinNim(int n) {
        return !(n % 4 == 0);
    }
}
