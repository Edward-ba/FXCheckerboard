package com.company;

import javafx.application.Application;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Launcher extends Application {

    @Override public void start(Stage stage) {

        int size = 8;
        int mult = 100;

        Group root = new Group();
        Scene scene = new Scene(root, size * mult, size * mult);
        scene.setFill(Color.WHITE);

        Rectangle[][] rectangles = new Rectangle[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                rectangles[i][j] = new Rectangle();
                rectangles[i][j].setX(i * mult);
                rectangles[i][j].setY(j * mult);
                rectangles[i][j].setWidth(mult);
                rectangles[i][j].setHeight(mult);
                rectangles[i][j].setArcWidth(0);
                rectangles[i][j].setArcHeight(0);
                if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0))
                    rectangles[i][j].setFill(Color.WHITE);
                else
                    rectangles[i][j].setFill(Color.rgb(230, 109, 87));
                root.getChildren().add(rectangles[i][j]);
            }
        }


        Line[] linesX = new Line[size + 1];
        Line[] linesY = new Line[size + 1];


        for (int i = 0; i <= size; ++i) {
            linesX[i] = new Line();
            linesX[i].setStartX((double) i * mult);
            linesX[i].setStartY(0);
            linesX[i].setEndX((double) i * mult);
            linesX[i].setEndY((double) size * mult);
            linesX[i].setStrokeWidth(1);
            linesX[i].setStroke(Color.BLUE);
            root.getChildren().add(linesX[i]);
        }
        for (int i = 0; i <= size; ++i) {
            linesY[i] = new Line();
            linesY[i].setStartX(0);
            linesY[i].setStartY((double) i * mult);
            linesY[i].setEndX(size * mult);
            linesY[i].setEndY((double) i * mult);
            linesY[i].setStrokeWidth(1);
            linesY[i].setStroke(Color.BLUE);
            root.getChildren().add(linesY[i]);
        }

        Circle[][] circles = new Circle[size][size];


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
