class AllOne {
    HashMap<String, Node> map = new HashMap<>();
    DoubleLinkedList list = new DoubleLinkedList();

    public AllOne() {
        
    }
    
    public void inc(String key) {
        if(!map.containsKey(key)) { // new count = 1
            Node min = list.getMin();
            if(min!= null && min.count == 1) {
                min.strings.add(key);
                map.put(key, min);
            } else { // min count > 1, create new node
                Node n = new Node(1);
                n.strings.add(key);
                list.addNodeAfter(n, list.head);
                map.put(key, n);
            }  
        } else {
            Node n = map.get(key);
            int count = n.count + 1;
            if(n.next != list.tail && n.next.count == count) {// if new count node exists
                n.next.strings.add(key);
                map.put(key, n.next);
            } else { // create new node
                Node node = new Node(count);
                node.strings.add(key);
                list.addNodeAfter(node, n);
                map.put(key, node);
            }
            n.strings.remove(key); // remove the string from the node
            if(n.strings.isEmpty()) {
                list.deleteNode(n);
            }
        }
    }
    
    public void dec(String key) {
        Node n = map.get(key);
        int count = n.count - 1;
        
        if(count == 0) { // delete from map
            map.remove(key);
        } else {
            if(n.prev != list.head && n.prev.count == count) {// if new count node exists
                n.prev.strings.add(key);
                map.put(key, n.prev);
            } else { // create new node
                Node node = new Node(count);
                node.strings.add(key);
                list.addNodeBefore(node, n);
                map.put(key, node);
            }
        }
        n.strings.remove(key); // remove the string from the node
        if(n.strings.isEmpty()) {
            list.deleteNode(n);
        }
    }
    
    public String getMaxKey() {
        Node max = list.getMax();
        if(max!= null) return max.strings.get(0);
        return "";
    }
    
    public String getMinKey() {
        Node min = list.getMin();
        if(min!= null) return min.strings.get(0);
        return "";
    }
}

class DoubleLinkedList {
    Node head;
    Node tail;
    DoubleLinkedList() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }
    
    Node getMin() {
        if(head.next != tail) {
            return head.next;
        } else {
            return null;
        }
    }
    
    Node getMax() {
        if(tail.prev != head) {
            return tail.prev;
        } else {
            return null;
        }
    }
    
    void addNodeAfter(Node n, Node curNode) {
        n.prev = curNode;
        n.next = curNode.next;
        curNode.next.prev = n;
        curNode.next = n;
    }
    
    void addNodeBefore(Node n, Node curNode) {
        n.prev = curNode.prev;
        n.next = curNode;
        curNode.prev.next = n;
        curNode.prev = n;
    }
    
    void deleteNode(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
}

class Node {
    int count;
    List<String> strings = new ArrayList<>();
    Node prev=null;
    Node next=null;
    
    public Node(int count) {
        this.count = count;
    }
}