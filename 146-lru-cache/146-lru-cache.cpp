class LRUCache {
    int capacity;
    list<pair<int,int>>dq;
    unordered_map<int, list<pair<int,int>>::iterator>map;
public:
    LRUCache(int capacity) {
        this->capacity = capacity;
    }
    
    int get(int key) {
        if(map.find(key)!=map.end()) {
            int value = map[key]->second;
            dq.erase(map[key]);
            dq.push_back({key,value});
            map[key]=--dq.end();
            return value;
        }
        return -1;
    }
    
    void put(int key, int value) {
        if(map.find(key)!=map.end()) {
            dq.erase(map[key]);
        } else if (capacity==dq.size()) {
            int removeKey = dq.front().first;
            dq.pop_front();
            map.erase(removeKey);
        }
        dq.push_back({key,value});
        map[key]=--dq.end();
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */