package video.management.calibration.view;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.textfield.BigDecimalField;
import video.management.calibration.entity.Calibration;

import java.math.BigDecimal;

public class CalibrationField extends CustomField<Calibration> {

    private final BigDecimalField skew_1 = new BigDecimalField();
    private final BigDecimalField skew_2 = new BigDecimalField();

    public CalibrationField(){

        add(skew_1, skew_2);
    }



    @Override
    protected Calibration generateModelValue() {
        Calibration modelValue = new Calibration();
        modelValue.setSkew_1(skew_1.getValue().floatValue());
        modelValue.setSkew_2(skew_2.getEmptyValue().floatValue());
        return modelValue;
    }

    @Override
    protected void setPresentationValue(Calibration value) {
        skew_1.setValue(BigDecimal.valueOf(value.getSkew_1()));
        skew_2.setValue(BigDecimal.valueOf(value.getSkew_2()));

    }
}
