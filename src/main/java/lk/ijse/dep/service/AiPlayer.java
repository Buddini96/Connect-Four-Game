package lk.ijse.dep.service;
import java.util.Random;
public class AiPlayer extends Player {
    public AiPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {
        do {
            col=(int)((Math.random() * (6-0))+0);
        }while (!board.isLegalMove(col));
            board.updateMove(col, Piece.GREEN);
            board.getBoardUI().update(col,false);
            Winner winner =this.board.findWinner();
            if (board.findWinner().getWinningPiece()==Piece.GREEN){
                board.getBoardUI().notifyWinner(winner);
            } else if (!board.exitLegalMoves()) {
                board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }

