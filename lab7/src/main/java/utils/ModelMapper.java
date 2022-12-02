package utils;

import model.StationState;
import model.StationTimeslotPair;
import model.StationTimeslotStats;
import model.TimeslotCriticalityPair;
import scala.Tuple2;

import java.util.Optional;

public class ModelMapper {
    
    private static String[] split(String s) {
        return s.split("\t");
    }
    
    static public Optional<StationState> mapToState(String s) {
        String[] tokenizedLine = split(s);
        
        if (Validator.isValid(tokenizedLine))
            return Optional.of(
                    new StationState(
                            tokenizedLine[0],
                            tokenizedLine[1],
                            tokenizedLine[2],
                            tokenizedLine[3]
                    ));
        
        return Optional.empty();
    }
    
    static public Tuple2<StationTimeslotPair, StationTimeslotStats> mapToStationTimeslotStats(StationState state) {
        StationTimeslotPair stationTimeslotPair = new StationTimeslotPair(state.getStationId(), state.getTimeSlot());
        
        return new Tuple2<>(
                stationTimeslotPair,
                StationTimeslotStats.buildStationTimeslotStats(state)
        );
    }
    
    public static Tuple2<String, TimeslotCriticalityPair> mapToTimeslotCriticalityPair(
            Tuple2<StationTimeslotPair, Double> stationTimeslotCrit) {
        
        return new Tuple2<>(
                stationTimeslotCrit._1.getStationId(),
                new TimeslotCriticalityPair(stationTimeslotCrit._1.getTimeslot(), stationTimeslotCrit._2())
        );
    }
}
