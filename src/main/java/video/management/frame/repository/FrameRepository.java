package video.management.frame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import video.management.video.entity.Video;

import java.util.List;

public interface FrameRepository extends JpaRepository<Video, Long> {
  List<Video> findAllByNameStartsWithIgnoreCase(String name);

}
