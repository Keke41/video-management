package video.management.calibration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import video.management.calibration.entity.Calibration;


import java.util.List;

public interface CalibrationRepository extends JpaRepository<Calibration, Long> {
//        List<Calibration> findAllByTypeStartsWithIgnoreCase(String type);
        List<Calibration> findAllByTypeStartsWithIgnoreCase(String type);


}

