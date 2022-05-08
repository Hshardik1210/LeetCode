class TrieNode {
    constructor(){
        this.children = {};
        this.end_of_word = false;   
    } 
}

var WordDictionary = function() {
    this.root = new TrieNode();
};

/** 
 * @param {string} word
 * @return {void}
 */
WordDictionary.prototype.addWord = function(word) {
    let curr = this.root;
    for (let ch of word){
        if (!(ch in curr.children)){
            curr.children[ch] = new TrieNode();
        }
        curr = curr.children[ch];
        
    }
    curr.end_of_word = true;
};

/** 
 * @param {string} word
 * @return {boolean}
 */
WordDictionary.prototype.search = function(word) {
    var dfs = function(idx, node) {
        let trie_node = node;
        for (let x = idx; x < word.length; x++) {
            let ch = word[x];
            if (ch === '.') {
                for (let child of Object.values(trie_node.children)) {
                    if (dfs(x+1, child) !== false) return true;
                }
                return false;
            } else {
                if (!(ch in trie_node.children)) return false;
                trie_node = trie_node.children[ch];
            }
        }
        return trie_node.end_of_word;
    }
    return dfs(0, this.root);
};