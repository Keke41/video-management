package video.management.annotation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import video.management.annotation.entity.Annotation;


import java.util.List;

public interface AnnotationRepository extends JpaRepository<Annotation, Long> {
        List<Annotation> findAllByNameStartsWithIgnoreCase(String name);

}

