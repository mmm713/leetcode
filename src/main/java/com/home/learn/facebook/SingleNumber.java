package com.home.learn.facebook;

public class SingleNumber {
    //所有人2次，找出现1次的
    public int singleNumber(int[] nums) {
        if(nums.length < 1) {
            return 0;
        }
        int result = nums[0];
        for(int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    //所有人3次，一个人一次
    //用3个整数来表示 INT 的各位的出现次数情况，one 表示出现了1次，two 表示出现了2次。当出现3次的时候该位清零。最后答案就是one的值。
    //
    //ones   代表第 ith 位只出现一次的掩码变量
    //twos  代表第 ith 位只出现两次次的掩码变量
    //threes  代表第 ith 位只出现三次的掩码变量
    //
    //假设现在有一个数字1，更新 one 的方法就是 ‘亦或’ 这个1，则 one 就变成了1，而 two 的更新方法是用上一个状态下的 one 去 ‘与’ 上数字1，
    // 然后 ‘或’ 上这个结果，这样假如之前 one 是1，那么此时 two 也会变成1，这 make sense，因为说明是当前位遇到两个1了；
    // 反之如果之前 one 是0，那么现在 two 也就是0。注意更新的顺序是先更新 two，再更新 one，不理解的话只要带个只有一个数字1的输入数组看一下就不难理解了。
    //
    // 然后更新 three，如果此时 one 和 two 都是1了，由于先更新的 two，再更新的 one，two 为1，说明此时至少有两个数字1了，而此时 one 为1，
    // 说明了此时已经有了三个数字1，这块要仔细想清楚，因为 one 是要 ‘亦或’ 一个1的，值能为1，说明之前 one 为0，实际情况是，当第二个1来的时候，
    // two 先更新为1，此时 one 再更新为0，下面 three 就是0了，那么 ‘与’ 上 three 的相反数1不会改变 one 和 two 的值；那么当第三个1来的时候，
    // two 还是1，此时 one 就更新为1了，那么 three 就更新为1了，此时就要清空 one 和 two 了，让它们 ‘与’ 上 three 的相反数0即可，最终结果将会保存在 one 中，
    public int singleNumberII(int[] nums) {
        int ones = 0, twos = 0, threes;
        for (int num : nums) {
            twos |= ones & num;
            ones ^= num;
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }


    //两个人出现一次，其余人出现2次
    //首先我们先把原数组全部异或起来，那么我们会得到一个数字，这个数字是两个不相同的数字异或的结果，
    // 我们取出其中任意一位为 ‘1’ 的位，为了方便起见，我们用 a &= -a 来取出最右端为 ‘1’ 的位，具体来说下这个是如何操作的吧。
    //
    // 就拿题目中的例子来说，如果我们将其全部 '异或' 起来，我们知道相同的两个数 '异或' 的话为0，那么两个1，两个2，都抵消了，
    // 就剩3和5 '异或' 起来，那么就是二进制的 11 和 101 '异或' ，得到110。
    //
    // 然后我们进行 a &= -a 操作。首先变负数吧，在二进制中负数采用补码的形式，而补码就是反码 +1，
    // 那么 110 的反码是 11...1001，那么加1后是 11...1010，然后和 110 相与，得到了 10，就是代码中的 diff 变量。
    //
    // 得到了这个 diff，就可以将原数组分为两个数组了。为啥呢，我们想阿，如果两个相同的数字 '异或' ，每位都会是0，
    // 而不同的数字 '异或' ，一定会有对应位不同，一个0一个1，这样 '异或' 是1。比如3和5的二进制 11 和 101，
    // 如果从低往高看，最开始产生不同的就是第二位，那么我们用第二位来和数组中每个数字相与，根据结果的不同，一定可以把3和5区分开来，
    //
    // 而其他的数字由于是成对出现，所以区分开来也是成对的，最终都会 '异或' 成0，不会3和5产生影响。
    // 分别将两个小组中的数字都异或起来，就可以得到最终结果了
    public int[] singleNumberIII(int[] nums) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // pick one bit as flag
        diff &= -diff;
        int[] res = new int[2];
        for (int num : nums) {
            if ((num & diff) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }
}
