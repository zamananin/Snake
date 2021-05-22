import javafx.scene.canvas.Canvas;

public class Area {
    int width = 40;
    int height = 25;
    int tileSize = 16;
    int walls = 8;
    int length = 5;
    int headX;
    int headY;
    int hols = 3;

    Tile[][] tiles = new Tile[width][height];

    public Area(){
        setArea();
    }


    public void setArea(){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                tiles[i][j] = new Tile();
                if(i == 0 || i == width-1 ||
                        j == 0 || j == height-1) tiles[i][j].wall = true;
            }
        }
        for (int i = 0; i < walls; i++){
            tiles[(int) (Math.random()*(width-2)+1)][(int) (Math.random()*(height-2)+1)].wall = true;
        }

        createFruit();

        boolean p = true;
        while (p){
            headX = (int) (Math.random()*(width));
            headY = (int) (Math.random()*(height));
            Tile tile = tiles[headX][headY];
            if(!tile.wall && !tile.fruit) p = false;
        }

        for (int i = 0; i <= hols; i++){
            int place = (int) (Math.random()*(width+height-4));
            if (place > width-2) {
                tiles[0][place - width + 1 ].wall = false;
                tiles[width-1][place - width + 1].wall = false;
            } else {
                tiles[place + 1][0].wall = false;
                tiles[place + 1][height - 1].wall = false;
            }

        }

        length = 5;
    }

    public class Tile{
        boolean wall = false;
        boolean fruit = false;
        int snake = 0;

    }

    public Canvas toCanvas(){
        return new Canvas((width + 2) * tileSize, (height + 2) * tileSize);
    }

    public void createFruit(){
        boolean p = true;
        while (p){
            Tile tile = tiles[(int) (Math.random()*(width))][(int) (Math.random()*(height))];
            if(!tile.wall && tile.snake == 0){
                tile.fruit = true;
                p = false;

            }
        }
    }

    public boolean setHead(int x, int y){
        if (x < 0 || x >= width || y < 0 ||y >= height) return  false;
        if (tiles[x][y].wall || tiles[x][y].snake > 0) return  false;
        tiles[x][y].snake += 1;
        return true;
    }
}
