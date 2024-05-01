package video.management.calibration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.Accessors;
import video.management.camera.entity.Camera;

import java.util.Random;


@Data
//@Accessors(fluent = true)
@Entity
public class Calibration {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private Float skew_1, skew_2;

//    private int skew_1 = 1;
//    private Float  skew_2;

    private String type; // igazabol nincs

    private Float focalLength_1, focalLength_2, focalLength_3, focalLength_4;

    private Float principalPoint_1, principalPoint_2, principalPoint_3, principalPoint_4;

    private Float pixelError_1, pixelError_2;

    @OneToOne
//    @JoinColumn(referencedColumnName = "id")
    private Camera camera;

//    public Calibration() {
//        Random random = new Random();
//
//        skew_1 = random.nextFloat();
//        skew_2 = random.nextFloat();
//        focalLength_1 = random.nextFloat();
//        focalLength_2 = random.nextFloat();
//        focalLength_3 = random.nextFloat();
//        focalLength_4 = random.nextFloat();
//        principalPoint_1 = random.nextFloat();
//        principalPoint_2 = random.nextFloat();
//        principalPoint_3 = random.nextFloat();
//        principalPoint_4 = random.nextFloat();
//        pixelError_1 = random.nextFloat();
//        pixelError_2 = random.nextFloat();
//        type = "tipus";
//    }
//
//    @Override
//    public String toString() {
//        return id + "," +
//                skew_1 + "," + skew_2 + "," +
//                type + "," +
//                focalLength_1 + "," + focalLength_2 + "," + focalLength_3 + "," + focalLength_4 + "," +
//                principalPoint_1 + "," + principalPoint_2 + "," + principalPoint_3 + "," + principalPoint_4 + "," +
//                pixelError_1 + "," + pixelError_2;
//    }
}


