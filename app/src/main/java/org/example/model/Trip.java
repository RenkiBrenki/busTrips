package org.example.model;

public class Trip {
    private Integer routeId;
    private Integer serviceId;
    private String id;

    public Trip(String id, Integer serviceId, Integer tripId) {
        this.id = id;
        this.serviceId = serviceId;
        this.routeId = tripId;
    }

    public Trip() {}

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRouteId() {
        return this.routeId;
    }

    public void setTripId(Integer value) {
        this.routeId = value;
    }
}
