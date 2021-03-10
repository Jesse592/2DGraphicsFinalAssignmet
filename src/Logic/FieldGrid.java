package Logic;

import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Stack;

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

    private FieldTile[][] generateTiles() {
        FieldTile[][] tiles = new FieldTile[this.height][this.width];

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                tiles[y][x] = new FieldTile(
                        new Point2D.Double(
                                (this.tileWidth * x) + (this.tileWidth / 2f),
                                (this.tileHeight * y) + (this.tileHeight / 2f)),
                        this.tileWidth,
                        this.tileHeight,
                        x,
                        y);
            }
        }

        return tiles;
    }

    public FieldTile getAtPoint(Point2D location) {
        int indexY = (int) (location.getY() / this.tileHeight);
        int indexX = (int) (location.getX() / this.tileWidth);

        if (this.tiles.length > indexY && this.tiles[indexY].length > indexX) {
            return this.tiles[indexY][indexX];
        }

        return null;
    }

    private void clearField() {
        for (FieldTile[] tilesY : this.tiles) {
            for (FieldTile tile : tilesY) {
                tile.setHeat(-1);
            }
        }
    }

    //Todo: implement method
    public boolean generateHeatMap(FieldTile startPoint) {
        clearField();

        int heat = startPoint.getHeat() + 1;
        startPoint.setHeat(heat);
        ArrayList<FieldTile> nextTiles = new ArrayList<>();

        getNeighbours(startPoint, nextTiles);

        ArrayList<FieldTile> newTiles = new ArrayList();
        do {
            heat++;

            for (FieldTile fieldTile : nextTiles) {
                if (fieldTile.getHeat() == -1 && fieldTile.isTransversable()) {
                    fieldTile.setHeat(heat);

                    getNeighbours(fieldTile, newTiles);
                }
            }

            nextTiles = new ArrayList<>(newTiles);
            newTiles.clear();
        } while(!nextTiles.isEmpty());

        return true;
    }

    private void getNeighbours(FieldTile startPoint, ArrayList<FieldTile> nextTiles) {
        int indexYStart = (int) startPoint.getIndex().getY();
        int indexXStart = (int) startPoint.getIndex().getX();

        if (indexYStart - 1 >= 0)
            nextTiles.add(this.tiles[indexYStart - 1][indexXStart]);
        if (indexYStart + 1 < this.height)
            nextTiles.add(this.tiles[indexYStart + 1][indexXStart]);
        if (indexXStart - 1 >= 0)
            nextTiles.add(this.tiles[indexYStart][indexXStart - 1]);
        if (indexXStart + 1 < this.width)
            nextTiles.add(this.tiles[indexYStart][indexXStart + 1]);
    }

    //Todo: implement method
    private boolean generateVectorFiled() {
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
