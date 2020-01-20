package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SquareMap map = new SquareMap(4);
        TextArea text = new TextArea();
        text.setPrefRowCount(20);
        text.setPrefColumnCount(20);
        text.setWrapText(true);
        GridPane theGame = new GridPane();
        Generator gen = new Generator();
        for (int k = 0; k < 16; k++) {
            if (map.add(gen.value(), gen.point())) break;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Rectangle rect = new Rectangle(80, 80);
                rect.setStroke(Color.WHITE);
                rect.setFill(Color.LAVENDER);
                if(map.atPosition(i, j)!=0)rect.setFill(Color.LIGHTGREEN);
                theGame.add(new Label("", rect), i, j);
                int p = map.atPosition(i, j);
                Label label = new Label("           " + p);
                label.setPrefSize(80, 80);
                theGame.add(label, i, j);

            }
        }
        Button top = new Button("TOP");
        top.setOnAction(e -> moveDirection(Direction.top, theGame, map));
        Button left = new Button("LEFT");
        left.setOnAction(e -> moveDirection(Direction.left, theGame, map));
        Button bottom = new Button("BOTTOM");
        bottom.setOnAction(e -> moveDirection(Direction.bottom, theGame, map));
        Button right = new Button("RIGHT");
        right.setOnAction(e -> moveDirection(Direction.right, theGame, map));
        VBox root = new VBox(5);
        root.getChildren().addAll(theGame, top, left, bottom, right);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048");
        primaryStage.show();

    }

    void moveDirection(Direction direction, GridPane theGame, SquareMap map) {
        map.moveDirection(direction);
        Generator gen = new Generator();
        for (int k = 0; k < 16; k++) {
            if (map.add(gen.value(), gen.point())) break;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Rectangle rect = new Rectangle(80, 80);
                rect.setFill(Color.LAVENDER);
                if(map.atPosition(i, j)!=0)rect.setFill(Color.LIGHTGREEN);
                if(map.atPosition(i, j)>=2048)rect.setFill(Color.LIGHTPINK);
                rect.setStroke(Color.WHITE);

                theGame.add(new Label("", rect), i, j);
                int p = map.atPosition(i, j);
                Label label = new Label("           " + p);
                label.setPrefSize(80, 80);
                theGame.add(label, i, j);
            }
        }

    }
}
