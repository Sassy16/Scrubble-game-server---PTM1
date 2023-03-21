package test;
import java.util.*;

public class Board {
    private static Tile boardArr[][] = new Tile[15][15];
    private Word wordsArr[] = new Word[15];
    private int wordsCount = 0;
    private static Board gameBoard = null;
    private String bonus[][] = new String[15][15];

    private Board(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                boardArr[i][j] = null;
            }
        }
        bonus[0][0] = "TW";
        bonus[0][7] = "TW";
        bonus[0][14] = "TW";
        bonus[7][0] = "TW";
        bonus[7][14] = "TW";
        bonus[14][0] = "TW";
        bonus[14][7] = "TW";
        bonus[14][14] = "TW";
        bonus[1][1] = "DW";
        bonus[1][13] = "DW";
        bonus[2][2] = "DW";
        bonus[2][12] = "DW";
        bonus[3][3] = "DW";
        bonus[3][11] = "DW";
        bonus[4][4] = "DW";
        bonus[4][10] = "DW";
        bonus[7][7] = "DW";
        bonus[10][4] = "DW";
        bonus[10][10] = "DW";
        bonus[11][3] = "DW";
        bonus[11][11] = "DW";
        bonus[12][2] = "DW";
        bonus[12][12] = "DW";
        bonus[13][1] = "DW";
        bonus[13][13] = "DW";
        bonus[1][5] = "TL";
        bonus[1][9] = "TL";
        bonus[5][1] = "TL";
        bonus[5][5] = "TL";
        bonus[5][9] = "TL";
        bonus[5][13] = "TL";
        bonus[9][1] = "TL";
        bonus[9][5] = "TL";
        bonus[9][9] = "TL";
        bonus[9][13] = "TL";
        bonus[13][5] = "TL";
        bonus[13][9] = "TL";
        bonus[0][3] = "DL";
        bonus[0][11] = "DL";
        bonus[2][6] = "DL";
        bonus[2][8] = "DL";
        bonus[3][0] = "DL";
        bonus[3][7] = "DL";
        bonus[3][14] = "DL";
        bonus[6][2] = "DL";
        bonus[6][6] = "DL";
        bonus[6][8] = "DL";
        bonus[6][12] = "DL";
        bonus[7][3] = "DL";
        bonus[7][11] = "DL";
        bonus[8][2] = "DL";
        bonus[8][6] = "DL";
        bonus[8][8] = "DL";
        bonus[8][12] = "DL";
        bonus[11][0] = "DL";
        bonus[11][7] = "DL";
        bonus[11][14] = "DL";
        bonus[12][6] = "DL";
        bonus[12][8] = "DL";
        bonus[14][3] = "DL";
        bonus[14][11] = "DL";


    }
    public static Board getBoard(){
        if(gameBoard == null){
            gameBoard = new Board();
        }
        return gameBoard;
    }


    public Tile[][] getTiles() {
        Tile [][] copy =(Tile[][]) boardArr.clone();
        return copy;
    }
    public boolean boardLegal(Word w){
        int col = w.getCol();
        int row = w.getRow();
        boolean flag = false;
        boolean vertical = w.getVertical();
        Tile[] tiles = w.getTilesArr();
        if(col<0 || col>14 || row<0 || row>14){
            return false;
        }
        if(vertical){
            if(row + tiles.length > 14){ //check if the word is out of bounds
                return false;
            }
            if(boardArr[7][7]==null){ //check if this is the first word
                if(col!=7) //check if the word is in the middle
                    return false;
                if(row+tiles.length<7 || row>7) 
                    return false;
            }
            else{
                for(int i = 0; i < tiles.length; i++){
                    if(boardArr[row+i][col]!=null || (col+1<15 && boardArr[row+i][col+1]!=null) ||(col-1>-1 && boardArr[row+i][col-1]!=null) || (row+i+1 < 15 && boardArr[row+i+1][col]!=null) || (row+i-1>-1 && boardArr[row+i-1][col]!=null))
                        flag = true;
                    if(boardArr[row+i][col]!=null && boardArr[row+i][col].getLetter()!=tiles[i].getLetter())
                        return false; 
                }
                if(!flag){
                    return false;
                }
            }
        }
        else{
            if(col + tiles.length > 14){ //check if the word is out of bounds
                return false;
            }
            if( boardArr[7][7]==null){ //check if this is the first word
                if(row!=7) //check if the word is in the middle
                    return false;
                if(col+tiles.length<7 || col>7)
                    return false;
            }
            else{
                for(int i = 0; i < tiles.length; i++){
                    if((boardArr[row][col+i]!=null || (row+1<15 && boardArr[row+1][col+i]!=null) ||(row-1>-1 && boardArr[row-1][col+i]!=null) || (col+i+1 < 15 && boardArr[row][col+i+1]!=null) || (col+i-1>-1 && boardArr[row][col+i-1]!=null)))
                        flag = true;
                    if(boardArr[row][col+i]!=null && boardArr[row][col+i].getLetter()!=tiles[i].getLetter())
                        return false; 
                }
                if(!flag){
                    return false;
                }
            }
        }
        return true;
     }
     
     public boolean dictionaryLegal(Word w){
        return true;
     }

     public ArrayList<Word> getWords(Word w){
        ArrayList<Word> words = new ArrayList<Word>();
        int col = w.getCol();
        int row = w.getRow();
        int start;
        int finish;
        boolean vertical = w.getVertical();
        Tile[] tiles = w.getTilesArr();
        ArrayList<Tile> temp = new ArrayList<Tile>();
        words.add(w);
        for(int i = 0; i < tiles.length; i++){
            if(vertical){
                start = col;
                finish = col;
                while(start-1>-1 && boardArr[row+i][start-1]!=null){
                    start--;
                }
                while(finish+1<15 && boardArr[row+i][finish+1]!=null){
                    finish++;
                }
                if(start != col || finish != col){
                    for(int j = start; j <= finish; j++){
                        temp.add(boardArr[row+i][j]);
                    }
                    Word word = new Word(temp.toArray(new Tile[temp.size()]), row + i, start, false);
                    for (int j = 0; j < wordsCount; j++) {
                        if (wordsArr[j].equals(word)) {
                            word = null;
                            break;
                        }
                    }
                    if (word != null) {
                        words.add(word);
                    }
                }

            }
            else{
                start = row;
                finish = row;
                while(start-1>-1 && boardArr[start-1][col+i]!=null){
                    start--;
                }
                while(finish+1<15 && boardArr[finish+1][col+i]!=null){
                    finish++;
                }
                if(start != row|| finish != row){
                    temp.clear();
                    for(int j = start; j <= finish; j++){
                        temp.add(boardArr[j][col+i]);
                    }
                    if(temp.size()>0){
                        Word word = new Word(temp.toArray(new Tile[temp.size()]), start, col+i, true);
                        for(int j = 0; j < wordsCount; j++){
                            if(wordsArr[j].equals(word)){
                                word = null;
                                break;
                            }
                        }
                        if(word!=null){
                            words.add(word);
                        }
                    }
                }
                
            }
        }
        return words;
     }

     public int getScore(Word w){
        if(wordsCount>0){
            bonus[7][7]=null;
        }
        int score = 0;
        int col = w.getCol();
        int row = w.getRow();
        boolean vertical = w.getVertical();
        Tile[] tiles = w.getTilesArr();
        for(int i = 0; i < tiles.length; i++){
            if(vertical){
                if (bonus[row+i][col] == null || bonus[row + i][col].equals("DW")|| bonus[row + i][col].equals("TW")) 
                    score += tiles[i].getScore(); 
                else if(bonus[row+i][col].equals("TL")){
                    score += tiles[i].getScore()*3;
                }
                else if(bonus[row+i][col].equals("DL")){
                    score += tiles[i].getScore()*2;
                }
            }
            else{
                if (bonus[row][col+i]==null || bonus[row][col+i].equals("DW") || bonus[row][col + i].equals("TW"))
                    score += tiles[i].getScore();
                else if(bonus[row][col+i].equals("TL")){
                    score += tiles[i].getScore()*3;
                }
                else if(bonus[row][col+i].equals("DL")){
                    score += tiles[i].getScore()*2;
                }
       
            }
        }
        for(int i = 0; i < tiles.length; i++){
            if(vertical){
                if(bonus[row+i][col]==null)
                    continue;
                else if(bonus[row+i][col].equals("DW")){
                    score *= 2;
                }
                else if(bonus[row+i][col].equals("TW")){
                    score *= 3;
                }
            }
            else{
                if (bonus[row][col+i]==null)
                    continue;
                else if(bonus[row][col+i].equals("DW")){
                    score *= 2;
                }
                else if(bonus[row][col+i].equals("TW")){
                    score *= 3;
                }
            }
        }
        return score;
     }

     public int tryPlaceWord(Word w){
        int score = 0;
        int col = w.getCol();
        int row = w.getRow();
        boolean wVertical = w.getVertical();
        Tile[] wTiles = w.getTilesArr();
        for(int i = 0 ; i<wTiles.length; i++){
            if(wVertical){
                if(wTiles[i]==null){
                    wTiles[i] = boardArr[row+i][col];
                }
            }
            else{
                if(wTiles[i]==null){
                    wTiles[i] = boardArr[row][col+i];
                }
            }
        }
        w.setTiles(wTiles);
        if(boardLegal(w) && dictionaryLegal(w)){
            this.placeWord(w);
            ArrayList<Word> words = getWords(w);
            for(int i = 0; i < words.size(); i++){
                if(dictionaryLegal(words.get(i))){
                    score += getScore(words.get(i));
                }
                else{
                    return 0;
                }
            }
            for(int i = 0; i < words.size(); i++){
                wordsArr[wordsCount] = words.get(i);
                wordsCount++;
                this.placeWord(words.get(i));
            }
            return score;
        }
            
        
        else{
            return -1;
        }
        

     }
     public void placeWord(Word w){
        int row = w.getRow();
        int col = w.getCol();
        boolean vertical = w.getVertical();
        Tile[] tiles = w.getTilesArr();
        for(int i = 0; i < tiles.length; i++){
            if(vertical){
                boardArr[row+i][col] = tiles[i];
            }
            else{
                boardArr[row][col+i] = tiles[i];
            }
        }
     }

}
