package video.management.video.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import video.management.camera.entity.Camera;

@Data
@Entity
public class Video {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String name;

    private Long frequency;

    private Long time;

    private String path;

    private Long length;

    private String comment;

    @ManyToOne
    private Camera camera;

    //TODO frame lista
}
