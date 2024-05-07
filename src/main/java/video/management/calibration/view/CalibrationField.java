package video.management.calibration.view;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.AbstractNumberField;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import video.management.calibration.entity.Calibration;

import java.math.BigDecimal;
import java.util.Optional;

public class CalibrationField extends CustomField<Calibration> {

    private final NumberField id = new NumberField("id");

    private final BigDecimalField skew_1 = new BigDecimalField("Skew_1");
    private final BigDecimalField skew_2 = new BigDecimalField("Skew_2");


    public CalibrationField(String label){
        setLabel(label);

        configureField();
    }

    private void configureField() {
        id.setReadOnly(true);
        HorizontalLayout layout = new HorizontalLayout(id,skew_1,skew_2);
        add(layout);
    }


    @Override
    protected Calibration generateModelValue() {
        Calibration modelValue = new Calibration();

        Optional.of(id)
                .map(AbstractNumberField::getValue)
                .map(Double::longValue)
                .ifPresent(modelValue::setId);
        Optional.of(skew_1)
                .map(BigDecimalField::getValue)
                .ifPresent(modelValue::setSkew_1);
        Optional.of(skew_2)
                .map(BigDecimalField::getValue)
                .ifPresent(modelValue::setSkew_2);

        return modelValue;
    }

    @Override
    protected void setPresentationValue(Calibration value) {

        Optional.ofNullable(value)
                .map(Calibration::getId)
                .map(Double::valueOf)
                .ifPresent(id::setValue);

        Optional.ofNullable(value)
                .map(Calibration::getSkew_1)
                .ifPresent(skew_1::setValue);

        Optional.ofNullable(value)
                .map(Calibration::getSkew_2)
                .ifPresent(skew_2::setValue);

    }
}
