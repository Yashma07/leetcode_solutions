class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        // 前面照旧算出 diff、chances;把 diff 放进计数数组
  long[] cnt = new long[100001];   // diff 值域 0..1e5
  int n = nums1.length;
  long sumDiff = 0;
  int maxDiff = 0;
  for (int i = 0; i < n; i++) {
      int d = Math.abs(nums1[i] - nums2[i]);
      cnt[d]++;
      sumDiff += d;
      maxDiff = Math.max(maxDiff, d);
  }
  long chances = (long) k1 + k2;
  if (sumDiff <= chances) return 0;

  // 从最高层往下压平
  for (int v = maxDiff; v >= 1 && chances > 0; v--) {
      if (cnt[v] == 0) continue;
      long here = cnt[v];            // 这一层有几个
      long move = Math.min(here, chances);  // 这一层最多能压几个(受预算限制)
      cnt[v] -= move;                // 被压的从 v 层离开
      cnt[v-1] += move;              // 落到 v-1 层
      chances -= move;               // 扣预算
  }

  long ans = 0;
  for (int v = 1; v <= maxDiff; v++) {
      ans += cnt[v] * (long) v * v;
  }
  return ans;

    }
}