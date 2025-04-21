package org.example.util;

import org.example.model.Stop;
import org.example.model.StopTime;
import org.example.model.Trip;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class ReadDataUtil {
    private String path;
    private LocalTime from;
    private LocalTime to;

    public ReadDataUtil(String path, LocalTime from, LocalTime to) {
        this.path = path;
        this.from = from;
        this.to = to;
    }

    public Stop getStation(Integer stationId) {
        String stopPath = this.path + "/stops.txt";
        return AbstractScanner.INSTANCE.readDataByField(
                Stop.class, stopPath, Stop::getId, stationId);
    }

    public HashMap<Integer, List<StopTime>> getStopTimesForStation(
            Integer stationId, Integer limit) {
        String stopPath = this.path + "/stop_times.txt";
        HashMap<Integer, List<StopTime>> stopTimesMap = new HashMap<>();
        Predicate<StopTime> condition =
                stopTime ->
                        stopTime.getStopId().equals(stationId)
                                && stopTime.getArrivalTime().isBefore(to)
                                && stopTime.getArrivalTime().isAfter(from);

        List<StopTime> stopTimes =
                AbstractScanner.INSTANCE.readData(StopTime.class, stopPath, condition);
        for (StopTime stopTime : stopTimes) {
            Integer routeId = getRouteIdByTripId(stopTime.getTripId());
            if (stopTimesMap.containsKey(routeId)) {
                List<StopTime> existing = stopTimesMap.get(routeId);
                if (existing.size() < limit) {
                    existing.add(stopTime);
                }
            } else {
                stopTimesMap.put(routeId, new ArrayList<>(List.of(stopTime)));
            }
            stopTimesMap
                    .get(routeId)
                    .sort((o1, o2) -> o1.getArrivalTime().compareTo(o2.getArrivalTime()));
        }
        return stopTimesMap;
    }

    public Integer getRouteIdByTripId(String tripId) {
        String stopPath = this.path + "/trips.txt";
        return AbstractScanner.INSTANCE
                .readDataByField(Trip.class, stopPath, Trip::getId, tripId)
                .getRouteId();
    }
}
