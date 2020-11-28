package com.jonathanfrosto.xadrez.chesss.pieces;

import com.jonathanfrosto.xadrez.boardgame.Board;
import com.jonathanfrosto.xadrez.boardgame.Position;
import com.jonathanfrosto.xadrez.chesss.ChessPiece;
import com.jonathanfrosto.xadrez.chesss.Color;

public class Queen extends ChessPiece {
    public Queen(Board board, Color color) {
        super(board, color);
    }

    private void testMove(int row, int col, boolean[][] matMoves, Position position) {
        position.setValues(this.position.getRow() + row, this.position.getColumn() + col);

        while (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
            matMoves[position.getRow()][position.getColumn()] = true;
            position.setValues(position.getRow() + row, position.getColumn() + col);
        }

        if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
            matMoves[position.getRow()][position.getColumn()] = true;
        }
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        //Above
        testMove(1, 0, mat, p);
        //Below
        testMove(-1, 0, mat, p);
        //Left
        testMove(0, -1, mat, p);
        //Right
        testMove(0, 1, mat, p);
        //NE
        testMove(-1, -1, mat, p);
        //NO
        testMove(-1, 1, mat, p);
        //SE
        testMove(1, -1, mat, p);
        //SO
        testMove(1, 1, mat, p);

        return mat;
    }

    @Override
    public String toString() {
        return "Q";
    }
}
