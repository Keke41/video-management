package video.management.annotation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.experimental.Accessors;
import video.management.video.entity.Video;


@Data
@Accessors(fluent = true)
@Entity
public class Annotation {
    @Id
    @GeneratedValue
    private Long id;

    private String comment;
    private String name;

    @ManyToOne
    private Video videoAnnotation;
}
