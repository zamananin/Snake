import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Timer;
import java.util.TimerTask;

public class GameCycle {
    GraphicsContext gc;
    Area area;
    int speed = 100;
    int score = 0;
    boolean running = true;
    Vector v = new Vector("right");



    public GameCycle(GraphicsContext inpGc, Area inpArea){
        gc = inpGc;
        area = inpArea;
        setCycle();
    }


    private void setCycle(){
        Timer timer = new Timer("timer", true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                step();
            }
        };
        timer.scheduleAtFixedRate(task, 0, speed);
    }

    private void step(){
        switch (v.getV()){
            case "right" -> area.headX++;
            case "down" -> area.headY ++;
            case "left" ->area.headX --;
            case "up" -> area.headY --;
        }

        if (area.headX == -1) area.headX += area.width;
        if (area.headX == area.width) area.headX -= area.width;
        if (area.headY == -1) area.headY += area.height;
        if (area.headY == area.height) area.headY -= area.height;

        if (!area.setHead(area.headX, area.headY)) {
            running = false;
            v.setV("stop");
            score = 0;
            running = true;
            area.setArea();
            v.setV("right");

        }
        else {
            if (area.tiles[area.headX][area.headY].fruit) {
                area.tiles[area.headX][area.headY].fruit = false;
                score += area.length;
                area.length++;
                area.createFruit();
            }
            draw();
        }

    }

    private void draw(){
        gc.setFill(new Color(0.9, 0.9, 0.95, 1));
        gc.fillRect(0, 0, (area.width+2)* area.tileSize, (area.height+2)* area.tileSize);
        gc.setFill(Color.BLACK);
        gc.fillText("Score: " + score, area.tileSize, (area.height+2)*area.tileSize);

        for (int i = 0; i < area.width; i++){
            for (int j = 0; j < area.height; j++){
                if (area.tiles[i][j].wall){
                    gc.setFill(new Color(0.0, 0.05, 0.4, 1));
                    int x = (1+i)*area.tileSize;
                    int y = (1+j)*area.tileSize;
                    gc.fillRect(x, y, area.tileSize, area.tileSize);
                }
                if (area.tiles[i][j].fruit){
                    gc.setFill(new Color(1.0, 0.1, 0.0, 1));
                    int x = (1+i)*area.tileSize;
                    int y = (1+j)*area.tileSize;
                    gc.fillOval(x, y, area.tileSize-4, area.tileSize-4);
                }
                if (area.tiles[i][j].snake>0){
                    area.tiles[i][j].snake++;
                    if (area.tiles[i][j].snake == area.length) area.tiles[i][j].snake = 0;

                    gc.setFill(new Color(0.0, 0.9, 0.0, 1));
                    int x = (1+i)*area.tileSize + (int) (Math.random()*4) -2;
                    int y = (1+j)*area.tileSize + (int) (Math.random()*4) -2;
                    gc.fillOval(x, y, area.tileSize-2, area.tileSize-2);
                }
            }
        }
    }
}
