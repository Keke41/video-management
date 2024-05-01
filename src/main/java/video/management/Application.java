package video.management;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

//    @Bean
//    public CommandLineRunner loadData(CameraRepository repository) {
//        return (args) -> {
//            for (int i = 0; i < 6; i++) {
//                Camera camera = new Camera();
//                camera.setType("camera" + i);
//                repository.save(camera);
//            }
//
////            repository.save(new Camera().setType("type1"));
////            repository.save(new Camera().type("type2"));
////            repository.save(new Camera().type("type3"));
////            repository.save(new Camera().type("type4"));
////            repository.save(new Camera().type("type5"));
////            repository.save(new Camera().type("type6"));
//
////            repository.save(new Camera("type1"));
////            repository.save(new Camera("type2"));
////            repository.save(new Camera("type3"));
////            repository.save(new Camera("type4"));
////            repository.save(new Camera("type5"));
////            repository.save(new Camera("type6"));
//        };
//
//    }
    @Bean
    public CommandLineRunner loadVideoData(VideoRepository repository, CameraRepository cameraRepository, CalibrationRepository calibrationRepository) {
        return (args) -> {
            for (int i = 0; i < 6; i++) {


                Camera camera = new Camera();
                Video video = new Video();


                Calibration calibration = new Calibration();
                calibration.setSkew_1((float) i);

                calibrationRepository.save(calibration);
//                calibration.setType("dnfsajkl");


                camera.setType("camera_" + i);
                camera.setCalibration(calibration);
//                camera.setValami("valami");
                video.setName("type_" + i);
                video.setLength(new Time(01, 23,i));
//                video.setTime(new Time(12,23,i));
//                video.setTime(LocalTime.of(12,32,i));
                video.setTime(LocalDateTime.of(2021,11,11,12,23,i));
                video.setFrequency(BigDecimal.valueOf(i));
                video.setPath("Path_"+i);
//                calibration.setType("calib_type_" + i);

                cameraRepository.save(camera);


                video.setCamera(camera);
                repository.save(video);

            }

        };
    }
}
