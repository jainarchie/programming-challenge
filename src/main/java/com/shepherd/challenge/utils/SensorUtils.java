package com.shepherd.challenge.utils;

import com.shepherd.challenge.dto.AnomalyDetectorResponse;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.dto.SensorResponse;
import com.shepherd.challenge.enums.SensorStatus;
import com.shepherd.challenge.model.SensorEventLog;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


/**
 * Utils class which stores utility methods for sensor related models.
 */
@Component
public class SensorUtils {

    /**
     * Fetches a basic response object for a sensor event
     * @param sensorEvent
     * @return
     */
    public static SensorResponse getSensorResponseForSensorEvent(@NotNull SensorEvent sensorEvent) {
        if(sensorEvent==null){
            throw new IllegalArgumentException("Sensor Event Cannot Be null");
        }
        return SensorResponse.builder()
                .sensorId(sensorEvent.getSensorId())
                .sensorValue(sensorEvent.getSensorValue())
                .eventId(sensorEvent.getEventId())
                .timestamp(sensorEvent.getTimestamp())
                .cause("")
                .message("")
                .sensorStatus(SensorStatus.NO_MODEL)
                .build();
    }

    /**
     * Generates a sensor log for an event received.
     * @param sensorEvent
     * @return
     */
    public static SensorEventLog getSensorLog(@NotNull SensorEvent sensorEvent) {
        if(sensorEvent==null){
            throw new IllegalArgumentException("Sensor Event cannot be null");
        }
        return SensorEventLog.builder()
                .sensorId(sensorEvent.getSensorId())
                .timestamp(sensorEvent.getTimestamp())
                .eventId(sensorEvent.getEventId())
                .value(sensorEvent.getSensorValue()).build();
    }


    /**
     * This method returns the response object which is of higher priority,
     * if both of the objects are of same priority, the first object is returned.
     * @param resultResponse
     * @param comparisionResponse
     * @return
     */
    public static AnomalyDetectorResponse setStatusByPriority(AnomalyDetectorResponse resultResponse, AnomalyDetectorResponse comparisionResponse) {
        if(resultResponse==null|| comparisionResponse==null || comparisionResponse.getSensorStatus()==null || resultResponse.getSensorStatus()==null){
            throw new IllegalArgumentException("Comparision Objects cannot be null");
        }
                if(comparisionResponse.getSensorStatus().getRank()<resultResponse.getSensorStatus().getRank()){
            resultResponse = comparisionResponse;
        }
        return resultResponse;
    }

    /**
     * Creates Sensor Response from the sensor event and anomaly detector response.
     * @param sensorEvent
     * @param anomalyDetectorResponse
     * @return
     */
    public static SensorResponse getSensorResponseForEvent(SensorEvent sensorEvent, AnomalyDetectorResponse anomalyDetectorResponse) {
        SensorResponse sensorResponse = getSensorResponseForSensorEvent(sensorEvent);
        if(anomalyDetectorResponse!=null){
            sensorResponse.setMessage(anomalyDetectorResponse.getMessage());
            sensorResponse.setCause(anomalyDetectorResponse.getCause());
            sensorResponse.setSensorStatus(anomalyDetectorResponse.getSensorStatus());
        }
        return sensorResponse;
    }

}
