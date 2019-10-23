package fractalart.service;

import fractalart.model.Fractal;
import fractalart.model.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.Random;

public class Drawer {
    private static final double CANTOR_SET_LENGTH = 250;
    private static final double TREE_ANGLE = -90;
    private static final double TREE_DEPTH = 10;

    private static final double MIN_LENGTH_CANTOR_SET = 1;
    private static final double DEPTH_TO_RETURN_FRACTAL_TREE = 0;
    private static final int FRACTAL_TREE_BOUND = 106;
    private static final int CANTOR_SET_Y_STEP = 20;

    private Canvas canvas;

    public Drawer(Canvas canvas) {
        this.canvas = canvas;
    }

    public void draw(Fractal fractal) {
        switch (fractal) {
            case CANTOR_SET:
                drawCantorSet();
                break;
            case TREE:
                drawFractalTree();
                break;
        }
    }

    private void drawCantorSet() {
        drawCantorSet(getCanvasCenter().getX() / 1.5, getCanvasCenter().getY(), CANTOR_SET_LENGTH);
    }

    private void drawCantorSet(double x, double y, double length) {
        if (length > MIN_LENGTH_CANTOR_SET) {
            canvas.getGraphicsContext2D().strokeLine(x, y, x + length, y);
            y += CANTOR_SET_Y_STEP;
            drawCantorSet(x, y, length / 3);
            drawCantorSet(x + length * 2 / 3, y, length / 3);
        }
    }

    private void drawFractalTree() {
        drawFractalTree(canvas.getWidth() / 2, canvas.getHeight(), TREE_ANGLE, TREE_DEPTH);
    }

    private void drawFractalTree(double x, double y, double angle, double depth) {
        if (depth == DEPTH_TO_RETURN_FRACTAL_TREE) return;

        if (depth < 9) {
            Color color = Color.rgb(
                    0, new Random().nextInt(FRACTAL_TREE_BOUND) + (250 - FRACTAL_TREE_BOUND), 0);
            canvas.getGraphicsContext2D().setStroke(color);
        } else {
            canvas.getGraphicsContext2D().setStroke(Color.BROWN);
        }

        double variationFactor = 10;
        double x2 = x + Math.cos(Math.toRadians(angle)) * depth * variationFactor;
        double y2 = y + Math.sin(Math.toRadians(angle)) * depth * variationFactor;
        canvas.getGraphicsContext2D().strokeLine(x, y, x2, y2);

        if (depth < 5) {
            drawFractalTree(x2 + variationFactor, y2 + variationFactor, angle, depth - 1);
        }

        drawFractalTree(x2, y2, angle - variationFactor * 2, depth - 1);
        drawFractalTree(x2, y2, angle + variationFactor * 2, depth - 1);
    }

    private Point getCanvasCenter() {
        return new Point(canvas.getWidth() / 2, canvas.getHeight() / 2);
    }
}
