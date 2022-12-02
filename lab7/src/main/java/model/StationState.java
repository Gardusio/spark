package model;

import java.time.LocalDate;

public class StationState {
    final String stationId;
    final LocalDate date;
    final String timestamp;
    final TimeSlot timeslot;
    final Integer usedSlots;
    final Integer freeSlots;
    
    public StationState(String stationId, String timestamp, String usedSlots, String freeSlots) {
        this.stationId = stationId;

        String[] tokenizedTimestamp = timestamp.split(" ");
        date = LocalDate.parse(tokenizedTimestamp[0]);
        this.timestamp = tokenizedTimestamp[1];
        this.timeslot = new TimeSlot(date, timestamp);
        
        this.usedSlots = Integer.parseInt(usedSlots);
        this.freeSlots = Integer.parseInt(freeSlots);
    }
    
    public String getStationId() {
        return stationId;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public Boolean isFull() {
        return freeSlots == 0;
    }
    
    public TimeSlot getTimeSlot() {
        return timeslot;
    }
}
