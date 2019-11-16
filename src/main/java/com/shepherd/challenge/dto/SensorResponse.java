package com.shepherd.challenge.dto;

import com.shepherd.challenge.enums.SensorStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * The standard response object for each event received.
 */
public class SensorResponse {

    //Populated from the event
    private String eventId;
    //Populated from the event
    private String sensorId;
    //Populated from the event
    private Long timestamp;
    //Populated from the event
    private Double sensorValue;
    //Populated from the anomaly detectors
    private SensorStatus sensorStatus;
    //Populated from the anomaly detectors, on which values made this happen.
    private String cause;
    //Populated from the anomaly detectors, on why it happened.
    private String message;
}
