package com.home.learn.pinterest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaximumLengthOfRepeatedSubarray {
    /*
    Time Complexity: O(M*N)O(M∗N), where M, NM,N are the lengths of A, B.
    Space Complexity: O(M*N)O(M∗N), the space used by dp.
     */
    public int findLengthDP(int[] A, int[] B) {
        int ans = 0;
        int[][] memo = new int[A.length + 1][B.length + 1];
        for (int i = A.length - 1; i >= 0; --i) {
            for (int j = B.length - 1; j >= 0; --j) {
                if (A[i] == B[j]) {
                    memo[i][j] = memo[i+1][j+1] + 1;
                    if (ans < memo[i][j]) ans = memo[i][j];
                }
            }
        }
        return ans;
    }

    /*
    Time Complexity: O((M + N) * \min(M, N) * \log{(\min(M, N))})O((M+N)∗min(M,N)∗log(min(M,N))),
    where M, NM,N are the lengths of A, B. The log factor comes from the binary search.
    The complexity of our naive check of a given \text{length}length is O((M+N) * \text{length})O((M+N)∗length),
     as we will create the seen strings with complexity O(M * \text{length})O(M∗length), then search
     for them with complexity O(N * \text{length})O(N∗length), and our total complexity when performing our check is the addition of these two.

    Space Complexity: O(M^2), the space used by seen.
     */
    public boolean check(int length, int[] A, int[] B) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i + length <= A.length; ++i) {
            seen.add(Arrays.toString(Arrays.copyOfRange(A, i, i+length)));
        }
        for (int j = 0; j + length <= B.length; ++j) {
            if (seen.contains(Arrays.toString(Arrays.copyOfRange(B, j, j+length)))) {
                return true;
            }
        }
        return false;
    }

    public int findLength(int[] A, int[] B) {
        int lo = 0, hi = Math.min(A.length, B.length) + 1;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (check(mi, A, B)) lo = mi + 1;
            else hi = mi;
        }
        return lo - 1;
    }
}
