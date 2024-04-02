package video.management;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import video.management.camera.entity.Camera;
import video.management.camera.repository.CameraRepository;
import video.management.video.entity.Video;
import video.management.video.repository.VideoRepository;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(CameraRepository repository) {
        return (args) -> {
            for (int i = 0; i < 6; i++) {
                Camera camera = new Camera();
                camera.setType("type" + i);
                repository.save(camera);
            }

//            repository.save(new Camera().setType("type1"));
//            repository.save(new Camera().type("type2"));
//            repository.save(new Camera().type("type3"));
//            repository.save(new Camera().type("type4"));
//            repository.save(new Camera().type("type5"));
//            repository.save(new Camera().type("type6"));

//            repository.save(new Camera("type1"));
//            repository.save(new Camera("type2"));
//            repository.save(new Camera("type3"));
//            repository.save(new Camera("type4"));
//            repository.save(new Camera("type5"));
//            repository.save(new Camera("type6"));
        };

    }
    @Bean
    public CommandLineRunner loadVideoData(VideoRepository repository) {
        return (args) -> {
            for (int i = 0; i < 6; i++) {
                Video video = new Video();
                video.setName("type" + i);
                repository.save(video);
            }

        };
    }
}
