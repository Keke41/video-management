package video.management.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.Accessors;
import video.management.camera.entity.Camera;


@Data
@Accessors(fluent = true)
@Entity
public class Calibration {
    @Id
    @GeneratedValue
    private Long id;

    private Long pixelError;

    private Long skew;

    private Long focalLength;

    private Long principalPoint;

    @OneToOne
//    @JoinColumn(referencedColumnName = "id")
    private Camera camera;
}
