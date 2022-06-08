class Solution
{
    public:

        void adjacencyList(vector<int> adj[], int u, int v)
        {
            adj[u].push_back(v);
            adj[v].push_back(u);
        }

    bool bfs(vector<int> adj[], int n, vector<bool> &visited, int source, int destination)
    {

        if (source == destination)
            return true;

        queue<int> q;
        q.push(source);
        while (!q.empty())
        {
            int front = q.front();

            for (auto x: adj[front])
            {
                if (x == destination)
                    return true;

                if (!visited[x])
                {
                    visited[x] = true;
                    q.push(x);
                }
            }
            q.pop();
        }

        return false;
    }

    bool dfs(vector<int> adj[], int n, vector<bool> &visited, int source, int destination)
    {
        if (source == destination)
            return true;
        visited[source] = true;
        for (int i = 0; i < adj[source].size(); i++)
        {
            if (visited[adj[source][i]] == false && dfs(adj, n, visited, adj[source][i], destination))
                return true;
        }
        return false;
    }

    bool validPath(int n, vector<vector < int>> &edges, int source, int destination)
    {
        vector<int> adj[n];

        for (int i = 0; i < edges.size(); i++)
        {
            adjacencyList(adj, edges[i][0], edges[i][1]);
        }

        vector<bool> visited(n, false);

        for (int i = 0; i < n; i++)
            if (visited[i] == false)
                if (dfs(adj, n, visited, source, destination))
                    return true;

        return false;
    }
};