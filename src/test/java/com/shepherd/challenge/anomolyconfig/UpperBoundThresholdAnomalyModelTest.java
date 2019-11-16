package com.shepherd.challenge.anomolyconfig;

import com.shepherd.challenge.TestUtils;
import com.shepherd.challenge.dto.AnomalyDetectorResponse;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.enums.SensorStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class UpperBoundThresholdAnomalyModelTest {

    @InjectMocks
    UpperBoundThresholdAnomalyModel upperBoundThresholdAnomalyModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<String> sensorList = new ArrayList<>();
        sensorList.add("SensorId");
        upperBoundThresholdAnomalyModel.setSensorIdList(sensorList);
        upperBoundThresholdAnomalyModel.setThreoshold(100.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void detectAnomaly() {
        upperBoundThresholdAnomalyModel.detectAnomaly(null);
    }

    @Test
    public void detectAnomaly2() {
        SensorEvent sensorEvent = TestUtils.getSensorEventObject();
        AnomalyDetectorResponse anomalyDetectorResponse = upperBoundThresholdAnomalyModel.detectAnomaly(sensorEvent);
        Assert.assertEquals(anomalyDetectorResponse.getSensorStatus(), SensorStatus.NO_ANOMALY);
    }


    @Test
    public void detectAnomaly3() {
        SensorEvent sensorEvent = TestUtils.getSensorEventObject();
        sensorEvent.setSensorValue(10000.0);
        AnomalyDetectorResponse anomalyDetectorResponse = upperBoundThresholdAnomalyModel.detectAnomaly(sensorEvent);
        Assert.assertEquals(anomalyDetectorResponse.getSensorStatus(), SensorStatus.ANOMALY);
    }

    @Test
    public void detectAnomaly4() {
        SensorEvent sensorEvent = TestUtils.getSensorEventObject();
        sensorEvent.setSensorId("abc");
        AnomalyDetectorResponse anomalyDetectorResponse = upperBoundThresholdAnomalyModel.detectAnomaly(sensorEvent);
        Assert.assertEquals(anomalyDetectorResponse.getSensorStatus(), SensorStatus.NO_MODEL);
    }

}