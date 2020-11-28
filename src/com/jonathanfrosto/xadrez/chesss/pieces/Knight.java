package com.jonathanfrosto.xadrez.chesss.pieces;

import com.jonathanfrosto.xadrez.boardgame.Board;
import com.jonathanfrosto.xadrez.boardgame.Position;
import com.jonathanfrosto.xadrez.chesss.ChessPiece;
import com.jonathanfrosto.xadrez.chesss.Color;

public class Knight extends ChessPiece {
    public Knight(Board board, Color color) {
        super(board, color);
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

        //Left-Up
        testMove(mat,auxPosition,-1,-2);
        //Left-Down
        testMove(mat,auxPosition,1,-2);
        //Right-Up
        testMove(mat,auxPosition,-1,2);
        //Right-Down
        testMove(mat,auxPosition,1,2);
        //Up-Left
        testMove(mat,auxPosition,-2,-1);
        //Up-Right
        testMove(mat,auxPosition,-2,1);
        //Down-Left
        testMove(mat,auxPosition,2,-1);
        //Down-Right
        testMove(mat,auxPosition,2,1);

        return mat;
    }

    @Override
    public String toString() {
        return "C";
    }
}
