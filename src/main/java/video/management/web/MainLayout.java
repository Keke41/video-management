package video.management.web;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import video.management.annotation.view.AnnotationView;
import video.management.camera.view.CameraView;
import video.management.video.view.VideoView;

public class MainLayout extends AppLayout {
    //private final SecurityService securityService;

//    public MainLayout(SecurityService securityService) {
//        this.securityService = securityService;
//        createHeader();
//        createDrawer();
//    }


    public MainLayout() {
        createHeader();
        createDrawer();
    }
    private void createHeader() {
        H1 logo = new H1("Video Management");
        logo.addClassNames(
            LumoUtility.FontSize.LARGE,
            LumoUtility.Margin.MEDIUM);

//        String u = securityService.getAuthenticatedUser().getUsername();
//        Button logout = new Button("Log out " + u, e -> securityService.logout()); // <2>

//        var header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        var header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo); // <4>
        header.setWidthFull();
        header.addClassNames(
            LumoUtility.Padding.Vertical.NONE,
            LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header); 

    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                new RouterLink("Camera", CameraView.class),
                new RouterLink("Video", VideoView.class),
                new RouterLink("Annotation", AnnotationView.class)

        ));
    }
}