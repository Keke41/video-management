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

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadVideoData(
            VideoRepository videoRepository,
            CameraRepository cameraRepository,
            CalibrationRepository calibrationRepository,
            AnnotationRepository annotationRepository) {
        return (args) -> {
            for (int i = 1; i < 5; i++) {

                Camera camera = new Camera();
                Video video = new Video();
                Annotation annotation = new Annotation();
                Calibration calibration = new Calibration();

                camera.setType("camera_" + i);

                calibration.setSkew_1(BigDecimal.valueOf(i));
                calibration.setSkew_2(BigDecimal.valueOf(i));
                calibration.setFocalLength_1(BigDecimal.valueOf(i));
                calibration.setFocalLength_2(BigDecimal.valueOf(i));
                calibration.setFocalLength_3(BigDecimal.valueOf(i));
                calibration.setFocalLength_4(BigDecimal.valueOf(i));
                calibration.setPrincipalPoint_1(BigDecimal.valueOf(i));
                calibration.setPrincipalPoint_2(BigDecimal.valueOf(i));
                calibration.setPrincipalPoint_3(BigDecimal.valueOf(i));
                calibration.setPrincipalPoint_4(BigDecimal.valueOf(i));
                calibration.setPixelError_1(BigDecimal.valueOf(i));
                calibration.setPixelError_2(BigDecimal.valueOf(i));

                camera.setCalibration(calibration);

                video.setName("type_" + i);
                video.setLength("00:0"+i);
//                video.setLength(new Time(01, 23,i));
                video.setTime(LocalDateTime.of(2021,11,11,12,23,i));
                video.setFrequency(BigDecimal.valueOf(i));
                video.setPath("Path_"+i);

                Camera save = cameraRepository.save(camera);
                video.setCamera(save);
                videoRepository.save(video);

                annotation.setName("Name_" + i);
                annotation.setComment("Comment_" + i);
                annotationRepository.save(annotation);
            }

        };
    }
}
