package com.jonathanfrosto.xadrez.chesss.pieces;

import com.jonathanfrosto.xadrez.boardgame.Board;
import com.jonathanfrosto.xadrez.boardgame.Position;
import com.jonathanfrosto.xadrez.chesss.ChessMatch;
import com.jonathanfrosto.xadrez.chesss.ChessPiece;
import com.jonathanfrosto.xadrez.chesss.Color;

public class King extends ChessPiece {
    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    private void testMove(boolean[][] matrix, Position auxPosition, int row, int column){
        auxPosition.setValues(position.getRow() + row, position.getColumn() + column);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)){
            matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }
    }

    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position auxPosition = new Position(0, 0);

        //Above
        testMove(mat,auxPosition,-1,0);
        //Below
        testMove(mat,auxPosition,1,0);
        //Left
        testMove(mat,auxPosition,0,-1);
        //Right
        testMove(mat,auxPosition,0,1);
        //NO
        testMove(mat,auxPosition,-1,-1);
        //NE
        testMove(mat,auxPosition,-1,1);
        //SO
        testMove(mat,auxPosition,1,-1);
        //SE
        testMove(mat,auxPosition,1,1);

        // Specialmove castling
        if (getMoveCount() == 0 && !chessMatch.isCheck()){
            // Kingside Rook
            Position posRook1 = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(posRook1)){
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    mat[p2.getRow()][p2.getColumn()] = true;

                }
            }

            //Queenside Rook
            Position posRook2 = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(posRook2)){
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
                    mat[p2.getRow()][p2.getColumn()] = true;
                }
            }
        }


        return mat;
    }
}
