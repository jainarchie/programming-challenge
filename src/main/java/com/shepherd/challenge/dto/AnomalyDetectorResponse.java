package com.shepherd.challenge.dto;

import com.shepherd.challenge.enums.SensorStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The class holds the reponse of the anomaly detectors
 */
@Data
@AllArgsConstructor
public class AnomalyDetectorResponse {
    private String message;
    private String cause;
    private SensorStatus sensorStatus;
}
