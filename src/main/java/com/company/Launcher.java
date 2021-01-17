package com.company;
import javafx.scene.*;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.scene.paint.*;

import java.util.Random;

public class Launcher extends Application {
    final int size = 8;
    final int mult = 100;

    @Override public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, size * mult, size * mult);

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Press "+ " x:" + (int) (mouseEvent.getSceneX()/mult) + " y:" + (int) (mouseEvent.getSceneY()/mult));
            }
        });

        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Release "+ " x:" + (int) (mouseEvent.getSceneX()/mult) + " y:" + (int) (mouseEvent.getSceneY()/mult));
            }
        });

        scene.setFill(Color.rgb(133, 3, 219));

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
                    rectangles[i][j].setFill(Color.rgb(252, 194, 121));
                else
                    rectangles[i][j].setFill(Color.rgb(95, 118, 20));
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
            linesX[i].setStroke(Color.rgb(14, 93, 163));
            root.getChildren().add(linesX[i]);
        }
        for (int i = 0; i <= size; ++i) {
            linesY[i] = new Line();
            linesY[i].setStartX(0);
            linesY[i].setStartY((double) i * mult);
            linesY[i].setEndX(size * mult);
            linesY[i].setEndY((double) i * mult);
            linesY[i].setStrokeWidth(1);
            linesY[i].setStroke(Color.rgb(14, 93, 163));
            root.getChildren().add(linesY[i]);
        }

        int[] pressedR = new int[1];
        int[] pressedC = new int[1];

        Circle[][] circles = new Circle[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) && (j < 3 || j > 4)) {
                    circles[i][j] = new Circle();
                    circles[i][j].setCenterX((double) i * 100 + 50);
                    circles[i][j].setCenterY((double) j * 100 + 50);
                    circles[i][j].setRadius((double) 45 * size / 8);
                    circles[i][j].setStrokeWidth(5);

                    circles[i][j].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            int r = (int) (mouseEvent.getSceneX()/mult);
                            int c = (int) (mouseEvent.getSceneY()/mult);
                            pressedR[0] = r;
                            pressedC[0] = c;
                            System.out.println("Press "+ " x:" + (int) (mouseEvent.getSceneX()/mult) + " y:" + (int) (mouseEvent.getSceneY()/mult));

                        }
                    });
                    int finalI = i;
                    int finalJ = j;
                    circles[i][j].addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            int r = (int) (mouseEvent.getSceneX()/mult);
                            int c = (int) (mouseEvent.getSceneY()/mult);
                            Random rand = new Random();
                            System.out.println("Release "+ " x:" + (int) (mouseEvent.getSceneX()/mult) + " y:" + (int) (mouseEvent.getSceneY()/mult));
                            circles[r][c] = new Circle();
                            circles[r][c].setCenterX((double) r * 100 + 50);
                            circles[r][c].setCenterY((double) c * 100 + 50);
                            circles[r][c].setRadius((double) mult / 2 - 5);
                            circles[r][c].setStrokeWidth(5);
                            circles[r][c].setFill(circles[finalI][finalJ].getFill());
                            circles[r][c].setStroke(circles[finalI][finalJ].getStroke());
                            root.getChildren().remove(circles[pressedR[0]][pressedC[0]]);
                            root.getChildren().add(circles[r][c]);
                        }
                    });


                    if (j < 3) {
                        circles[i][j].setFill(Color.rgb(17, 100, 206));
                        circles[i][j].setStroke(Color.rgb(124, 69, 23));
                    }
                    else {
                        circles[i][j].setFill(Color.rgb(66, 162, 59));
                        circles[i][j].setStroke(Color.rgb(168, 74, 253));
                    }
                    root.getChildren().add(circles[i][j]);
                }
            }
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
