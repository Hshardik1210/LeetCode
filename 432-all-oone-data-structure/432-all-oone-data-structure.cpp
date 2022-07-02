class AllOne {
    unordered_map<string,int>freq;
    map<int,unordered_set<string>>indexMap;
public:
    AllOne() {
        
    }
    
    void inc(string key) {
        if(freq.find(key)==freq.end()) {
            freq[key]++;
            indexMap[freq[key]].insert(key);   
        } else {
            indexMap[freq[key]].erase(key);
            if(indexMap[freq[key]].size()==0)
                indexMap.erase(freq[key]);
            freq[key]++;
            indexMap[freq[key]].insert(key);
        }
    }
    
    void dec(string key) {
        if(freq.find(key)==freq.end())
            return;
        indexMap[freq[key]].erase(key);
        if(indexMap[freq[key]].size()==0)
            indexMap.erase(freq[key]);
        freq[key]--;
        if(freq[key]==0) {
            freq.erase(key);
        } 
        else
            indexMap[freq[key]].insert(key);
    }
    
    string getMaxKey() {
        return indexMap.empty()? "": *((indexMap.rbegin()->second).begin());
    }
    
    string getMinKey() {
        return indexMap.empty()? "": *((indexMap.begin()->second).begin());
    }
};

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne* obj = new AllOne();
 * obj->inc(key);
 * obj->dec(key);
 * string param_3 = obj->getMaxKey();
 * string param_4 = obj->getMinKey();
 */