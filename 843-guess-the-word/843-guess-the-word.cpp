/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Master {
 *   public:
 *     int guess(string word);
 * };
 */
class Solution {
public:
    int countMatch(string s1, string s2) {
        int count=0;
        for(int i=0;i<6;i++) {
            if(s1[i]==s2[i])
                count++;
        }
        return count;
    }
    
    vector<string> filterWordsWithMatchCount(vector<string>& wordlist, string word,int expectedCount) {
        vector<string>filteredWords;
        for(int i=0;i<wordlist.size();i++) {
            if(wordlist[i]!=word && countMatch(word,wordlist[i]) == expectedCount)
                filteredWords.push_back(wordlist[i]);
        }
        return filteredWords;
    }
    
    void findSecretWord(vector<string>& wordlist, Master& master) {
        srand(time(NULL));
        for(int i=0;i<10;i++) {
            int index = rand() % wordlist.size();
            string word = wordlist[index];
            int matchCount = master.guess(word);
            if(matchCount==6)
                return;
            wordlist = filterWordsWithMatchCount(wordlist, word, matchCount);
        }
    }
};