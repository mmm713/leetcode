package com.home.learn.robinhood;

import java.util.ArrayList;
import java.util.List;

public class CodeSignal {
    public String aggregate_prices(String prices_to_parse) {
        String[] priceTime = prices_to_parse.split(",");
        if(priceTime.length <= 0) return "";
        StringBuilder sb = new StringBuilder();
        PriceState ps = new PriceState();
        ps.setStart(-10);
        for(int i = 0; i < priceTime.length; i++) {
            PriceTime current = new PriceTime(priceTime[i]);
            if(current.time > (ps.getStart() + 9)) {
                //flush states to output
                //find the new start time
                if(i > 0) {
                    generateOutput(sb, ps, false);
                    int gap = current.time / 10 * 10 - ps.getStart();
                    if(gap > 10) {
                        for(int j = ps.getStart() + 10; j < current.time / 10 * 10; j += 10) {
                            generateOutput(sb, ps, true);
                        }
                    }
                }
                ps = new PriceState(current.time / 10 * 10, current.price, current.price, current.price, current.price);
            } else {
                ps.max = Math.max(ps.max, current.price);
                ps.min = Math.min(ps.min, current.price);
                ps.last = current.price;
            }
        }
        generateOutput(sb, ps, false);
        return sb.toString();
    }

    private void generateOutput(StringBuilder sb, PriceState priceState, boolean missing) {
        if(missing) {
            sb.append("{").append(priceState.getStart()).append(",").append(priceState.getLast())
                    .append(",").append(priceState.getLast()).append(",")
                    .append(priceState.getLast()).append(",")
                    .append(priceState.getLast()).append("}");
        } else {
            sb.append("{").append(priceState.getStart()).append(",").append(priceState.getFirst())
                    .append(",").append(priceState.getLast()).append(",")
                    .append(priceState.getMax()).append(",")
                    .append(priceState.getMin()).append("}");
        }
    }

    static class PriceState{
        int start;
        int first;
        int last;
        int max;
        int min;

        public PriceState() {
        }

        public PriceState(int start, int first, int last, int max, int min) {
            this.start = start;
            this.first = first;
            this.last = last;
            this.max = max;
            this.min = min;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public int getLast() {
            return last;
        }

        public void setLast(int last) {
            this.last = last;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    static class PriceTime {
        int time;
        int price;

        public PriceTime(String s) {
            String[] ss = s.split(":");
            System.out.println(" time : " + ss[1] + " price is : " + ss[0]);
            this.time = Integer.parseInt(ss[1]);
            this.price = Integer.parseInt(ss[0]);
        }
    }

}
