/*
 * @lc app=leetcode id=208 lang=java
 *
 * [208] Implement Trie (Prefix Tree)
 */

// @lc code=start
class Trie {
    private static class TrieNode {
        public boolean[] wordEnd;
        public TrieNode[] nextNode;
        public TrieNode() {
            wordEnd = new boolean[26];
            nextNode = new TrieNode[26];
        }
        
    }
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length() - 1; ++i) {
            char c = word.charAt(i);
            int pos = c - 'a';
            if (cur.nextNode[pos] == null) {
                cur.nextNode[pos] = new TrieNode();
            }
            cur = cur.nextNode[pos];
        }
        cur.wordEnd[word.charAt(word.length() - 1) - 'a'] = true;
    }
    
    public boolean search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length() - 1; ++i) {
            char c = word.charAt(i);
            int pos = c - 'a';
            if (cur.nextNode[pos] == null) {
                return false;
            }
            cur = cur.nextNode[pos];
        }
        return cur.wordEnd[word.charAt(word.length() - 1) - 'a'];    
    }
    
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (int i = 0; i < prefix.length() - 1; ++i) {
            char c = prefix.charAt(i);
            int pos = c - 'a';
            if (cur.nextNode[pos] == null) {
                return false;
            }
            cur = cur.nextNode[pos];
        }
        return cur.wordEnd[prefix.charAt(prefix.length() - 1) - 'a'] || cur.nextNode[prefix.charAt(prefix.length() - 1) - 'a'] != null;  
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
// @lc code=end

