package com.jonathanfrosto.xadrez.application;

import com.jonathanfrosto.xadrez.boardgame.BoardException;
import com.jonathanfrosto.xadrez.chesss.ChessException;
import com.jonathanfrosto.xadrez.chesss.ChessMatch;
import com.jonathanfrosto.xadrez.chesss.ChessPiece;
import com.jonathanfrosto.xadrez.chesss.ChessPosition;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<ChessPiece> captured = new ArrayList<>();
        ChessMatch chessMatch = new ChessMatch();

        while (!chessMatch.isCheckMate()) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);

                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performerChessMove(source, target);
                if (capturedPiece != null){
                    captured.add(capturedPiece);
                }

                if (chessMatch.getPromoted() != null){
                    System.out.println("Digite a peça para promoção (B/C/R/Q): ");
                    String type = sc.nextLine().toUpperCase();
                    while (!type.equals("B") && !type.equals("C") && !type.equals("R") && !type.equals("Q")){
                        System.out.println("Valor digitado não válido, digite novamente: ");
                        type = sc.nextLine().toUpperCase();
                    }

                    chessMatch.replacePromotedPiece(type);
                }

                System.out.println();
            } catch (InputMismatchException | BoardException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.printMatch(chessMatch,captured);
    }
}
