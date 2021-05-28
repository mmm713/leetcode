package com.home.learn.facebook;

import java.util.Arrays;
import java.util.HashMap;

public class LongestSubstringContainingVowelsEvenCounts {
    int[] c_m = new int[]{1, 0, 0, 0, 2, 0, 0, 0, 4, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0};
    //可以利用array原理在于0 XOR 0永远为0，1 XOR 后会有奇偶变化
    public int findTheLongestSubstring(String s) {
        int[] m = new int[32];
        Arrays.fill(m, -1);

        int result = 0, mask = 0;
        for(int i = 0; i < s.length(); i++){
            mask ^= c_m[s.charAt(i) - 'a'];
            if(mask != 0 && m[mask] == -1)
                m[mask] = i;

            result = Math.max(result, i - m[mask]);
        }

        return result;
    }

    //利用奇偶相加特性与XOR相同
    //找到之前可以等于当前bit mask的结果，即可保证是偶数个
    public int findTheLongestSubstringMap(String s) {
        HashMap<Character,Integer> mp = new HashMap<>();
        mp.put('a',0);mp.put('e',1);mp.put('i',2);mp.put('o',3);mp.put('u',4);
        HashMap<Integer, Integer> mps = new HashMap<>();
        mps.put(0,-1);
        int msk=0, ans =0;
        for(int i=0;i<s.length();i++){
            if(mp.containsKey(s.charAt(i)))
                msk = msk^(1<<(mp.get(s.charAt(i))+1));
            if(mps.containsKey(msk))
                ans= Math.max(ans,i-mps.get(msk));
            else
                mps.put(msk,i);
        }
        return ans;
    }
}
