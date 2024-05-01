package video.management.web;


import software.xdev.vaadin.grid_exporter.GridExporterProvider;


public class JsonGridExporterProvider extends GridExporterProvider
{
    public JsonGridExporterProvider()
    {
        super(JsonConfigComponent.DEFAULT_VALUES, new JsonFormat());
    }
}
