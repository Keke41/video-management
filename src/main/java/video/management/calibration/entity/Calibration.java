package video.management.calibration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import video.management.camera.entity.Camera;
import java.math.BigDecimal;


@Data
@Entity
public class Calibration {
    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal skew;
    private BigDecimal focalLength_1, focalLength_2;
    private BigDecimal principalPoint_1, principalPoint_2;
    private BigDecimal pixelError_1, pixelError_2;

    @OneToOne
    private Camera camera;
}
