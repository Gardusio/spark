package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class TimeSlot implements Serializable {
    
    final private String dayOfTheWeek;
    final private String hourSlot;
    
    public TimeSlot(LocalDate date, String hour) {
        this(
                date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                hour.split(":")[0]
        );
    }
    
    public TimeSlot(String dayOfTheWeek, String hourSlot) {
        this.dayOfTheWeek = dayOfTheWeek;
        this.hourSlot = hourSlot;
    }
    
    public static TimeSlot earliestHour(TimeSlot t1, TimeSlot t2) {
        return Integer.parseInt(t1.getHourSlot()) < Integer.parseInt(t2.getHourSlot()) ? t1 : t2;
    }
    
    public boolean isInThisTimeslot(String timestamp) {
        return hourSlot.equals(timestamp.split(":")[0]);
    }
    
    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }
    
    public String getHourSlot() {
        return hourSlot;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        TimeSlot that = (TimeSlot) o;
        return this.hourSlot.equals(that.getHourSlot()) &&
                this.dayOfTheWeek.equals(that.getDayOfTheWeek());
    }
    
    @Override
    public int hashCode() {
        int result = dayOfTheWeek != null ? dayOfTheWeek.hashCode() : 0;
        result = 31 * result + (hourSlot != null ? hourSlot.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return "(" + dayOfTheWeek + ", " + hourSlot + ")";
    }
    
    public static Double maxCriticality(Double crit, Double crit1) {
        return Double.max(crit, crit1);
    }
    
    public boolean sameHour(TimeSlot t2) {
        return hourSlot.equals(t2.hourSlot);
    }
}
