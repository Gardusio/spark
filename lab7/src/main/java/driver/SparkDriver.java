package driver;

import model.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import utils.ModelMapper;

import java.util.*;

public class SparkDriver {
    
    final static String STATIONS_STATE_FILEPATH = "src/main/resources/registerSample.csv";
    final static String STATIONS_FILEPATH = "src/main/resources/stations.csv";
    final static String OUTPUT_PATH = "src/main/resources/output";
    
    public static void main(String[] args) {
        
        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);
        
        //final Integer treshold = Integer.parseInt(args[0]);
        final Integer treshold = 0;
        //final String inputPath = args[0];
        //final String outputPath=args[1];
        
        // Remember to remove .setMaster("local") before running your application on the cluster
        SparkConf conf = new SparkConf().setAppName("LAB 7").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        //JavaRDD<Station> stations = sc.textFile(STATIONS_FILEPATH).map(Station::new);
        JavaRDD<StationState> stationsState = sc.textFile(STATIONS_STATE_FILEPATH)
                .map(ModelMapper::mapToState)
                .filter(Optional::isPresent)
                .map(Optional::get);
        
        JavaPairRDD<StationTimeslotPair, Double> criticalitiesForEachStationTimeslotPair = stationsState
                .mapToPair(ModelMapper::mapToStationTimeslotStats)
                .reduceByKey(StationTimeslotStats::sumStats)
                .mapValues(StationTimeslotStats::computeCriticality);
        
        JavaPairRDD<StationTimeslotPair, Double> aboveThresholdCriticalities = criticalitiesForEachStationTimeslotPair
                .filter(sIdTidCriticality -> sIdTidCriticality._2 >= 0.4D);
    
        JavaPairRDD<String, TimeslotCriticalityPair> mostCriticalTimeSlotPerStations = aboveThresholdCriticalities
                .mapToPair(ModelMapper::mapToTimeslotCriticalityPair)
                .reduceByKey(TimeslotCriticalityPair::mostCriticalTimeslot);
        
        
        mostCriticalTimeSlotPerStations.saveAsTextFile(OUTPUT_PATH);
        sc.close();
    }
}