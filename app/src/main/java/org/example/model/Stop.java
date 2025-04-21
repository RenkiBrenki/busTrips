package org.example.model;

public class Stop {
    private Integer id;
    private Integer code;
    private String name;
    private Double latitude;
    private Double longitude;

    public Stop(Integer id, Integer code, String name, Double latitude, Double longitude) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Stop() {}

    @Override
    public String toString() {
        return "Stop{"
                + "id="
                + id
                + ",code="
                + code
                + ", name='"
                + name
                + '\''
                + ", latitude="
                + latitude
                + ", longitude="
                + longitude
                + '}';
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public void setCode(Integer value) {
        this.code = value;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
