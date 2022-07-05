class Node {
    public:
    int key;
    int val;
    Node* next;
};


class MyHashMap {
    vector<Node*> table;
    int mod = 1000; // Larger the value, Smaller the LinkedList chain size)
public:
    MyHashMap() {
        table = vector<Node*>(mod, nullptr);
    }
    
    void put(int key, int value) {
        int hash = key%mod;
        Node* head = table[hash];
        
        Node* t = head;
        while(t != nullptr) {
            if(t->key == key) break;
            t = t->next;
        }
        
        if(t == nullptr) {
        Node* newNode = new Node();
        newNode->key = key;
        newNode->val = value;
        newNode->next = head;
        head = newNode;
        table[hash] = head; 
        } else {
            t->val = value;
        }
    }
    
    int get(int key) {
        int hash = key%mod;
        Node* head = table[hash];
        Node* t = head;
        while(t != nullptr) {
            if(t->key == key) return t->val;
            t = t->next;
        }
        return -1;
    }
    
    void remove(int key) {
        int hash = key%mod;
        Node* head = table[hash];
        Node* t = head;
        if(t == nullptr) return;
        
        if(t->key == key) {
            table[hash] = head->next;
            return;
        }
        
        while(t->next != nullptr) {
            if(t->next->key == key) break;
            t = t->next;
        }
        
        if(t->next != nullptr) {
            auto n = t->next->next;
            t->next = n;
            table[hash] = head;
        }
    }
};