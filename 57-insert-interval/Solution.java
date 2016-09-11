/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> r = new ArrayList<Interval>();
        int i = 0;
        // insert all intervals before newInterval
        while(i<intervals.size() && intervals.get(i).end < newInterval.start){
            r.add(intervals.get(i));
            i++;
        }
        while(i<intervals.size() && intervals.get(i).start <= newInterval.end){
             newInterval.start = Math.min(newInterval.start, intervals.get(i).start);               
             newInterval.end = Math.max(newInterval.end, intervals.get(i).end);    
             i++;
        }
        r.add(newInterval);
        
        // insert all intervals after newInterval
        while(i<intervals.size() && newInterval.end < intervals.get(i).start){
            r.add(intervals.get(i));
            i++;
        }
        return r;
    }
}