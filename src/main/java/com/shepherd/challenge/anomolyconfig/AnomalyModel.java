package com.shepherd.challenge.anomolyconfig;

import com.shepherd.challenge.dto.AnomalyDetectorResponse;
import com.shepherd.challenge.dto.SensorEvent;

/**
 * Interface to be extended by all anomaly models
 */
public interface AnomalyModel {
    public AnomalyDetectorResponse detectAnomaly(SensorEvent sensorEvent);
}
