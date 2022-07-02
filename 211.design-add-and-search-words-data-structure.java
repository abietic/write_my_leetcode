/*
 * @lc app=leetcode id=211 lang=java
 *
 * [211] Design Add and Search Words Data Structure
 */

// @lc code=start
class WordDictionary {

    private static class TireNode {
        public TireNode[] children;
        public boolean[] isWord;
        public TireNode() {
            children = new TireNode[26];
            isWord = new boolean[26];
        }
        
    }


    private TireNode root;

    // 应该使用Tire和一些搜索技巧就能完成
    // 直接将.替换成26个字母依次尝试的方法应该不太行，以为30^3 * 10^4 = 2.7 * 10^8
    public WordDictionary() {
        root = new TireNode();
    }
    private static TireNode _searchForAddWord(byte[] cs, TireNode root) {
        TireNode cur = root, tmp;
        for (int i = 0; i < cs.length; ++i) {
            // 应该匹配的字符节点的索引值
            int idx = cs[i] - 'a';
            if (i == cs.length - 1) {
                // 如果已经是最后一个字符了，不需要子节点
                // 标识有相应单词即可
                cur.isWord[idx] = true;
            } else if (cur.children[idx] == null) {
                // 如果单词还未结束，需要继续创造子节点进行单词的记录
                tmp = new TireNode();
                cur.children[idx] = tmp;
                cur = tmp;
            } else {
                // 与其它已经输入的单词有公共前缀
                cur = cur.children[idx];
            }
        }
        return cur;
    }
    public void addWord(String word) {
         _searchForAddWord(word.getBytes(), root);
    }
    private static boolean _search(byte[] cs, int start, TireNode root) {
        TireNode cur = root;
        for (int i = start; i < cs.length; ++i) {
            if (cs[i] == '.') {
                // 如果是通配符，对于所有存在的子节点进行递归查询，
                // 如果通配符正好是最后一个字符，找一个布尔值为正的即可
                if (i == cs.length - 1) {
                    for (boolean chance : cur.isWord) {
                        if (chance) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    for (TireNode child : cur.children) {
                        if (child != null && _search(cs, i + 1, child)) {
                            return true;
                        }
                    }
                    return false;
                }
            } else {
                int idx = cs[i] - 'a';
                if (i == cs.length - 1) {
                    return cur.isWord[idx];
                } else if (cur.children[idx] == null) {
                    return false;
                } else {
                    cur = cur.children[idx];
                }
            }
        }
        // 应该是到达不了的，因为单词长度至少为1
        return false;
    }
    public boolean search(String word) {
        return _search(word.getBytes(), 0, root);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
// @lc code=end

