package com.home.learn.smartnews;

import java.util.*;

public class TopKFrequentInArray {
    public int[] topKFrequentPq(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<Frequent> rL = new PriorityQueue<>(Comparator.comparingInt(l -> l.value));
        for(int i = 0; i < nums.length;i++){
            int count = 1;
            while(i<nums.length-1 && nums[i]==nums[i+1]){count++;i++;}
            if(rL.size()<k) {
                Frequent f= new Frequent(nums[i], count);
                rL.add(f);
            } else {
                if (rL.peek().value<count) {
                    Frequent f= new Frequent(nums[i], count);
                    rL.poll();
                    rL.add(f);
                }
            }
        }
        int[] ll = new int[k];
        for(int i=0;i<k;i++){
            ll[i] =  rL.poll().key;
        }
        return ll;
    }

    static class Frequent{
        int key;
        int value;
        public Frequent(int key, int value){
            this.key=key;
            this.value=value;
        }
    }
    //桶排序做法
    public int[] topKFrequent(int[] nums, int k) {
        // frequency map of numbers
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        // declared bucket of size nums.length + 1 b/c frequncy max can be of number is nums.length
        List<Integer>[] freqBucket = new ArrayList[nums.length + 1];
        for(int num : map.keySet()) {
            int freq = map.get(num);
            if(freqBucket[freq] == null)
                freqBucket[freq] = new ArrayList<>();
            freqBucket[freq].add(num);
        }

        int[] result = new int[k];
        int index = 0;
        for(int i = freqBucket.length - 1; i >= 0; i--) {
            if(freqBucket[i] != null) {
                for (int n : freqBucket[i]) {
                    result[index++] = n;
                    if (index == k)  // check if we already  got k element return result
                        return result;
                }
            }
        }

        return result;
    }


    int[] unique;
    Map<Integer, Integer> count;

    public void swap(int a, int b) {
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }

    public int partition(int left, int right, int pivot_index) {
        int pivot_frequency = count.get(unique[pivot_index]);
        // 1. move pivot to end
        swap(pivot_index, right);
        int store_index = left;

        // 2. move all less frequent elements to the left
        for (int i = left; i <= right; i++) {
            if (count.get(unique[i]) < pivot_frequency) {
                swap(store_index, i);
                store_index++;
            }
        }

        // 3. move pivot to its final place
        swap(store_index, right);

        return store_index;
    }

    public void quickselect(int left, int right, int k_smallest) {
        /*
        Sort a list within left..right till kth less frequent element
        takes its place.
        */

        // base case: the list contains only one element
        if (left == right) return;

        // select a random pivot_index
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);

        // find the pivot position in a sorted list
        pivot_index = partition(left, right, pivot_index);

        // if the pivot is in its final sorted position
        if (k_smallest < pivot_index) {
            // go left
            quickselect(left, pivot_index - 1, k_smallest);
        } else if (k_smallest > pivot_index) {
            // go right
            quickselect(pivot_index + 1, right, k_smallest);
        }
    }

    public int[] topKFrequentV2(int[] nums, int k) {
        // build hash map : character and how often it appears
        count = new HashMap<>();
        for (int num: nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // array of unique elements
        int n = count.size();
        unique = new int[n];
        int i = 0;
        for (int num: count.keySet()) {
            unique[i] = num;
            i++;
        }

        // kth top frequent element is (n - k)th less frequent.
        // Do a partial sort: from less frequent to the most frequent, till
        // (n - k)th less frequent element takes its place (n - k) in a sorted array.
        // All element on the left are less frequent.
        // All the elements on the right are more frequent.
        quickselect(0, n - 1, n - k);
        // Return top k frequent elements
        return Arrays.copyOfRange(unique, n - k, n);
    }
}
