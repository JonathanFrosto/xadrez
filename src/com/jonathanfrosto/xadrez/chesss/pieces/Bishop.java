package com.jonathanfrosto.xadrez.chesss.pieces;

import com.jonathanfrosto.xadrez.boardgame.Board;
import com.jonathanfrosto.xadrez.boardgame.Position;
import com.jonathanfrosto.xadrez.chesss.ChessPiece;
import com.jonathanfrosto.xadrez.chesss.Color;

public class Bishop extends ChessPiece {
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    private void testMove(boolean[][] matMoves, Position p, int row, int col) {
        p.setValues(position.getRow() + row, position.getColumn() + col);

        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            matMoves[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + row, p.getColumn() + col);
        }

        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            matMoves[p.getRow()][p.getColumn()] = true;
        }
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        //NE
        testMove(mat, p, -1, -1);
        //NO
        testMove(mat, p, -1, 1);
        //SE
        testMove(mat, p, 1, -1);
        //SO
        testMove(mat, p, 1, 1);

        return mat;
    }

    @Override
    public String toString() {
        return "B";
    }
}
