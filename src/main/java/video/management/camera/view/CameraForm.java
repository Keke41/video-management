package video.management.camera.view;

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
import video.management.calibration.view.CalibrationField;
import video.management.camera.entity.Camera;

public class CameraForm extends FormLayout {

    TextField type = new TextField("Type");
    CalibrationField calibration = new CalibrationField("Calibration");
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Camera> binder = new BeanValidationBinder<>(Camera.class);


    public CameraForm() {
        binder.bindInstanceFields(this);
        add(type, calibration, createButtonsLayout());
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

    public void setCamera(Camera camera) {
        binder.setBean(camera);
    }

    public static abstract class CameraFormEvent extends ComponentEvent<CameraForm> {
        private Camera camera;

        protected CameraFormEvent(CameraForm source, Camera camera) {
            super(source, false);
            this.camera = camera;
        }
        public Camera getCamera() {
            return camera;
        }
    }

    public static class SaveEvent extends CameraFormEvent {
        SaveEvent(CameraForm source, Camera camera) {
            super(source, camera);
        }
    }

    public static class DeleteEvent extends CameraFormEvent {
        DeleteEvent(CameraForm source, Camera camera) {
            super(source, camera);
        }

    }

    public static class CloseEvent extends CameraFormEvent {
        CloseEvent(CameraForm source) {
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
