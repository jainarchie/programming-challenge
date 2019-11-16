package com.shepherd.challenge.enums;

/**
 * Enum which stores all the possible status of a sensor.
 * The statuses are ranked 1 being higher priority than 3.
 */
public enum SensorStatus {

    ANOMALY(1),
    NO_ANOMALY(2),
    NO_MODEL(3);

    private int statusRank;

    SensorStatus(int statusRank){
        this.statusRank = statusRank;
    }

    public int getRank(){
        return  this.statusRank;
    }
}
