package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllNodesDistanceKBinaryTree {
    // 在进行递归之前，我们要先判断一个 corner case，那就是当 K==0 时，此时要返回的就是目标结点值本身，可以直接返回。
    // 否则就要进行递归了。这里的递归函数跟之前的有所不同，是需要返回值的，这个返回值表示的含义比较复杂，
    //
    // 若为0，表示当前结点为空或者当前结点就是距离目标结点为K的点，此时返回值为0，是为了进行剪枝，使得不用对其左右子结点再次进行递归。
    // 当目标结点正好是当前结点的时候，递归函数返回值为1，其他的返回值为当前结点离目标结点的距离加1。
    // 还需要一个参数 dist，其含义为离目标结点的距离，注意和递归的返回值区别，这里不用加1，且其为0时候不是为了剪枝，而是真不知道离目标结点的距离。
    //
    // 在递归函数中，首先判断若当前结点为空，则直接返回0。
    // 然后判断 dist 是否为k，是的话，说目标结点距离当前结点的距离为K，
    // 是符合题意的，需要加入结果 res 中，并返回0，注意这里返回0是为了剪枝。
    // 否则判断，若当前结点正好就是目标结点，或者已经遍历过了目标结点（表现为 dist 大于0），
    // 那么对左右子树分别调用递归函数，并将返回值分别存入 left 和 right 两个变量中。注意此时应带入 dist+1，
    // 因为是先序遍历，若目标结点之前被遍历到了，那么说明目标结点肯定不在当前结点的子树中，
    // 当前要往子树遍历的话，肯定离目标结点又远了一些，需要加1。若当前结点不是目标结点，
    // 也还没见到目标结点时，同样也需要对左右子结点调用递归函数，但此时 dist 不加1，
    // 因为不确定目标结点的位置。若 left 或者 right 值等于K，则说明目标结点在子树中，
    // 且距离当前结点为K（为啥呢？因为目标结点本身是返回1，所以当左右子结点返回K时，和当前结点距离是K）。
    //
    // 接下来判断，若当前结点是目标结点，直接返回1，这个前面解释过了。然后再看 left 和 right 的值是否大于0，
    // 若 left 值大于0，说明目标结点在左子树中，我们此时就要对右子结点再调用一次递归，并且 dist 带入 left+1，
    // 同理，若 right 值大于0，说明目标结点在右子树中，我们此时就要对左子结点再调用一次递归，并且 dist 带入 right+1。
    // 这两步很重要，是之所以能不建立邻接链表的关键所在。若 left 大于0，则返回 left+1，若 right 大于0，则返回 right+1，否则就返回0
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        //演戏可以忽略此判定
        if(k == 0) return Collections.singletonList(target.val);
        List<Integer> res = new ArrayList<>();
        dfs(root, target, k, 0, res);
        return res;
    }

    private int dfs(TreeNode root, TreeNode target, int k, int dist, List<Integer> res) {
        if(root == null) return 0;
        if(dist == k) {
            res.add(root.val);
            return 0;
        }
        int l, r;
        boolean foundOrLooking = root.val == target.val || dist > 0;
        l = dfs(root.left, target, k, dist + (foundOrLooking ? 1 : 0), res);
        r = dfs(root.right, target, k, dist + (foundOrLooking ? 1 : 0), res);
        if(root.val == target.val) {
            return 1;
        }
        //演戏可以忽略此判定
        if(l == k || r == k) {
            res.add(root.val);
            return 0;
        }
        if(l > 0) {
            dfs(root.right, target, k, l + 1, res);
        } else if(r > 0) {
            dfs(root.left, target, k, r + 1, res);
        }
        if(l > 0 || r > 0) {
            return l > 0 ? ++l : ++r;
        } else {
            return 0;
        }
    }
}
