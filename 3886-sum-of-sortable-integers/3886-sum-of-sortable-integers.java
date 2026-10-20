class Solution {
    public int sortableIntegers(int[] nums) {

        int n = nums.length;

        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];
        int[] drops = new int[n];

        prefixMax[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }

        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        for (int i = 1; i < n; i++) {
            drops[i] = drops[i - 1];

            if (nums[i - 1] > nums[i]) {
                drops[i]++;
            }
        }

        int answer = 0;

        for (int k = 1; k <= n; k++) {

            if (n % k != 0) {
                continue;
            }

            boolean possible = true;

            // Check boundaries between blocks
            for (int i = k; i < n; i += k) {
                if (prefixMax[i - 1] > suffixMin[i]) {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            // Check whether every block can be cyclically sorted
            for (int start = 0; start < n; start += k) {

                int end = start + k - 1;

                int insideDrops = drops[end] - drops[start];

                int circularDrop = 0;

                if (nums[end] > nums[start]) {
                    circularDrop = 1;
                }

                if (insideDrops + circularDrop > 1) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                answer += k;
            }
        }

        return answer;
    }
}