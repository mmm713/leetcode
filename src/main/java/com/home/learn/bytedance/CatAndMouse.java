package com.home.learn.bytedance;

import java.util.Arrays;

public class CatAndMouse {
    /**
    这道题在无向图上模仿了猫抓老鼠的这一个过程，老鼠位于结点1，猫位于结点2，老鼠的目标是逃回老鼠洞结点0，猫的目标是在老鼠进洞之前抓住它。
    这里假设猫和老鼠都不是沙雕，都会选择最优的策略。若老鼠能成功逃回洞里，则返回1；若猫能成功抓到老鼠，则返回2；若谁也不能达到目标，则表示平局，返回0。
    其实这道题的本质还是一个无向图的遍历问题，只不过现在有两个物体在遍历，比一般的图遍历要复杂一些。
    假设图中有n个结点，不论是猫还是老鼠，当各自走完了n个结点时还没有分出胜负，则表示平局，若一人走一步，则最多有 2n 步。
    这样的话每一个状态实际上是由三个因素组成的：当前步数，老鼠所在结点，和猫所在结点。

    这里可以用动态规划 Dynamic Programming 来解，使用一个三维的 dp 数组，
    其中 dp[t][x][y] 表示当前步数为t，老鼠在结点x，猫在结点y时最终会返回的值，均初始化为 -1。
    要求的其实是起始状态 dp[0][1][2] 的返回值，但没法一下子求出，这个起始状态实际上是要通过其他状态转移过来。
    先来看一些终止状态，首先当老鼠到达洞口的时候，此时老鼠赢，返回值是1，即所有 dp[?][0][?] 状态的返回值都是1。
    其次，当猫和老鼠处于同一个位置时，表示猫抓到老鼠了，此时猫赢，返回值是2，即所有 dp[?][y][y] 状态的返回值都是2。
    最后，当走完了 2n 步还没有分出胜负的话，则是平局，直接返回0即可。

    理清了上面的思路，其实代码就不难写了，这里使用递归的写法，在递归函中，首先判断步数是否到了 2n，是的话直接返回0；
    否则判断x和y是否相等，是的话当前状态赋值为2并返回；
    否则再判断x是否等于0，是的话当前状态赋值为1并返回。
    若当前状态的 dp 值不是 -1，则表示之前已经更新过了，不需要重复计算了，直接返回即可。
    否则就要来计算当前的 dp 值，先确定当前该谁走，只要判断t的奇偶即可，因为最开始步数0的时候是老鼠先走。

    若此时该老鼠走了，它能走的相邻结点可以在 graph 中找到，对于每一个可以到达的相邻结点，都调用递归函数，此时步数是 t+1，老鼠位置为相邻结点，猫的位置不变。
    若返回值是1，表示老鼠赢，则将当前状态赋值为1并返回；
    若返回状态是2，此时不能立马返回猫赢，因为老鼠可以不走这个结点；
    若返回值是0，表示老鼠走这个结点是有平局的机会，但老鼠还是要争取赢的机会，
    所以此时用一个 bool 变量标记下猫肯定赢不了，但此时也不能直接返回，因为 Jerry 一直要追寻赢的机会。
    直到遍历完了所有可能性，老鼠最终还是没有赢，则看下之前那个 bool 型变量 catWin，若为 true，则标记当前状态为2并返回，反之，则标记当前状态为0并返回。

    若此时该猫走了，基本跟老鼠的策略相同，它能走的相邻结点也可以在 graph 中找到，对于每一个可以到达的相邻结点，首先要判断是否为结点0（老鼠洞），因为猫是不能进洞的，所以要直接跳过这个结点。
    否则就调用递归函数，此时步数是 t+1，老鼠位置不变，猫的位置为相邻结点。
    若返回值是2，表示猫赢，则将当前状态赋值为2并返回；
    若返回状态是1，此时不能立马返回老鼠赢，因为猫可以不走这个结点；
    若返回值是0，表示猫走这个结点是有平局的机会，但猫还是要争取赢的机会，
    所以此时用一个 bool 变量标记下老鼠肯定赢不了，但此时也不能直接返回，因为 Tom 也一直要追寻赢的机会。
    直到遍历完了所有可能性，猫最终还是没有赢，则看下之前那个 bool 型变量 mouseWin，若为 true，则标记当前状态为1并返回，反之，则标记当前状态为0并返回，


    int catMouseGame(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<vector<vector<int>>> dp(2 * n, vector<vector<int>>(n, vector<int>(n, -1)));
        return helper(graph, 0, 1, 2, dp);
    }
    int helper(vector<vector<int>>& graph, int t, int x, int y, vector<vector<vector<int>>>& dp) {
    	if (t == graph.size() * 2) return 0;
    	if (x == y) return dp[t][x][y] = 2;
    	if (x == 0) return dp[t][x][y] = 1;
    	if (dp[t][x][y] != -1) return dp[t][x][y];
    	bool mouseTurn = (t % 2 == 0);
    	if (mouseTurn) {
    		bool catWin = true;
    		for (int i = 0; i < graph[x].size(); ++i) {
    			int next = helper(graph, t + 1, graph[x][i], y, dp);
    			if (next == 1) return dp[t][x][y] = 1;
    			else if (next != 2) catWin = false;
    		}
    		if (catWin) return dp[t][x][y] = 2;
    		else return dp[t][x][y] = 0;
    	} else {
    		bool mouseWin = true;
    		for (int i = 0; i < graph[y].size(); ++i) {
    			if (graph[y][i] == 0) continue;
    			int next = helper(graph, t + 1, x, graph[y][i], dp);
    			if (next == 2) return dp[t][x][y] = 2;
    			else if (next != 1) mouseWin = false;
    		}
    		if (mouseWin) return dp[t][x][y] = 1;
    		else return dp[t][x][y] = 0;
    	}
    }
     */

	public int catMouseGame(int[][] graph) {
		int n = graph.length;
		int[][][] dp = new int[2 * n][n][n];
		for(int[][] d : dp)
			for(int[] p : d)
				Arrays.fill(p, -1);
		return dfs(graph, 0, 1, 2, dp);
	}

	private int dfs(int[][] graph, int t, int x, int y, int[][][] dp) {
		if(t == graph.length * 2) return 0;
		if (x == y) return dp[t][x][y] = 2;
		if (x == 0) return dp[t][x][y] = 1;
		if (dp[t][x][y] != -1) return dp[t][x][y];
		boolean mouseTurn = t % 2 == 0;
		int step = mouseTurn ? x : y;
		int target = mouseTurn ? 1 : 2;
		int lose = mouseTurn ? 2 : 1;
		boolean win = true;
		for (int i = 0; i < graph[step].length; i++) {
			if (!mouseTurn && graph[step][i] == 0) continue;
			int next = mouseTurn ? dfs(graph, t + 1, graph[step][i], y, dp) :
					dfs(graph, t + 1, x, graph[step][i], dp);
			if(next == target) return dp[t][x][y] = target;
			else if(next != lose) win = false;
		}
		if(win) return dp[t][x][y] = lose;
		else return dp[t][x][y] = 0;
	}
}
