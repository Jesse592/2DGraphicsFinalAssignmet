package Logic;

import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;

public class FieldGrid {

    private int width;
    private int height;

    private int tileWidth;
    private int tileHeight;

    private FieldTile[][] tiles;

    public FieldGrid(int width, int height) {
        this(width, height, 25, 25);
    }
    public FieldGrid(int width, int height, int tileWidth, int tileHeight) {
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        this.tiles = generateTiles();
    }

    //Todo: implement method
    private FieldTile[][] generateTiles() {
        FieldTile[][] tiles = new FieldTile[this.height][this.width];

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                tiles[y][x] = new FieldTile(
                        new Point2D.Double(
                                (this.tileWidth * x) + (this.tileWidth / 2f),
                                (this.tileHeight * y) + (this.tileHeight / 2f)),
                        this.tileWidth,
                        this.tileHeight);
            }
        }

        return tiles;
    }

    //Todo: implement method
    private boolean generateHeatMap(){
        return true;
    }

    //Todo: implement method
    private boolean generateVectorFiled(){
        return true;
    }

    public void draw(FXGraphics2D g2d) {
        for (FieldTile[] tileYs : this.tiles) {
            for (FieldTile tile : tileYs) {
                tile.draw(g2d);
            }
        }
    }
}
