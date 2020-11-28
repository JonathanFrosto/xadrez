package com.jonathanfrosto.xadrez.chesss.pieces;

import com.jonathanfrosto.xadrez.boardgame.Board;
import com.jonathanfrosto.xadrez.boardgame.Position;
import com.jonathanfrosto.xadrez.chesss.ChessPiece;
import com.jonathanfrosto.xadrez.chesss.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    private void verifyMovesVertical(int i, boolean[][] matMoves, Position position) {
        position.setValues(this.position.getRow() + i, this.position.getColumn());

        while (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
            matMoves[position.getRow()][position.getColumn()] = true;
            position.setRow(position.getRow() + i);
        }

        if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
            matMoves[position.getRow()][position.getColumn()] = true;
        }
    }

    private void verifyMovesHorizontal(int i, boolean[][] matMoves, Position position) {
        position.setValues(this.position.getRow(), this.position.getColumn() + i);

        while (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
            matMoves[position.getRow()][position.getColumn()] = true;
            position.setColumn(position.getColumn() + i);
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
        verifyMovesVertical(1,mat,p);
        //Below
        verifyMovesVertical(-1, mat, p);
        //Left
        verifyMovesHorizontal(-1,mat,p);
        //Right
        verifyMovesHorizontal(1,mat,p);
        return mat;
    }
}
