package com.home.learn.leetcode;


import com.home.learn.library.MountainArray;

public class FindInMountainArray {

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length();
        /* Finds the index of the mountain's peak. */
        int peakIndex = getPeakIndex(0, len - 1, mountainArr);
        /* Searches for the target element to the left of the peak. */
        int leftSearch = getTarget(0, peakIndex, target, mountainArr, true);
        if (leftSearch != -1) {
            /* The target element was found to the left of the peak. */
            return leftSearch;
        } else {
		    /*
			  The target element was NOT found to the left of the peak,
			  so search for the target element to the right of the peak.
			*/
            return getTarget(peakIndex + 1, len - 1, target, mountainArr, false);
        }
    }

    private int getPeakIndex(int low, int high, MountainArray arr) {
        if (low > high) { return -1; }
        if (low == high) {
            /* The peak of the MountainArray is found. */
            return low;
        }
        int mid = ((high - low) / 2) + low;
        if (mid == 0) {
		    /*
		      The peak cannot be the first element in the MountainArray,
			  so continue searching on the right half of the MountainArray.
			*/
            return getPeakIndex(mid + 1, high, arr);
        }
        int left = arr.get(mid - 1);
        int curr = arr.get(mid);
        int right = arr.get(mid + 1);
        if (left < curr && curr > right) {
            /* The peak of the MountainArray is found. */
            return mid;
        } else if (left < curr && curr < right) {
            /* Continue searching on the right half of the MountainArray. */
            return getPeakIndex(mid + 1, high, arr);
        } else {
            /* Continue searching on the left half of the MountainArray. */
            return getPeakIndex(low, mid - 1, arr);
        }
    }

    /*
      The parameter 'left' indicates whether we are looking for the target element to the left of the mountain's peak.
      If 'left' is true, then we are looking for the target element to the left of the mountain's peak.
      If 'left' is false, then we are looking for the target element to the right of the mountain's peak.
    */
    private int getTarget(int low, int high, int target, MountainArray arr, boolean left) {
        if (low > high) { return -1; }
        if (low == high) {
		    /*
			  We have narrowed down the search to a single element.
			  If the element is the target, then return its index. Otherwise, return -1.
			*/
            return (arr.get(low) == target) ? low : -1;
        }
        int mid = ((high - low) / 2) + low;
        int curr = arr.get(mid);
        if (curr == target) {
            /* The target element is found. */
            return mid;
        } else if ((left && curr < target) || (!left && curr > target)) {
		    /*
			  If we're searching to the left of the peak and the current element is less than the target,
			  then continue searching on the right half of the MountainArray.
			  Similarly, if we're searching to the right of the peak and the current element is greater than the target,
			  then continue searching on the right half of the MountainArray.
		    */
            return getTarget(mid + 1, high, target, arr, left);
        } else {
            /* Continue searching on the left half of the MountainArray. */
            return getTarget(low, mid - 1, target, arr, left);
        }
    }
}
