package com.shepherd.challenge.anomolyconfig;


import com.shepherd.challenge.TestUtils;
import com.shepherd.challenge.dal.SensorEventLogRepository;

import com.shepherd.challenge.dto.AnomalyDetectorResponse;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.enums.SensorStatus;
import com.shepherd.challenge.model.SensorEventLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
public class WindowThresholdModelTest {

    @InjectMocks
    WindowThresholdModel windowThresholdModel;

    @Mock
    private SensorEventLogRepository sensorEventLogRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<String> sensorList = new ArrayList<>();
        sensorList.add("SensorId");
        windowThresholdModel.setSensorIdList(sensorList);
        windowThresholdModel.setThreshold(100.0);
        windowThresholdModel.setWindowSize(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void detectAnomaly() {
        windowThresholdModel.detectAnomaly(null);
    }


    @Test
    public void detectAnomaly2() {
        SensorEvent sensorEvent = TestUtils.getSensorEventObject();
        sensorEvent.setSensorId("abc");
        AnomalyDetectorResponse anomalyDetectorResponse = windowThresholdModel.detectAnomaly(sensorEvent);
        Assert.assertEquals(anomalyDetectorResponse.getSensorStatus(), SensorStatus.NO_MODEL);
    }


    @Test
    public void detectAnomaly3() {
        SensorEvent sensorEvent = TestUtils.getSensorEventObject();
        Page<SensorEventLog> page = TestUtils.getPage();
        page.getTotalElements();
        Mockito.when(sensorEventLogRepository.findBySensorId(PageRequest.of(0, 1), sensorEvent.getSensorId())).thenReturn(page);
        AnomalyDetectorResponse anomalyDetectorResponse = windowThresholdModel.detectAnomaly(sensorEvent);
        Assert.assertEquals(anomalyDetectorResponse.getSensorStatus(), SensorStatus.NO_ANOMALY);
    }

    @Test
    public void detectAnomaly4() {
        SensorEvent sensorEvent = TestUtils.getSensorEventObject();
        sensorEvent.setSensorValue(10000.0);
        Page<SensorEventLog> page = TestUtils.getFillPage();
        Mockito.when(sensorEventLogRepository.findBySensorId(PageRequest.of(0, 1), sensorEvent.getSensorId())).thenReturn(page);
        AnomalyDetectorResponse anomalyDetectorResponse = windowThresholdModel.detectAnomaly(sensorEvent);
        Assert.assertEquals(anomalyDetectorResponse.getSensorStatus(), SensorStatus.ANOMALY);
    }


}