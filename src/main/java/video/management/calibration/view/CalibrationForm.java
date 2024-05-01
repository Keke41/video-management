package video.management.calibration.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import video.management.calibration.entity.Calibration;

public class CalibrationForm extends FormLayout {

    TextField type = new TextField("Calib_Type");

    Button save = new Button("Calib_Save");
    Button delete = new Button("Calib_Delete");
    Button close = new Button("Calib_Cancel");

    Binder<Calibration> binder = new BeanValidationBinder<>(Calibration.class);


    public CalibrationForm() {
        binder.bindInstanceFields(this);
        add(type, createButtonsLayout());
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave()); // <1>
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean()))); // <2>
        close.addClickListener(event -> fireEvent(new CloseEvent(this))); // <3>

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); // <4>
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if(binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean())); // <6>
        }
    }


//    public void setContact(Camera camera) {
//        binder.setBean(camera); // <1>
//    }

    public void setCalibration(Calibration calibration) {
        binder.setBean(calibration);}
    // did this last time

    // Events
    public static abstract class CalibrationFormEvent extends ComponentEvent<CalibrationForm> {
        private Calibration calibration;

        protected CalibrationFormEvent(CalibrationForm source, Calibration calibration) {
            super(source, false);
            this.calibration = calibration;
        }

        public Calibration getCalibration() {
            return calibration;
        }
    }

    public static class SaveEvent extends CalibrationFormEvent {
        SaveEvent(CalibrationForm source, Calibration calibration) {
            super(source, calibration);
        }
    }

    public static class DeleteEvent extends CalibrationFormEvent {
        DeleteEvent(CalibrationForm source, Calibration calibration) {
            super(source, calibration);
        }

    }

    public static class CloseEvent extends CalibrationFormEvent {
        CloseEvent(CalibrationForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }







}

