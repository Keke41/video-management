package video.management.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.Accessors;



@Data
@Accessors(fluent = true)
@Entity

public class Frame {
    @Id
    @GeneratedValue
    private Long id;

    //TODO videoannotáció  ID

    private Long index;


}
