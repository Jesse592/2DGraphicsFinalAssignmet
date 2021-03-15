package Logic;

import org.jetbrains.annotations.NotNull;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;
import java.lang.reflect.Field;
import java.util.ArrayList;

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
                FieldTile tile = new FieldTile(
                        new Point2D.Double(
                                (this.tileWidth * x) + (this.tileWidth / 2f),
                                (this.tileHeight * y) + (this.tileHeight / 2f)),
                        this.tileWidth,
                        this.tileHeight,
                        x,
                        y);

                if (y == 0 || y == this.height-1 || x == 0 || x == this.width-1) {
                    tile.setTransversable(false);
                }

                tiles[y][x] = tile;
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

    public void generateHeatMap(FieldTile startPoint) {
        clearField();

        int heat = startPoint.getHeat() + 1;
        startPoint.setHeat(heat);
        ArrayList<FieldTile> nextTiles = new ArrayList<>();

        setNeighbours(startPoint, nextTiles);

        ArrayList<FieldTile> newTiles = new ArrayList();
        do {
            heat++;

            for (FieldTile fieldTile : nextTiles) {
                if (fieldTile.getHeat() == -1 && fieldTile.isTransversable()) {
                    fieldTile.setHeat(heat);

                    setNeighbours(fieldTile, newTiles);
                }
            }

            nextTiles = new ArrayList<>(newTiles);
            newTiles.clear();
        } while(!nextTiles.isEmpty());

    }

    private void setNeighbours(FieldTile startPoint, ArrayList<FieldTile> nextTiles) {
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

    public void generateVectorField()
    {

        FieldTile[][] fieldTiles = this.tiles;
        for (int y = 1, fieldTilesLength = fieldTiles.length - 1; y < fieldTilesLength; y++) {
            FieldTile[] tilesY = fieldTiles[y];

            for (int x = 1, tilesYLength = tilesY.length - 1; x < tilesYLength; x++) {
                FieldTile tile = tilesY[x];

                if (tile.isTransversable()) {
                    Point2D vector = calculateVector(y, x, tile);
                    tile.setVector(vector);
                }

            }

        }

    }

    @NotNull
    private Point2D calculateVector(int y, int x, FieldTile tile) {
        int vectorX;
        int vectorY;

        FieldTile upTile = this.tiles[y -1][x];
        FieldTile downTile = this.tiles[y +1][x];
        FieldTile leftTile = this.tiles[y][x -1];
        FieldTile rightTile = this.tiles[y][x +1];

        if(leftTile.isTransversable() && leftTile.getHeat() != -1)
            vectorX = leftTile.getHeat();
        else
            vectorX = tile.getHeat();

        if(rightTile.isTransversable() && rightTile.getHeat() != -1)
            vectorX -= rightTile.getHeat();
        else
            vectorX -= tile.getHeat();

        if(upTile.isTransversable() && upTile.getHeat() != -1)
            vectorY = upTile.getHeat();
        else
            vectorY = tile.getHeat();

        if(downTile.isTransversable() && downTile.getHeat() != -1)
            vectorY -= downTile.getHeat();
        else
            vectorY -= tile.getHeat();

        //creating the vector and normalizing it
        double vectorLength = Math.sqrt(vectorX*vectorX + vectorY*vectorY);
        if (vectorLength != 0)
            return new Point2D.Double(vectorX / vectorLength, vectorY / vectorLength);
        else
            return new Point2D.Double(0,0);

    }

    public void draw(FXGraphics2D g2d) {
        for (FieldTile[] tileYs : this.tiles) {
            for (FieldTile tile : tileYs) {
                tile.draw(g2d);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }
}
