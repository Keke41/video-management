// Ennek az osztálynak lehet, hogy valamilyen más nevet kellene kitalálni :)

package video.management.object.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.Accessors;


@Data
@Accessors(fluent = true)
@Entity

public class Object {
    @Id
    @GeneratedValue
    private Long id;

    private Long speed;

    private String category;

    //TODO Frame lista
}
