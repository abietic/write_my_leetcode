import java.util.HashMap;

/*
 * @lc app=leetcode id=87 lang=java
 *
 * [87] Scramble String
 */

// @lc code=start
class Solution {
    private static final class StringKeyPair {
        public final String s1,s2;

        public StringKeyPair(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((s1 == null) ? 0 : s1.hashCode());
            result = prime * result + ((s2 == null) ? 0 : s2.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            StringKeyPair other = (StringKeyPair) obj;
            if (s1 == null) {
                if (other.s1 != null)
                    return false;
            } else if (!s1.equals(other.s1))
                return false;
            if (s2 == null) {
                if (other.s2 != null)
                    return false;
            } else if (!s2.equals(other.s2))
                return false;
            return true;
        }
        
    }
    private static HashMap<StringKeyPair, Boolean> memo;
    private boolean _isScramble(String s1, String s2) {
        // if (s1.length() == 1) {
        //     return s1.charAt(0) == s2.charAt(0);
        // }
        if (s1.equals(s2)) {
            return true;
        }
        StringKeyPair keyPair = new StringKeyPair(s1, s2);
        Boolean res = memo.get(keyPair);
        if (res != null) {
            return res;
        }
        for (int i = 1; i < s1.length(); ++i) {
            String s1Sub1 = s1.substring(0, i),s1Sub2 = s1.substring(i, s1.length()), s2Sub1 = s2.substring(0, i), s2Sub2 = s2.substring(i, s2.length()), s2Sub3 = s2.substring(s2.length() - i, s2.length()), s2Sub4 = s2.substring(0, s2.length() - i);
            if ((_isScramble(s1Sub1, s2Sub1) && _isScramble(s1Sub2, s2Sub2)) || (_isScramble(s1Sub1, s2Sub3)&&_isScramble(s1Sub2, s2Sub4))) {
                memo.put(keyPair, true);
                return true;
            }
        }
        memo.put(keyPair, false);
        return false;
    } 
    public boolean isScramble(String s1, String s2) {
        memo = new HashMap<>();
        return _isScramble(s1, s2);
    }
}
// @lc code=end

