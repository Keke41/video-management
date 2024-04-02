package video.management.video.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import video.management.video.entity.Video;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
  List<Video> findAllByNameStartsWithIgnoreCase(String name);

}
