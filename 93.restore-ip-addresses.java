import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=93 lang=java
 *
 * [93] Restore IP Addresses
 */

// @lc code=start
class Solution {
    private List<String> _restoreIpAddresses(String s, int remainingParts) {
        List<String> res = new ArrayList<>();
        if (s.isEmpty()) {
            return res;
        }
        if (remainingParts == 1) {
            if (s.length() > 3) {
                return res;
            } else if (s.length() > 1 && s.charAt(0) == '0') {
                return res;
            } else if (Integer.parseInt(s) > 255) {
                return res;
            }
            res.add(s);
            return res;
        }
        List<String> last;
        if (s.charAt(0) == '0') {
            last = _restoreIpAddresses(s.substring(1), remainingParts - 1);
            for (String lastString : last) {
                res.add("0." + lastString);
            }
            return res;
        }
        last = _restoreIpAddresses(s.substring(1), remainingParts - 1);
        for (String lastString : last) {
            res.add(s.charAt(0) + "." + lastString);
        }
        if (s.length() >= 2) {
            last = _restoreIpAddresses(s.substring(2), remainingParts - 1);
            for (String lastString : last) {
                res.add(s.substring(0, 2) + "." + lastString);
            }
        }
        if (s.length() >= 3 && Integer.parseInt(s.substring(0, 3)) <= 255) {
            last = _restoreIpAddresses(s.substring(3), remainingParts - 1);
            for (String lastString : last) {
                res.add(s.substring(0, 3) + "." + lastString);
            }
        }
        return res;
    }

    public List<String> restoreIpAddresses(String s) {
        return _restoreIpAddresses(s, 4);
    }
}
// @lc code=end
