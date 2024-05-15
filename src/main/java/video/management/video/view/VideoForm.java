package video.management.video.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import video.management.camera.entity.Camera;
import video.management.video.entity.Video;

import java.util.List;

public class VideoForm extends FormLayout {

    TextField name = new TextField("Name");

    DateTimePicker time = new DateTimePicker("Time");
//    TimePicker length = new TimePicker("Length"); // TODO !!!!

    TextField length = new TextField("Length");
    BigDecimalField frequency = new BigDecimalField("Frequency");
    TextField path = new TextField("Path");
    ComboBox<Camera> camera = new ComboBox<>("Camera");
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Video> binder = new BeanValidationBinder<>(Video.class);


    public VideoForm(List<Camera> cameras) {
        binder.bindInstanceFields(this);
        camera.setItems(cameras);
        camera.setItemLabelGenerator(Camera::getType);
        add(name, camera,length, time, frequency,path,createButtonsLayout());
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

    public void setVideo(Video video) {
        binder.setBean(video);}

    // Events
    public static abstract class VideoFormEvent extends ComponentEvent<VideoForm> {
        private Video video;

        protected VideoFormEvent(VideoForm source, Video video) {
            super(source, false);
            this.video = video;
        }

        public Video getVideo() {
            return video;
        }
    }

    public static class SaveEvent extends VideoFormEvent {
        SaveEvent(VideoForm source, Video video) {
            super(source, video);
        }
    }

    public static class DeleteEvent extends VideoFormEvent {
        DeleteEvent(VideoForm source, Video video) {
            super(source, video);
        }

    }

    public static class CloseEvent extends VideoFormEvent {
        CloseEvent(VideoForm source) {
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
