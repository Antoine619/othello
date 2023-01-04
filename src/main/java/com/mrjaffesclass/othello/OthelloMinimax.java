/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.main.java.com.mrjaffesclass.othello;

/**
 *
 * @author student
 */
import java.util.*;

public class OthelloMinimax extends Player{
    public setColor(int color){
        this.color = color;
    }
    
    public getColor(){
        return this.color;
    }
    //static final int BLACK = 1;
    //static final int WHITE = -1;
    static final int EMPTY = 0;
    
    static final int INF = 1000000000;

    static int[][] board = new int[9][9];
    static int currentPlayer = BLACK;
    static int maxDepth = 5;

    public static void main(String[] args) {
        // Initialize the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = EMPTY;
            }
        }
        board[3][3] = WHITE;
        board[4][4] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;

        // Start the game
        while (true) {
            int bestRow = -1, bestCol = -1;
            int bestScore = -INF;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = currentPlayer;
                        int score = minimax(1, false);
                        board[i][j] = EMPTY;
                        if (score > bestScore) {
                            bestScore = score;
                            bestRow = i;
                            bestCol = j;
                        }
                    }
                }
            }
            if (bestRow == -1 && bestCol == -1) {
                break;
            }
            board[bestRow][bestCol] = currentPlayer;
            currentPlayer = -currentPlayer;
        }
    }

    static int minimax(int depth, boolean isMaximizing) {
        if (depth == maxDepth) {
            return evaluate();
        }
        int bestScore = isMaximizing ? -INF : INF;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = currentPlayer;
                    int score = minimax(depth + 1, !isMaximizing);
                    board[i][j] = EMPTY;
                    if (isMaximizing) { 
                        bestScore = Math.max(bestScore, score);
                    } else {
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
        }
        return bestScore;
    }

    static int evaluate() {
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                score += board[i][j];
            }
        }
        return score;
    }
}