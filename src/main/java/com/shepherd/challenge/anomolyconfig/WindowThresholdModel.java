package com.shepherd.challenge.anomolyconfig;

import com.shepherd.challenge.dal.SensorEventLogRepository;
import com.shepherd.challenge.dto.AnomalyDetectorResponse;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.enums.SensorStatus;
import com.shepherd.challenge.model.SensorEventLog;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

@Data
@Component
public class WindowThresholdModel  implements AnomalyModel {

    Logger logger = LoggerFactory.getLogger(WindowThresholdModel.class);

    @Autowired
    SensorEventLogRepository sensorEventLogRepository;

    @Value("${window.sensor}")
    private String sensorIds;

    @Value("${window.threshold}")
    private Double threshold;

    @Value("${window.model}")
    private String model;

    @Value("${window.size}")
    private Integer windowSize;

    private List<String> sensorIdList;

    @PostConstruct
    public void populateSensorIdList(){
        sensorIdList = Arrays.asList(sensorIds.split("\\s*,\\s*"));
    }

    /**
     * This method is responsible to detect anomaly in window size.
     * It fetches values for window size and avergaes it out, if avg is > threshold returns ANOMALY
     * else  returns NO_ANOMALY
     * if sensor is not valid for model it returns NO_MODEL
     * @param sensorEvent
     * @return
     */
    @Override
    public AnomalyDetectorResponse detectAnomaly(SensorEvent sensorEvent) {
        logger.info("starting Anomaly detection in WindowThresholdModel");
        if(sensorEvent==null){
            throw new IllegalArgumentException("Sensor event  cannot be null");
        }
        AnomalyDetectorResponse detectorResponse = new AnomalyDetectorResponse("","", SensorStatus.NO_MODEL);
        if(sensorIdList.contains(sensorEvent.getSensorId())){
            logger.info("The event is qualified for WindowThresholdModel ");
            detectorResponse.setSensorStatus(SensorStatus.NO_ANOMALY);
            Page<SensorEventLog> sensorEventLogPage = sensorEventLogRepository.findBySensorId(PageRequest.of(0, windowSize-1), sensorEvent.getSensorId());
            logger.info("Successfully fetched "+ sensorEventLogPage.getTotalElements()+" past events from DB for sensorid: "+ sensorEvent.getSensorId());
            List<SensorEventLog> sensorEventLogList = sensorEventLogPage.getContent();
            if(!CollectionUtils.isEmpty(sensorEventLogList)){
                OptionalDouble avgThreshold = sensorEventLogList.stream().mapToDouble(s -> s.getValue()).average();
                Double avgValue;
                if(avgThreshold.isPresent()){
                   avgValue = avgThreshold.getAsDouble()+(sensorEvent.getSensorValue()/2);
                }else{
                    avgValue = sensorEvent.getSensorValue();
                }
                    if(avgValue>threshold){
                        logger.info("The event exceeds the threshold value.");
                        detectorResponse.setSensorStatus(SensorStatus.ANOMALY);
                        detectorResponse.setMessage("The avergae value is more than threshold");
                        detectorResponse.setCause("Average value is: "+ avgThreshold.getAsDouble()+" while threshold is "+ threshold);
                    }
                }
            }
        return detectorResponse;
    }
}
