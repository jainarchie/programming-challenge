package com.shepherd.challenge.anomolyconfig;

import com.shepherd.challenge.dto.AnomalyDetectorResponse;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.enums.SensorStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Upper bound anomaly model which checks for a threshold value.
 */
@Component
public class UpperBoundThresholdAnomalyModel implements AnomalyModel {

    public static Logger logger = LoggerFactory.getLogger(UpperBoundThresholdAnomalyModel.class);

    @Value("${upperbound.sensorid}")
    String sensorids;

    @Value("${upperbound.model}")
    String modelId;

    @Value("${upperbound.threshold}")
    Double threoshold;


    private List<String> sensorIdList;

    @PostConstruct
    public void populateSensorIdList(){

        sensorIdList = Arrays.asList(sensorids.split("\\s*,\\s*"));
    }

    /**
     * This method detects if the sensor qualifies for the detector and then checks if value is in desired range.
     * If sensor is not valid: NO_MODEL
     * if threshold crossed: ANOMALY
     * if values in range: NO_ANOMALY
     * @param sensorEvent
     * @return
     */
    @Override
    public AnomalyDetectorResponse detectAnomaly(SensorEvent sensorEvent) {
        if(sensorEvent==null){
            throw new IllegalArgumentException("Sensor event  cannot be null");
        }
        //Create a basic response model
        AnomalyDetectorResponse anomalyDetectorResponse = new AnomalyDetectorResponse("", "", SensorStatus.NO_MODEL);
        //Check if event qualifies for the model
        if(sensorIdList.contains(sensorEvent.getSensorId())){
            logger.info("The event is qualified for UpperBoundThresholdAnomalyModel ");
            if(sensorEvent.getSensorValue()==null){
                throw new IllegalArgumentException("The sensor value cannot be null");
            }
            //Check if the event value is greater than the threshold
            if(sensorEvent.getSensorValue()>threoshold){
                logger.info("The event exceeds the threshold value.");
                //Update sensor status
                anomalyDetectorResponse.setSensorStatus(SensorStatus.ANOMALY);
                anomalyDetectorResponse.setMessage("The sensor value is more than the threshold value");
                anomalyDetectorResponse.setCause(sensorEvent.getSensorValue()+" is more than threshold value of "+ threoshold);
            }
            logger.info("The event is in desired range of values.");
            //Since value is not exceeding threshold we identify this as not an anomaly
            anomalyDetectorResponse.setSensorStatus(SensorStatus.NO_ANOMALY);
        }
        return anomalyDetectorResponse;
    }
}
