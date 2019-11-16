package com.shepherd.challenge.dto;

import lombok.Builder;
import lombok.Data;
/**
 * the standard sensor event received from each sensor in order to update its current value along with the timestamp;
 */
@Data
public class SensorEvent {
    private String eventId;
    private String sensorId;
    private Long timestamp;
    private Double sensorValue;
}
