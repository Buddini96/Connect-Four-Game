package lk.ijse.dep.service;
import static lk.ijse.dep.service.Piece.EMPTY;

public class BoardImpl implements Board {
    private Piece[][] pieces;
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI=boardUI;

        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];

    for (int i =0; i<NUM_OF_COLS; i++){
          for(int j=0; j<NUM_OF_ROWS; j++){
                pieces[i][j] = Piece.EMPTY;
         }
       }
   }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) { //part 3
        int availableSpot = 5;
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (pieces[col][i] == Piece.EMPTY) {
                availableSpot--;
            }
        }
        if (availableSpot == 5) availableSpot = -1;

        return availableSpot;
    }
    @Override
    public boolean isLegalMove(int col) { // part 4
        boolean legalMove = true;
        int availableSpot = findNextAvailableSpot(col);

        if(availableSpot ==-1) legalMove = false;
            return legalMove;
    }

    @Override
    public boolean exitLegalMoves() { // part 5

        for(int i=0; i< NUM_OF_COLS; i++){
            for (int j=0; j< NUM_OF_ROWS; j++){
                if (pieces[i][j]==Piece.EMPTY){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) { // part 6
        pieces[col][findNextAvailableSpot(col)]=move;

    }

    @Override
    public Winner findWinner() { // part 7
        Piece winningPiece = Piece.EMPTY;
        int row1=0;
        int col1=0;
        int row2 =0;
        int col2 =0;

        for (int i = 0; i <NUM_OF_COLS; i++) {
            if(findNextAvailableSpot(i)==4 || findNextAvailableSpot(i)==-1){
                if(pieces[i][0]==pieces[i][1] && pieces[i][1]==pieces[i][2] && pieces[i][2]==pieces[i][3]){
                    winningPiece=pieces[i][0];
                    row1=0;
                    row2=3;
                    col1=i;
                    col2=i;
                } else if (pieces[i][1]==pieces[i][2] && pieces[i][2]==pieces[i][3] && pieces[i][3]==pieces[i][4]) {
                    winningPiece=pieces[i][1];
                    row1=1;
                    row2=4;
                    col1=i;
                    col2=i;
                }
            }
        }

        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (findAvialableInRow(i)==4 || findAvialableInRow(i)==5 ||findAvialableInRow(i)==-1){
                if(pieces[0][i]==pieces[1][i] && pieces[1][i]==pieces[2][i] && pieces[2][i]==pieces[3][i]){
                    winningPiece=pieces[0][i];
                    row1=i;
                    row2=i;
                    col1=0;
                    col2=3;
                }
                else if(pieces[1][i]==pieces[2][i] && pieces[2][i]==pieces[3][i] && pieces[3][i]==pieces[4][i]){
                    winningPiece=pieces[1][i];
                    row1=i;
                    row2=i;
                    col1=1;
                    col2=4;
                }
                else if(pieces[2][i]==pieces[3][i] && pieces[3][i]==pieces[4][i] && pieces[4][i]==pieces[5][i]){
                    winningPiece=pieces[2][i];
                    row1=i;
                    row2=i;
                    col1=2;
                    col2=5;
                }
            }
        }
        Winner winner;
        if(winningPiece== EMPTY){
            winner=new Winner(winningPiece);
        }else{
            winner =new Winner(winningPiece,col1,row1,col2,row2);
        }
        return winner;
    }
    private int findAvialableInRow(int row) {
        int count = 6;
        for(int i = 0; i< NUM_OF_COLS; i++){
            if(pieces[i][row]==Piece.EMPTY){
                count--;
            }
        }
        if(count==6)count=-1;
        return count;
    }
}

