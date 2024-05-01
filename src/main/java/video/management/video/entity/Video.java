package video.management.video.entity;

import com.vaadin.flow.component.textfield.BigDecimalField;
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
import java.time.LocalTime;

@Data
@Entity
public class Video {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String name;

    private Time length;

//    private Long time;
//    private LocalTime time;

    private LocalDateTime time;

//    private int frequency;
    private BigDecimal frequency;

    private String path;

//    private String comment;

    @ManyToOne
    private Camera camera;

    //TODO frame lista
}
