class MaxStack {
    map<int, vector<list<int>::iterator>>m;
    list<int>dl;
public:
    MaxStack() {
        
    }
    
    void push(int x) {
        dl.push_back(x);
        m[x].push_back(--dl.end());
    }
    
    int pop() {
        int top = this->top();
        dl.pop_back();
        m[top].pop_back();
        if(m[top].size()==0)
            m.erase(top);
        return top;
    }
    
    int top() {
        return *(dl.rbegin());
    }
    
    int peekMax() {
        return m.rbegin()->first;
    }
    
    int popMax() {
        int max = peekMax();
        auto it = m[max].back();
        dl.erase(it);
        m[max].pop_back();
        if(m[max].size()==0)
            m.erase(max);
        return max;
    }
};

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack* obj = new MaxStack();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->peekMax();
 * int param_5 = obj->popMax();
 */