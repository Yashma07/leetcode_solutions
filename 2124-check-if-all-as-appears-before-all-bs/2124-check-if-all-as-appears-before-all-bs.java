class Solution {
    public boolean checkString(String s) {
        int n = s.length(), lastAPos = -1, firstBPos = n;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'a') {
                lastAPos = i;
            } else if(s.charAt(i) == 'b' && firstBPos == n) {
                firstBPos = i;
            }
        }
        return lastAPos < firstBPos;
    }
}