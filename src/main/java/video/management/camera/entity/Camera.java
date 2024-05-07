package video.management.camera.entity;

import jakarta.persistence.*;
import lombok.*;
import video.management.calibration.entity.Calibration;

@Data
@Entity
public class Camera {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String type;

    @OneToOne(mappedBy = "camera",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Calibration calibration;

    public void setCalibration(Calibration calibration){
        this.calibration = calibration;
        this.calibration.setCamera(this);
    }

}
