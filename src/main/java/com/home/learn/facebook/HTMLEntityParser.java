package com.home.learn.facebook;

public class HTMLEntityParser {
    public String entityParser(String text) {
        StringBuilder sb = new StringBuilder();
        int n = text.length();
        char[] sc = text.toCharArray();
        for (int i = 0; i < n; i++){
            char c = sc[i];
            if (c != '&'){
                sb.append(c);
                continue;
            }
            if (i + 3 < n){
                char c1 = sc[i + 1];
                char c2 = sc[i + 2];
                char c3 = sc[i + 3];
                if (c2 == 't' && c3 == ';') {
                    if (c1 == 'g') {
                        sb.append('>');
                        i += 3;
                        continue;
                    } else if (c1 == 'l') {
                        sb.append('<');
                        i += 3;
                        continue;
                    }
                }
                if (i + 4 < n) {
                    char c4 = sc[i + 4];
                    if (c1 == 'a' && c2 == 'm' && c3 == 'p' && c4 == ';'){
                        sb.append('&');
                        i += 4;
                        continue;
                    }
                    if (i + 5 < n){
                        char c5 = sc[i + 5];
                        if (c3 == 'o' && c5 == ';' ){
                            if (c1 == 'q' && c2 == 'u' && c4 == 't'){
                                sb.append('\"');
                                i += 5;
                                continue;
                            }
                            if (c1 == 'a' && c2 == 'p' && c4 == 's'){
                                sb.append('\'');
                                i += 5;
                                continue;
                            }
                        }
                        if (i + 6 < n){
                            if (c1 == 'f' && c2 == 'r' && c3 == 'a' && c4 == 's' && c5 == 'l' && sc[i + 6] == ';'){
                                sb.append("/");
                                i += 6;
                                continue;
                            }
                        }
                    }
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
