package com.jonathanfrosto.xadrez.chesss.pieces;

import com.jonathanfrosto.xadrez.boardgame.Board;
import com.jonathanfrosto.xadrez.boardgame.Position;
import com.jonathanfrosto.xadrez.chesss.ChessPiece;
import com.jonathanfrosto.xadrez.chesss.Color;

public class Pawn extends ChessPiece {
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    private void testMove(boolean[][] mat, Position p, int row) {
        p.setValues(this.position.getRow() + row, this.position.getColumn());
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;

            p.setValues(this.position.getRow() + row + row, this.position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }
        }

        p.setValues(this.position.getRow() + row, this.position.getColumn() + 1);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        p.setValues(this.position.getRow() + row, this.position.getColumn() - 1);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            testMove(mat, p, -1);
        } else {
            testMove(mat, p, 1);
        }

        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}
