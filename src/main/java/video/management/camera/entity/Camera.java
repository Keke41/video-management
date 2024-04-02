package video.management.camera.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import video.management.backend.entity.Calibration;

@Data
@Entity
public class Camera {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String type;

    @OneToOne(mappedBy = "camera")
    private Calibration calibration;
}
