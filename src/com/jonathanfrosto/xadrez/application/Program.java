package com.jonathanfrosto.xadrez.application;

import com.jonathanfrosto.xadrez.boardgame.Position;
import com.jonathanfrosto.xadrez.chesss.ChessMatch;

public class Program {
    public static void main(String[] args) {
        
        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces());
    }
}
