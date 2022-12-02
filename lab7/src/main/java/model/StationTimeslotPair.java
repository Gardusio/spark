package model;

import scala.Tuple2;

import java.io.Serializable;

public class StationTimeslotPair implements Serializable {
    final private String stationId;
    final private TimeSlot timeslot;
    
    public StationTimeslotPair(String sid, TimeSlot t) {
        stationId = sid;
        timeslot = t;
    }
    
    public String getStationId() {
        return stationId;
    }
    
    public TimeSlot getTimeslot() {
        return timeslot;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        StationTimeslotPair that = (StationTimeslotPair) o;
        
        if (stationId != null ? !stationId.equals(that.stationId) : that.stationId != null) return false;
        return timeslot != null ? timeslot.equals(that.timeslot) : that.timeslot == null;
    }
    
    @Override
    public int hashCode() {
        int result = stationId != null ? stationId.hashCode() : 0;
        result = 31 * result + (timeslot != null ? timeslot.hashCode() : 0);
        return result;
    }
}
