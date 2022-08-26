/**
 * This is an implementation of MyCalendar.
 * Name: Ruoqing Song
 * Email: r2song@ucsd.edu
 * Sources used: Zybook
 *
 * This file contains the implementation of MyCalendar that
 * corresponds to TreeMap data structure, and some methods that
 * help to add events.
 */

/**
 * This class implements MyCalendar and its relevant methods
 * Instance Variables:
 * calendar -  the TreeMap that stores the booked events
 */
public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;

    /**
     * This method initialize the calendar object
     */
    public MyCalendar() {
        calendar = new MyTreeMap<Integer, Integer>();
    }

    /**
     * This method check if a new event can be added
     * according to the time windows
     * @param start the start time of the event
     * @param end the end time of the event
     * @return true if there is no time conflict
     */
    public boolean book(int start, int end) {
        if(start<0 || start >= end){
			throw new IllegalArgumentException();   
        }
        Integer floorKey = calendar.floorKey(start);
        Integer floorVal = calendar.get(floorKey);
        boolean valBool;
        if (floorVal != null){
            valBool = start >= floorVal;
        } else{
            valBool = true;
        }
        Integer ceilingKey = calendar.ceilingKey(start);
        if ((valBool) &&
                (ceilingKey == null || end <= ceilingKey)){
            calendar.put(start, end);
            return true;
        }else{
            return false;
        }
    }

    /**
     * This method returns the calendar
     * @return MyTreeMap calendar
     */
    public MyTreeMap<Integer, Integer> getCalendar(){
        return this.calendar;
    }
}