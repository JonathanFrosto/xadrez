package com.jonathanfrosto.xadrez.chesss.pieces;

import com.jonathanfrosto.xadrez.boardgame.Board;
import com.jonathanfrosto.xadrez.boardgame.Position;
import com.jonathanfrosto.xadrez.chesss.ChessMatch;
import com.jonathanfrosto.xadrez.chesss.ChessPiece;
import com.jonathanfrosto.xadrez.chesss.Color;

public class Pawn extends ChessPiece {
    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    private void testEnemyPiece(boolean[][] mat, Position p, int row, int horizontal) {
        p.setValues(this.position.getRow() + row, this.position.getColumn() + horizontal);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
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
        testEnemyPiece(mat, p, row, -1);
        testEnemyPiece(mat, p, row, 1);
    }

    public void testEnPassant(boolean[][] mat, int positionRow, int row){
        if (position.getRow() == positionRow) {
            Position left = new Position(position.getRow(), position.getColumn() - 1);
            if (getBoard().positionExists(left) && isThereOpponentPiece(left) &&
                    getBoard().piece(left) == chessMatch.getEnPassant()) {
                mat[left.getRow() + row][left.getColumn()] = true;
            }
            Position right = new Position(position.getRow(), position.getColumn() + 1);
            if (getBoard().positionExists(right) && isThereOpponentPiece(right) &&
                    getBoard().piece(right) == chessMatch.getEnPassant()) {
                mat[right.getRow() + row][right.getColumn()] = true;
            }
        }
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            testMove(mat, p, -1);
            //En passant
            testEnPassant(mat, 3, -1);
        } else {
            testMove(mat, p, 1);
            //En passant
            testEnPassant(mat, 4, 1);
        }

        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}
