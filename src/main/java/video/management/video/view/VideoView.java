package video.management.video.view;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import video.management.camera.entity.Camera;
import video.management.video.entity.Video;
import video.management.video.repository.VideoRepository;
import video.management.camera.repository.CameraRepository;
import video.management.web.MainLayout;

import java.util.Collections;
import java.util.Optional;

@PageTitle("Viki Szakdoga")
@Route(value = "video", layout = MainLayout.class)
public class VideoView extends VerticalLayout {
    private final VideoRepository repo;

    private final CameraRepository cameraRepository;
    private final Grid<Video> grid;
    private final TextField filterText = new TextField();
    private VideoForm form;

    public VideoView(VideoRepository repo, CameraRepository cameraRepository) {
        this.repo = repo;
        this.cameraRepository = cameraRepository;
        this.grid = new Grid<>(Video.class);
        configureForm();
        configureGrid();
        add(getToolbar(), getContent());
        listEntities();
        closeEditor();

    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addButton = new Button("Add video");
        addButton.addClickListener(click -> addEntity());

        var toolbar = new HorizontalLayout(filterText, addButton);

        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(repo.findAllByNameStartsWithIgnoreCase(filterText.getValue()));

    }


    private void listEntities() {
        grid.setItems(repo.findAll());
    }

    public void editEntity(Video video) {
        if (video == null) {
            closeEditor();
        } else {
            form.setVideo(video);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setVideo(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void configureForm() {
        form = new VideoForm(cameraRepository.findAll());
        form.setWidth("25em");
        form.addSaveListener(this::saveVideo); // <1>
        form.addDeleteListener(this::deleteVideo); // <2>
        form.addCloseListener(e -> closeEditor()); // <3>
    }

    private void saveVideo(VideoForm.SaveEvent event) {
        //repo.saveCamera(event.getContact());
        repo.save(event.getVideo());
        updateList();
        closeEditor();
    }

    private void deleteVideo(VideoForm.DeleteEvent event) {
        //repo.deleteCamera(event.getContact());
        repo.delete(event.getVideo());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
//        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassNames("video-grid");
        grid.setNestedNullBehavior(Grid.NestedNullBehavior.ALLOW_NULLS);
        grid.setColumns("id", "name", "camera.type", "length","time", "frequency","path");
        grid.getColumnByKey("camera.type").setHeader("Camera");
        grid.asSingleSelect().addValueChangeListener(event ->
                editEntity(event.getValue()));
    }

    private void addEntity() {
        grid.asSingleSelect().clear();
        editEntity(new Video());
    }

}
