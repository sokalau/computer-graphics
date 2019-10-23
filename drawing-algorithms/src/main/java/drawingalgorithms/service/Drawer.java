package drawingalgorithms.service;

import drawingalgorithms.model.Algorithm;
import javafx.scene.canvas.Canvas;

public class Drawer {
    private Canvas canvas;

    private static final double POINT_RADIUS = 2;

    public Drawer(Canvas canvas) {
        this.canvas = canvas;
    }

    public void draw(Algorithm algorithm) {
        switch (algorithm) {
            case LINE_DRAWING:
                drawLine();
                break;
            case CIRCLE_DRAWING:
                drawCircle();
                break;
        }
    }

    private void drawLine() {
        int x0 = 50;
        int y0 = 50;
        int x1 = 200;
        int y1 = 200;
        drawLine(x0, y0, x1, y1);
    }

    private void drawLine(int x0, int y0, int x1, int y1) {
        int deltaX = x1 - x0;
        int deltaY = y1 - y0;
        int d = 2 * deltaY - deltaX;
        for (int i = x0; i < x1; i++) {
            canvas.getGraphicsContext2D().strokeOval(i, y0, POINT_RADIUS, POINT_RADIUS);
            if (d > 0) {
                y0 += 1;
                d = d - 2 * deltaX;
            } else {
                d = d + 2 * deltaX;
            }
        }
    }

    private void drawCircle(int x0, int y0, int x, int y) {
        canvas.getGraphicsContext2D().strokeOval(x0 + x, y0 + y, POINT_RADIUS, POINT_RADIUS);
        canvas.getGraphicsContext2D().strokeOval(x0 - x, y0 + y, POINT_RADIUS, POINT_RADIUS);
        canvas.getGraphicsContext2D().strokeOval(x0 + x, y0 - y, POINT_RADIUS, POINT_RADIUS);
        canvas.getGraphicsContext2D().strokeOval(x0 - x, y0 - y, POINT_RADIUS, POINT_RADIUS);
        canvas.getGraphicsContext2D().strokeOval(x0 + y, y0 + x, POINT_RADIUS, POINT_RADIUS);
        canvas.getGraphicsContext2D().strokeOval(x0 - y, y0 + x, POINT_RADIUS, POINT_RADIUS);
        canvas.getGraphicsContext2D().strokeOval(x0 + y, y0 - x, POINT_RADIUS, POINT_RADIUS);
        canvas.getGraphicsContext2D().strokeOval(x0 - y, y0 - x, POINT_RADIUS, POINT_RADIUS);
    }

    private void drawCircle(int x0, int y0, int radius) {
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;
        drawCircle(x0, y0, x, y);
        while (y >= x) {
            x++;
            if (d > 0) {
                y--;
                d += 4 * (x - y) + 10;
            } else {
                d += 4 * x + 6;
            }
            drawCircle(x0, y0, x, y);
        }
    }

    private void drawCircle() {
        int x0 = 50;
        int y0 = 50;
        int radius = 20;
        drawCircle(x0, y0, radius);
    }
}
