package com.home.learn.facebook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseWords {
    public String reverseWords(String s) {
        // remove leading spaces
        s = s.trim();
        // split by multiple spaces
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }


    public String reverseWordsV2(String s) {
        if (s == null) return null;

        char[] a = s.toCharArray();
        int n = a.length;

        // step 1. reverse the whole string
        reverse(a, 0, n - 1);
        // step 2. reverse each word
        reverseWords(a, n);
        // step 3. clean up spaces
        return cleanSpaces(a, n);
    }

    void reverseWords(char[] a, int n) {
        int i = 0, j = 0;

        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++; // skip spaces
            while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
            reverse(a, i, j - 1);                      // reverse the word
        }
    }

    // trim leading, trailing and multiple spaces
    String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;

        while (j < n) {
            while (j < n && a[j] == ' ') j++;             // skip spaces
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
            while (j < n && a[j] == ' ') j++;             // skip spaces
            if (j < n) a[i++] = ' ';                      // keep only one space
        }

        return new String(a).substring(0, i);
    }

    // reverse a[] from a[i] to a[j]
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }


    public String reverseWordsIII(String s) {
        String[] words = s.split(" ");
        StringBuilder res=new StringBuilder();
        for (String word: words)
            res.append(new StringBuffer(word).reverse().toString()).append(" ");
        return res.toString().trim();
    }


    public String reverseWordsIIIV2(String s) {
        char[] sc = s.toCharArray();
        int start = -1, end = -1;
        char tmp;
        for(int i = 0; i < sc.length; i++) {
            if(sc[i] == ' ') {
                if(start != -1) {
                    while(end > start) {
                        tmp = sc[start];
                        sc[start] = sc[end];
                        sc[end] = tmp;
                        end--;
                        start++;
                    }
                }
                start = -1;
                end = -1;
            } else {
                if(start == -1) {
                   start = i;
                   end = i;
                } else {
                    end++;
                }
            }
        }
        if(start != -1) {
            while(end > start) {
                tmp = sc[start];
                sc[start] = sc[end];
                sc[end] = tmp;
                end--;
                start++;
            }
        }
        return String.valueOf(sc);
    }
}
