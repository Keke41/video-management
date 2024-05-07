package video.management.camera.view;


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
import video.management.camera.entity.Camera;
import video.management.camera.repository.CameraRepository;
import video.management.web.JsonGridExporterProvider;
import video.management.web.MainLayout;

import java.util.List;

@PageTitle("Viki Szakdoga")
@Route(value = "camera", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class CameraView extends VerticalLayout {
    private final CameraRepository repo;
    private final Grid<Camera> grid;
    private final TextField filterText = new TextField();
    private CameraForm form;


    public CameraView(CameraRepository repo) {
        this.repo = repo;
        this.grid = new Grid<>(Camera.class);
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

        Button addButton = new Button("Add camera");
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
        grid.setItems(repo.findAllByTypeStartsWithIgnoreCase(filterText.getValue()));
    }


    private void listEntities() {
        List<Camera> all = repo.findAll();
        grid.setItems(all);
    }

    public void  editEntity(Camera camera) {
        if (camera == null) {
            closeEditor();
        } else {
            form.setCamera(camera);
            form.setVisible(true);

            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setCamera(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void configureForm() {
        form = new CameraForm();
        form.setWidth("10em");
        form.addSaveListener(this::saveCamera); // <1>
        form.addDeleteListener(this::deleteCamera); // <2>
        form.addCloseListener(e -> closeEditor()); // <3>

    }

    private void saveCamera(CameraForm.SaveEvent event) {
        repo.save(event.getCamera());
        updateList();
        closeEditor();
    }


    private void deleteCamera(CameraForm.DeleteEvent event) {
        //repo.deleteCamera(event.getContact());
        repo.delete(event.getCamera());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(5, grid);
        content.setFlexGrow(1, form);

        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassNames("camera-grid");
        grid.setNestedNullBehavior(Grid.NestedNullBehavior.ALLOW_NULLS);
        grid.setColumns("id", "type", "calibration");

        grid.asSingleSelect().addValueChangeListener(event ->
                editEntity(event.getValue()));
    }

    private void addEntity() {
        grid.asSingleSelect().clear();
        editEntity(new Camera());
    }

}
