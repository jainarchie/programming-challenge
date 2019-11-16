package com.shepherd.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorEventLog {

    @Id
    private String eventId;

    private String sensorId;

    private Long timestamp;

    private Double value;

}
