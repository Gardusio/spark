package model;

import java.io.Serializable;

public class TimeslotCriticalityPair implements Serializable {
    private final TimeSlot timeslot;
    private final Double criticality;
    
    public TimeslotCriticalityPair(TimeSlot t, Double c) {
        timeslot = t;
        criticality = c;
    }
    
    public TimeSlot getTimeslot() {
        return timeslot;
    }
    
    public Double getCriticality() {
        return criticality;
    }
    
    public static TimeslotCriticalityPair mostCriticalTimeslot(
            TimeslotCriticalityPair tc1,
            TimeslotCriticalityPair tc2) {
    
        TimeSlot t1 = tc1.getTimeslot();
        TimeSlot t2 = tc2.getTimeslot();

        Double crit1 = tc1.getCriticality();
        Double crit2 = tc2.getCriticality();
        
        if(crit1.equals(crit2)) {
            if (t1.sameHour(t2))
                return TimeSlot.earliestHour(t1, t2).equals(t1) ? tc1 : tc2;
            else
                return t1.getHourSlot().compareTo(t2.getHourSlot()) <= 0 ? tc1 : tc2;
        }
        
        return Double.max(crit1, crit2) == crit1  ? tc1 : tc2;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        TimeslotCriticalityPair that = (TimeslotCriticalityPair) o;
        
        if (timeslot != null ? !timeslot.equals(that.timeslot) : that.timeslot != null) return false;
        return criticality != null ? criticality.equals(that.criticality) : that.criticality == null;
    }
    
    @Override
    public int hashCode() {
        int result = timeslot != null ? timeslot.hashCode() : 0;
        result = 31 * result + (criticality != null ? criticality.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
        return "(timeslot = " + timeslot + ", criticality = " + criticality + ")";
    }
}
