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
//            for (int i = 1; i < 5; i++) {
//
//                Camera camera = new Camera();
//                Video video = new Video();
//                Annotation annotation = new Annotation();
//                Calibration calibration = new Calibration();
//
//                camera.setType("camera_" + i);
//
//                calibration.setSkew(BigDecimal.valueOf(i));
//                calibration.setFocalLength_1(BigDecimal.valueOf(i));
//                calibration.setFocalLength_2(BigDecimal.valueOf(i));
//                calibration.setPrincipalPoint_1(BigDecimal.valueOf(i));
//                calibration.setPrincipalPoint_2(BigDecimal.valueOf(i));
//                calibration.setPixelError_1(BigDecimal.valueOf(i));
//                calibration.setPixelError_2(BigDecimal.valueOf(i));
//
//                camera.setCalibration(calibration);
//
//                video.setName("Name_" + i);
//                video.setLength("00:0"+i);
////                video.setLength(new Time(01, 23,i));
//                video.setTime(LocalDateTime.of(2021,11,11,12,23,i));
//                video.setFrequency(BigDecimal.valueOf(i));
//                video.setPath("Path_"+i);
//
//                Camera save = cameraRepository.save(camera);
//                video.setCamera(save);
//                videoRepository.save(video);
//
//                annotation.setName("Name_" + i);
//                annotation.setComment("Comment_" + i);
//                annotationRepository.save(annotation);
//            }
                //OPTION 2
                // <1>
                Camera camera_1 = new Camera();
                Video video_1 = new Video();
                Annotation annotation_1 = new Annotation();
                Calibration calibration_1 = new Calibration();

                camera_1.setType("Canon EOS 5D Mark IV");

                calibration_1.setSkew(BigDecimal.valueOf(0.1));
                calibration_1.setFocalLength_1(BigDecimal.valueOf(850));
                calibration_1.setFocalLength_2(BigDecimal.valueOf(850));
                calibration_1.setPrincipalPoint_1(BigDecimal.valueOf(320));
                calibration_1.setPrincipalPoint_2(BigDecimal.valueOf(240));
                calibration_1.setPixelError_1(BigDecimal.valueOf(0.2));
                calibration_1.setPixelError_2(BigDecimal.valueOf(0.3));

                camera_1.setCalibration(calibration_1);

                video_1.setName("Name_1");
                video_1.setLength("12:48");
//                video.setLength(new Time(01, 23,i));
                video_1.setTime(LocalDateTime.of(2021,11,15,04,23,01));
                video_1.setFrequency(BigDecimal.valueOf(25));
                video_1.setPath("Path_1");

                Camera save = cameraRepository.save(camera_1);
                video_1.setCamera(save);
                videoRepository.save(video_1);

                annotation_1.setName("Name_1");
                annotation_1.setComment("Comment_1");
                annotationRepository.save(annotation_1);

            // <2>
            Camera camera_2 = new Camera();
            Video video_2 = new Video();
            Annotation annotation_2 = new Annotation();
            Calibration calibration_2 = new Calibration();

            camera_2.setType("iPhone 13 Pro");

            calibration_2.setSkew(BigDecimal.valueOf(0.2));
            calibration_2.setFocalLength_1(BigDecimal.valueOf(400));
            calibration_2.setFocalLength_2(BigDecimal.valueOf(400));
            calibration_2.setPrincipalPoint_1(BigDecimal.valueOf(315));
            calibration_2.setPrincipalPoint_2(BigDecimal.valueOf(245));
            calibration_2.setPixelError_1(BigDecimal.valueOf(0.5));
            calibration_2.setPixelError_2(BigDecimal.valueOf(0.4));

            camera_2.setCalibration(calibration_2);

            video_2.setName("Name_2");
            video_2.setLength("51:16");
//                video.setLength(new Time(01, 23,i));
            video_2.setTime(LocalDateTime.of(2024,1,8,02,00,00));
            video_2.setFrequency(BigDecimal.valueOf(24));
            video_2.setPath("Path_2");

            Camera save_2 = cameraRepository.save(camera_2);
            video_2.setCamera(save_2);
            videoRepository.save(video_2);

            annotation_2.setName("Name_2");
            annotation_2.setComment("Comment_2");
            annotationRepository.save(annotation_2);

            // <3>
            Camera camera_3 = new Camera();
            Video video_3 = new Video();
            Annotation annotation_3 = new Annotation();
            Calibration calibration_3 = new Calibration();

            camera_3.setType("GoPro Hero 9");

            calibration_3.setSkew(BigDecimal.valueOf(0.15));
            calibration_3.setFocalLength_1(BigDecimal.valueOf(550));
            calibration_3.setFocalLength_2(BigDecimal.valueOf(550));
            calibration_3.setPrincipalPoint_1(BigDecimal.valueOf(300));
            calibration_3.setPrincipalPoint_2(BigDecimal.valueOf(250));
            calibration_3.setPixelError_1(BigDecimal.valueOf(0.3));
            calibration_3.setPixelError_2(BigDecimal.valueOf(0.2));

            camera_3.setCalibration(calibration_3);

            video_3.setName("Name_3");
            video_3.setLength("05:42");
//                video.setLength(new Time(01, 23,i));
            video_3.setTime(LocalDateTime.of(2022,2,2,22,00,00));
            video_3.setFrequency(BigDecimal.valueOf(29.97));
            video_3.setPath("Path_3");

            Camera save_3 = cameraRepository.save(camera_3);
            video_3.setCamera(save_3);
            videoRepository.save(video_3);

            annotation_3.setName("Name_3");
            annotation_3.setComment("Comment_3");
            annotationRepository.save(annotation_3);


            // <4>
            Camera camera_4 = new Camera();
            Video video_4 = new Video();
            Annotation annotation_4 = new Annotation();
            Calibration calibration_4 = new Calibration();

            camera_4.setType("Logitech C920");

            calibration_4.setSkew(BigDecimal.valueOf(0.1));
            calibration_4.setFocalLength_1(BigDecimal.valueOf(500));
            calibration_4.setFocalLength_2(BigDecimal.valueOf(500));
            calibration_4.setPrincipalPoint_1(BigDecimal.valueOf(310));
            calibration_4.setPrincipalPoint_2(BigDecimal.valueOf(230));
            calibration_4.setPixelError_1(BigDecimal.valueOf(0.4));
            calibration_4.setPixelError_2(BigDecimal.valueOf(0.3));

            camera_4.setCalibration(calibration_4);

            video_4.setName("Name_4");
            video_4.setLength("14:08");
//                video.setLength(new Time(01, 23,i));
            video_4.setTime(LocalDateTime.of(2023,3,15,22,00,00));
            video_4.setFrequency(BigDecimal.valueOf(60));
            video_4.setPath("Path_4");

            Camera save_4 = cameraRepository.save(camera_4);
            video_4.setCamera(save_4);
            videoRepository.save(video_4);

            annotation_4.setName("Name_4");
            annotation_4.setComment("Comment_4");
            annotationRepository.save(annotation_4);

        };
    }
}
