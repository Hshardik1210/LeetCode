class Solution {
    vector<vector<int>>result;
public:
    vector<vector<int>> allPathsSourceTarget(vector<vector<int>>& graph) {
        vector<int>visited(graph.size(),false);
        vector<int>path;
        int source = 0;
        int destination = graph.size()-1;
        dfs(graph, visited, source, destination, path);
        return result;
    }
    
    void dfs(vector<vector<int>>&graph, vector<int>&visited, int source, int destination, vector<int>&path) {

        path.push_back(source);
        if(source==destination) {
            result.push_back(path);
            return;
        }
        
        visited[source] = true;
        for(int i=0;i<graph[source].size();i++) {
            if(!visited[graph[source][i]]) {
                dfs(graph, visited, graph[source][i], destination, path);
                path.pop_back();
            }
        }
        visited[source] = false;
    }
};