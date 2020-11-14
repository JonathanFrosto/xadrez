package com.jonathanfrosto.xadrez.application;

import com.jonathanfrosto.xadrez.boardgame.Piece;
import com.jonathanfrosto.xadrez.chesss.ChessPiece;

public class UI {

    public static void printBoard(ChessPiece[][] pieces){
        for (int i = 0; i < pieces.length; i++){
            System.out.print(8 - i + " ");
            for (int j = 0; j < pieces.length; j++){
                printPiece(pieces[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printPiece(Piece piece){
        if(piece == null){
            System.out.print("-");
        }
        else{
            System.out.print(piece);
        }
        System.out.print(" ");
    }
}
