package video.management.calibration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.Accessors;
import video.management.camera.entity.Camera;

import java.math.BigDecimal;
import java.util.Random;


@Data
@Entity
public class Calibration {
    @Id
    @GeneratedValue
//    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private BigDecimal skew_1, skew_2;

    private BigDecimal focalLength_1, focalLength_2, focalLength_3, focalLength_4;

    private BigDecimal principalPoint_1, principalPoint_2, principalPoint_3, principalPoint_4;

    private BigDecimal pixelError_1, pixelError_2;

    @OneToOne
    private Camera camera;

}


