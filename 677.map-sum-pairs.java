/*
 * @lc app=leetcode id=677 lang=java
 *
 * [677] Map Sum Pairs
 */

// @lc code=start
class MapSum {

    private static class TireNode {
        public CharSlot[] charSlots;

        public TireNode() {
            charSlots = new CharSlot[26];
        }
        
    }

    private static class CharSlot {
        public int sum;
        public TireNode next;
        public int wordVal; // 只有在是单词的时候才不为0
        public CharSlot() {
            sum = 0;
            next = null;
            wordVal = 0;
        }
    }

    private TireNode root;

    private TireNode _insert(String key, int val, TireNode node) {
        if (node == null) {
            // 添加节点
            node = new TireNode();
        }
        int slotIndex = key.charAt(0) - 'a';
        if (node.charSlots[slotIndex] == null) {
            // 添加字符槽
            node.charSlots[slotIndex] = new CharSlot();
        }
        if (key.length() == 1) {
            // 更新键值对
            node.charSlots[slotIndex].sum -= node.charSlots[slotIndex].wordVal;
            node.charSlots[slotIndex].wordVal = val;
            // 更新前缀和
            node.charSlots[slotIndex].sum += node.charSlots[slotIndex].wordVal;
            return node;
        }
        node.charSlots[slotIndex].next = _insert(key.substring(1), val, node.charSlots[slotIndex].next);
        int neo_sum = 0;
        CharSlot[] to_cal = node.charSlots[slotIndex].next.charSlots;
        for (int i = 0; i < 26; ++i) {
            if (to_cal[i] == null) {
                continue;
            }
            neo_sum += to_cal[i].sum;
        }
        neo_sum += node.charSlots[slotIndex].wordVal;
        node.charSlots[slotIndex].sum = neo_sum;
        return node;
    }

    public MapSum() {
        root = new TireNode();
    }
    
    public void insert(String key, int val) {
        root = _insert(key, val, root);
    }
    
    public int sum(String prefix) {
        TireNode node = root;
        while(node != null) {
            int slotIndex = prefix.charAt(0) - 'a';
            CharSlot cs = node.charSlots[slotIndex];
            if (cs == null) {
                return 0;
            }
            if (prefix.length() == 1) {
                return cs.sum;
            }
            node = cs.next;
            prefix = prefix.substring(1);
        }
        return 0;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
// @lc code=end

