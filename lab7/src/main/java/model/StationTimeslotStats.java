package model;

import java.io.Serializable;

public class StationTimeslotStats implements Serializable {
    private final Integer fullStatesCount;
    private final Integer numberOfReadings;
    
    public StationTimeslotStats(Integer fullStatesCount, Integer numberOfReadings) {
        this.fullStatesCount = fullStatesCount;
        this.numberOfReadings = numberOfReadings;
    }
    
    public static StationTimeslotStats buildStationTimeslotStats(StationState state) {
        Integer countAsFull = state.isFull() ? 1 : 0;
        return new StationTimeslotStats(countAsFull, 1);
    }
    
    public static StationTimeslotStats sumStats(StationTimeslotStats stats, StationTimeslotStats stats1) {
        return new StationTimeslotStats(
                stats.getFullStatesCount() + stats1.getFullStatesCount(),
                stats.getNumberOfReadings() + stats1.getNumberOfReadings()
        );
    }
    
    public Double computeCriticality() {
        return (fullStatesCount.doubleValue() / numberOfReadings.doubleValue());
    }
    
    public Integer getFullStatesCount() {
        return fullStatesCount;
    }
    
    public Integer getNumberOfReadings() {
        return numberOfReadings;
    }
}
