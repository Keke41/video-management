package video.management.annotation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import video.management.video.entity.Video;


@Data
@Entity
public class Annotation {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String comment;
    private String name;

    @ManyToOne
    private Video videoAnnotation;
}
