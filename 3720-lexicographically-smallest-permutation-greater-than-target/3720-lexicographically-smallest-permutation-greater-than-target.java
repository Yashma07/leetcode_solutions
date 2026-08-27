class Solution {
    public String lexGreaterPermutation(String s, String target) {
        // "smallest" that is strictly greater than
        // it means we match as far as we can
        // then when we can't match anymore, we go up.
        // after we go up on one, we do the rest by minimal lexicographical ordering.

        // 1. count chars in each
        // 2. match as far as you can, record this as maxPrefixLength or smth. (startIndex below)
        // 3. see if there is a higher character we can fill in with
        // if there is, we're good. go to step 4
        // if there is not a higher character, we matched too far.
        // try un-doing matching the last character. repeat step 3
        // until you get a valid prefix and a minimal character that beats
        // target lexicographically.
        // 4. lexicographical minimum from that point.
        // 5. if we never found a result from 3 or 4, we couldn't complete the mission. return "".

        int[] sCount = new int[26];

        for(int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
        }

        // phase two
        // think of count in s as your bank, then you greedily pay out
        int startIndex = 0;
        while(startIndex < target.length() && sCount[target.charAt(startIndex) - 'a'] > 0) {
            sCount[target.charAt(startIndex) - 'a'] -= 1;
            startIndex++;
        }

        // phase three: we have to try and see if we can make a lexicographically larger value now.
        for(int i = startIndex; i >= 0; i--) {
            if(i < startIndex) {
                // pay back the character.
                sCount[target.charAt(i) - 'a']++;
            }

            // now we try and make a lexicographically better result.
            if(i < s.length()) {
                int targetChar = target.charAt(i) - 'a';
                for(int c = targetChar + 1; c < 26; c++) {
                    if(sCount[c] > 0) {
                        // we have found a smallest character that's bigger than the next thing in target
                        // and we still have it available to use. We have a result.
                        StringBuilder result = new StringBuilder();
                        result.append(target.substring(0, i)); // does not include index i - correct
                        result.append((char)(c + 'a'));
                        sCount[c]--;
                        
                        // now just construct the rest of the characters in the best way possible.
                        // fill in earliest possible characters as soon as we can.
                        for(int j = 0; j < 26; j++) {
                            while(sCount[j] > 0) {
                                result.append((char)(j + 'a'));
                                sCount[j]--;
                            }
                        }

                        return result.toString();
                    }
                }
            }
        }

        // could not complete the mission. we're screwed.
        return "";
    }
}