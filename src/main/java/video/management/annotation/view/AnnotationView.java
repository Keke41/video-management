package video.management.annotation.view;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import software.xdev.vaadin.grid_exporter.GridExporter;
import video.management.annotation.entity.Annotation;
import video.management.annotation.repository.AnnotationRepository;
import video.management.web.JsonGridExporterProvider;
import video.management.web.MainLayout;

@PageTitle("Viki Szakdoga")
@Route(value = "annotation", layout = MainLayout.class)
public class AnnotationView extends VerticalLayout {
    private final AnnotationRepository repo;

    private final Grid<Annotation> grid;
    private final TextField filterText = new TextField();
    private AnnotationForm form;



    public AnnotationView(AnnotationRepository repo) {
        this.repo = repo;
//        this.calibrationRepo = calibrationRepository;
        this.grid = new Grid<>(Annotation.class);
        configureForm();
        configureGrid();
        add(getToolbar(),getContent());
        listEntities();
        closeEditor();

    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by type...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addButton = new Button("Add annotation");
        addButton.addClickListener(click -> addEntity());

        Button exportButton = new Button(
                "Export",
                VaadinIcon.PRINT.create(),
                e -> GridExporter.newWithDefaults(this.grid)
                        .loadFromProvider(new JsonGridExporterProvider())
                        .open());

        var toolbar = new HorizontalLayout(filterText, addButton, exportButton);

        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(repo.findAllByNameStartsWithIgnoreCase(filterText.getValue()));
    }


    private void listEntities() {
        grid.setItems(repo.findAll());
    }

    public void  editEntity(Annotation annotation) {
        if (annotation == null) {
            closeEditor();
        } else {
            form.setAnnotation(annotation);
            form.setVisible(true);

//            calibrationForm.setCalibration(camera);
//            calibrationForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setAnnotation(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void configureForm() {
        form = new AnnotationForm();
        form.setWidth("10em");
        form.addSaveListener(this::saveAnnotation); // <1>
        form.addDeleteListener(this::deleteAnnotation); // <2>
        form.addCloseListener(e -> closeEditor()); // <3>

//        calibrationForm = new CalibrationForm();
//        calibrationForm.setWidth("10em");
//        calibrationForm.addSaveListener(this::saveCalibration); // <1>
//        calibrationForm.addDeleteListener(this::deleteCamera); // <2>
//        calibrationForm.addCloseListener(e -> closeEditor()); // <3>
    }

    private void saveAnnotation(AnnotationForm.SaveEvent event) {
        //repo.saveCamera(event.getContact());
        repo.save(event.getAnnotation());
        updateList();
        closeEditor();
    }
// temp off //
//    private void saveCalibration(CalibrationForm.SaveEvent event) {
//        //repo.saveCamera(event.getContact());
//        calibrationRepo.save(event.getCalibration());
//        updateList();
//        closeEditor();
//    }

    private void deleteAnnotation(AnnotationForm.DeleteEvent event) {
        //repo.deleteCamera(event.getContact());
        repo.delete(event.getAnnotation());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getContent() {
//        HorizontalLayout content = new HorizontalLayout(grid, form, calibrationForm);
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(5, grid);
        content.setFlexGrow(1, form);
//        content.setFlexGrow(1, calibrationForm);

        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassNames("annotation-grid");
//        grid.setSizeFull();
//        grid.setColumns("id", "type"); // MAYBE I NEED THIS
//        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
//        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
      //  grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setNestedNullBehavior(Grid.NestedNullBehavior.ALLOW_NULLS);
        grid.setColumns("id", "comment", "name");
//        grid.getColumnByKey("calibration.type").setHeader("Calibration");

        grid.asSingleSelect().addValueChangeListener(event ->
                editEntity(event.getValue()));
    }

    private void addEntity() {
        grid.asSingleSelect().clear();
        editEntity(new Annotation());
    }

}
