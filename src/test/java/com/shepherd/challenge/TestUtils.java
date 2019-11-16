package com.shepherd.challenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shepherd.challenge.dto.AnomalyDetectorResponse;
import com.shepherd.challenge.dto.SensorEvent;
import com.shepherd.challenge.enums.SensorStatus;
import com.shepherd.challenge.model.SensorEventLog;
import com.shepherd.challenge.utils.SensorUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class TestUtils {

    public static SensorEvent getSensorEventObject(){
        SensorEvent sensorEvent = new  SensorEvent();
        sensorEvent.setEventId("EventId");
        sensorEvent.setSensorId("SensorId");
        sensorEvent.setSensorValue(50.0);
        sensorEvent.setTimestamp(1573900751000l);
        return sensorEvent;
    }

    public static AnomalyDetectorResponse getAnomalyDetectorResponse(){
        return new AnomalyDetectorResponse("", "", SensorStatus.NO_MODEL);
    }

    public static Page  getPage(){
        Page<SensorEventLog> page = new Page<SensorEventLog>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super SensorEventLog, ? extends U> function) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<SensorEventLog> getContent() {
                return null;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<SensorEventLog> iterator() {
                return null;
            }
        };
        return  page;
    }




    public static String serializeObject(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RestClientException("Couldn't serialize object :" + obj, e);
        }
    }

    public static Page<SensorEventLog> getFillPage() {
        Page<SensorEventLog> page = new Page<SensorEventLog>() {
            @Override
            public int getTotalPages() {
                return 1;
            }

            @Override
            public long getTotalElements() {
                return 1;
            }

            @Override
            public <U> Page<U> map(Function<? super SensorEventLog, ? extends U> function) {
                return null;
            }

            @Override
            public int getNumber() {
                return 1;
            }

            @Override
            public int getSize() {
                return 1;
            }

            @Override
            public int getNumberOfElements() {
                return 1;
            }

            @Override
            public List<SensorEventLog> getContent() {
                SensorEventLog sensorEventLog = SensorUtils.getSensorLog(getSensorEventObject());
                List<SensorEventLog> list = new ArrayList<>();
                list.add(sensorEventLog);
                return list;
            }

            @Override
            public boolean hasContent() {
                return true;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<SensorEventLog> iterator() {
                return null;
            }
        };
        return  page;
    }
}
