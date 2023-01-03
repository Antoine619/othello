/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrjaffesclass.othello;

/**
 *
 * @author student
 */
import java.util.ArrayList;
import java.util.List;

public class OthelloMinimax {
  private static final int MAX_DEPTH = 5;

  public static int minimax(Board board, int depth, boolean isMaximizing) {
    if (depth == MAX_DEPTH || board.gameOver()) {
      return board.evaluate();
    }

    List<Move> moves = board.getPossibleMoves();

    if (isMaximizing) {
      int bestScore = Integer.MIN_VALUE;
      for (Move move : moves) {
        board.makeMove(move);
        int score = minimax(board, depth + 1, false);
        board.undoMove(move);
        bestScore = Math.max(bestScore, score);
      }
      return bestScore;
    } else {
      int bestScore = Integer.MAX_VALUE;
      for (Move move : moves) {
        board.makeMove(move);
        int score = minimax(board, depth + 1, true);
        board.undoMove(move);
        bestScore = Math.min(bestScore, score);
      }
      return bestScore;
    }
  }

  public static Move findBestMove(Board board) {
    List<Move> moves = board.getPossibleMoves();
    int bestScore = Integer.MIN_VALUE;
    Move bestMove = null;
    for (Move move : moves) {
      board.makeMove(move);
      int score = minimax(board, 0, false);
      board.undoMove(move);
      if (score > bestScore) {
        bestScore = score;
        bestMove = move;
      }
    }
    return bestMove;
  }
}
