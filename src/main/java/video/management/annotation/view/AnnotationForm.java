package video.management.annotation.view;

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
import video.management.annotation.entity.Annotation;


public class AnnotationForm extends FormLayout {

    TextField name = new TextField("Name");
    TextField comment = new TextField("Comment");
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Annotation> binder = new BeanValidationBinder<>(Annotation.class);


    public AnnotationForm() {
        binder.bindInstanceFields(this);
        add(name,comment,createButtonsLayout());
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

    public void setAnnotation(Annotation annotation) {
        binder.setBean(annotation);
    }


    // Events
    public static abstract class AnnotationFormEvent extends ComponentEvent<AnnotationForm> {
        private Annotation annotation;

        protected AnnotationFormEvent(AnnotationForm source, Annotation annotation) {
            super(source, false);
            this.annotation = annotation;
        }

        public Annotation getAnnotation() {
            return annotation;
        }
    }

    public static class SaveEvent extends AnnotationFormEvent {
        SaveEvent(AnnotationForm source, Annotation annotation) {
            super(source, annotation);
        }
    }

    public static class DeleteEvent extends AnnotationFormEvent {
        DeleteEvent(AnnotationForm source, Annotation annotation) {
            super(source, annotation);
        }

    }

    public static class CloseEvent extends AnnotationFormEvent {
        CloseEvent(AnnotationForm source) {
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
