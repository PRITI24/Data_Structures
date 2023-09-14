/*

https://leetcode.com/problems/reconstruct-itinerary/description/?envType=daily-question&envId=2023-09-14
 * 332. Reconstruct Itinerary
Hard
5.5K
1.8K
Companies
You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.

 

Example 1:


Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
Output: ["JFK","MUC","LHR","SFO","SJC"]
Example 2:


Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 

Constraints:

1 <= tickets.length <= 300
tickets[i].length == 2
fromi.length == 3
toi.length == 3
fromi and toi consist of uppercase English letters.
fromi != toi
 * 
 */

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        /*
            hashmap of start and end destination list
        */
        String begin_airport="JFK";
        HashMap<String, PriorityQueue<String>> map=new HashMap<>();
        for(List<String> itr:tickets){
            if(!map.containsKey(itr.get(0))){

                PriorityQueue<String> pq = new PriorityQueue<>();

                map.put(itr.get(0), pq);

            }

            map.get(itr.get(0)).add(itr.get(1));
        }
        // for(Map.Entry<String, List<String>> em:hm.entrySet()){
        //     System.out.println("printing hashmap:"+em.getKey()+". value:"+em.getValue());
        // }
        LinkedList<String> output=new LinkedList<>();
        // output.add(begin_airport);
        // validItenary(output,begin_airport,hm,tickets.size());
        dfs("JFK",map,output);
        return output;
    }
    public void validItenary(List<String> output,String begin_airport,HashMap<String, List<String>> hm,int desire){
        List<String> temp=hm.get(begin_airport);
        System.out.println(begin_airport+". "+temp);
        if(temp==null || temp.size()<=0)return;
        Collections.sort(temp);
        String t=temp.get(0);
        temp.remove(0);
        hm.put(begin_airport,temp);
        output.add(t);
        System.out.println(begin_airport+". after adding"+temp+". "+output);
        begin_airport=t;
    }
    public void dfs(String s, Map<String, PriorityQueue<String>> map, LinkedList<String> list){

        PriorityQueue<String> pq = map.get(s);

        while(pq!=null && !pq.isEmpty()){

            dfs(pq.poll(), map, list);

        }

        list.addFirst(s);

    }
}