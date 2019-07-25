package computergraphics;

import computergraphics.model.Fractal;
import computergraphics.service.Drawer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FractalArt extends Application {
    private static final String FRACTAL_ART = "Fractal art";

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();

        Canvas canvas = new Canvas(800, 600);
        canvas.getGraphicsContext2D().setLineWidth(3);
        root.getChildren().add(canvas);

        Drawer drawer = new Drawer(canvas);
        drawer.draw(Fractal.TREE);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle(FRACTAL_ART);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
