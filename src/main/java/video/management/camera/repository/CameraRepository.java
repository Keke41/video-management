package video.management.camera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import video.management.camera.entity.Camera;

import java.util.List;

public interface CameraRepository extends JpaRepository<Camera, Long> {
        List<Camera> findAllByTypeStartsWithIgnoreCase(String type);

}

