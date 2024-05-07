package video.management;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import video.management.annotation.entity.Annotation;
import video.management.annotation.repository.AnnotationRepository;
import video.management.calibration.entity.Calibration;
import video.management.calibration.repository.CalibrationRepository;
import video.management.camera.entity.Camera;
import video.management.camera.repository.CameraRepository;
import video.management.video.entity.Video;
import video.management.video.repository.VideoRepository;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadVideoData(VideoRepository repository, CameraRepository cameraRepository, CalibrationRepository calibrationRepository, AnnotationRepository annotationRepository) {
        return (args) -> {
            for (int i = 0; i < 6; i++) {


                Camera camera = new Camera();
                Video video = new Video();
                Annotation annotation = new Annotation();
                Calibration calibration = new Calibration();

                calibration.setSkew_1(BigDecimal.valueOf(i));
//                calibration.setCamera(camera);

//                Calibration savedCalibration = calibrationRepository.save(calibration);


                camera.setType("camera_" + i);
                camera.setCalibration(calibration);
                video.setName("type_" + i);
                video.setLength(new Time(01, 23,i));
                video.setTime(LocalDateTime.of(2021,11,11,12,23,i));
                video.setFrequency(BigDecimal.valueOf(i));
                video.setPath("Path_"+i);

                Camera save = cameraRepository.save(camera);


                video.setCamera(save);
                repository.save(video);

                annotation.setName("Name_" + i);
                annotationRepository.save(annotation);
            }

        };
    }
}
