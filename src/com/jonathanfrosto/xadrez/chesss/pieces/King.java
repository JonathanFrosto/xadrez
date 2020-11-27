package com.jonathanfrosto.xadrez.chesss.pieces;

import com.jonathanfrosto.xadrez.boardgame.Board;
import com.jonathanfrosto.xadrez.boardgame.Position;
import com.jonathanfrosto.xadrez.chesss.ChessPiece;
import com.jonathanfrosto.xadrez.chesss.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
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

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position auxPosition = new Position(0, 0);

        //Aboce
        testMove(mat,auxPosition,-1,0);
        //Below
        testMove(mat,auxPosition,1,0);
        //Left
        testMove(mat,auxPosition,0,-1);
        //Right
        testMove(mat,auxPosition,0,1);
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

        return mat;
    }
}
