package video.management.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@Entity

public class Position {
    @Id
    @GeneratedValue
    private Long id;

    private Long xMin;

    private Long xMax;

    private Long yMin;

    private Long yMax;

    @OneToOne
    private Object object;
}
