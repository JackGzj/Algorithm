package jianzhioffer;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class OtherTimu {

    public static void main(String[] args) {
        MyCalendar cal = new MyCalendar();
        cal.book(10, 30);
        cal.book(40, 50);
    }

    static class MyCalendar {
        TreeMap<Integer, Integer> calendarMap;

        public MyCalendar() {
            calendarMap = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            if (!calendarMap.isEmpty()) {
                Map.Entry<Integer, Integer> entry = calendarMap.floorEntry(start);
                if (entry != null && entry.getValue() > start) {
                    return false;
                }
                entry = calendarMap.ceilingEntry(start);
                if (entry != null && entry.getKey() < end) {
                    return false;
                }
            }
            calendarMap.put(start, end);
            return true;
        }
    }
}
