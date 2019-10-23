package drawingalgorithms;

import drawingalgorithms.model.Algorithm;
import drawingalgorithms.service.Drawer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Random;

public class DrawingAlgorithms extends Application {
    private static final String DrawingAlgorithms = "Drawing algorithms";

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();

        Canvas canvas = new Canvas(800, 600);
        canvas.getGraphicsContext2D().setLineWidth(3);
        root.getChildren().add(canvas);

        Drawer drawer = new Drawer(canvas);
        Algorithm[] algorithms = Algorithm.values();
        int index = new Random().nextInt(algorithms.length);
        drawer.draw(algorithms[index]);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle(DrawingAlgorithms);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
