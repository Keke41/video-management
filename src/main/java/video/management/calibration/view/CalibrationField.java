package video.management.calibration.view;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.AbstractNumberField;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.NumberField;
import video.management.calibration.entity.Calibration;

import java.util.Optional;

public class CalibrationField extends CustomField<Calibration> {

    private final NumberField id = new NumberField("Calibration_id");
    private final BigDecimalField skew = new BigDecimalField("Skew");
    private final BigDecimalField focalLength_1 = new BigDecimalField("FocalLength_1");
    private final BigDecimalField focalLength_2 = new BigDecimalField("FocalLength_2");

    private final BigDecimalField principalPoint_1 = new BigDecimalField("PrincipalPoint_1");
    private final BigDecimalField principalPoint_2 = new BigDecimalField("PrincipalPoint_2");

    private final BigDecimalField pixelError_1 = new BigDecimalField("PixelError_1");
    private final BigDecimalField pixelError_2 = new BigDecimalField("pixelError_2");


    public CalibrationField(String label){
        setLabel(label);

        configureField();
    }

    private void configureField() {
        id.setReadOnly(true);
        HorizontalLayout skews = new HorizontalLayout(skew);
        HorizontalLayout focalLengths = new HorizontalLayout(focalLength_1, focalLength_2);
        HorizontalLayout principalPoints = new HorizontalLayout(principalPoint_1, principalPoint_2);
        HorizontalLayout pixelErrors = new HorizontalLayout(pixelError_1,pixelError_2);

        VerticalLayout layout = new VerticalLayout(
                skews,
                focalLengths,
                principalPoints,
                pixelErrors,
                id);

        add(layout);
    }

    @Override
    protected Calibration generateModelValue() {
        Calibration modelValue = new Calibration();

        Optional.of(id)
                .map(AbstractNumberField::getValue)
                .map(Double::longValue)
                .ifPresent(modelValue::setId);
        Optional.of(skew)
                .map(BigDecimalField::getValue)
                .ifPresent(modelValue::setSkew);
        Optional.of(focalLength_1)
                .map(BigDecimalField::getValue)
                .ifPresent(modelValue::setFocalLength_1);
        Optional.of(focalLength_2)
                .map(BigDecimalField::getValue)
                .ifPresent(modelValue::setFocalLength_2);
        Optional.of(principalPoint_1)
                .map(BigDecimalField::getValue)
                .ifPresent(modelValue::setPrincipalPoint_1);
        Optional.of(principalPoint_2)
                .map(BigDecimalField::getValue)
                .ifPresent(modelValue::setPrincipalPoint_2);
        Optional.of(pixelError_1)
                .map(BigDecimalField::getValue)
                .ifPresent(modelValue::setPixelError_1);
        Optional.of(pixelError_2)
                .map(BigDecimalField::getValue)
                .ifPresent(modelValue::setPixelError_2);

        return modelValue;
    }

    @Override
    protected void setPresentationValue(Calibration value) {

        Optional.ofNullable(value)
                .map(Calibration::getId)
                .map(Double::valueOf)
                .ifPresent(id::setValue);
        Optional.ofNullable(value)
                .map(Calibration::getSkew)
                .ifPresent(skew::setValue);
        Optional.ofNullable(value)
                .map(Calibration::getFocalLength_1)
                .ifPresent(focalLength_1::setValue);
        Optional.ofNullable(value)
                .map(Calibration::getFocalLength_2)
                .ifPresent(focalLength_2::setValue);
        Optional.ofNullable(value)
                .map(Calibration::getPrincipalPoint_1)
                .ifPresent(principalPoint_1::setValue);
        Optional.ofNullable(value)
                .map(Calibration::getPrincipalPoint_2)
                .ifPresent(principalPoint_2::setValue);
        Optional.ofNullable(value)
                .map(Calibration::getPixelError_1)
                .ifPresent(pixelError_1::setValue);
        Optional.ofNullable(value)
                .map(Calibration::getPixelError_2)
                .ifPresent(pixelError_2::setValue);
    }

}


