package Logic;

public class FieldGrid {

    private int width;
    private int height;

    private FiledTile[][] tiles;

    public FieldGrid(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = generateTiles();
    }

    //Todo: implement method
    private FiledTile[][] generateTiles() {
        return new FiledTile[this.height][this.width];
    }

    //Todo: implement method
    private boolean generateHeatMap(){
        return true;
    }

    //Todo: implement method
    private boolean generateVectorFiled(){
        return true;
    }
}
