package com.shepherd.challenge.handler;

import com.shepherd.challenge.dal.SensorEventLogRepository;
import com.shepherd.challenge.dto.AnomalyDetectorResponse;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.dto.SensorResponse;
import com.shepherd.challenge.utils.SensorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class SensorEventHandler {

    public static Logger logger = LoggerFactory.getLogger(SensorEventHandler.class);

    @Autowired
    SensorEventLogRepository sensorEventLogRepository;

    @Autowired
    private AnomalyDetector anomalyDetector;


    /**
     * Logs the event received in the Database and console.
     * @param sensorEvent
     * @return
     */
    public SensorResponse logEvent(@NotNull SensorEvent sensorEvent) {
        if(sensorEvent==null){
            throw new IllegalArgumentException("Sensor Event Cannot be null");
        }
        logger.info("Received event for logging: "+ sensorEvent);
        sensorEventLogRepository.save(SensorUtils.getSensorLog(sensorEvent));
        logger.info("Saved log to db.");
        SensorResponse sensorResponse = SensorUtils.getSensorResponseForSensorEvent(sensorEvent);
        logger.info("Returning logEvent Response "+sensorResponse);
        return sensorResponse;
    }


    /**
     * The method takes in the sensor event, logs it, runs against anomaly detectors and returns a valid status.
     * @param sensorEvent
     * @return
     */
    public SensorResponse senseEvent(SensorEvent sensorEvent) {
        sensorEventLogRepository.save(SensorUtils.getSensorLog(sensorEvent));
        logger.info("Saved log to db.");
        AnomalyDetectorResponse anomalyDetectorResponse = anomalyDetector.applyAnomalyModels(sensorEvent);
        SensorResponse sensorResponse = SensorUtils.getSensorResponseForEvent(sensorEvent, anomalyDetectorResponse);
        logger.info("Sensor response returned: "+ sensorResponse);
        return sensorResponse;
    }

}
