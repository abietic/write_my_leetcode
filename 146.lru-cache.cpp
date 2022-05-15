#include <list>
#include <unordered_map>
#include <tuple>
using namespace std;
/*
 * @lc app=leetcode id=146 lang=cpp
 *
 * [146] LRU Cache
 */

// @lc code=start
class LRUCache
{
private:
    // 用来存真正的键值对
    list<pair<int, int>> _kv_pair;
    // 用来快速查找键值对
    unordered_map<int, list<pair<int, int>>::iterator> _lru_map;
    // 大小限制
    const int _capacity;

public:
    LRUCache(int capacity) : _capacity(capacity), _lru_map(capacity)
    {
    }

    int get(int key)
    {
        if (_lru_map.find(key) != _lru_map.end())
        {
            // 如果查找的键值对存在，更新键值对的访问时间，并返回值
            _update(key);
            return _lru_map[key]->second;
        }
        return -1;
    }

    void put(int key, int value)
    {
        if (_lru_map.find(key) != _lru_map.end())
        {
            // 如果是一个替换操作不需要删除最近最少使用
            _update(key);
            _lru_map[key]->second = value;
            return;
        }
        if (_lru_map.size() == _capacity)
        {
            // 到达最大大小了，需要删除最近最少使用
            _shrink();
        }
        _kv_pair.push_front(make_pair(key, value));
        _lru_map.insert(make_pair(key, _kv_pair.begin()));
    }

private:
    // 链表从头到尾，逐渐越久未被访问
    void _update(int key)
    {
        // 当前键值对的迭代器
        auto update_iter = _lru_map[key]; 
        // 如果已经是最近访问的键值对了，不需要变动
        if (update_iter == _kv_pair.begin()) {
            return;
        }
        // 将当前迭代器对应的元素转移到临时链表中
        // 将键值对更新到链表头
        _kv_pair.splice(_kv_pair.begin(), _kv_pair, update_iter++, update_iter);
        // 更新快查中的迭代器
        _lru_map[key] = _kv_pair.begin();
    }
    void _shrink()
    {
        // 删除快查
        _lru_map.erase(_kv_pair.back().first);
        // 弹出键值对
        _kv_pair.pop_back();
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
// @lc code=end
