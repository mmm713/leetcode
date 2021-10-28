package com.home.learn.doordash;

import com.home.learn.library.ListNode;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r1 = l1, r2 = l2, prev = null;
        int carry = 0;
        while(r1 != null) {
            int temp = r1.val;
            if(r2 != null) {
                r1.val = (r1.val + r2.val + carry) % 10;
                carry = (temp + r2.val + carry) / 10;
                if(r1.next == null)  {
                    r1.next = r2.next;
                    r2 = null;
                } else {
                    r2 = r2.next;
                }
            } else {
                r1.val = (r1.val + carry) % 10;
                carry = (carry + temp) / 10;
            }
            prev = r1;
            r1 = r1.next;
        }
        if(carry > 0)  {
            prev.next = new ListNode(carry);
        }
        return l1;
    }

    int calSum(int[] a, int[] b, int n, int m) {
        if (n >= m)
            return calSumUtil(a, b, n, m);
        else
            return calSumUtil(b, a, m, n);
    }
    // array形式，a比b长
    int calSumUtil(int[] a, int[] b, int n, int m) {
        // array to store sum.
        int[] sum= new int[n];
        int i = n - 1, j = m - 1, k = n - 1;
        int carry = 0, s;
        while (j >= 0) {
            s = a[i] + b[j] + carry;
            sum[k] = (s % 10);
            carry = s / 10;
            k--;
            i--;
            j--;
        }
        while (i >= 0) {
            s = a[i] + carry;
            sum[k] = (s % 10);
            carry = s / 10;
            i--;
            k--;
        }
        int ans = 0;
        if (carry == 1)
            ans = 10;
        // Converting array into number.
        for ( i = 0; i <= n - 1; i++) {
            ans += sum[i];
            ans *= 10;
        }
        return ans / 10;
    }

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}
