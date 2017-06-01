package demoApp;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.internal.jni.CoreLayer;
import com.esri.arcgisruntime.internal.jni.CoreRequest;
import com.esri.arcgisruntime.layers.Layer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.portal.Portal;
import com.esri.arcgisruntime.portal.PortalItem;
import static java.lang.Thread.sleep;

public class MyMapApp extends Application {

    private ArcGISMap map;
    private MapView mapView;

    @Override
    public void start(Stage stage) throws Exception {
        //create a border pane
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
        //size the stage and add a title
        stage.setTitle("My first map application");
        stage.setWidth(600);
        stage.setHeight(350);
        stage.setScene(scene);
        stage.show();

        ///*
        map = new ArcGISMap("http://www.arcgis.com/home/item.html?id=459bf5ed4f4d47c299938a4263249b90");
        //*/
        /*
        //create an ArcGISMap that defines the layers of data to view
        map = new ArcGISMap();
        //make the basemap for streets
        map.setBasemap(Basemap.createNationalGeographic());
         */
        //map.addDoneLoadingListener(listener);
        //create the MapView JavaFX control and assign its map
        mapView = new MapView();
        mapView.setMap(map);
        //add the MapView
        borderPane.setCenter(mapView);
        map.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("map loaded");
                System.out.println(map.getOperationalLayers().size());
                for (Layer lay : map.getOperationalLayers()) {
                    System.out.println(lay.getLoadStatus());
                }
            }
        });
    }

    @Override
    public void stop() throws Exception {
        //release resources when the application closes
        mapView.dispose();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private static class LayerImpl extends Layer {

        public LayerImpl(CoreLayer coreLayer) {
            super(coreLayer);
        }

        @Override
        protected ListenableFuture<?> onRequestRequired(CoreRequest cr) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
