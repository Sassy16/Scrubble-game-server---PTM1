package test;
public class Tile {

    public final char letter;
    public final int score;
    private static Bag bag = null;
	
    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public boolean equals(Tile other) {
        return this.letter == other.letter && this.score == other.score;
    }

    public int hashCode(){
        return this.letter+this.score;
    }

    public char getLetter() {
        return this.letter;
    }
    public int getScore() {
        return this.score;
    }

    public static class Bag{
        int amountArr[] = new int[26];
        Tile tileArr[] = new Tile[26];
        int maxTiles [] = new int [26];

        private Bag(){
            amountArr[0] = 9;
            amountArr[1] = 2;
            amountArr[2] = 2;
            amountArr[3] = 4;
            amountArr[4] = 12;
            amountArr[5] = 2;
            amountArr[6] = 3;
            amountArr[7] = 2;
            amountArr[8] = 9;
            amountArr[9] = 1;
            amountArr[10] = 1;
            amountArr[11] = 4;
            amountArr[12] = 2;
            amountArr[13] = 6;
            amountArr[14] = 8;
            amountArr[15] = 2;
            amountArr[16] = 1;
            amountArr[17] = 6;
            amountArr[18] = 4;
            amountArr[19] = 6;
            amountArr[20] = 4;
            amountArr[21] = 2;
            amountArr[22] = 2;
            amountArr[23] = 1;
            amountArr[24] = 2;
            amountArr[25] = 1;
            maxTiles = amountArr.clone();
            tileArr[0] = new Tile('A', 1);
            tileArr[1] = new Tile('B', 3);
            tileArr[2] = new Tile('C', 3);
            tileArr[3] = new Tile('D', 2);
            tileArr[4] = new Tile('E', 1);
            tileArr[5] = new Tile('F', 4);
            tileArr[6] = new Tile('G', 2);
            tileArr[7] = new Tile('H', 4);
            tileArr[8] = new Tile('I', 1);
            tileArr[9] = new Tile('J', 8);
            tileArr[10] = new Tile('K', 5);
            tileArr[11] = new Tile('L', 1);
            tileArr[12] = new Tile('M', 3);
            tileArr[13] = new Tile('N', 1);
            tileArr[14] = new Tile('O', 1);
            tileArr[15] = new Tile('P', 3);
            tileArr[16] = new Tile('Q', 10);
            tileArr[17] = new Tile('R', 1);
            tileArr[18] = new Tile('S', 1);
            tileArr[19] = new Tile('T', 1);
            tileArr[20] = new Tile('U', 1);
            tileArr[21] = new Tile('V', 4);
            tileArr[22] = new Tile('W', 4);
            tileArr[23] = new Tile('X', 8);
            tileArr[24] = new Tile('Y', 4);
            tileArr[25] = new Tile('Z', 10);
        }

        public Tile getRand(){
            if(this.size()==0){
                return null;
            }
            int rand = (int)(Math.random()*26);
            while(amountArr[rand] == 0){
                rand = (int)(Math.random()*26);
            }
            amountArr[rand]--;
            return tileArr[rand];
        }

        public int size(){
            int size = 0;
            for(int i = 0; i < 26; i++){
                size += amountArr[i];
            }
            return size;
        }

        public Tile getTile(char letter){
            for(int i = 0; i < 26; i++){
                if(tileArr[i].letter == letter){
                    if(amountArr[i] == 0){
                        return null;
                    }
                    amountArr[i]--;
                    return tileArr[i];
                }
            }
            return null;
        }

        public void put(Tile t){
            for(int i = 0; i < 26; i++){
                if(tileArr[i].letter == t.letter && amountArr[i] < maxTiles[i]){
                    amountArr[i]++;
                }
            }
        }

        public int[] getQuantities(){
            int arr[] = new int[26];
            arr = amountArr.clone();
            return arr;
        }

        public static Bag getBag() {
            if(bag == null){
                bag = new Bag();
            }
            return bag;
        }
        
    }
    

}
