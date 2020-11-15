package com.jonathanfrosto.xadrez.chesss;

import com.jonathanfrosto.xadrez.boardgame.Board;
import com.jonathanfrosto.xadrez.boardgame.Piece;
import com.jonathanfrosto.xadrez.boardgame.Position;
import com.jonathanfrosto.xadrez.chesss.pieces.Rook;

public class ChessMatch {
    private Board board;

    public ChessMatch() {
        this.board = new Board(8,8);
        initialSetup();
    }

    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++){
            for (int j = 0; j < board.getRows(); j++){
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }

    private void placeNewPeace(char column, int row, Piece piece){
        board.placePiece(piece, new ChessPosition(column,row).toPosition());
    }

    private void initialSetup(){
        placeNewPeace('b',8,new Rook(board, Color.WHITE));
    }
}
