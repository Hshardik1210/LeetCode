
class RandomizedCollection {
    unordered_map<int,int>m;
      vector<vector<int>> v;
public:
    RandomizedCollection() {
        
    }
    
    bool insert(int val) {
        bool res = m.find(val) == m.end();
        if(!res) {
            int index = m[val];
            v[index].push_back(val);
        } else {
            m[val] = v.size();
            vector<int>temp;
            temp.push_back(val);
            v.push_back(temp);
        }
        return res;
    }
    
    bool remove(int val) {
        if(m.find(val)==m.end())
            return false;
        int index = m[val];
        if(v[index].size()==1) {
            m[v[v.size()-1][0]] = index;
            swap(v[index],v[v.size()-1]);
            v.pop_back();
            m.erase(val);
        } else {
            v[index].pop_back();
        }
        return true;
        
    }
    
    int getRandom() {
        int row = rand()%v.size();
        int col = rand()%v[row].size();
        return v[row][col];
    }
};

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection* obj = new RandomizedCollection();
 * bool param_1 = obj->insert(val);
 * bool param_2 = obj->remove(val);
 * int param_3 = obj->getRandom();
 */