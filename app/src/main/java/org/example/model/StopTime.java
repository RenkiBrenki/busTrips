package org.example.model;

import java.time.LocalTime;

public class StopTime {
    private String tripId;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private Integer stopId;
    private Integer sequence;

    public StopTime(
            String tripId,
            Integer sequence,
            Integer stopId,
            LocalTime arrivalTime,
            LocalTime departureTime) {
        this.tripId = tripId;
        this.sequence = sequence;
        this.stopId = stopId;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public StopTime() {}

    public String getTripId() {
        return tripId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public Integer getStopId() {
        return stopId;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setId(String value) {
        this.tripId = value;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public void setStopId(Integer stopId) {
        this.stopId = stopId;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}
