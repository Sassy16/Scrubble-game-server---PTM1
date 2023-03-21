package test;

public class Word {
    private Tile tilesArr[] = new Tile[15];
    private int col;
    private int row;
    private boolean vertical;
    public Word(Tile[] tileArr, int row, int col, boolean vertical) {
        this.tilesArr = (Tile[])tileArr.clone();
        this.col = col;
        this.row = row;
        this.vertical = vertical;
    }
    public boolean equals(Word other) {
        if (this.col != other.col || this.row != other.row || this.vertical != other.vertical) {
            return false;
        }
        if(this.tilesArr.length != other.tilesArr.length) {
            return false;
        }
        for (int i = 0; i < tilesArr.length; i++) {
            if (!(this.tilesArr[i].equals(other.tilesArr[i]))) {
                return false;
            }
        }
        return true;
    }

    public Tile[] getTilesArr() {
        Tile [] copy = (Tile[]) tilesArr.clone();
        return copy;
    }
    
    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean getVertical() {
        return vertical;
    }

    public void setTiles(Tile[] tilesArr) {
        this.tilesArr = (Tile[])tilesArr.clone();
    }
	
}
