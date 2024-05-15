package video.management.measurements.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@Entity

public class Measurements {
    @Id
    @GeneratedValue
    private Long id;

    private Long yaw;

    private Long roll;

    private Long pitch;

    private Long timeStamp;
}
