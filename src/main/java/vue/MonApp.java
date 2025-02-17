package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



import java.io.File;

public class MonApp extends Application {
    public void start(Stage stage){


        VBox root = new VBoxRoot();
        Scene scene = new Scene(root,300,480);
        File fileCss = new File("premiersStyles.css");
        scene.getStylesheets().add(fileCss.toURI().toString());
        stage.setScene(scene);
        stage.setTitle("ma premiere fenetre");
        stage.show();
    }

    public static void main(String[] ars){
        Application.launch();
    }
}
