/*

https://leetcode.com/problems/min-cost-to-connect-all-points/description/?envType=daily-question&envId=2023-09-15
 * 1584. Min Cost to Connect All Points
Medium
4.5K
106
Companies
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

 

Example 1:


Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation: 

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.
Example 2:

Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18
 

Constraints:

1 <= points.length <= 1000
-106 <= xi, yi <= 106
All pairs (xi, yi) are distinct.
 */

class Pair{
    int first;
    int second;
    public Pair(int a,int b){
        this.first = a;
        this.second = b;
     }
}
/**
1. create the adj list with val for every node
2. do the prims algh

 */
class Solution {
    public int minCostConnectPoints(int[][] points) {
        List<List<Pair>> adjList = new ArrayList<>();
        int n = points.length;
        for(int i = 0;i<n;i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0;i<n;i++){
            int[] origin = points[i];
            // System.out.println("--");
            // System.out.println(Arrays.toString(origin));
            // System.out.println("--");
            List<Pair> ls = adjList.get(i);
            for(int j = 0;j<n;j++){
                if(j!=i){
                    int[] dest = points[j];
                    // System.out.println(Arrays.toString(dest));
                    Pair p = new Pair(Math.abs(origin[0]-dest[0])+Math.abs(origin[1]- dest[1]),j);
                    ls.add(p); 
                }
            }
        }
//this commented code is only to print the adj list for debugging purpose
        // for(var ls:adjList){
        //     for(var p:ls){
        //         System.out.println(p.first + " "+ p.second);
        //     }
        //     System.out.println("--");
        // }
        int mstSum = 0;
        //<wt,node>
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)-> a.first == b.first? a.second-b.second:
        a.first-b.first);
        pq.add(new Pair(0,0));
        int[] vis = new int[n];
        while(!pq.isEmpty()){
            Pair it = pq.poll();
            int currWt = it.first;
            int node = it.second;
            if(vis[node]==1){
                continue;
            }
            vis[node]=1;
            mstSum += currWt;
            for(Pair p: adjList.get(node)){
                int wtToget = p.first;
                int adjNode = p.second;
                if(vis[adjNode]==0){
                    pq.offer(new Pair(wtToget,adjNode));
                }
                // we are not marking the visited arr as visited when putting in becaus for same node
                //there can be a minimul value present in the pq, which will be picked when popping
            }
        }
        return mstSum;
        
    }
}