package model;

public class Station {
    final String stationId;
    final String longitude;
    final String latitude;
    final String name;
    
    public Station(String rawLine) {
        String[] tokenizedLine = rawLine.split("\t");
        
        stationId = tokenizedLine[0];
        longitude = tokenizedLine[1];
        latitude = tokenizedLine[2];
        name = tokenizedLine[3];
    }
    
    public String getStationId() {
        return stationId;
    }
    
    public String getLongitude() {
        return longitude;
    }
    
    public String getLatitude() {
        return latitude;
    }
    
    public String getName() {
        return name;
    }
}
