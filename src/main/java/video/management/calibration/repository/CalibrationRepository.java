package video.management.calibration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import video.management.calibration.entity.Calibration;


public interface CalibrationRepository extends JpaRepository<Calibration, Long> {


}

