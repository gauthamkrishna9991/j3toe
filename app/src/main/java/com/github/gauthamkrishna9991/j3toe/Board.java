package com.github.gauthamkrishna9991.j3toe;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private List<Square> boardSquares;
    private Player playerX;
    private Player playerO;

    public Board() {
        this.boardSquares = new ArrayList<Square>(9);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardSquares.add(i * 3 + j, new Square());
            }
        }
        this.playerX = new Player(SquareState.X);
        this.playerO = new Player(SquareState.O);
    }

    public boolean makeMove(Player player, int x, int y) {
        if (x >= 0 && x < 3 && y >= 0 && y < 3) {
            return boardSquares.get(x * 3 + y).updateState(player);
        }
        return false;
    }

    public boolean isGameOver() {
        // If there is a winner, return true, for game over
        if (this.checkWinner() != SquareState.NONE) {
            return true;
        }
        for(int i = 0; i < 9; i++) {
            // If there are any empty squares
            if (this.boardSquares.get(i).getState() == SquareState.NONE) {
                return false;
            }
        }
        return true;
    }

    public SquareState checkWinner() {
        // Horizontal Checks
        for(int i = 0; i < 3; i++) {
            if (
                this.boardSquares.get(i * 3).getState() == this.boardSquares.get((i * 3) + 1).getState()
                && this.boardSquares.get(i * 3).getState() == this.boardSquares.get((i * 3) + 2).getState()
                && this.boardSquares.get(i * 3 + 1).getState() == this.boardSquares.get(i * 3 + 2).getState()
                && this.boardSquares.get(i * 3).getState() != SquareState.NONE
            ) {
                return this.boardSquares.get(i * 3).getState();
            }
        }
        // Vertical Checks (WIP)
        for(int i = 0; i < 3; i++) {
            if (
                this.boardSquares.get(i).getState() == this.boardSquares.get(i + 3).getState()
                && this.boardSquares.get(i).getState() == this.boardSquares.get(i + 6).getState()
                && this.boardSquares.get(i + 3).getState() == this.boardSquares.get(i + 6).getState()
                && this.boardSquares.get(i).getState() != SquareState.NONE
            ) {
                return this.boardSquares.get(i).getState();
            }
        }
        // Diagonal Checks: 1
        if (
            this.boardSquares.get(0).getState() == this.boardSquares.get(4).getState()
            && this.boardSquares.get(0).getState() == this.boardSquares.get(8).getState()
            && this.boardSquares.get(4).getState() == this.boardSquares.get(8).getState()
        ) {
            return this.boardSquares.get(0).getState();
        }
        // Diagonal Checks: 2
        if (
            this.boardSquares.get(2).getState() == this.boardSquares.get(4).getState()
            && this.boardSquares.get(4).getState() == this.boardSquares.get(6).getState()
            && this.boardSquares.get(2).getState() == this.boardSquares.get(6).getState()
        ) {
            return this.boardSquares.get(2).getState();
        }
        // If no matches exist, return none.
        return SquareState.NONE;
    }

    public static void playGame() {
        Board b = new Board();
        Player presentPlayer = b.playerO;
        System.out.println("Start Game");
        // Input Stream Reader
        InputStreamReader iReader = new InputStreamReader(System.in);
        Scanner scin = new Scanner(iReader);
        while (!b.isGameOver()) {
            System.out.print("PLAYER " + presentPlayer.playerState + ": ");
            int val;
            String s = scin.nextLine();
            Scanner valin = new Scanner(s);
            if (valin.hasNextInt()) {
                val = valin.nextInt();
                int y = val % 10;
                int x = (val / 10) % 10;
                if (x < 3 && y < 3) {
                    if (b.makeMove(presentPlayer, x, y)) {
                        if (presentPlayer.playerState == b.playerO.playerState) {
                            presentPlayer = b.playerX;
                        } else {
                            presentPlayer = b.playerO;
                        }
                        System.out.println(b.toString());
                    } else {
                        System.out.println("ILLEGAL MOVE");
                    }
                } else {
                    System.out.println("ILLEGAL MOVE");
                }
            }
            valin.close();
        }
        scin.close();
        if (b.checkWinner() != SquareState.NONE) {
            System.out.println("Player " + b.checkWinner() + " WON.");
        } else {
            System.out.println("DRAW.");
        }
        System.out.println("GAME OVER");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" |");
        for(int i = 0; i < 3; i++) {
            sb.append(i + "");
            sb.append("|");
        }
        sb.append("\n--------");
        for (int i = 0; i < 3; i++) {
            sb.append("\n" + i + "|");
            for (int j = 0; j < 3; j++) {
                sb.append(boardSquares.get(i * 3 + j).toString());
                sb.append("|");
            }
            sb.append("\n--------");
        }
        return sb.toString();
    }
}
