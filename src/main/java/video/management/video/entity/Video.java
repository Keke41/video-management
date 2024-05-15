package video.management.video.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import video.management.camera.entity.Camera;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;

@Data
@Entity
public class Video {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String name;

    private String length;

    private LocalDateTime time;

    private BigDecimal frequency;

    private String path;

    @ManyToOne
    private Camera camera;

}
