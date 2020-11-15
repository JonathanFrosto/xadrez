package com.jonathanfrosto.xadrez.application;

import com.jonathanfrosto.xadrez.boardgame.BoardException;
import com.jonathanfrosto.xadrez.chesss.ChessException;
import com.jonathanfrosto.xadrez.chesss.ChessMatch;
import com.jonathanfrosto.xadrez.chesss.ChessPiece;
import com.jonathanfrosto.xadrez.chesss.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ChessMatch chessMatch = new ChessMatch();

        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());

                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performerChessMove(source, target);
            } catch (InputMismatchException | BoardException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}
