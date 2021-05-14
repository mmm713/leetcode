package com.home.learn.facebook;

public class IntegerToEnglishWords {

    private static final String[] UNIT1 = new String[] {
            "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };
    private static final String[] UNIT2 = new String[] {
            "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public String numberToWords(int num) {
        if (num == 0) return UNIT1[0];
        StringBuilder sb = new StringBuilder();
        int u = num % 1000; num/=1000;
        int x = num % 1000; num/=1000;
        int y = num % 1000; num/=1000;
        int z = num;
        if (z > 0)
            baseConvert(sb, z).append(" Billion");
        if (y > 0)
            baseConvert(sb, y).append(" Million");
        if (x > 0)
            baseConvert(sb, x).append(" Thousand");
        baseConvert(sb, u);
        return sb.toString().trim();
    }

    private StringBuilder baseConvert(StringBuilder sb, int num) {
        int a = num % 10; num /= 10;
        int b = num % 10; num /= 10;
        int c = num % 10;
        if (c > 0)
            sb.append(" ").append(UNIT1[c]).append(" Hundred");
        if (b == 1) {
            sb.append(" ").append(UNIT1[b * 10 + a]);
            return sb;
        }
        if (b > 1)
            sb.append(" ").append(UNIT2[b]);
        if (a > 0)
            sb.append(" ").append(UNIT1[a]);
        return sb;
    }
}
