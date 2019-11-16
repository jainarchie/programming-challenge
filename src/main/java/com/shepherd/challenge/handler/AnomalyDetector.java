package com.shepherd.challenge.handler;

import com.shepherd.challenge.anomolyconfig.AnomalyModel;
import com.shepherd.challenge.anomolyconfig.UpperBoundThresholdAnomalyModel;
import com.shepherd.challenge.dto.AnomalyDetectorResponse;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.enums.SensorStatus;
import com.shepherd.challenge.utils.SensorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * This class runs all the models on the sensors.
 */
@Component
public class AnomalyDetector {

    Logger logger = LoggerFactory.getLogger(AnomalyDetector.class);
    public List<AnomalyModel> anomalyModelList = new ArrayList<>();

    @Autowired
    UpperBoundThresholdAnomalyModel upperBoundThresholdAnomalyModel;


    /**
     * This list contains all the models required to be applied on the sensors.
     * Add all the models to this list.
     */
    @PostConstruct
    public void populateAnomalyModelMap(){
        anomalyModelList.add(upperBoundThresholdAnomalyModel);
    }

    /**
     * This method is responsible to apply all the anomlay model to the given sensor event.
     * @param sensorEvent
     * @return
     */
    public AnomalyDetectorResponse applyAnomalyModels(SensorEvent sensorEvent) {
        if(sensorEvent==null){
            throw new IllegalArgumentException("Sensor Event Cannot be null");
        }
        AnomalyDetectorResponse anomalyDetectorResponse = new AnomalyDetectorResponse("","", SensorStatus.NO_MODEL);
        for(AnomalyModel anomalyModel: anomalyModelList){
            anomalyDetectorResponse = SensorUtils.setStatusByPriority(anomalyDetectorResponse, anomalyModel.detectAnomaly(sensorEvent) );
        }
        logger.info("Returning response after model verification: "+ anomalyDetectorResponse);
        return anomalyDetectorResponse;
    }
}
