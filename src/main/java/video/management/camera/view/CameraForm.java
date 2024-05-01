package video.management.camera.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import video.management.calibration.entity.Calibration;
import video.management.camera.entity.Camera;

public class CameraForm extends FormLayout {

    TextField type = new TextField("Type");

//    TextField skew_1 = new TextField("Skew");
//    ComboBox<Calibration> skew_1 = new ComboBox<>("Skew_1");

    TextField skew1Field = new TextField("Skew_1");
    TextField skew2Field = new TextField("Skew_2");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Camera> binder = new BeanValidationBinder<>(Camera.class);


    public CameraForm() {
        binder.bindInstanceFields(this);
        add(type, skew1Field, skew2Field, createButtonsLayout());
//        add(type,skew_1, createButtonsLayout());
//        skew_1.setItemLabelGenerator(this::generateSkewLabel);
    }


//    private String generateSkewLabel(Calibration calibration) {
//        if (calibration != null && calibration.getSkew_1() != 0) {
//            return "Skew_1: " + calibration.getSkew_1();
//        }
//        return "Skew_1: Not Available";
//    }

//    private String generateSkewLabel(Calibration calibration) {
//        if (calibration != null && calibration.getSkew_1() != null) {
//            return "Skew_1: " + calibration.getSkew_1();
//        }
//        return "Skew_1: Not Available";
//    }

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


//   // 09/04/2024 should I update this method?
    public void setCamera(Camera camera) {
        binder.setBean(camera);
    }

//    public void setCamera(Camera camera) {
//        binder.setBean(camera);
//        if (camera != null && camera.getCalibration() != null) {
//            Calibration calibration = camera.getCalibration();
//            skew1Field.setValue(String.valueOf(calibration.getSkew_1()));
//            skew2Field.setValue(String.valueOf(calibration.getSkew_2()));
//        }
//    }

    // another try

//    public void setCamera(Camera camera) {
//        binder.setBean(camera);
//        if (camera != null && camera.getCalibration() != null) {
//            Calibration calibration = camera.getCalibration();
////            System.out.println("Calibration: " + calibration); // Debug print
//            System.out.println("Skew 1: " + calibration.getSkew_1()); // Debug print
//            System.out.println("Skew 2: " + calibration.getSkew_2()); // Debug print
//            skew1Field.setValue(String.valueOf(calibration.getSkew_1()));
//            skew2Field.setValue(String.valueOf(calibration.getSkew_2()));
//        }
//    }


    // Events
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

