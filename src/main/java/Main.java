import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage){
        Area area = new Area();
        Canvas canvas = area.toCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GameCycle game = new GameCycle(gc, area);

        BorderPane root = new RootBorderPane(canvas).toBorderPain();


        Scene scene = new Scene(root);

        scene.setOnKeyPressed(keyEvent -> {
            String s = "";
            switch (keyEvent.getCode()){
                case W -> s = "up";
                case A -> s = "left";
                case S -> s = "down";
                case D -> s = "right";
            }
            game.v.setV(s);
        });

        stage.setScene(scene);
        stage.setTitle("SNAKE");
        stage.setWidth(800);
        stage.setHeight(550);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
