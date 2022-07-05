/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {

    public int findCelebrity(int n) {
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < n; i++) {
            s.push(i);
        }

        while (s.size() > 1) {
            int i = s.pop();
            int j = s.pop();
            if (knows(i, j))
                s.push(j);
            else 
                s.push(i);
        }

        int x = s.pop();
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (i == x)
                continue;
            if (knows(x, i) || !knows(i, x))
                flag = false;
        }

        if (flag) 
            return x;
        return -1;
    }
}
